import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

public abstract class BaseDao <T> {
    
    private final String SERVIDOR = "localhost";
    private final int PUERTO = 27017;
    private final String BASE_DATOS = "Secretaria de salud";
    
    protected MongoDatabase getMongoDatabase(){
        try{
            
            CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
            CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    pojoCodecRegistry);
            ConnectionString cadenaConexion = new ConnectionString("mongodb://" + SERVIDOR + "/" + PUERTO);
            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(cadenaConexion)
                    .codecRegistry(codecRegistry)
                    .build();
                    
            MongoClient servidor = MongoClients.create(clientSettings);
            MongoDatabase baseDatos = servidor.getDatabase(BASE_DATOS);
        return baseDatos;
        }catch(Exception ex){
            System.err.println(ex.getMessage());
            throw ex;
        }       
    }
    
    public abstract void guardar(T entidad);
    public abstract ArrayList <T> consultar();
    public abstract T consultarPorId(ObjectId id);
    public abstract void actualizar(T entidad);
    public abstract void eliminar(T entidad);
    protected abstract MongoCollection<T> getColection();
}
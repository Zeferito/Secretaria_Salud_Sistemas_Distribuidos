package equipo.distribuidos.appsecretariasalud

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExpedientesService {

   // @Headers("Access-Control-Allow-Origin: *")
    @POST("/registrar-paciente")
    fun registrarUsuario(@Body usuario:Usuario): Call<Unit>

    @POST("/login")
    fun login(@Body usuario: Usuario):Call<Usuario>

    @GET("/permisos/{idPaciente}")
    fun solicitudesPermisos(@Path("idPaciente") idPaciente:String):Call<ArrayList<Permiso>>

    @POST("/aceptar-solicitud/{idSolicitud}")
    fun aceptarSolicitud(@Path("idSolicitud")idSolicitud:String):Call<Unit>

    @POST("/rechazar-solicitud/{idSolicitud}")
    fun rechazarSolicitud(@Path("idSolicitud")idSolicitud:String):Call<Unit>

    @GET("expedientes")
    fun getExpedientes(): Call<List<Expediente>>
}
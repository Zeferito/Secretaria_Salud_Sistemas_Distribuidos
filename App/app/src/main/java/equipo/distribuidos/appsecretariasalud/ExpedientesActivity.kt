package equipo.distribuidos.appsecretariasalud

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExpedientesActivity : AppCompatActivity() {
    private lateinit var expedienteService: ExpedientesService
    private lateinit var recyclerView: RecyclerView
    private lateinit var expedienteAdapter: ExpedienteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expedientes)

        recyclerView = findViewById(R.id.recyclerViewExpedientes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        expedienteAdapter = ExpedienteAdapter()
        recyclerView.adapter = expedienteAdapter

        // Configurar Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("localhost:8080/expedientes")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        expedienteService = retrofit.create(ExpedientesService::class.java)

        // Obtener expedientes
        obtenerExpedientes()
    }

    private fun obtenerExpedientes() {
        expedienteService.getExpedientes().enqueue(object : Callback<List<Expediente>> {
            override fun onResponse(
                call: Call<List<Expediente>>,
                response: Response<List<Expediente>>
            ) {
                if (response.isSuccessful) {
                    val expedientes = response.body()
                    expedienteAdapter.actualizarExpedientes(expedientes)
                } else {
                    // Manejar errores de respuesta
                    Log.e(TAG, "Error en la respuesta: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Expediente>>, t: Throwable) {
                // Manejar errores de comunicación
                Log.e(TAG, "Error en la comunicación: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "ExpedientesActivity"

    }
}
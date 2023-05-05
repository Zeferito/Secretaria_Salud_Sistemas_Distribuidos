package equipo.distribuidos.appsecretariasalud

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AutorizacionService {

    @POST("/registrar-paciente")
    fun registrarUsuario(@Body usuario:Usuario): Call<Usuario>
}
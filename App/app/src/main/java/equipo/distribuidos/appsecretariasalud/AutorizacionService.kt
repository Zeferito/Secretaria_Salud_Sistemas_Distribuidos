package equipo.distribuidos.appsecretariasalud

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AutorizacionService {

   // @Headers("Access-Control-Allow-Origin: *")
    @POST("/registrar-paciente")
    fun registrarUsuario(@Body usuario:Usuario): Call<Unit>
}
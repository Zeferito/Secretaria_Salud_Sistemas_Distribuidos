package equipo.distribuidos.appsecretariasalud

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    var dir=""
    private  var retrofitService=Retrofit.Builder()
        .baseUrl("http://$dir:8080")
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    val autorizacionService = retrofitService.create(AutorizacionService::class.java)
}
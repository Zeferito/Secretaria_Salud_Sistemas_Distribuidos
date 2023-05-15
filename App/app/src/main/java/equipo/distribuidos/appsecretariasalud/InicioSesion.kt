package equipo.distribuidos.appsecretariasalud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlin.concurrent.thread

class InicioSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)


        var txtCorreo: EditText =findViewById(R.id.txtCorreo)
        var txtPassword: EditText =findViewById(R.id.txtPassword)

        var btnLogin: Button =findViewById(R.id.btnIniciarSesion)
        var idPaciente=""
        btnLogin.setOnClickListener {
            var usuario = Usuario("","",txtCorreo.text.toString(),txtPassword.text.toString())
            thread {
                val request=RetrofitService.autorizacionService.login(usuario)
                val requestFinal=request.execute()
                if(requestFinal.isSuccessful){
                    idPaciente= requestFinal.body()!!.id
                    runOnUiThread{
                        Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT)
                        val intent = Intent(applicationContext, Permisos::class.java)
                        Log.i("PACIENTE_ID",idPaciente)
                        intent.putExtra("id",idPaciente)
                        startActivity(intent)
                    }
                }
            }

        }

        var btnRegistrate:Button=findViewById(R.id.btnRegistrate)
        btnRegistrate.setOnClickListener {
            val intent = Intent(applicationContext, Registro::class.java)
            startActivity(intent)
        }
    }
}
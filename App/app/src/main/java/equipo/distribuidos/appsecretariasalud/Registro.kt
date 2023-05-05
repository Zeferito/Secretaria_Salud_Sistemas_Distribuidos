package equipo.distribuidos.appsecretariasalud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.concurrent.thread

class Registro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        var txtNombre:EditText=findViewById(R.id.txtNombre)
        var txtCorreo:EditText=findViewById(R.id.txtCorreo)
        var txtPassword:EditText=findViewById(R.id.txtPassword)

        var btnRegistrar:Button=findViewById(R.id.btnRegistrarse)

        btnRegistrar.setOnClickListener {
            var usuario = Usuario(txtNombre.text.toString(),txtCorreo.text.toString(),txtPassword.text.toString())
            thread {
                val request=RetrofitService.autorizacionService.registrarUsuario(usuario)
                val body=request.execute().body()
                print("peticion realizada")
                print(body)
            }
        }

    }
}
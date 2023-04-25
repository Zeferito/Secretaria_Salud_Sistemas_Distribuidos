package equipo.distribuidos.appsecretariasalud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btn_expediente: Button = findViewById(R.id.exp)
        val btn_confir_exp: Button = findViewById(R.id.conf)

        btn_confir_exp.setOnClickListener{
            val intent: Intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
        btn_expediente.setOnClickListener{
            val intent: Intent = Intent(this, Recision_Expediente::class.java)
            startActivity(intent)
        }
    }
}
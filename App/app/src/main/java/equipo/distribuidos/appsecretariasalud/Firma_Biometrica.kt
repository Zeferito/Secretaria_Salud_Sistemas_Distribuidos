package equipo.distribuidos.appsecretariasalud

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets


class Firma_Biometrica : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firma_biometrica)

        val btn_firma: ImageView = findViewById(R.id.firma)



        //someTask().execute();




        btn_firma.setOnClickListener{
            val intent: Intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

}

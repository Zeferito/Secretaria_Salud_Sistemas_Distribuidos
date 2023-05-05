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
class someTask() : AsyncTask<Void, Void, String>() {
    var QUEUE_NAME="secretaria-salud"
    var direccion="192.168.1.199"
    override fun doInBackground(vararg params: Void?): String? {
        // ...
        val factory = ConnectionFactory()
        factory.host= direccion;
        factory.username="test"
        factory.password="test"
        factory.port=5672
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val consumerTag = "ConsumidorMovil"

        channel.queueDeclare(QUEUE_NAME, false, false, false, null)

        println("[$consumerTag] Waiting for messages...")
        val deliverCallback = DeliverCallback { consumerTag: String?, delivery: Delivery ->
            val message = String(delivery.body, StandardCharsets.UTF_8)
            println("[$consumerTag] un doctor a solicitado tu expediente")
        }
        val cancelCallback = CancelCallback { consumerTag: String? ->
            println("[$consumerTag] was canceled")
        }

        channel.basicConsume(QUEUE_NAME, true, consumerTag, deliverCallback, cancelCallback)

        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // ...
    }
}
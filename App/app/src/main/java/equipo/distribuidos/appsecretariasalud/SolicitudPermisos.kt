package equipo.distribuidos.appsecretariasalud

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import java.nio.charset.StandardCharsets

class SolicitudPermisos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitud_permisos)

        //corre lo de rabbit en segundo plano
        someTask().execute()
    }

    class someTask() : AsyncTask<Void, Void, String>() {
        var QUEUE_NAME="solicitudes"
        var direccion=""
        override fun doInBackground(vararg params: Void?): String? {
            // ...u
            val factory = ConnectionFactory()
            factory.host= direccion;
            factory.username="test"
            factory.password="test"
            factory.virtualHost="/"
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

}
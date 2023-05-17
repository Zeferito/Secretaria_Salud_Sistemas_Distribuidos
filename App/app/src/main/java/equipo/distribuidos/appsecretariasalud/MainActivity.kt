package equipo.distribuidos.appsecretariasalud

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.hardware.biometrics.BiometricPrompt
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       //startTimer()

        val intent = Intent(applicationContext, InicioSesion::class.java)
        startActivity(intent)

    }

    fun startTimer() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {

            }

            override fun onFinish() {
                val intent = Intent(applicationContext, Firma_Biometrica::class.java).apply {}
                startActivity(intent)
            }
        }.start()
    }
}
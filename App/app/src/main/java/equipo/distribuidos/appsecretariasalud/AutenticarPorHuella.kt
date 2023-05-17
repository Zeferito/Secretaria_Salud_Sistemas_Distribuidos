package equipo.distribuidos.appsecretariasalud

import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CancellationSignal
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

class AutenticarPorHuella : AppCompatActivity() {

    private var cancellationSignal: CancellationSignal? = null
    private val  authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object: BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
                    notificarUsuario("Authenticacion invalida: $errString")
                    startActivity(Intent(this@AutenticarPorHuella, InicioSesion::class.java))
                }
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
                    notificarUsuario("Authentication Válida!")
                    val intentPermisos = Intent(applicationContext, Permisos::class.java)
                    var idPaciente=intent.getStringExtra("id")
                    if(idPaciente!=null){
                        intentPermisos.putExtra("id",idPaciente)
                    }
                    startActivity(intentPermisos)
                }
            }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autenticar_por_huella)
        //cofig para permiso con huella

        checkBiometricSupport()
        val biometricPrompt : BiometricPrompt = BiometricPrompt.Builder(this)
            .setTitle("Permisos de expediente")
            .setSubtitle("Se requiere autenticación por huella")
            .setDescription("Autenticación por huella")
            .setNegativeButton("Cancelar", this.mainExecutor, DialogInterface.OnClickListener { dialog, which ->
            }).build()
        biometricPrompt.authenticate(getCancellationSignal(), mainExecutor, authenticationCallback)
    }


    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
            notificarUsuario("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }
    private fun checkBiometricSupport(): Boolean {
        val keyguardManager : KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if(!keyguardManager.isKeyguardSecure) {
            notificarUsuario("La autenticación por huella no esta activada.")
            return false
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED) {
            notificarUsuario("La autenticación por huella no esta activada.")
            return false
        }
        return if (packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)) {
            true
        } else true
    }
    private fun notificarUsuario(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}
package equipo.distribuidos.appsecretariasalud

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import kotlin.concurrent.thread

class Permisos : AppCompatActivity() {
    //id de prueba
    var id="1234"
    var permisosSoli=ArrayList<Permiso>()
    lateinit var adaptador:AdaptadorPermisos
    lateinit var permisosList:ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permisos)

        var idPaciente=intent.getStringExtra("id")
        if(id!=""){
            id= idPaciente!!
        }

        permisosList= findViewById(R.id.list_permisos)
        adaptador= AdaptadorPermisos(this, permisosSoli)
        permisosList.adapter = adaptador

        thread {
            var permisosResult=RetrofitService.autorizacionService.solicitudesPermisos(id).execute()
            if(permisosResult.isSuccessful){
                permisosSoli.addAll(permisosResult.body()!!)
                println("PERMISOS:")
                for (permiso in permisosSoli){
                    println(permiso.toString())

                    runOnUiThread {
                        adaptador.notifyDataSetChanged()
                    }

                }
            }
        }
        var btnRecargar:Button=findViewById(R.id.btnRecargar)
        btnRecargar.setOnClickListener {
            permisosSoli.clear()
            adaptador.notifyDataSetChanged()
            thread {
                var permisosResult =
                    RetrofitService.autorizacionService.solicitudesPermisos(id).execute()
                if (permisosResult.isSuccessful) {
                    permisosSoli.addAll(permisosResult.body()!!)
                    println("PERMISOS:")
                    for (permiso in permisosSoli) {
                        println(permiso.toString())

                        runOnUiThread {
                            adaptador.notifyDataSetChanged()
                        }

                    }
                }
            }
        }

    }

    class AdaptadorPermisos:BaseAdapter{

        var permisos=ArrayList<Permiso>()
        var contexto: Context?=null

        constructor(contexto: Context, permisos: ArrayList<Permiso>){
            this.permisos=permisos
            this.contexto=contexto
        }

        override fun getCount(): Int {
            return permisos.size
        }

        override fun getItem(p0: Int): Any {
            return permisos[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var permiso=permisos[p0]
            var inflador= LayoutInflater.from(contexto)
            var vista=inflador.inflate(R.layout.permiso_view, null)

            var fecha=vista.findViewById(R.id.txtFecha) as TextView
            var estado=vista.findViewById(R.id.txtEstado) as TextView

            fecha.text=permiso.fecha.toString()
            estado.text=permiso.estado

            vista.setOnClickListener {
                val builder =  AlertDialog.Builder(contexto)
                builder.setMessage("¿Aceptar solicitud?")
                    .setPositiveButton("Aceptar",
                        DialogInterface.OnClickListener { dialog, id ->
                            //enviar solicitud
                            thread {
                                RetrofitService.autorizacionService
                                    .aceptarSolicitud(permiso.id)
                                    .execute()
                            }
                            dialog.dismiss()
                        })
                    .setNeutralButton("Rechazar",
                        DialogInterface.OnClickListener { dialog, id ->
                            //enviar solicitud
                            thread {
                                RetrofitService.autorizacionService
                                    .rechazarSolicitud(permiso.id)
                                    .execute()
                            }
                            dialog.dismiss()
                        })
                    .setNegativeButton("Cancelar",
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
                builder.create()
                builder.show()
            }

            return vista

        }

    }

}


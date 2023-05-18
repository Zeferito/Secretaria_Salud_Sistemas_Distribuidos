package equipo.distribuidos.appsecretariasalud

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpedienteAdapter : RecyclerView.Adapter<ExpedienteAdapter.ExpedienteViewHolder>() {
    private var expedientes: List<Expediente> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpedienteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_expediente, parent, false)
        return ExpedienteViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpedienteViewHolder, position: Int) {
        val expediente = expedientes[position]
        holder.bind(expediente)
    }

    override fun getItemCount(): Int {
        return expedientes.size
    }

    fun actualizarExpedientes(nuevosExpedientes: List<Expediente>?) {
        if (nuevosExpedientes != null) {
            expedientes = nuevosExpedientes
            notifyDataSetChanged()
        }
    }

    inner class ExpedienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombre)
        private val textViewConsulta: TextView = itemView.findViewById(R.id.textViewConsulta)

        fun bind(expediente: Expediente) {
            textViewNombre.text = expediente.nombre
            textViewConsulta.text = expediente.consulta.pregunta

        }
    }
}
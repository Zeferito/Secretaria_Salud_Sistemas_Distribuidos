package equipo.distribuidos.appsecretariasalud

import java.util.*

data class Permiso(var id:String,val idDoctor:String,val idPaciente:String,val estado:String,val fecha: Date)

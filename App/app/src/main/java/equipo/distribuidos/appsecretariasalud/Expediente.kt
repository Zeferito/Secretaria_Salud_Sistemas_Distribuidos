package equipo.distribuidos.appsecretariasalud

data class Expediente(
    val id: String,
    val nombre: String,
    val consulta: Consulta,
    val archivosPDF: List<ByteArray>,
    val archivosImagenes: List<ByteArray>
)

data class Consulta(
    val pregunta: String
)
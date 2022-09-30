package `07-Intercambio`

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.util.*

@Serializable
@SerialName("perrito") // Si queremos cambiar el nombre de la etiqueta
data class Perro(
    val id: String = UUID.randomUUID().toString(),
    val nombre: String,
    @SerialName("longevidad")
    val edad: Int,
    val dueño: String? = null
)

fun main() {
    // Salvamos a un archivo
    val fichero = File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "intercambio.json")
    var perros =
        listOf(
            Perro(nombre = "Bobby", edad = 2),
            Perro(nombre = "Tara", edad = 3),
            Perro(nombre = "Salchi", edad = 4, dueño = "Pepe")
        )

    // Escribimos en un archivo
    val json = Json { prettyPrint = true }
    fichero.writeText(json.encodeToString(perros))

    // Leemos de un archivo
    perros = Json.decodeFromString<List<Perro>>(fichero.readText())
    println(perros)

}
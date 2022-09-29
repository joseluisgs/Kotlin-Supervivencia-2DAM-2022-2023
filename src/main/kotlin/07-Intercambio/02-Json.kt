package `07-Intercambio`

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

@Serializable
data class Perro(val nombre: String, val edad: Int)

fun main() {
    // Salvamos a un archivo
    val fichero = File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "intercambio.json")
    var perros = listOf(Perro("Bobby", 2), Perro("Tara", 3), Perro("Salchi", 4))

    // Escribimos en un archivo
    val json = Json { prettyPrint = true }
    fichero.writeText(json.encodeToString(perros))

    // Leemos de un archivo
    perros = Json.decodeFromString<List<Perro>>(fichero.readText())
    println(perros)

}
package `07-Intercambio`

import java.io.File

data class Persona(val nombre: String, val edad: Int)

fun main() {
    // Salvamos a un archivo
    val fichero = File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "intercambio.csv")
    var personas = listOf(Persona("Juan", 20), Persona("Ana", 30), Persona("Pedro", 40))

    // Cabecera y cuerpo
    fichero.writeText("Nombre;Edad")
    personas.forEach { fichero.appendText("\n${it.nombre};${it.edad}") }

    // Leemos de un archivo
    personas = fichero.readLines().drop(1)
        .map { it.split(";") }
        .map { Persona(it[0], it[1].toInt()) }
    println(personas)

}
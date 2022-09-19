package `04-Ficheros`

import java.io.*


data class Persona(val nombre: String, val edad: Int) : Serializable

fun main() {
    val file = "data${File.separator}personas.dat"
    val personas = listOf(
        Persona("Juan", 20),
        Persona("Ana", 30),
        Persona("Luis", 40)
    )

    // write a list into a binary file
    File(file).writeBytes(personas.toString().toByteArray())

    // Leemos como dato binario
    val personasLeidas = File(file).readBytes().toString(Charsets.UTF_8)
    println(personasLeidas)

    // Escribimos la lista personas como binario
    ObjectOutputStream(FileOutputStream(file)).use { it -> it.writeObject(personas) }

    // Leemos la lista personas como binario
    ObjectInputStream(FileInputStream(file)).use { it -> it.readObject() }.also { println(it) }
    ObjectInputStream(FileInputStream(file)).use { it -> println(it.readObject()) }

}

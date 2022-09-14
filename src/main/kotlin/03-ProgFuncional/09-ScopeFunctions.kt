package `03-ProgFuncional`

import java.util.*

/* Nos permiten realizar acciones sobre el objeto que estamos trabajando
 y varían si el propio objeto es el receptor del mismo
 Es una combinación de todo lo visto
 https://kotlinlang.org/docs/scope-functions.html
                        */

// no hay una regla para ellas y a veces depende del gusto del programador

data class Persona(
    var nombre: String = "",
    var edad: Int = 0,
) {
    val uuid = UUID.randomUUID()

    fun saludar() {
        println("Hola, me llamo $nombre y tengo $edad años")
    }
}
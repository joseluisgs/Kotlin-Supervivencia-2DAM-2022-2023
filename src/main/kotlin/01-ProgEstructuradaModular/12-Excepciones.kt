package `01-ProgEstructuradaModular`

import java.io.IOException

/**
 * En Kotlin no hay diferencia entre Excepciones tipo Checked y Unchecked
 * https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/
 * es decir si el compilador te obliga a capturar una excepción
 * Es por ello que debemos capturar lo que consideremos necesario
 * o para informar a Java usar la anotacion @Throws
 * https://kotlinlang.org/docs/exceptions.html
 */

fun main() {
    // ¿Que pasa aquí?
    val message = "Welcome to Kotlin Tutorials"
    message.toInt()

    try {
        val message = "Welcome to Kotlin Tutorials"
        message.toInt()
    } catch (exception: NumberFormatException) {
        println("Exception occurred: ${exception.message}")
    }


}

// Nothing es el tipo que se usa si devolvemos una excepción
// si queremos que Java se entere de que puede lanzar una excepción
// debemos usar la anotación @Throws
@Throws(IOException::class)
fun throwException(): Nothing = throw Exception("IOException Exception occurred")
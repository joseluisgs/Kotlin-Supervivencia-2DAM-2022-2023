package `01-ProgEstructuradaModular`

import java.io.IOException

/**
 * En Kotlin no hay diferencia entre Excepciones tipo Checked y Unchecked
 * https://www.geeksforgeeks.org/checked-vs-unchecked-exceptions-in-java/
 * es decir si el compilador te obliga a capturar una excepción
 * Es por ello que debemos capturar lo que consideremos necesario
 * o para informar a Java usar la anotacion @Throws
 * https://kotlinlang.org/docs/exceptions.html
 * https://rollbar.com/blog/how-to-handle-checked-unchecked-exceptions-in-java/#:~:text=Difference%20Between%20Checked%20and%20Unchecked%20Exceptions%20in%20Java,-To%20summarize%2C%20the&text=unchecked%20exception%20is%3A-,A%20checked%20exception%20is%20caught%20at%20compile%20time%20whereas%20a,t%20required%20to%20be%20handled.
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
package `01-ProgEstructuradaModular`

/*
// También podemos usar expresiones regulares
https://www.baeldung.com/kotlin/regular-expressions
https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-regex/
 */

fun main() {

    // Podemos usar una expresion regular para filtar los valores
    println("Dime un e-mail: ")
    // Expresion regular para validar un email
    var email = readln().matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$"))

    println("Email: $email")
    // es válido
    if (email) {
        println("Email válido")
    } else {
        println("Email no válido")
    }
    val regex = """^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\$""".toRegex()
    do {
        println("Dime un e-mail valido: ")
        val input = readln()
        email = input.matches(regex)
    } while (!email)

}
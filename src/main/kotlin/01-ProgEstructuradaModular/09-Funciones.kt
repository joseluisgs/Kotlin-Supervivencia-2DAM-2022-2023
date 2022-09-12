package `01-ProgEstructuradaModular`

/**
 * Usamos las funciones para cerar fragmentos de codigo que podemos reutilizar
 * https://kotlinlang.org/docs/functions.html
 */
fun main() {
    val valor = miFuncion2()
    println(valor)

    procedimiento()

    // Parametros con valores por defecto
    println(parametros("Pepe"))
    println(parametros("Pepe", 23))
    println(parametros("Pepe", 23, true))

    // Parametros nombrados
    println(parametros(edad = 25, repetidor = false, nombre = "Pedro"))

    // Numero variable de parámetros
    println(calificacion("Pepe", 7.0))
    println(calificacion("Pepe", 7.0, 8.5))
    println(calificacion("Pepe", 7.0, 8.5, 9.0))
    var notas = DoubleArray(10)
    println(calificacion("Pepe", *notas))

    val (a, b) = notas

}

fun procedimiento() {
    println("Soy un procedimiento y devulevo Unit")
}

fun miFuncion(): Int {
    return 1
}

fun miFuncion2() = "Esto es una expression Body"

// Parametros
/*
fun parametros(nombre: String, edad:Int): String {
    return "$nombre $edad"
}

// Parametros
fun parametros(nombre: String, edad:Int, repetidor: Boolean): String {
    return "$nombre $edad $repetidor";
}
*/

// Parametros con valores por defecto
fun parametros(nombre: String, edad: Int = 18, repetidor: Boolean = false): String {
    return "$nombre $edad $repetidor"
}

// Numero indeterminado de parametros
fun calificacion(nombre: String, vararg notas: Double): String {
    // for(i in notas)
    return "$nombre $notas"
}

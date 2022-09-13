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
    println(parametros(nombre = "Pedro", edad = 25, repetidor = false))

    // Numero variable de parámetros
    println(calificacion("Pepe", 7.0))
    println(calificacion("Pepe", 7.0, 8.5))
    println(calificacion("Pepe", 7.0, 8.5, 9.0))
    var notas = DoubleArray(10)

    // con * indicamos que desempaquetamos el array en elementos
    // spread operator
    //https://kotlinlang.org/docs/functions.html#variable-number-of-arguments-varargs
    println(calificacion("Pepe", *notas))

    val (a, b) = notas // Desestructuración de datos, primer y segundo elemento

    val (c, _, _, f) = notas // Desestructuración de datos, primer y cuarto

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

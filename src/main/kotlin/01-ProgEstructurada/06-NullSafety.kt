package `01-ProgEstructurada`

/**
 * En kotlin debemos especificar si una variable es nula o no
 * https://kotlinlang.org/docs/null-safety.html
 */

fun main() {
    var nombre: String? = null
    println(nombre)
    nombre = "Juan" // Prueba a comentar esto a ver que pasa!
    println(nombre)
    var apellidos: String = "Perez"
    println(apellidos)
    // apellidos = null // No me deja el compilador
    apellidos = nombre

    val edad = 18
    val edad2: Int? = null
}
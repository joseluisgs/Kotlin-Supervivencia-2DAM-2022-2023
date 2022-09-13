package `01-ProgEstructuradaModular`

/**
 * Los rangos y progresiones nos permiten operar con secuencias de números.
 * https://kotlinlang.org/docs/ranges.html
 */

fun main() {

    // Rango de enteros entre 1 y 10
    val rango1 = 1..10
    // Rango de enteros entre 1 y 10 con paso 2
    val rango2 = 1..10 step 2
    // Rango de enteros entre 10 y 1
    val rango3 = 10 downTo 1
    // Rango de enteros entre 10 y 1 con paso 2
    val rango4 = 10 downTo 1 step 2
    // Rango de caracteres entre 'a' y 'z'
    val rango5 = 'a'..'z'
    // Rango de caracteres entre 'z' y 'a'
    val rango6 = 'z' downTo 'a'
    // Rango de caracteres entre 'z' y 'a' con paso 2
    val rango7 = 'z' downTo 'a' step 2

}
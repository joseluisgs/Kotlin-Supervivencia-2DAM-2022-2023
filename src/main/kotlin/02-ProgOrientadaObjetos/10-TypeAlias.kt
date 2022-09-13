package `02-ProgOrientadaObjetos`

/**
 * Type Alias nos permite hacer un alias para un tipo de dato que tengamos en nuestro código.
 * Type aliases
 * https://kotlinlang.org/docs/type-aliases.html
 */

typealias MyInt = Int
typealias Matrix = Array<IntArray>
typealias NoRepetidos = Set<Int>

fun main() {
    val myInt: MyInt = 10
    println(myInt)
    val matrix: Matrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
    println(matrix)
    val noRepetidos: NoRepetidos = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
}
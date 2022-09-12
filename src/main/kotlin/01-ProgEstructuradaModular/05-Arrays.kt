package `01-ProgEstructuradaModular`

/**
 * Es una estructura de datos donde se accede por indice
 * https://kotlinlang.org/docs/arrays.html#string-templates
 */

fun main() {
    // Array unidimensional
    val array1 = arrayOf(1, 2, 3, 4, 5)
    val array2 = IntArray(10)
    println(array2)
    val array3 = IntArray(10) { i -> i * 2 }
    println(array3)

    // Array multidimensional
    val array4 = Array(3) { IntArray(3) }
    println(array4)
    val array5 = Array(3) { IntArray(3) { i -> i * 2 } }
    println(array5)
    println(array1[3])
    println(array5[2][1])
}
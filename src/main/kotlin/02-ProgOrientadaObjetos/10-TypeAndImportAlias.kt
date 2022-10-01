package `02-ProgOrientadaObjetos`

/**
 * Type Alias nos permite hacer un alias para un tipo de dato que tengamos en nuestro código.
 * Type aliases
 * https://kotlinlang.org/docs/type-aliases.html
 *
 * Import Alias nos permite hacer un alias para un import que tengamos en nuestro código.
 * De esta manera no nos tenemos que preocupar por el nombre del import, ya que podemos
 * usar el alias que nosotros queramos o si hay dos clases que se llaman igual en dos paquetes
 * gracias a los alias podemos diferenciarlas.
 *
 * https://medium.com/@erickpranata/kotlin-import-alias-3f2831295902
 */

// Se puede poner alias tanto para nombrar como diferencia si se llaman igual
// import java.util.Date as UtilDate
// import java.util.List as UtilList
// import kotlin.collections.List as KotlinList


// Pueden ser públicos o privados
private typealias MyInt = Int
typealias Matrix = Array<IntArray>
typealias Columna = IntArray
typealias NoRepetidos = Set<Int>

fun main() {
    val myInt: MyInt = 10
    println(myInt)
    val matrix: Matrix = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9))
    println(matrix)
    val noRepetidos: NoRepetidos = setOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
}
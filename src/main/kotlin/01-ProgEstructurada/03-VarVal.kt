package `01-ProgEstructurada`

/*
var se puede cambiar su valor: lectura y escritura
val no se puede cambiar su valor: solo lectura
const constante en tiempo de compilacion

https://kotlinlang.org/docs/basic-syntax.html#variables
 */

const val PI = 3.1416

fun main() {
    var edad = 18
    edad = 19
    println(edad)
    val nombre = "Juan"
    println(nombre)
    // nombre = "Pepe" // error
    println(PI)
}
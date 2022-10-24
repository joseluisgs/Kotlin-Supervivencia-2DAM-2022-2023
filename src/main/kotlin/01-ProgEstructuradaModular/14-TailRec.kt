package `01-ProgEstructuradaModular`

/**
 * Tail recursion es una técnica de programación que consiste en reemplazar una llamada recursiva por una
 * estructura de control iterativa. Esto permite que el compilador pueda optimizar la recursividad.
 * https://kotlinlang.org/docs/functions.html#tail-recursive-functions
 */

tailrec fun factorial(n: Int, acum: Int = 1): Int {
    return if (n == 0) acum else factorial(n - 1, n * acum)
}

fun main() {
    println(factorial(5))
}
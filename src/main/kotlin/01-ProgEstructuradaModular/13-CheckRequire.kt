package `01-ProgEstructuradaModular`

/**
 * require, junto con requireNotNull, se puede usar para indicar que una condición dada debe ser verdadera.
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/require.html
 *
 *
check, junto con checkNotNull, son funciones que se pueden usar para validar que una condición determinada
es verdadera (o diferente de null en checkNotNull)
https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/check.html
 */

fun dividir(a: Int, b: Int): Int {
    require(b != 0) { "No se puede dividir por cero" }
    return a / b
}

fun getIndices(lista: List<String>): List<Int> {
    check(lista.isNotEmpty()) { "La lista no puede estar vacía" }
    return lista.indices.toList()
}


fun main() {
    println(dividir(10, 2))
    //println(dividir(10, 0))
    println(getIndices(listOf("a", "b", "c")))
    //println(getIndices(listOf()))
}
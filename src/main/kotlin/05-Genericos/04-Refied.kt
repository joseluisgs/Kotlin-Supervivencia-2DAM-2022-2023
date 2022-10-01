package `05-Genericos`

/**
 * Refied: Si quiero mantner información de tipo genérico y no quiero que se pierda
 * ya que una instancia de una clase genérica no conserva sus parámetros de tipo en tiempo de ejecución.
 */

// imaginemos
// Se pierde imformación en tiempo de ejecución
// no podríamos saber o fiñtar por el tipo
//fun <T> Iterable<*>.filterIsInstance() = filter { it is T } // ERROR!!!

// Refied para conservar la información de tipo genérico en tiempo de ejecución
// ayudandonos de la palabra reservada reified e inline
inline fun <reified T> Iterable<*>.filterIsInstance() = filter { it is T }

fun main() {
    val set = setOf("1984", 2, 3, "Brave new world", 11)
    // Ahora filtramos por Int
    println(set.filterIsInstance<Int>())
}
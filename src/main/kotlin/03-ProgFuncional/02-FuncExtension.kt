package `03-ProgFuncional`

import kotlin.math.pow
import kotlin.math.roundToInt

/**
 * Funciones de extension
 * Nos permiten extender las funcionalidades de una clase sin usar la herencia
 * https://kotlinlang.org/docs/extensions.html#extension-functions
 *
 * Además, podemos crear extension fuction para los companion objects
 * Esto es interesante para fabricar métodos estáticos
 * https://kotlinlang.org/docs/extensions.html#companion-object-extensions
 *
 * O crearle nuevas propiedades, extension properties
 * https://kotlinlang.org/docs/extensions.html#extension-properties
 *
 * Todo esto es interesante para extender las funcionalidades de las clases o data clases
 */

fun Int.isEven() = this % 2 == 0
fun Int.isOdd() = !this.isEven()

fun Double.roundTo(decimals: Int): Double {
    val factor = 10.0.pow(decimals)
    return (this * factor).roundToInt() / factor
}

fun String.isPalindrome(): Boolean {
    val reversed = this.reversed()
    return this == reversed
}

fun <T> Collection<T>.miForEach(accion: (T) -> Unit) {
    for (i in this) {
        accion(i)
    }
}

fun <T> Collection<T>.miFilter(predicado: (T) -> Boolean): Collection<T> {
    val resultado = mutableListOf<T>()
    for (i in this) {
        if (predicado(i)) {
            resultado.add(i)
        }
    }
    return resultado
}

fun <T> Collection<T>.miFind(predicado: (T) -> Boolean): Boolean {
    for (i in this) {
        if (predicado(i)) {
            return true
        }
    }
    return false
}

fun Collection<Number>.miMax(): Number {
    var maximo = this.first()
    for (i in this) {
        if (i.toDouble() > maximo.toDouble()) {
            maximo = i
        }
    }
    return maximo
}

data class Person(val nombre: String, val edad: Int) {
    companion object
}

// Método, de esta manera podemos crear métodos estáticos cuando queramos
fun Person.Companion.create(nombre: String, edad: Int) = Person(nombre, edad)

// Propiedad
val Person.Companion.canVote: Int
    get() = 120


fun main(args: Array<String>) {
    val num = 10
    println(num.isEven())
    println(num.isOdd())

    println(10.894545454.roundTo(2))
    println(10.894545454.roundTo(3))

    println("anitalavalatina".isPalindrome())
    println("dad".isPalindrome())

    val lista = listOf(1, 2, 3, 4, 5)
    lista.miForEach { println(it) }


    println(lista.miFilter { it % 2 == 0 })
    println(lista.miFilter { it.isEven() })

    println(lista.miFind { it == 3 })
    println(lista.miFind { it == 6 })
    println(lista.miFind { it > 5 })

    println(lista.miMax())

    val palabras = listOf("hola", "que", "tal", "abcdefghi")
    println(palabras.miFilter { word -> word.length > 6 })
    println(palabras.miFilter { it -> it.length > 6 })
    println(palabras.miFilter { it.length > 6 })

    val p1 = Person.create("Juan", 20)

}
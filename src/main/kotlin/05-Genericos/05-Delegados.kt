package `05-Genericos`

import kotlin.reflect.KProperty

/**
 * Podemos crear nuestros propios delegados
 * https://kotlinlang.org/docs/delegated-properties.html#delegating-to-another-property
 */

// Por un hash map delegarlo a una clase
// https://kotlinlang.org/docs/delegated-properties.html#storing-properties-in-a-map
class DelegadoMap<K, V>(private val map: MutableMap<K, V>) : MutableMap<K, V> by map {
    override fun put(key: K, value: V): V? {
        println("Se ha agregado un nuevo elemento")
        return map.put(key, value)
    }
}

// O crear nuestro porpio Lazy
// https://kotlinlang.org/docs/delegated-properties.html#property-delegate-requirements
class MyLazy<T>(private val init: () -> T) {
    private var value: T? = null

    // Para ello debemos implementar el getValue pata que funcioen ele By
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        if (value == null) {
            value = init()
        }
        return value!!
    }

    // Si queremos que se pueda modificar
    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: T) {
        value = newValue
    }

}

fun myLazy(init: () -> Int) = MyLazy(init)


fun main() {
    val delegado = DelegadoMap(mutableMapOf(1 to "Uno", 2 to "Dos"))
    delegado[3] = "Tres"
    delegado.entries.forEach { println(it) }

    val lazyString by MyLazy { "Hola mundo" }
    println(lazyString)
    val lazyString2 by myLazy { 2 + 2 }
    println(lazyString2)
}

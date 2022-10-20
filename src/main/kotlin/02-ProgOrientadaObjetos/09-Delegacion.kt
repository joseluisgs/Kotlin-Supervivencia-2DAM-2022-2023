package `02-ProgOrientadaObjetos`

import kotlin.properties.Delegates

/**
 * Delegación es cuando dejamos a otra entidad que maneje una de nuestras propiedades
 * Lazy es cuando la propiedad se inicializa hasta que se la requiera
 * https://kotlinlang.org/docs/delegated-properties.html#lazy-properties
 *
 * Observable es cuando queremos que una propiedad se actualice cuando otra cambia realizamos una acciḉon
 * https://kotlinlang.org/docs/delegated-properties.html#observable-properties
 *
 * Vetoable cuando se actualiza una propiedad ejecuta una acción y podemos acotar el valor que se le asigna
 *
 * https://medium.com/backyard-programmers/kotlin-standard-delegates-lazy-observable-and-vetoable-761a82b74e57
 */

class Delegate {
    val lazyValue: String by lazy {
        println("Ahora es cuando me creo que soy Lazy")
        "Hello"
    }

    // Patron observer!!
    var observableValue: String by Delegates.observable("Hello") { _, old, new ->
        println("Se cambio $old -> $new")
    }

    var vetoableValue: Int by Delegates.vetoable(0) { _, old, new ->
        println("Se cambio el valor de la propiedad de $old a $new")
        new in 1..10
    }

    // Not null
    var notNull by Delegates.notNull<String>()

}

fun main() {
    val delegate = Delegate()
    println(delegate.lazyValue)
    println(delegate.lazyValue)

    delegate.observableValue = "Hola"
    println(delegate.observableValue)
    delegate.observableValue = "Mundo"
    println(delegate.observableValue)

    delegate.vetoableValue = 5
    println(delegate.vetoableValue)
    delegate.vetoableValue = 15
    println(delegate.vetoableValue)

    delegate.notNull = "Hola"
    println(delegate.notNull)

}
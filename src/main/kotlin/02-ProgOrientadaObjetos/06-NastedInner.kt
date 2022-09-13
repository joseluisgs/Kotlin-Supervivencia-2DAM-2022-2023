package `02-ProgOrientadaObjetos`

/**
 * Nasted Class  e Inner Class
 * https://kotlinlang.org/docs/nested-classes.html
 * una clase anidada es una clase definida dentro de otra clase
 * Nasted no puede acceder a propiedades/métodos privadas de la clase externa
 * Inner si puede acceder a propiedades/métodos
 */

class Outer {
    private val bar: Int = 1

    class Nested {
        fun foo() = 2
        // fun returnBar(): Int = bar // no puedo acceder a la clase superior
    }
}

class Outer2 {
    private val bar: Int = 1

    inner class Inner {
        fun foo() = bar
    }
}

fun main() {
    val outer = Outer()
    val nested = Outer.Nested()
    println(nested.foo()) // == 2
    // println(outer.returnBar()) // No es posible acceder a la propiedad/métodos privados de la clase externa
    println(Outer.Nested().foo()) // == 2

    val outer2 = Outer2()
    val inner = Outer2().Inner()
    println(inner.foo()) // == 1
    println(outer2.Inner().foo()) // == 1
}
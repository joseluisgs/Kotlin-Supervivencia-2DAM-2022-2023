package `03-ProgFuncional`

/*
Labda con Rceiver es una mezcla de Lambda Funciones de extensión
Las lambdas con receptores son básicamente iguales a las funciones de extensión,
solo se pueden almacenar en propiedades y pasar a las funciones.
https://kotlinlang.org/docs/lambdas.html#function-literals-with-receiver

Dentro del cuerpo de la función literal, el objeto receptor pasado a la llamada
se convierte en un implícito this

Nos sirve para extender funciones de una clase y aplicar una funcion a cada una de las funciones de la clase
con ello podemos hacer safebuilders o DSL

https://www.baeldung.com/kotlin/lambda-receiver
*/


// Aqui esta claro que extendemos  con una función de extensión y con una función de receptor
fun Int.sumExtension(other: Int): Int = this + other
// 3.sumExtension(4) --> 7 pero yo no puedo hacer sumExtension(3,4), pierdo esa opción

// Pero vamos a hacerlo de esta manera. De esta manera usamos el lambda con el receptor
// tenemos implicito el this
val sum: Int.(Int) -> Int = { a -> this + a } // this es el Int que recibe la función


// Aqui extendemos con opp que a su vez como parametro usa en vez de una función o lambda una función de extensión
fun Int.opp(f: Int.() -> Int): Int = f()

// Safe Builder String con lambdas con receptor,

fun buildString(actions: StringBuilder.() -> Unit): String {
    val builder = StringBuilder()
    builder.actions()
    return builder.toString()
}

// Making a DSL
class HTML {
    fun body() {

    }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // create the receiver object
    html.init()        // pass the receiver object to the lambda
    return html
}


fun main() {
    println(3.sumExtension(4))
    println(3.sumExtension(4))
    // println(sumExtension(3,4))
    println(sum(1, 2))
    println(1.sum(2))

    println(10.opp { this.times(2) })
    println(10.opp { plus(10) })
    println(10.opp { this * 2 })

    // Safe Builders
    val str = buildString {
        append("Hello, ".uppercase())
        append("World!")
    }
    println(str)
    /**
     * Dentro de la lambda que pasa a buildString, está en un nuevo ámbito y, como tal,
     * tiene nuevos métodos y propiedades disponibles para su uso.
     * En este caso específico, puedes usar métodos del tipo StringBuilder sin tener que llamarlos
     * en ninguna instancia.
     * No administras la instancia real de StringBuilder en la que se realizarán estas llamadas de función:
     * depende de la implementación interna de la función crear una y llamar a su función de extensión en ella.
     * En consecuencia, también sería posible que esta función hiciera mucho más que simplemente
     * llamar a la lambda que le pasó una vez en un StringBuilder:
     * podría llamarla varias veces, en varias instancias de StringBuilder,
     * almacenarla para su uso posterior, etc.
     */
}
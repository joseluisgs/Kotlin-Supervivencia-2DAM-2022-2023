package `03-ProgFuncional`

import java.util.*

/* Nos permiten realizar acciones sobre el objeto que estamos trabajando
 y varían si el propio objeto es el receptor del mismo
 Es una combinación de todo lo visto
 https://kotlinlang.org/docs/scope-functions.html
 son run, let, with, apply y also
                        */

// no hay una regla para ellas y a veces depende del gusto del programador

// Por ejemplo voy a definir mi propia apply
fun <T> T.apply2(block: T.() -> Unit): T {
    block()
    return this
}

// Por ejemplo voy a definir mi propia run
fun <T, R> T.run2(block: T.() -> R): R {
    return block()
}

// Por ejemplo voy a definir mi propia with
fun <T, R> with2(receiver: T, block: T.() -> R): R {
    return receiver.block()
}

// Mi propia let
fun <T, R> T.let2(block: (T) -> R): R {
    return block(this)
}

data class Persona(
    var nombre: String = "",
    var edad: Int = 0,
) {
    val uuid = UUID.randomUUID()

    fun saludar() {
        println("Hola, me llamo $nombre y tengo $edad años")
    }
}

fun main() {
    // Run
    // nos permite inicializar y ejecutar un método del objeto, el propio objeto es el receptor
    val p1 = Persona()
    p1.run {
        nombre = "Juan"
        edad = 25
        saludar()
    }

    // With, nos permite cambiar el estado de un objeto, hay que indicarle el objeto
    val p2 = Persona()
    with(p2) {
        nombre = "Juan"
        edad = 25
        //saludar()
    }
    p2.saludar()

    // Apply nos permite cambiar el estado de un objeto, el propio objeto es el receptor: ¡Anda la interfaz fluida!
    val p3 = Persona()
    p3.apply {
        nombre = "Juan"
        edad = 25
    }.saludar()

    // Let se suele usar para comparaciones y realizar una accion, el propio objeto es el receptor (adios if!!)
    val p4 = Persona()
    p4.let {
        it.saludar()
    }

    var p5: Persona? = null
    p5?.let {
        it.nombre = "Juan"
        it.saludar()
    }

    // Also se usa para realizar una accion, posterior
    var p6 = Persona()
    p2.apply {
        nombre = "Juan"
        edad = 25
    }.also {
        it.saludar()
    }

    val randomNull: String? = null
    // Por favor ya no!!! que no estais en Java
    if (randomNull != null) {
        println(randomNull.length)
    }

    // Usando Scope Functions incluso puedo simular un if else
    randomNull?.let {
        println(it.length)
    }.also { println("Hola") }

    // Usando Elvis, pero e implica excribir una opcion o no o llamadas seguras
    println(randomNull?.length ?: "No hay nada")
    println(randomNull?.length)

    val b: String? = "Me gusta Kotlin"
    b?.let {
        println("B no es null")
    } ?: run {
        println("B sí es null")
    }.also {
        println("ademas si hace el run hago esto")
    }

    // queda mas claro con un if
    if (b != null) {
        println("B no es null")
    } else {
        println("B sí es null")
    }

    // Swap con also
    var var1 = 1
    var var2 = 2
    var1 = var2.also { var2 = var1 } // var1 = var2 y ademas var2 = var1
    println("var1 = $var1, var2 = $var2")


}
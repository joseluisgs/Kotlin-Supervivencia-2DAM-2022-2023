package `03-ProgFuncional`

/**
 * Las funciones infijas o infix nos permite escribir funciones de una manera más legible
 * https://kotlinlang.org/docs/functions.html#infix-notation
 */

infix fun Int.pow(n : Int): Int{
    return this.toDouble().pow(n).toInt()
}

class Persona(val nombre: String, val edad: Int) {
    infix fun esMayorQue(persona: Persona): Boolean {
        return this.edad > persona.edad
    }
}

fun main() {
    val base = 3
    val exponente = 4
    val resultado = base pow 4 // Es lo mismo que base.pow(exponente)
    print("$base ^ $exponente = $resultado")

    val p1 = Persona("Pepe", 20)
    val p2 = Persona("Juan", 30)
    val esMayor = p1 esMayorQue p2 // Es lo mismo que p1.esMayorQue(p2)
    println("¿${p1.nombre} es mayor que ${p2.nombre}? $esMayor")
}
package `02-ProgOrientadaObjetos`

/**
 * Object nos sirve para crear clases anonimas
 * https://kotlinlang.org/docs/object-declarations.html
 *
 * Con Companion Objects podemos crear objetos que son compartidos por todas las instancias de una clase
 * Es decir, métodos y atributos de clase
 * https://kotlinlang.org/docs/object-declarations.html#companion-objects
 *
 * También puede ser usado como Object
 * https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview
 */

class CompanionObject {
    companion object {
        // Atributos de clase
        var contador: Int = 0
        fun create() {
            contador++
        }
    }
}

// uno de sus usos es usarlo como factory de objetos
class Persona(val nombre: String) {
    companion object Factory {
        fun create(nombre: String): Persona {
            return Persona(nombre.uppercase())
        }
    }
}

// Es hacer un singleton
object Singleton {
    var contador: Int = 0
    fun hazlo() {
        contador++
    }
}

fun main() {
    val objeto = object {
        var nombre: String = "Objeto"
        fun saludar() = println("Hola soy un $nombre")
    }
    objeto.saludar()
    objeto.nombre = "Objeto"
    objeto.saludar()

    CompanionObject.create()
    CompanionObject.create()
    println(CompanionObject.contador)

    val persona = Persona.create("Juan")
    println(persona.nombre)

    Singleton.hazlo()
    Singleton.hazlo()
    println(Singleton.contador)
}
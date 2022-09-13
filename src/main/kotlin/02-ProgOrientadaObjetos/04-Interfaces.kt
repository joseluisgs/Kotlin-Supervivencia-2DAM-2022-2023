package `02-ProgOrientadaObjetos`

/*
Las interfaces son un contrato que define un conjunto de métodos que deben ser implementados por una clase.
Se puede definir una implementación por defecto y propiedades en una interfaz.
La HERENCIA DE INTERFACES MULTIPLE.
 */

interface IA {
    fun a(): String = "a"
    fun b(): String
}

interface IB {
    fun a(): String
    fun b(): String
}

interface IC {
    val name: String
    fun a() = "Hola $name"
}

interface IMagic : IA, IB, IC {
    override fun a() = super<IC>.a() // cual estoy ejecutando
}

class MiClase : IA, IB, IC {
    // Sobre escribo variables
    override val name: String = "MiClase"

    // Puedo sobrescribir el método o combiar o elegir de cual interfaz heredar
    override fun a(): String {
        return super<IA>.a() + super<IC>.a()
    }

    // implemento mi método
    override fun b() = "b"
}

// Herencia e interfaces, se puede heredar de una clase y de una interfaz
open class Ejemplo01(val edad: Int) : IA, IB {
    override fun a() = "a"
    override fun b() = "b"
}

class Ejemplo02(override val name: String, edad: Int) : Ejemplo01(edad), IMagic {
    override fun a() = super<IMagic>.a()
    override fun b() = "patata"
}

fun main() {
    val miClase = MiClase()
    println(miClase.a())
    println(miClase.b())
    println(miClase.name)

    val ejemplo02 = Ejemplo02("Ejemplo02", 10)
    println(ejemplo02.a())
    println(ejemplo02.b())
    println(ejemplo02.name)
}
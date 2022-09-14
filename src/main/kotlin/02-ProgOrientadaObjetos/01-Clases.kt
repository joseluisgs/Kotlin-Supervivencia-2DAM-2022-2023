package `02-ProgOrientadaObjetos`

/**
 * Kotlin es un lenguaje tipado, la definición de los objetos se hacen por clases
 * Es importante que instanciemos todos los valaores que tengamos sobre todo si no son nulos
 * https://kotlinlang.org/docs/classes.html
 */

// Muy a lo Java, es feo
class Animal() {
    private var nombre: String = ""
    private var edad: Int = 0

    // Init es un método que se ejecuta al instanciar el objetio, podemos tener varios bloques init
    // pero se suele poner uno y tenerlo al comienzo
    init {
        println("Se ha creado un animal")
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getNombre(): String {
        return this.nombre
    }
}

// Mas Kotlin
class Animal2 constructor(var nombre: String = "", var edad: Int = 0) {
    init {
        println("Se ha creado un animal")
    }
}

// Mas Kotlin aún
class Animal3(var nombre: String = "", var edad: Int = 0) {
    init {
        println("Se ha creado un animal")
    }
}

// Podemos tener tantos constructores como queramos, pero ¿para qué?
// Estudia si es necesario tener más de un constructor o usa parámetros por defecto

class Animal4(var nombre: String = "", var edad: Int = 0) {
    var muerto = false

    constructor(nombre: String, edad: Int, muerto: Boolean) : this(nombre, edad) {
        this.muerto = muerto
    }

    init {
        println("Se ha creado un animal y esta $muerto")
    }
}

class Animal5(val nombre: String) {
    val camada: MutableList<Animal5> = mutableListOf()

    constructor(name: String, padre: Animal5) : this(name) {
        padre.camada.add(this)
    }
}

fun main() {
    // No hace falta el método new
    val animal1 = Animal()
    val animal2 = Animal2("Perro", 5)
    val animal3 = Animal3("Gato", 3)
    val animal4 = Animal4("Gato", 3, true)
    val animal5 = Animal5("Perro")
    val animal6 = Animal5("Gato", animal5)
    println(animal6.nombre)
    // animal6.nombre = "Gato2"  // no tienes el set porque es val

}
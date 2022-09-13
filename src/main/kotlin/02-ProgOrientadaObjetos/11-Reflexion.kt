package `02-ProgOrientadaObjetos`

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

/**
 * Reflexion en Kotlin es la capacidad de analizar una clase en tiempo de ejecución
 * https://kotlinlang.org/docs/reflection.html
 * https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/
 * https://www.geeksforgeeks.org/kotlin-reflection/
 * https://www.baeldung.com/kotlin/reflection
 */

data class UnaClase(val nombre: String, var edad: Int) {
    fun unMetodo() {
        println("Hola Mundo, tengo $edad años y te llamas $nombre")
    }

    fun otroMetodo(valor: Int) {
        edad += valor
        return unMetodo()
    }
}

fun main() {
    val unaClase = UnaClase("Juan", 20)
    unaClase.unMetodo()

    // Usamos relfexion para obtener información de la clase del objeto de unaClase
    val clase = unaClase::class
    println(clase.simpleName)
    println(clase.qualifiedName)
    println(clase.members)

    val unMetodo = clase.members.find { it.name == "unMetodo" }
    unMetodo?.call(unaClase)
    val otroMetodo = clase.members.find { it.name == "otroMetodo" }
    println(otroMetodo?.call(unaClase, 10))

    val unaPropiedad = clase.members.find { it.name == "edad" } as KMutableProperty<*>
    // getter
    println(unaPropiedad.getter.call(unaClase))
    println(unaClase.edad)
    // setter
    unaPropiedad.setter.call(unaClase, 50)
    println(unaPropiedad.getter.call(unaClase))
    println(unaClase.edad)

    println(clase.memberProperties)
    for (prop in clase.memberProperties) {
        println(prop.name)
    }


    println(clase.memberFunctions)
    for (func in clase.memberFunctions) {
        println("${func.name} ${func.parameters} ${func.returnType}")
    }

    println(clase.constructors)
    for (cons in clase.constructors) {
        println("${cons.name} ${cons.parameters} ${cons.returnType}")
    }

    val constructor = clase.constructors.find { it.parameters.size == 2 }
    val nuevaInstancia = constructor?.call("Pedro", 30)
    println(nuevaInstancia)
    nuevaInstancia?.unMetodo()
    nuevaInstancia?.otroMetodo(20)
    println(nuevaInstancia)

    val otraForma = ::UnaClase
    println(otraForma)
    val otraInstancia = otraForma.call("Maria", 40)
    println(otraInstancia)
}
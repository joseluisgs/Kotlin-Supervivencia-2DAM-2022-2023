package `02-ProgOrientadaObjetos`

/**
 * La herencia nos permite crear nuevas clases a partir de otras ya existentes.
 * En Kotlin las clases por defecto son cerradas, por lo que si queremos que una clase pueda ser heredada
 * debemos indicarlo con la palabra reservada open.
 * Igual para métodos y propiedades que quieran ser sobrescritos
 * Las clases pueden ser asbtractas y no pueden ser instanciadas
 * Las clases pueden ser finales y no pueden ser heredadas
 * Los métodos del padre puedo ejecutarlos con super
 * https://kotlinlang.org/docs/inheritance.html
 */

abstract class ClassA(val name: String) {
    open fun printName() {
        println("ClassA: $name")
    }
}

open class ClassB(name: String) : ClassA(name) {
    override fun printName() {
        println("ClassB: $name")
    }

    open fun printSuper() {
        println("ClassB: $name")
        super.printName()
    }
}

class ClassC(private val myName: String) : ClassB(myName.uppercase()) {
    override fun printName() {
        println("ClassC: $name")
    }

    override fun printSuper() {
        println("ClassC: $name")
        super.printName()
    }

    fun printMyName() {
        println("ClassC: $myName")
    }
}


fun main() {

    val classB = ClassB("ClassB")
    classB.printName()
    classB.printSuper()

    val classC = ClassC("ClassC")
    classC.printName()
    classC.printSuper()
    classC.printMyName()
}
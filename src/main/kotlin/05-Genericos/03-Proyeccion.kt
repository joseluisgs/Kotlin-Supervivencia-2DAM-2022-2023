package `05-Genericos`

/**
 * La proyección de un tipo genérico es el tipo de los objetos que se pueden
 * obtener de él.
 * https://kotlinlang.org/docs/generics.html
 */

// Uso array porque se puede consumir y producir(gett y set) y queremos especificar
// El manejo de la varianza

// Copiar Array of Subtipos a un Array of Supertipos
// Array<out T> es un Array de T de solo lectura
// Out, cualquier cosa por encima de Any, es lo cumple Any?


fun copy1(from: Array<out Any>, to: Array<Any?>) {
    for (i in from.indices)
        to[i] = from[i]
}

// Añadir elementos de un subtipo a un Array de supertipo
// Array<in T> es un Array de T donde vamos a producir
// con in decimos que como máximo un Int
fun fill(dest: Array<in Int>, value: Int) {
    dest[0] = value
}

/*
Hay situaciones en las que no nos importa el tipo específico de valor.
Digamos que solo queremos imprimir todos los elementos de un Array y no importa cuál
sea el tipo de elementos en este Array.

 */

fun printArray(array: Array<*>) {
    array.forEach { println(it) }
}

fun main() {
    val ints = arrayOf(1, 2, 3)
    val any = arrayOfNulls<Any>(3)
    copy1(ints, any)
    println(any.contentToString())

    val objects: Array<Any?> = arrayOfNulls(1)
    fill(objects, 1)

    val array = arrayOf(1, 2, 3)
    printArray(array)
}
package `01-ProgEstructuradaModular`

/*
Entrada de datos con readln()
Salida de datos con print o println
https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.io/readln.html

 */

fun main(args: Array<String>) {
    var nombre = ""
    var edad = -1

    // Pedir el nombre
    do {
        println("Dime tu nombre: ")
        nombre = readln()
    } while (nombre.isEmpty())

    // Pedir edad
    do {
        println("Dime tu edad: ")
        edad = readln().toIntOrNull() ?: -1
    } while (edad < 0)

    println("Hola $nombre tienes $edad")

    // Podemos leer los argumentos si los hemos capturado en la función main
    println("Argumentos: ${args.size}")
    for (arg in args) {
        println(arg)
    }

    // incluso podemos tomar varias cosas del tiron por ejemplo separados por coma
    println("Dime tu nombre y edad separados por coma: ")
    // Desestructuramos la entrada
    val (nombre2, edad2) = readln().split(",")
    println("Hola $nombre2 tienes $edad2")
    // O leer una lista de valores separados por coma
    println("Dime una lista de valores separados por coma: ")
    val lista = readln().split(",")
    println("Lista: $lista")

}
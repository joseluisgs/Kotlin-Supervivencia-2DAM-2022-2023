package `04-Ficheros`

import java.io.File

fun main() {
    // Podemos leer ficheros de texto de la siguiente manera
    val csvFile = "data${File.separator}tenistas.csv"
    if (!File(csvFile).exists()) {
        throw IllegalArgumentException("El fichero $csvFile no existe")
    }

    // Por favor mira el tipo de cada cosa
    // con readText() leemos todo el fichero de golpe
    val csv = File(csvFile).readText()
    println(csv)
    // Linea a liena para procesarla
    val lines = File(csvFile).readLines()
    println(lines)
    // Por cada linea
    File(csvFile).forEachLine {
        println(it)
    }
    // Usando el bufferedReader
    File(csvFile).bufferedReader().use {
        println(it)
    }

    // Vamos a escribir ficheros de texto
    val newFile = "data${File.separator}tenistas2.csv"
    // Escribimos todo el fichero de golpe y sobrescribimos si existe
    File(newFile).writeText(csv)
    // Añadimos el texto al final del fichero
    File(newFile).appendText(csv)
    // Escribimos con printWriter
    File(newFile).printWriter().use {
        it.println(csv)
    }
}
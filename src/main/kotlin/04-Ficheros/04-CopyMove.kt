package `04-Ficheros`

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

fun main(args: Array<String>) {
    // Tenemos que tener dos ficheros en los argumentos
    if (args.size != 2) {
        println("Error: Debe indicar dos ficheros")
        return
    }
    val fichero1 = args[0]
    val fichero2 = args[1]
    // existe el fichero1?
    if (!File(fichero1).exists()) {
        println("Error: El fichero $fichero1 no existe")
        return
    }
    // Si existe el fichero 2 preguntamos si sobreescribimos
    if (File(fichero2).exists()) {
        print("El fichero $fichero2 ya existe. ¿Sobreescribir? (s/n): ")
        val respuesta = readln() ?: throw Exception("Error al leer la respuesta")
        if (respuesta != "s") {
            println("No se ha copiado el fichero")
            return
        }
        // Movemos el fichero 1 como fichero 2
        Files.move(File(fichero1).toPath(), File(fichero2).toPath(), StandardCopyOption.REPLACE_EXISTING)
        // Si quremos copiar el fichero 1 como fichero 2
        // Files.copy(File(fichero1).toPath(), File(fichero2).toPath(), StandardCopyOption.REPLACE_EXISTING)
    }
}
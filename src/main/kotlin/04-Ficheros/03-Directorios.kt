package `04-Ficheros`

import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.getAttribute
import kotlin.io.path.getOwner

fun main(args: Array<String>) {
    // Si el argumento es 0 es este directorio, si no es otro
    val dir = if (args.isEmpty()) "." else args[0]
    // Creamos un objeto File con el directorio
    val f = File(dir)
    // Comprobamos las condiciones, usamos salida rapida
    if (!f.exists() || !f.isDirectory) {
        println("El directorio no existe o no es un directorio")
        return
    }

    // list files inside the directory
    val files = f.listFiles()
    files?.let {
        // Nombre: Tamaño: (bytes) Tipo: Permisos: Path:
        it.forEach { file ->
            println(
                "${file.name} / ${file.length()} bytes / ${file.extension} / " +
                        (if (file.canRead()) "r" else "-") +
                        (if (file.canWrite()) "w" else "-") +
                        (if (file.canExecute()) "x" else "-") +
                        "${if (file.isDirectory) "d" else "-"} / " +
                        Path(file.absolutePath).getAttribute("unix:permissions") + " / " +
                        Path(file.absolutePath).getOwner() + " / " +
                        file.absolutePath
            )
        }
    }

    // Tambien podemos recorrer recursivamente un directorio con walk, por ejemplo mi home
    File(".").walk().forEach { println(it) }

}
package `08-Concurrencia`

/**
 * Un proceso es una unidad de ejecución que se ejecuta en un sistema operativo.
 * En JVM podemos tener varios procesos, cada uno con su propio espacio de memoria.
 * Podemos crear un proceso con ProcessBuilder.
 * Leemos los datos desde un proceso con InputStream.
 * Mandamos datos a un proceso con OutputStream.
 * https://www.baeldung.com/java-lang-processbuilder-api
 */

fun main() {
    println("Hola Procesos")

    // Proceso de ejecución de un comando ls -ls
    // val p = Runtime.getRuntime().exec("ls -ls")
    val ls = ProcessBuilder("ls", "-ls", ".").start()
    // Asi lo leemos todo con la Colecciones
    val lsOut = ls.inputStream.bufferedReader()
        .lineSequence()
        .filter { it.contains(".kts") || it.contains(".bat") }
        .joinToString("\n")
    println(lsOut)

    ls.waitFor()
    var exitValue = ls.exitValue()
    println("Valor de salida proceso ls: $exitValue")

    // Proceso de ejecución de un comando cat
    // Tomamos la primera linea
    val ficheroCat = lsOut.lines()
        .first().split(" ").last()
    println(ficheroCat)

    // Proceso de ejecución de un comando cat
    val cat = ProcessBuilder("cat", ficheroCat).start()
    cat.waitFor()

    // La salida del cat se la pasamos a la entrada del grep
    val grep = ProcessBuilder("grep", "kotlinOptions.jvmTarget").start()

    // Leemos el cat
    val catOut = cat.inputStream.bufferedReader().readText()
    // Lo pasamos al grep
    grep.outputStream.bufferedWriter().use { it.write(catOut) }
    // leer el resultado del grep
    val grepOut = grep.inputStream.bufferedReader().readText()

    exitValue = cat.exitValue()
    println("Valor de salida proceso cat: $exitValue")
    grep.waitFor()
    exitValue = grep.exitValue()
    println("Valor de salida proceso cat: $exitValue")

    // El contenido a buscar era:
    // kotlinOptions.jvmTarget = "1.8"
    println("El contenido a buscar era: $grepOut")
    // Has compilado tu programa para una version
    // de Java inferior a la 1.8
    println(
        "Has compilado para la version JVM: ${
            grepOut.trim()
                .split(" ")
                .last().replace("\"", "")
        }"
    )

    // conexion a educamadrid
    val ping = ProcessBuilder("ping", "-c4", "www.educamadrid.org").start()
    val pingOut = ping.inputStream.bufferedReader().lineSequence()
        .last().split("/")
    //println(pingOut)
    println("Tiempo medio de respuesta: ${pingOut[4]} ms")


}
package `08-Concurrencia`

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*
import java.util.concurrent.*


/**
 * Future es una interfaz que nos permite obtener el resultado de una tarea
 * que se ejecuta en un hilo diferente al principal, asíncrónico
 * Usa callable para obtener el resultado
 * get() es un método bloqueante, es decir, se queda esperando a que la tarea
 * termine para devolver el resultado
 * isDone() devuelve true si la tarea ha terminado
 * cancel() cancela la tarea
 * isCancelled() devuelve true si la tarea ha sido cancelada
 * get(long, TimeUnit) devuelve el resultado de la tarea o lanza una excepción
 * si la tarea no ha terminado en el tiempo indicado
 * CompletableFuture es una implementación de Future que nos permite
 * encadenar tareas, es decir, ejecutar una tarea cuando otra haya terminado
 * y así sucesivamente
 *
 * https://www.baeldung.com/java-future
 * https://www.baeldung.com/java-completablefuture
 * https://www.baeldung.com/java-9-completablefuture
 */


fun main() {
    // Sistema operativo
    println("Sistema operativo: " + System.getProperty("os.name"))
    // Versión de JVM
    println("Versión de java: " + System.getProperty("java.version"))
    // Numero de cores del sistema
    println("Numero de cores del sistema: " + Runtime.getRuntime().availableProcessors())

    /*future1()
    future2()
    future3()
    future4()
    future5()
    future6()
    future7()*/
    future8()
}

fun future8() {
    val pool = Executors.newSingleThreadExecutor()
    val processBuilder = ProcessBuilder()
    if (System.getProperty("os.name").lowercase(Locale.getDefault()).contains("windows")) {
        processBuilder.command("cmd.exe", "/c", "ping -c 4 google.com")
    } else {
        processBuilder.command("bash", "-c", "ping -c 4 google.com")
    }

    try {
        val process = processBuilder.start()
        println("process ping...")
        val task = ProcessReadTask(process.inputStream)
        // Espertamos porque recibimos una promesa, es asincrono
        // Le decimos que se ejecute una tarea como hilo
        val future: Future<List<String>> = pool.submit(task)

        // no bloqueo, se puede usar otras tareas aquí
        while (!future.isDone) {
            println("Esperando...")
            Thread.sleep(300)
        }

        // Esperamos que se cumpla la tarea 5 segundos
        val result = future[5, TimeUnit.SECONDS]
        result.forEach(::println)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        pool.shutdown()
    }
}

private class ProcessReadTask(private val inputStream: InputStream) : Callable<List<String>> {
    override fun call(): List<String> {
        return BufferedReader(InputStreamReader(inputStream)).lines().toList()
    }
}

private fun factorial(num: Long): Long {
    Thread.sleep(2000)
    return if (num >= 1) {
        num * factorial(num - 1)
    } else 1L
}

fun future7() {
    // No necesitamos  ExecutorService.
    // CompletableFuture internamente usa ForkJoinPool para implementar la asincronía
    val completableFuture = CompletableFuture.supplyAsync {
        try {
            return@supplyAsync factorial(10L)
        } catch (e: InterruptedException) {
            e.printStackTrace()
            return@supplyAsync 0L
        }
    }
    while (!completableFuture.isDone) {
        println("CompletableFuture aun no ha terminado...")
        Thread.sleep(200)
    }
    val result = completableFuture.get()
    println("Resultado: $result")
}

fun future6() {
    println("Ejemplo Future y invokeAny")
    // Ejecutamos multiples tareas y esperamos a que termine una
    // Ejecutamos multiples tareas y esperamos a que termine una
    val executorService = Executors.newFixedThreadPool(5)

    val task1 = Callable {
        println("Comenzando Task1")
        Thread.sleep(2000)
        println("Terminando Task1")
        "Resultado Task1"
    }

    val task2 = Callable {
        println("Comenzando Task2")
        Thread.sleep(1000)
        println("Terminando Task2")
        "Resultado Task2"
    }

    val task3 = Callable {
        println("Comenzando Task3")
        Thread.sleep(5000)
        println("Terminando Task3")
        "Resultado Task3"
    }

    val taskList: List<Callable<String>> = listOf(task1, task2, task3)

    val result = executorService.invokeAny(taskList) //al menos 1

    // El resultado se imprime cuando termine una
    println(result)

    executorService.shutdown()
}

fun future5() {
    println("Ejemplo Future y invokeAll")
    // Ejecutamos multiples tareas y esperamos que terminen todas
    val executorService = Executors.newFixedThreadPool(5)

    val task1 = Callable {
        println("Comenzando Task1")
        Thread.sleep(2000)
        println("Terminando Task1")
        "Resultado Task1"
    }

    val task2 = Callable {
        println("Comenzando Task2")
        Thread.sleep(1000)
        println("Terminando Task2")
        "Resultado Task2"
    }

    val task3 = Callable {
        println("Comenzando Task3")
        Thread.sleep(5000)
        println("Terminando Task3")
        "Resultado Task3"
    }

    val taskList: List<Callable<String>> = listOf(task1, task2, task3)

    val futures = executorService.invokeAll(taskList) // todas


    for (future in futures) {
        // El resultado solo se imprime si todas las promesas/futuros se cumplen
        println(future.get())
    }

    executorService.shutdown()
}

fun future4() {
    println("Ejemplo Future y TimeOut")
    val executorService = Executors.newSingleThreadExecutor()
    try {
        val future = executorService.submit<String> {
            Thread.sleep(2000)
            "Hola desde Callable"
        }

        // Le añadimos un timeout para no esperar para siempre. Nos arrojará una excepción si llega el caso
        val result = future[1, TimeUnit.SECONDS]
        println("Tarea completada! Recibiendo el resultado")
        println(result)
    } catch (e: TimeoutException) {
        System.err.println("Ha terminado el tiempo")
    }
    executorService.shutdown()
}

fun future3() {
    println("Ejemplo Future y Cancelled")
    val executorService = Executors.newSingleThreadExecutor()

    val startTime = System.nanoTime()
    val future = executorService.submit<String> {
        Thread.sleep(2000)
        "Hola desde Callable"
    }

    while (!future.isDone) {
        println("Tarea no terminada...")
        Thread.sleep(200)
        val elapsedTimeInSec = (System.nanoTime() - startTime) / 1000000000.0

        // Si pasa este determinado tiempo, la cancelamos
        if (elapsedTimeInSec > 1) {
            future.cancel(true)
        }
    }

    if (!future.isCancelled) {
        println("Tarea completada! Recibiendo el resultado")
        val result = future.get()
        println(result)
    } else {
        println("La Tarea fue cancelada")
    }

    executorService.shutdown()
}

fun future2() {
    println("Ejemplo Future y isDone")
    val executorService = Executors.newSingleThreadExecutor()

    val future = executorService.submit<String> {
        Thread.sleep(2000)
        "Hola desde Callable"
    }
    // Esperamos mientras no esté terminada
    while (!future.isDone) {
        println("Tarea no terminada...")
        Thread.sleep(200)
    }

    println("Tarea completada! Recibiendo el resultado")
    val result = future.get()
    println(result)

    executorService.shutdown()
}

fun future1() {
    println("Ejemplo Callable y Get")
    val executorService = Executors.newSingleThreadExecutor()

    val callable: Callable<String> = Callable<String> {
        // Perform some computation
        println("Entramos en el Callable")
        Thread.sleep(2000)
        "Hola desde Callable"
    }

    println("Enviando Callable")
    val future: Future<String> = executorService.submit(callable)

    println("Podemos hacer otras cosas hasta que se ejecute el Callable")

    println("Obtenemos el resultado de Callable")
    // Future.get() bloquea el programa hasta que se resuleve la promesa
    val result: String = future.get()
    println(result)

    executorService.shutdown()
}



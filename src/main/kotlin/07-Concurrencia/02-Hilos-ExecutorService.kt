import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

/**
 * Ejemplo de uso de hilos con Kotlin
 * Thread lanza hilos, podemos heredar de Hilos, o implementar
 * Runnable: para ejecutar código en un hilo
 * Callable: para ejecutar código en un hilo y devolver un valor
 * ExecutorService: para ejecutar hilos teniendo un pool de hilos fijos
 * y con ello optimizar recursos. Es decir, no creando hilos son razon!
 */

fun main() {
    println("Mis cajas \uD83D\uDED2")

    // hilosFijos()
    // executeWorkerRunnable()
    executeWorkerCallable()
}

fun executeWorkerCallable() {
    val NUM_HILOS = 4

    val clientes = mutableListOf<Cliente>()

    repeat((1..10).random()) {
        clientes.add(Cliente("Cliente ${it + 1}"))
    }
    println("Tenemos en cola a ${clientes.size} clientes")

    val executor = Executors.newFixedThreadPool(NUM_HILOS)
    var recaudacion = 0
    measureTimeMillis {
        val cajas = mutableListOf<Caja>()
        clientes.forEach {
            cajas.add(Caja(it))
        }
        val futures = executor.invokeAll(cajas)
        futures.forEachIndexed { index, future ->
            recaudacion += future.get()
            println("Caja: ${index + 1} factura un precio total de ${future.get()} €")
        }
        executor.shutdown()
    }.also {
        println("Tiempo total de atención total: $it ms")
        println("Recaudación total: $recaudacion €")
    }


}

fun executeWorker() {
    val NUM_HILOS = 4

    // Queue of clients
    val clientes = mutableListOf<Cliente>()
    repeat((1..10).random()) {
        clientes.add(Cliente("Cliente ${it + 1}"))
    }
    println("Tenemos en cola a ${clientes.size} clientes")

    val executor = Executors.newFixedThreadPool(NUM_HILOS)
    measureTimeMillis {
        clientes.forEach {
            executor.execute(Caja(it))
        }
        executor.shutdown()
    }.also {
        println("Tiempo total de atención total: $it ms")
    }

}

private fun hilosFijos() {
    val NUM_HILOS = 4

    measureTimeMillis {
        val cajas = mutableListOf<Thread>()
        repeat(NUM_HILOS) {
            val cliente = Cliente("Cliente ${it + 1}")
            val caja = Thread(Caja(cliente))
            cajas.add(caja)
            caja.start()
        }

        cajas.forEach { it.join() }
    }.also {
        println("Tiempo total de procesamiento total: $it ms")
    }
}

// ----------------------------------------------------------------

private class Caja(val cliente: Cliente) : Runnable, Callable<Int> {
    override fun run() {
        var precioTotal = 0
        measureTimeMillis {
            println("Caja: ${Thread.currentThread().name} atendiendo a ${cliente.nombre} que tiene ${cliente.carro.productos.size} productos")
            cliente.carro.productos.forEachIndexed { index, producto ->
                println("Caja: ${Thread.currentThread().name} atendiendo a ${cliente.nombre}, producto: ${index + 1} con precio: ${producto.precio} €")
                Thread.sleep(producto.precio.toLong() * 1000)
                precioTotal += producto.precio
            }
            println("Caja: ${Thread.currentThread().name} terminó de atender a ${cliente.nombre}")
            //println("Caja: ${Thread.currentThread().name} precio total de ${cliente.nombre}: $precioTotal €")
        }.also {
            println("Caja: ${Thread.currentThread().name} tiempo de atención final: $it ms")
        }
    }

    override fun call(): Int {
        var precioTotal = 0
        measureTimeMillis {
            println("Caja: ${Thread.currentThread().name} atendiendo a ${cliente.nombre} que tiene ${cliente.carro.productos.size} productos")
            cliente.carro.productos.forEachIndexed { index, producto ->
                println("Caja: ${Thread.currentThread().name} atendiendo a ${cliente.nombre}, producto: ${index + 1} con precio: ${producto.precio} €")
                Thread.sleep(producto.precio.toLong() * 1000)
                precioTotal += producto.precio
            }
            println("Caja: ${Thread.currentThread().name} terminó de atender a ${cliente.nombre}")
            println("Caja: ${Thread.currentThread().name} precio total de ${cliente.nombre}: $precioTotal €")
        }.also {
            println("Caja: ${Thread.currentThread().name} tiempo de atención final: $it ms")
        }
        return precioTotal
    }
}

private data class Cliente(var nombre: String) {
    val carro = Carro()
}

private class Carro() {
    private var _productos = mutableListOf<Producto>()
    val productos: List<Producto>
        get() = _productos

    init {
        repeat((5..15).random()) {
            _productos.add(Producto())
        }
    }

    override fun toString(): String {
        return productos.toString()
    }
}

private data class Producto(
    val precio: Int = (1..10).random()
)



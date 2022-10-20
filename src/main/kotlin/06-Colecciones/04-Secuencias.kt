package `06-Colecciones`

/**
 * Colecciones son Eagerly: Gastan menos memoria pueden realizar más pasadas
 * Esto significa que todas las operaciones aplicadas sobre la colección siempre se evalúan
 * y se evalúan en el mismo orden en que se aplican.
 * Cada transformación se realiza sobre toda la colección.
 * Se crea una nueva colección después de aplicar la transformación en la colección inicial.
 *  Adecuado para listas más pequeñas, no para las más grandes, ya que el procesamiento de la funcion de
 *  recopilación intermedia se vuelve costoso.
 *
 * Una secuencia se puede considerar como una colección de datos que se evalúa de forma perezosa
 * y que puede tener infinitos elementos.
 * https://kotlinlang.org/docs/sequences.html
 *
 * Secuencias son Lazy: Gastan más memoria pueden realizar menos pasadas
 * según la necesidad, según la operación de la terminal.
 * Cada transformación se realiza elemento por elemento.
 * No se crea ninguna colección nueva.
 * Adecuado para listas más grandes.
 *
 */

private val alumnado = listOf(
    Alumno("Juan", 7.5, "DAM"),
    Alumno("Pedro", 8.5, "DAM"),
    Alumno("Ana", 9.5, "DAW"),
    Alumno("María", 8.5, "DAM"),
    Alumno("José", 9.5, "DAW"),
    Alumno("Alicia", 7.5, "DAW")
)

fun main() {
    val alumnado = alumnado.asSequence()
        .groupBy { it.curso }
        .map { it.value.maxByOrNull { it.nota } }
        .filterNotNull()
        .sortedBy { it.nota }
        .map { it.nombre }
        .joinToString()

    // Coleccion
    val operations1 = (1..10)
        .filter { it % 2 == 1 } // 1, 3, 5, 7, 9
        .map { it * 2 }        // 2, 6, 10, 14, 18
        .take(2)               // 2, 6

    val operations2 = (1..10).asSequence()
        .filter { it % 2 == 1 } // 1, 3, 5, 7, 9
        .map { it * 2 } // 2, 6, 10,
        .take(2) // 2, 6
        .toList()


    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthsList = words.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)
    // 23 operaciones

    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // terminal operation: obtaining the result as a List
    println(lengthsSequence.toList())
    // 18 operaciones


    // secuencia de numeros primos
    val numbers = generateSequence(2) { it + 1 }
    val primes = numbers.filter { number ->
        numbers.takeWhile { it <= number / 2 }.all { number % it != 0 }
    }
    println(primes.take(10).toList())
    // yield
    // Da un valor al iterador que se está construyendo y se suspende hasta que se solicita el siguiente valor.
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.sequences/-sequence-scope/yield.html
    val primos = sequence {
        yield(1)
        yieldAll(listOf(3, 5))
        yieldAll(generateSequence(7) { it + 2 })
    }
    println(primos.take(10).toList())

    fun fibonacci() = sequence {
        var terms = Pair(0, 1)

        // this sequence is infinite
        while (true) {
            yield(terms.first)
            terms = Pair(terms.second, terms.first + terms.second)
        }
    }

    println(fibonacci().take(10).toList()) // [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
}
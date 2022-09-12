package `01-ProgEstructuradaModular`

/**
 * Control de Flujo
 * - Condicionales
 * - Bucles
 * https://kotlinlang.org/docs/control-flow.html
 */

fun main() {
    // if if else
    val num = 2
    if (num % 2 == 0) {
        println("Es par")
    } else {
        println("Es impar")
    }

    // No tenemos operador ternario no lo necesitamos, if es una expresion
    val salida = if (num % 2 == 0) "Es par" else "Es impar"
    println(salida)


    // When, nos permte hacer varias comprobaciones

    when (num) {
        1 -> print("num == 1")
        2 -> print("num == 2")
        else -> {
            print("ni es 1 ni 2")
        }
    }

    // When tambiene es una expresion
    val salida2 = when {
        num % 2 == 0 -> "Es par"
        num < 0 -> "Es negativo"
        num is Int -> "Es Entero"
        num !in 0..1 -> "No es 0 o 1"
        else -> "Es otra cosa"
    }

    // Bucles indefinidos

    // While. Bucle indefinido
    var dato = 0
    while (dato < 10) {
        println(dato)
        dato += 1
    }

    // Do while.
    dato = 10
    do {
        println(dato)
        dato -= 1
    } while (dato > 0)

    // Bucles definidos

    // for, con rangos
    for (i in 1..10) {
        println(i)
    }
    for (i in 0 until 10) {
        println(i)
    }
    for (i in 0 until 10 step 2) {
        println(i)
    }

    for (i in 10 downTo 1 step 2) {
        println(i)
    }

    val enteros = IntArray(10)

    // For each con cada elemento
    for (i in enteros)
        println(i)

    for (i in enteros.reversed())
        println(i)

    // For con índices
    for (i in enteros.indices) {
        enteros[i] = enteros[i] * 2
        println(enteros[i])
    }

    // Podemos ir hacia atrás si queremos
    for (i in enteros.indices.reversed()) {
        enteros[i] = enteros[i] * 2
        println(enteros[i])
    }

    // También podemos usar el índice yu elemento a la vez
    for (i in enteros.withIndex()) {
        println("${i.index} ${i.value}")
        enteros[i.index] = enteros[i.index] * i.value
    }

    // O usar desectructuración
    for ((index, value) in enteros.withIndex()) {
        println("$index $value")
        enteros[index] = enteros[index] * value
    }

    for ((index, value) in enteros.withIndex().reversed()) {
        println("$index $value")
    }

    // O no usar un for, si no un repeat para opciones repetidas
    repeat(10) {
        println("Hola")
    }
}
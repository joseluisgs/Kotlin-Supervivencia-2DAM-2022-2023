package `03-ProgFuncional`

/*
Las funciones son ciudadanos de primera clase
Puedo usarlas como tipo, existe el tipo funcion
https://kotlinlang.org/docs/functions.html#function-usage

Lambda es una funcion anonima que se puede usar como parametro y se define cuando la necesitemos
 */

fun suma(a: Int, b: Int): Int {
    return a + b
}

// Puedo almacenar funciones en variables
val resta = fun(a: Int, b: Int): Int {
    return a - b
}

// Puedo pasarlas como parámetros
fun funcionConParametrosFuncion(a: Int, b: Int, funcion: (Int, Int) -> Int): Int {
    return funcion(a, b)
}

// Puedo devolverlas como resultado
fun funcionQueDevuelveFuncion(): (Int, Int) -> Int {
    return resta
}

// Y si ahora le definimos el comportamiento que queremos? en base a su tipo función (Int, Int) -> Int
// Esto es una función anónima, o lambda, es decir, programamos el comportamiento en base a la
// Especificación del tipo de dato que queremos.
// una lambda es: { x: Int, y: Int -> x + y }

var add: (Int, Int) -> Int = { a, b -> a + b }
var subtract = { a: Int, b: Int -> a - b }

fun operacion(a: Int, b: Int, calculo: (Int, Int) -> Int): Int {
    return calculo(a, b)
}

// Mi repeat
fun miRepeat(veces: Int, accion: (Int) -> Unit) {
    for (i in 1..veces) {
        accion(i)
    }
}

fun miForEach(lista: Collection<Int>, accion: (Int) -> Unit) {
    for (i in lista) {
        accion(i)
    }
}

// Con genericos
fun <T> miForEachGenerico(lista: Collection<T>, accion: (T) -> Unit) {
    for (i in lista) {
        accion(i)
    }
}


fun main() {
    val miSuma = ::suma
    println(suma(1, 2))
    println(miSuma(1, 2))
    println(resta(1, 2))
    println(funcionConParametrosFuncion(1, 2, resta))
    println(funcionConParametrosFuncion(1, 2, ::suma))
    println(funcionConParametrosFuncion(1, 2, miSuma))
    println(funcionQueDevuelveFuncion()(1, 2))
    println(add(1, 2))
    println(subtract(1, 2))

    // Multiplicamos con la lambda, sobre la marcga definimos la funcion que queremos
    println(funcionConParametrosFuncion(1, 2, { a, b -> a * b }))
    // Si la lambda es el ultimo parámetro, podemos ponerla fuera de los paréntesis
    println(funcionConParametrosFuncion(1, 2) { a, b -> a * b })

    // Calculadora con operacion
    println(operacion(1, 2) { a, b -> a + b })
    println(operacion(1, 2) { a, b -> a - b })
    println(operacion(1, 2) { a, b -> a * b })
    println(operacion(1, 2) { a, b -> a / b })

    miRepeat(19) {
        println("Hola $it")
    }
    repeat(19) {
        println("Hola $it")
    }

    val lista = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val listaString = listOf("Hola", "que", "tal", "estas", "hoy")
    lista.forEach {
        println(it)
    }

    miForEach(lista) {
        println(it)
    }

    miForEachGenerico(listaString) {
        println(it)
    }
}
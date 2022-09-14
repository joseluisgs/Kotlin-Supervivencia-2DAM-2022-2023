package `03-ProgFuncional`

/**
 * En Kotlin podemos sobrecargar operadores
 * Esto nos es útil para no usar las funciones que vienen por debajo y ser un código más legible
 * Lo hacemos gracias a operator
 * https://kotlinlang.org/docs/operator-overloading.html#inline-properties
 */

data class Punto(var x: Int, var y: Int)

operator fun Punto.unaryMinus() = Punto(-x, -y)
operator fun Punto.inc() = Punto(x + 1, y + 1)
operator fun Punto.dec() = Punto(x - 1, y - 1)
operator fun Punto.plus(other: Punto) = Punto(x + other.x, y + other.y)
operator fun Punto.minus(other: Punto) = Punto(x - other.x, y - other.y)
operator fun Punto.times(other: Punto) = Punto(x * other.x, y * other.y)
operator fun Punto.div(other: Punto) = Punto(x / other.x, y / other.y)
operator fun Punto.contains(other: Punto) = x == other.x && y == other.y
operator fun Punto.get(index: Char) = when (index) {
    'x' -> x
    'y' -> y
    else -> throw IndexOutOfBoundsException("No existe la coordenada $index")
}

operator fun Punto.set(index: Char, value: Int) = when (index) {
    'x' -> x = value
    'y' -> y = value
    else -> throw IndexOutOfBoundsException("No existe la coordenada $index")
}

operator fun Punto.component1() = x
operator fun Punto.component2() = y


fun main() {
    var punto = Punto(10, 20)
    println(-punto)
    punto++
    println(punto)
    punto--
    println(punto)
    punto += Punto(10, 20)
    println(punto)
    punto -= Punto(10, 20)
    val punto2 = Punto(10, 20)
    println(punto + punto2)
    println(punto - punto2)
    println(punto * punto2)
    println(punto / punto2)
    println(punto in punto2)
    println(punto['x'])
    println(punto['y'])
    punto['x'] = 100
    punto['y'] = 200
    println(punto)
    val (x, y) = punto
    println("x: $x, y: $y")
}
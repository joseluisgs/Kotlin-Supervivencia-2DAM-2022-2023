package `06-Colecciones`

// Pares y Triples sirven para pasar un valor compuesto de dos o tres cosas
data class Alumno(
    val nombre: String,
    val nota: Double,
    val curso: String
)

fun main() {
    val pair = Pair(listOf(1, 2), "Juan")
    val triple = Triple(1, Alumno("Pepe", 7.7, "DAM"), listOf(1, 2))
    println(pair)
    println(pair.first)
    println(pair.second)
    println(pair.toList())
    println(triple)
    println(triple.first)
    println(triple.second)
    println(triple.third)
    println(triple.toList())
}
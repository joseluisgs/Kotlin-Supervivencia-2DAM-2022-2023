package `06-Colecciones`

import kotlin.math.pow
import kotlin.math.roundToInt

// https://kotlinlang.org/docs/collection-operations.html
private val alumnado = listOf(
    Alumno("Juan", 7.5, "DAM"),
    Alumno("Pedro", 8.5, "DAM"),
    Alumno("Ana", 9.5, "DAW"),
    Alumno("María", 8.5, "DAM"),
    Alumno("José", 9.5, "DAW"),
    Alumno("Alicia", 7.5, "DAW")
)

fun Double.roundTo(numFractionDigits: Int): Double {
    val factor = 10.0.pow(numFractionDigits.toDouble())
    return (this * factor).roundToInt() / factor
}

fun main() {
    // Todos los alumnos
    println("Todos los alumnos:")
    alumnado.forEach { println(it) }

    // Alumnos de DAM
    println("Alumnos de DAM:")
    println(alumnado.filter { it.curso == "DAM" })

    // Alumnos con nota >= 8.5
    println("Alumnos con nota >= 8.5:")
    println(alumnado.filter { it.nota >= 8.5 })

    println("Alumnos con nota >= 8.5 y de DAM")
    println(alumnado.filter { it.nota >= 8.5 && it.curso == "DAM" })

    // Lista de Alumnos de DAM con nota >= 8.5
    val (alumnosSobresaliente, alumnosOtros) = alumnado.partition { it.nota >= 8.5 && it.curso == "DAM" }
    println("Alumnos de DAM con nota >= 8.5:")
    println(alumnosSobresaliente)
    println("Alumnos de otras notas:")
    println(alumnosOtros.joinToString(prefix = "{{", separator = " | ", postfix = "}}"))

    // Alumnos con nota máxima
    println("Alumnos con nota máxima:")
    val notaMaxima = alumnado.maxBy { it.nota }
    println("Alumno ${notaMaxima.nombre} con nota: ${notaMaxima.nota}")

    // Nota media de los alumnos de DAM
    println("Nota media de los alumnos de DAM:")
    val notaMediaDAM = alumnado.filter { it.curso == "DAM" }
        .map { it.nota }
        .average()
        .roundTo(2)
    println(notaMediaDAM)

    // Alumnado agrupado por grupo
    println("Alumnado agrupado por grupo:")
    val agrupadoPorGrupo = alumnado.groupBy { it.curso }
    println(agrupadoPorGrupo)

    // Alumnado agrupado por nota mayor que 8.5
    println("Alumnado agrupado por nota mayor que 8.5:")
    val agrupadoPorNota = alumnado.groupBy { if (it.nota >= 8.5) "Sobresalientes" else "Otros" }
    println(agrupadoPorNota)

    // Alumnado ordenado por nota descendente
    println("Alumnado ordenado por nota descendente:")
    val ordenadoPorNota = alumnado.sortedByDescending { it.nota }
    println(ordenadoPorNota)

    // Buscar alumnos con nota >= 8.5
    println("Buscar alumnos con nota >= 8.5:")
    val buscarNota = alumnado.find { it.nota >= 8.5 }
    println(buscarNota ?: "No hay alumnos con nota >= 8.5")

    // Buscar alumnos con nota >= 8.5 y curso DAW
    println("Buscar alumnos con nota >= 8.5 y curso DAW:")
    val buscarNotaYCurso = alumnado.find { it.nota >= 8.5 && it.curso == "DAW" }
    println(buscarNotaYCurso ?: "No hay alumnos con nota >= 8.5 y curso DAW")

    // Numero de alumnos agrupados por nota mayor que 8.5
    println("Numero de alumnos agrupados por nota mayor que 8.5:")
    val numeroAlumnos = alumnado.groupBy { if (it.nota >= 8.5) "Sobrsalientes" else "Otros" }
        .map { it.key to it.value.size }
    println(numeroAlumnos)
}
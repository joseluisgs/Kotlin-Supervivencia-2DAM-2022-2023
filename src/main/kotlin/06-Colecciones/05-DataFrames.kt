package `06-Colecciones`

import models.Alumnado
import models.curso
import models.nota
import org.jetbrains.kotlinx.dataframe.DataFrame
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.kotlinx.dataframe.io.readCSV
import org.jetbrains.kotlinx.dataframe.io.readJson
import org.jetbrains.kotlinx.dataframe.io.writeCSV
import org.jetbrains.kotlinx.dataframe.io.writeJson

/**
 * Son colecciones masivas de elementos estructurados
 * usadas para procesar grandes cantidades de datos
 * y realizar analysis de datos
 * https://kotlin.github.io/dataframe/overview.html
 * https://blog.jetbrains.com/kotlin/2022/06/kotlin-dataframe-library-preview/
 */

// Podemos usar anotaciones para Data Frames, no es obligatorio si solo usamos colecciones
// Primero haz el build!! (Como me da error por el nombre del paquete, cámbialo!
//@DataSchema
// mira el paquete models
/*data class Alumnado(
    val nombre: String,
    val nota: Double,
    val curso: String
)*/

private val alumnadoList = listOf(
    Alumnado("Juan", 7.5, "DAM"),
    Alumnado("Pedro", 9.75, "DAM"),
    Alumnado("Ana", 9.5, "DAW"),
    Alumnado("María", 8.5, "DAM"),
    Alumnado("José", 7.5, "DAW"),
    Alumnado("Alicia", 7.5, "DAW")
)

fun main() {
    val df = alumnadoList.toDataFrame()
    // Lo casteamos para tener tipificado todo respecto a la clase Alumnado
    df.cast<Alumnado>()
    // Mostramos el esquema del Data Frame
    println(df.schema())
    // Las primeras 3 filas
    df.head(3).print()

    // Filtramos por curso, si estuviese casteado, ponemos el atributo
    // Al no tenerlo casteado por el nombre del paquete, debo poner el nombre de la columna
    val porCurso = df.filter { it.curso == "DAM" }.print()
    //df.filter { it["curso"] == "DAM" }.print()

    // Agrupamos por curso y calculamos la media de las notas
    df.groupBy("curso").mean().print()

    // Ordenados por nota mayor que 8.5
    df.filter { it.nota > 8.5 }.print()

    // Estadisticas por curso
    df.groupBy("curso")
        .aggregate {
            count() into "Total"
            mean(it.nota) into "Media"
            min(it.nota) into "Min"
            max(it.nota) into "Max"
        }.print()

    // Ordenamos los cursos por nota media
    df.groupBy("curso")
        .aggregate {
            mean(it.nota) into "Media"
        }.sortByDesc("Media").print()

    // Alumnos con la nota máxima por curso
    val porNota = df.groupBy("curso")
        .aggregate {
            max(it.nota) into "Max"
        }
    val notaMaxDam = porNota["Max"].first()
    //println(notaMaxDam)
    val notaMaxDaw = porNota["Max"].last()
    //println(notaMaxDaw)

    // Aliumno con nota máxima en DAM
    df.filter { it.curso == "DAM" && it.nota == notaMaxDam }.print()
    // Alumno con nota máxima en DAW
    df.filter { it.curso == "DAW" && it.nota == notaMaxDaw }.print()

    // Podemos salvarlos como CSV
    df.writeCSV("./data/alumnado-sal.csv")
    // Como JSON
    df.writeJson("./data/alumnado-sal.json", prettyPrint = true)

    // podemos leerlos de un CSV
    val df2 = DataFrame.readCSV("./data/alumnado-sal.csv")
    df2.cast<Alumnado>()
    df2.print()

    // podemos leerlos de un JSON
    val df3 = DataFrame.readJson("./data/alumnado-sal.json")
    df3.cast<Alumnado>()
    df3.print()

}
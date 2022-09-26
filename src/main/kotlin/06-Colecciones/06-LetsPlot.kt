package `06-Colecciones`

import jetbrains.datalore.base.values.Color
import jetbrains.datalore.plot.PlotHtmlExport
import jetbrains.datalore.plot.PlotHtmlHelper.scriptUrl
import jetbrains.datalore.plot.PlotSvgExport
import models.Alumnado
import models.nota
import org.jetbrains.kotlinx.dataframe.api.*
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.export.VersionChecker
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomBar
import org.jetbrains.letsPlot.geom.geomTile
import org.jetbrains.letsPlot.ggplot
import org.jetbrains.letsPlot.ggsize
import org.jetbrains.letsPlot.intern.Plot
import org.jetbrains.letsPlot.intern.toSpec
import org.jetbrains.letsPlot.label.ggtitle
import org.jetbrains.letsPlot.label.labs
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.scale.scaleFillGradient
import org.jetbrains.letsPlot.themes.elementBlank
import org.jetbrains.letsPlot.themes.theme


/**
 * Lets Plot es una librería de visualización de datos que permite crear gráficos de forma sencilla y rápida.
 * basada en ggplot2 de R.
 * http://verso.mat.uam.es/~joser.berrendero/R/introggplot2.html
 * https://lets-plot.org/
 * https://github.com/JetBrains/lets-plot
 * https://github.com/JetBrains/lets-plot-kotlin
 * https://datalore.jetbrains.com/view/notebook/nsYaTYEEYG1wGIaqa2bWr5
 */

// Para salvar en HTML
fun Plot.exportToHtml() =
    PlotHtmlExport.buildHtmlFromRawSpecs(this.toSpec(), scriptUrl(VersionChecker.letsPlotJsVersion))

// Para salvar en SVG
fun Plot.exportToSvg() = PlotSvgExport.buildSvgImageFromRawSpecs(this.toSpec())

private val alumnadoList = listOf(
    Alumnado("Juan", 7.5, "DAM"),
    Alumnado("Pedro", 9.75, "DAM"),
    Alumnado("Ana", 9.5, "DAW"),
    Alumnado("María", 8.5, "DAM"),
    Alumnado("José", 6.5, "DAW"),
    Alumnado("Alicia", 7.5, "DAW"),
    Alumnado("Eva", 8.5, "DAM"),
    Alumnado("Patricia", 7.5, "DAW"),
    Alumnado("Raúl", 7.75, "DAM"),
    Alumnado("Alejandro", 7.5, "DAM"),
    Alumnado("Sandra", 7.5, "DAW"),
    Alumnado("Luis", 8.5, "DAW"),
)

fun main() {

    val df = alumnadoList.toDataFrame()

    // Media de DAM y DAW // Puedes hacerlo con un agregado
    val estadisticas = df.groupBy("curso")
        .aggregate {
            count() into "total"
            mean(it.nota) into "media"
            max(it.nota) into "max"
            min(it.nota) into "min"
        }
    println(estadisticas)

    // Grafico de barras
    var fig: Plot = letsPlot(data = estadisticas.toMap()) + geomBar(
        stat = Stat.identity,
        alpha = 0.8
    ) {
        x = "curso"; y = "total"
    } + labs(
        x = "Curso",
        y = "Total",
        title = "Total de alumnos por curso",
    )
    ggsave(fig, "01-totalPorcurso.png")

    // Grafico de barras de media
    fig = letsPlot(data = estadisticas.toMap()) + geomBar(
        stat = Stat.identity,
        alpha = 0.8
    ) {
        x = "curso"; y = "media"
    } + labs(
        x = "Curso",
        y = "Media",
        title = "Media de alumnos por curso",
    )
    ggsave(fig, "02-mediaPorcurso.png")

    // Barras super puestas, va por capas, la primera la última
    fig = letsPlot(data = estadisticas.toMap()) + geomBar(
        stat = Stat.identity,
        alpha = 0.8,
        fill = Color.PACIFIC_BLUE,
    ) {
        x = "curso"; y = "max"
    } + geomBar(
        stat = Stat.identity,
        alpha = 0.8,
        fill = Color.DARK_GREEN
    ) {
        x = "curso"; y = "media"
    } + geomBar(
        stat = Stat.identity,
        alpha = 0.8,
        fill = Color.DARK_MAGENTA,
    ) {
        x = "curso"; y = "min"
    } + labs(
        x = "Curso",
        y = "Calificación",
        title = "Calificaciones por curso",
    )
    ggsave(fig, "03-calificacionesCurso.png")

    val dfCursoNota = df.groupBy("curso", "nota").count() // contamos
    println(dfCursoNota)

    // Grafico de calor, con el número de alumnos por curso y nota para rellenar
    // Si no usamos count es que hemos usado el agregado y ponemos el nombre de la columna
    fig = ggplot(dfCursoNota.toMap()) +
            geomTile(height = 0.9, width = 0.9) { x = "nota"; y = "curso"; fill = "count" } +
            theme(panelBackground = elementBlank(), panelGrid = elementBlank()) +
            scaleFillGradient(low = "#ffcfcf", high = "#e0000b") +
            ggtitle("Alumnos por notas en cada curso") +
            ggsize(900, 700)

    ggsave(fig, "04-porCursoNota.png")


}
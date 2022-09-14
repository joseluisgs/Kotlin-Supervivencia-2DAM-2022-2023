package `02-ProgOrientadaObjetos`

/**
 * Enums: Son clases que nos permiten crear Enums.
 * Pero también pueden tener métodos y atributos.
 * https://kotlinlang.org/docs/enum-classes.html
 *
 * Sealed: Sealed classes permiten que una clase tenga subclases, pero que estas
 * no puedan ser definidas fuera de la clase padre.
 * https://kotlinlang.org/docs/sealed-classes.html
 */
enum class DiasSemana {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
}

enum class DiasSemana2(val numero: Int) {
    LUNES(1), MARTES(2), MIERCOLES(3), JUEVES(4), VIERNES(5), SABADO(6), DOMINGO(7)
}

sealed class MiError
class ErrorBaseDatos(val database: String) : MiError()
class ErrorFichero(val fichero: String) : MiError()
class ErrorApiRest(val url: String) : MiError()

fun log(e: MiError) = when (e) {
    is ErrorBaseDatos -> println("Error en la base de datos ${e.database}")
    is ErrorFichero -> println("Error en el fichero ${e.fichero}")
    is ErrorApiRest -> println("Error en la API REST ${e.url}")
}


fun main() {
    println(DiasSemana2.LUNES.numero)
    println(DiasSemana.valueOf("LUNES"))
    println(DiasSemana.values().contentToString())

    log(ErrorBaseDatos("base1"))

}
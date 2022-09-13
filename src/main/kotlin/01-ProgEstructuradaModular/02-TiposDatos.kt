package `01-ProgEstructuradaModular`

/*
En ciencias de la computación, un tipo de dato informático o simplemente tipo,
es un atributo de los datos que indica al ordenador (y/o al programador/programadora)
sobre la clase de datos que se va a manejar. Esto incluye imponer restricciones en los datos,
como qué valores pueden tomar y qué operaciones se pueden realizar.

https://kotlinlang.org/docs/basic-types.html

 */

fun main() {
    // Tipo de datos
    val entero: Int = 10
    val edad = 23
    val byte: Byte = 127
    val long = 1234567890123456789L
    val decimal: Double = 10.5
    val float: Float = 10.5f
    val texto: String = "Hola mundo"
    val booleano: Boolean = true
    val caracter: Char = 'a'
    println("Entero: $entero")

    // Los castings son explicitos por el programador
    // https://kotlinlang.org/docs/numbers.html#explicit-number-conversions
    val castingEntero = decimal.toInt()

}
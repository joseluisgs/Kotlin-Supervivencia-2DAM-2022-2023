package `01-ProgEstructurada`

/**
 * Nos permite manejar cadenass de una forma mas simple
 * https://kotlinlang.org/docs/basic-syntax.html#string-templates
 */

fun main() {
    val nombre = "Juan Perez"
    val edad = 20
    println("Tu nombre es: " + nombre + " y tienes: " + edad) // Buaggg!!!
    println("Tu nombre es: $nombre y tienes: $edad")
    println("Tu nombre es: ${nombre.uppercase()} y tienes: $edad")

    val json = """
        {
            "nombre": "$nombre",
            "edad": $edad
        }
    """.trimIndent()
    println(json)
}
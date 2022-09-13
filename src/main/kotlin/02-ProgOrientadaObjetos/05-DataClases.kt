package `02-ProgOrientadaObjetos`

import java.time.Instant

/**
 * Data Classes nospermiten crear clases con los métodos: equals, hashCode, toString, copy y componentN
 * https://kotlinlang.org/docs/data-classes.html
 */

data class Ordenador(
    val numSerie: String = Instant.now().toString(),
    var marca: String,
    var modelo: String,
    var ram: Int,
    var disco: Int,
    var ssd: Boolean
)

fun main() {
    val ordenador1 = Ordenador(numSerie = "02", marca = "HP", modelo = "Pavilion", ram = 8, disco = 1000, ssd = false)
    val ordenador2 = Ordenador(marca = "HP", modelo = "Pavilion", ram = 8, disco = 1000, ssd = false)

    println(ordenador1)
    println(ordenador2)
    println(ordenador1.hashCode())
    println(ordenador2 == ordenador1) // tienen los mismos campos
    println(ordenador2 === ordenador1) // es el mismo objeto
    val ordenador3 = ordenador1.copy(marca = "Lenovo", ssd = true) // copia una clase con nuevos datos
    println(ordenador3)
    val (_, marca, modelo, ram, disco, ssd) = ordenador3 // desestructuración de clases
    println("Marca: $marca, Modelo: $modelo, RAM: $ram, Disco: $disco, SSD: $ssd")
    val otro = ordenador3.copy()
    println(otro)
    println(otro == ordenador3)  // true
    println(otro === ordenador3) // false
    println(ordenador3.component1()) // numSerie

}

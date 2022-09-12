package `01-ProgEstructurada`

/**
 * En kotlin debemos especificar si una variable es nula o no
 * https://kotlinlang.org/docs/null-safety.html
 */

fun main() {
    var nombre: String? = null
    println(nombre)
    nombre = "Juan" // Prueba a comentar esto a ver que pasa!
    println(nombre)
    var apellidos: String = "Perez"
    println(apellidos)
    // apellidos = null // No me deja el compilador
    apellidos = nombre

    val edad = 18
    val edad2: Int? = null

    // Llamadas seguras, pòdemos llamar a un método de un objeto qie podría hacer uso de nulo
    // usando las llamadas seguras, de esta manera si el objeto es nulo no se ejecuta el método
    // y no se produce un error (y nos ahorramos el uso de if)
    val otroString: String? = null
    println(otroString?.length)

    // Podemos usar el operador elvis para asignar un valor por defecto si es null
    // Así podemos ahorrarnos el uso de if por defecto
    val edad3 = edad2 ?: 0
    println(edad3)

    val edad4 = edad2 ?: "No se ha especificado"
    println(edad4)

    // el operador de aserción no nulo (!!) , si usamos este operador y el objeto es nulo
    // convierte cualquier valor en un tipo no nulo y lanza una excepción (NPE)
    // si el valor es nulo
    println(otroString!!.length)
}
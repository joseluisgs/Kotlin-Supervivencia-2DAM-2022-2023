package `01-ProgEstructurada`

import java.MiClase

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

    // Plataform types (!)
    // En Kotlin tenemos los tipos de datos de plataforma, que son los tipos de datos
    // que se usan en Java, por ejemplo String, Int, etc
    // Estos tipos de datos no son nulos por defecto, por lo que si usamos un tipo defecto
    // de kotlin, como por ejemplo String?, y lo asignamos a un tipo de java, como por ejemplo
    // String, el compilador nos dará un error, ya que no se puede asignar un tipo nulo a uno
    // no nulo
    // val nullable: String? = item // allowed, always works
    // val notNull: String = item // allowed, may fail at runtime

    // Imagina que tenemos una clase de Java, pero en java cualqueir objeto puede we nulo y no nulo
    // Kotlin rebaja el tipo de dato a un tipo de dato de plataforma y operar según necesidades.

    // Usar notaciones en JAVA, muy recomendadas no solo por Kotlin, si no para Java también
    // https://kotlinlang.org/docs/java-interop.html#nullability-annotations

    val miClase = MiClase()

    val res01 = miClase.stringNullable // Soy nulo o no?? No lo sé, soy String! Debes decidirlo tu, o usar elvis o !!
    val res02 = miClase.stringNotNull // ¿Que soy? Soy String! No soy nulo o si? No lo sé, es tu responsabilidad

    // Y si me dices lo que quieres que sea?
    val res03: String? = miClase.stringNullable
    val res04: String = miClase.stringNotNull

    // Y si me equivoco, porque no lo se??
    val res05: String = miClase.stringNullable ?: "No se ha especificado"
    val res06: String = miClase.stringNotNull ?: "No se ha especificado"

    // O me la juego con !! (Puedo darte un NPE)
    val res07: String = miClase.stringNullable!!
    val res08: String = miClase.stringNotNull!!

    // Mejor las anotaciones en Java para Java y Kotlin o Scala
    val res09 = miClase.stringNullableWithNotation // Mira que tipo soy, ya soy String?
    val res10 = miClase.stringNotNullNotation // Mira que tipo soy, ya soy String


}
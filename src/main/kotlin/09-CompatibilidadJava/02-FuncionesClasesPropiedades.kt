@file:JvmName("KotlinCompatibility") // Cambiamos el nombre del fichero

package `09-CompatibilidadJava`

import java.io.File
import java.io.IOException

/**
 * Las funciones a alto nivel son funciones que no pertenecen a ninguna clase
 * y se compilan como métodos estáticos
 * por lo que debemos indicar el nombre de la clase a la que pertenecen: ficheroKt
 * y el nombre de la función
 * Si queremos cambiar como se llama las cosas usamos anotaciones
 * https://kotlinlang.org/docs/reference/java-to-kotlin-interop.html#jvmname
 * @file:JvmName("KotlinCompatibility") // Cambiamos el nombre del fichero
 * ya podría usar desde Java: KotlinCompatibility.prueba(1)
 */


fun prueba(n: Int): Int {
    return n + 1
}

/**
 *
 * Las funciones de extensión se ven desde java usando como primer argumento
 * el objeto que estaba siendo extendido
 * por ejemplo Int.suma(n: Int) donde hacemos en kotlin 1.suma(2)
 * se vería como KotlinCompatibility.suma(1, 2)
 * Lo de KotlinCompatibility es el nombre que hemos establecido, recuerda, si no es el del fichero
 */

fun Int.suma(n: Int): Int {
    return this + n
}

/**
 * Si tenemos valores por defecto, para evitar sobrecargas, estos no se ven desde java como tal,
 * pues solo tendremos una función si queremos
 * que nos cree todas las sobrecargas existentes, debemos indicar que queremos @JvmOverloads
 * https://kotlinlang.org/docs/java-to-kotlin-interop.html#overloads-generation
 */
@JvmOverloads
fun valoresPorDefecto(n: Int = 1, s: String = "Hola"): String {
    return "$n $s"
}

/**
 * Las lambdas se ven como interfaces funcionales y se deben usar desde java con el método invoke
 * https://www.baeldung.com/kotlin/lambda-expressions (punto 5)
 */

fun miLambda(n: Int, lambda: (Int) -> Int): Int {
    return lambda(n)
}

/**
 * Propiedades implementa los getter y setter dependiendo si son val/var y si son private o public
 * https://kotlinlang.org/docs/java-to-kotlin-interop.html#properties
 *
 * Si necesitas exponer una propiedad como un campo de una clase, puedes usar @JvmField
 * https://kotlinlang.org/docs/java-to-kotlin-interop.html#instance-fields
 *
 * Puedes cambiar el nombre de métodos y propiedades usando @JvmName
 */
class Propiedades(
    var nombre: String = "Pepe",
    @JvmField val edad: Int = 23,
    private var apellido: String = "Perez"
) {
    @JvmName("getNombreCompleto")
    fun getDatos(): String {
        return nombre + apellido
    }
}

/**
 * Los object se pueden usar como singleton, por lo tanto podemos acceder a cada elemento de ellos desde Java
 * usando INSTANCE
 * De nuevo gracias con @JvmName podemos cambiar el nombre y exponerlos
 * https://kotlinlang.org/docs/java-to-kotlin-interop.html#static-fields
 *
 * Con @JvmStatic podemos exponer métodos estáticos
 */

object Singleton {
    const val nombre = "Pepe"
    const val edad = 23

    @JvmName("getDatos") // No es necesario, pero si queremos cambiar el nombre
    @JvmStatic
    fun getInfo(): String {
        return nombre + edad
    }
}

/**
 * Data class se ven como POJOs, por lo tanto se pueden usar desde Java sin problemas
 * pero la función copy no funciona igual y perdemos desestructuración
 */

data class DataClass(
    val nombre: String = "Pepe",
    val edad: Int = 23
)

/**
 * Sealed class se ven como interfaces o clases normales y no nos aportan ninguna
 * ventaja en Java con el swinch, aunque si podriamos usarlo si vamos a java 17 y usamos el switch
 * https://blogs.oracle.com/javamagazine/post/java-pattern-matching-switch-preview
 */

sealed class SealedClass {
    object Hijo1 : SealedClass()
    class Hijo2(var number: Int) : SealedClass()
}

/**
 * Funciones inline se pueden ejecutar desde java, pero no van a hacer el mismo efecto inline
 */

inline fun inlineFunction(n: Int, lambda: (Int) -> Int): Int {
    return lambda(n)
}

/**
 * Excepciones en Kotlin son siempre unchecked, para indicarle a Java que es una excepción comprobada/checked
 * debemos usar @Throws
 * https://kotlinlang.org/docs/java-to-kotlin-interop.html#checked-exceptions
 */

@Throws(IOException::class)
fun fileException(file: File) {
    if (!file.exists()) {
        throw IOException("El fichero no existe")
    }
}

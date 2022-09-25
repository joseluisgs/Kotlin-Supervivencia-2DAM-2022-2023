package `05-Genericos`

/**
 * Los genéricos nos permiten generalizar tipos de datos
 * a: un enfoque especial para describir datos y algoritmos
 * que permite trabajar con diferentes tipos de datos sin cambiar su descripción
 * https://kotlinlang.org/docs/generics.html
 * https://www.baeldung.com/kotlin/generics
 * Por defecto un generico T incluye al nulo, por lo que si no queremos que sea nulo
 * debemos especificar que no lo es con su extensión
 * Esta idea la podemos aplicar a cualquier tipo de datopara acotar el genérico
 */

 // Aqui P es nulable
data class Producto<P>(val nombre: String, val precio: P)
// Aqui P no es nulable
data class Producto2<P : Any>(val nombre: String, val precio: P)
// Aqui es de cualquier cosa que extienda de Number
data class Producto3<P : Number>(val nombre: String, val precio: P)

open class Persona(val nombre: String, val edad: Int) {
    override fun toString(): String {
        return "Persona(nombre='$nombre', edad=$edad)"
    }
}

class Alumno(nombre: String, edad: Int, val curso: String) : Persona(nombre, edad) {
    override fun toString(): String {
        return "Alumno(nombre='$nombre', edad=$edad, curso='$curso')"
    }
}

fun <T> recorrerLista(lista: List<T>) {
    for (elemento in lista) {
        println(elemento)
    }
}

fun main(args: Array<String>) {
    val listaProductos = listOf(Producto("Pan", 10.0), Producto("Leche", 20.0), Producto("Cereal", 30.0))
    val listaPersonas = listOf(Persona("Juan", 20), Persona("Pedro", 30), Persona("Maria", 40))
    val listaAlumnos = listOf(Alumno("Juan", 20, "1º"), Alumno("Pedro", 30, "2º"), Alumno("Maria", 40, "3º"))
    recorrerLista(listaProductos)
    recorrerLista(listaPersonas)
    recorrerLista(listaAlumnos)

    // Una persona es un alumno
    // Persona <- Alumno
    val persona: Persona = Alumno("Juan", 20, "1º")
    // Por lo tanto una lista de personas puede contener alumnos
    val listaPersonas2 = listOf(Persona("Juan", 20), Persona("Pedro", 30), Alumno("Maria", 40, "3º"))
    recorrerLista(listaPersonas2)
}
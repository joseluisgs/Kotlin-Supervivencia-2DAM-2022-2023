package `02-ProgOrientadaObjetos`

/*
En Kotlin los casting se hacen con as
Si no se puede hacer el casting, se lanza una excepción
O se puede devolver null con as?
Tambien existe el smart casting
Se comprueba con is
https://kotlinlang.org/docs/typecasts.html
 */

fun main(args: Array<String>) {
    val a: Any = "Hola"
    val b: String = a as String
    println(b)

    val c: Any = 1
    val d: String? = c as? String
    println(d)

    val e: Any = 1
    if (e is String) {
        println(e.length)
    }
}
package `05-Genericos`

/*
Varianza es la capacidad de un tipo de ser usado en un contexto donde se espera otro tipo.
Covarianza es la capacidad de un tipo de ser usado en un contexto donde se espera un tipo más general.
Contravarianza es la capacidad de un tipo de ser usado en un contexto donde se espera un tipo más específico.
Invarianza: un tipo no puede ser usado en un contexto donde se espera otro tipo.

Colecciones Mutables es invariante con respecto a su tipo genérico
(no podemos ni consumir ni producir/añadir valores con un tipo más genérico)
Colecciones Inmutables es covariante con respecto a su tipo genérico
(podemos consumir sus valores con un tipo más genérico)

¿Qué significa consumir (covarianza) y qué producir (contravarianza)?
Consumir implica tener funciones que devuelven un valor del tipo genérico
Producir implica tener funciones que reciben por argumento un objeto del tipo genérico
 */

fun main() {
    // Any <- Int
    // MutableList<Any> <- MutableList<Int>?????  NO
    // Kotlin no deja hacer esto,
    // es decir, no es seguro
    //val list: MutableList<Int> = mutableListOf(1, 2, 3)
    //val list2: MutableList<Any> = list
    //list2.add("Hello")
    // val i = list[3]
    // Pasa esto porque el genérico esta para consumir y para producir
    // Consumir: get o []
    // Producir: add o set

    // Probemos con esto ahora
    val list: List<Int> = listOf(1, 2, 3)
    val list2: List<Any> = list
    println(list2)
    // Ahora si deja, ¿por qué?
    // Porque List es inmutable, no se puede modificar
    // y por lo tanto no hay problema
    // No producimos!!! No hay add, set, etc

    // Si simplemente vamos a consumir datos (somos consumidores, o consumers),
    // entonces podemos relajar esa condición:
    val list3: List<Int> = listOf(1, 2, 3)
    val list4: List<Number> = list
    val obj: Number = list3[0] // Consumimos, porque le pedimos que nos devuleva un dato
    println(obj.toString())
    // si solo consumimos tenemos que
    // List<Any> <- List<Int>  OK (subtipo

    // sigamos
    // Tenemos esto
    // Number <- Int
    // EncrypterInvariante<Number> <- EncrypterInvariante<Int>  NO // Invaruiante, porque no aseguramos si consumimos o producimos
    // Es decir, no sabemos si va a estar en lso retunoes de las funciones o en los parametros de los métodos
    // O en ambos lados
    val e: EncrypterInvariante<Int> = EncrypterInvariante(10)
    // val e2: EncrypterInvariante<Number> = e // Por defecto es invariante

    // si sabemos que solo vamos a consumir, podemos permitirlo
    // el generico solo esta en los parámetros de las funciones
    // EncrypterCovariante<Number> <- EncrypterCovariante<Int>  OK // Covariante, porque solo consumimos
    val e3: EncrypterCovariante<Int> = EncrypterCovariante(10)
    val e4: EncrypterCovariante<Number> = e3

    // si sabemos que solo vamos a producir, podemos permitirlo
    // Es decir el genérico solo esta en las devuluciones de las funciones
    // EncrypterContravariante<Int> <- EncrypterContravariante<Out>  OK // Contravariante, porque solo producimos
    val e5: EncrypterContravariante<Number> = EncrypterContravariante()
    val e6: EncrypterContravariante<Int> = e5

    // Otro ejemplo de contravarianza


}

// Invariante: No sabemos si vamos a consumir o producir
class EncrypterInvariante<T>(val item: T)

// Covariante: Nosotros aseguramos que solo vamos a consumir con el generico
//  usamos out
class EncrypterCovariante<out T>(val item: T)

// Contravariante: Nosotros aseguramos que solo vamos a producir con el generico
// usamos in
class EncrypterContravariante<in T>() {
    fun <T> encrypt(): T {
        return 3 as T
    }
}

interface ComparableInvariante<T> {
    fun compare(other: T): Int
}

/**
 * Esto va a dar error, ya que no hemos definido el tipo de varianza, y por tanto el tipo aquí es invariante: si no indicamos la varianza,
 * no podemos convertir un Comparable<Number en un Comparable<Float>.
 */
fun testInvariante(comparable: ComparableInvariante<Number>) {
    //val comp: ComparableInvariante<Float> = comparable
}

interface ComparableContravariante<in T> {
    fun compare(other: T): Int
}

/**
 * Tiene sentido que puedas hacerlo, ya que si el Comparable permite comparar Numbers,
 * comparar con Floats es un subconjunto de lo que este tipo te deja hacer.
 */
fun testCotravariante(comparable: ComparableContravariante<Number>) {
    val comp: ComparableContravariante<Float> = comparable
}


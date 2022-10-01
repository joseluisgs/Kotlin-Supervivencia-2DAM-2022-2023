package `03-ProgFuncional`


/**
 * Las funciones de orden superior tienen ciertas penalizaciones de tiempo de ejecución:
 * cada función es un objeto y captura un cierre.
 * Un cierre es un ámbito de variables a las que se puede acceder en el cuerpo de la función.
 * Las asignaciones de memoria (tanto para objetos de función como para clases) y
 * las llamadas virtuales introducen una sobrecarga de tiempo de ejecución.
 *
 * https://kotlinlang.org/docs/inline-functions.html
 *
 * Con inline, pegamos el cuerpo de la lambda en el lugar donde se llama a la función
 * Crece el código, pero ahorramos memoria y tiempo de ejecución
 * Hay que buscar el equilibrio entre el tamaño del código y el tiempo de ejecución
 * No siempre interesa
 *
 * noinline: para que no se haga inline una función lambda o función de orden superior eligiendo
 * la que queremos que no se haga inline o no
 *
 * crossinline: para que no se haga inline una función lambda aunque se llame desde una inline
 * Con ello evitamos que se puedan hacer retunes, pues recuerda que el inline pega el código
 * https://www.baeldung.com/kotlin/crossinline-vs-noinline
 *
 *
 * Kotlin y Java borran la información de tipo genérico en el momento de la compilación o sea
 * todos los posibles formas de genércos se manfiestan como raw raw en tiempo de ejecución
 * Es decir List<Int> y  List<String> son solo List en tiempo de ejecución
 *
 *
 * Con refied podemos acceder a la información de tipo genérico en tiempo de ejecución
 */

fun <T> Collection<T>.filterNormal(predicado: (T) -> Boolean): Collection<T> {
    val resultado = mutableListOf<T>()
    for (i in this) {
        if (predicado(i)) {
            resultado.add(i)
        }
    }
    return resultado
}

inline fun <T> Collection<T>.filterInline(predicado: (T) -> Boolean): Collection<T> {
    val resultado = mutableListOf<T>()
    for (i in this) {
        if (predicado(i)) {
            resultado.add(i)
        }
    }
    return resultado
}

inline fun <reified T> Collection<T>.filterRefied(predicado: (T) -> Boolean): Collection<T> {
    val resultado = mutableListOf<T>()
    for (i in this) {
        if (predicado(i)) {
            resultado.add(i)
        }
    }
    return resultado
}


fun main() {
    val lista = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val resultado = lista.filterNormal { it % 2 == 0 }
    println(resultado)
    println()
    val resultado2 = lista.filterInline { it % 2 == 0 }
    println(resultado2)
    println()
    val resultado3 = lista.filterRefied { it % 2 == 0 }
    println(resultado3)
    println()
}
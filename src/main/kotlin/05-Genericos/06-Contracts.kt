package `05-Genericos`

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Le da información al compilador sobre los efectos de una función
 * https://www.baeldung.com/kotlin/contracts
 * function {
contract {
Effect
}
}
 *
 * Podemos chequear garantias sobre un valor de retorno
 *  Garantizar el número de veces que podemos llamar a una función lambda
 *
 */

// Va ser un string
@OptIn(ExperimentalContracts::class)
fun stringReverse(cadena: String?): String {
    // Si se devuelve el valor, sabemos que no es null
    contract { returns() implies (cadena != null) }
    return cadena?.reversed() ?: "Estoy vacío"
}

@OptIn(ExperimentalContracts::class)
inline fun <R> myRun(block: () -> R): R {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}

fun main() {
    val name: String? = null
    val reversedName = stringReverse(name)

    myRun {
        println("Hello")
    }
}
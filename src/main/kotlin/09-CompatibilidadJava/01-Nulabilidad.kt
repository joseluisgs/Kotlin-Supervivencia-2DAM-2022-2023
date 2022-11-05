package `09-CompatibilidadJava`

import mijava.MiClase

/**
 * Recuerda que en Kotlin tenemos tipos Nulos y No Nulos
 * Es por ello que si traemos una clase de Java, debemos
 * indicar que es Nula o No Nula, porque no la marca como Tipo Plataforma
 * Ver tema 01-07-NullSafety.kt
 * Lo mejor es que desde el código de Java usar las anotaciones @Nullable y @NotNull
 */

fun main() {
    val clase = MiClase()
    val mensaje = clase.stringNotNull // String !
    val mensaje2 = clase.stringNullable // String !
    val mensaje3 = clase.stringNotNullNotation // String
    val mensaje4 = clase.stringNullableWithNotation// String?

}
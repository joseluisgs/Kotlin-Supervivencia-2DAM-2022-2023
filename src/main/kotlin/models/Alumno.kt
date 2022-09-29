package models

import org.jetbrains.kotlinx.dataframe.annotations.DataSchema

// Algunos modelos usados para la generacioón de cogo que dan errores en la compilación
// o generación de código por el nombre de paquetes
@DataSchema
data class Alumno(
    val nombre: String,
    val nota: Double,
    val curso: String
)
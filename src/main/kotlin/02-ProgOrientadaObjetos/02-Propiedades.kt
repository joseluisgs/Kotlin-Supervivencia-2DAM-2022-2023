package `02-ProgOrientadaObjetos`

/**
 * En Kotlin, las propieades puede ser declaradas con var o val (var = variable, val = valor)
 * Si es var, tenemos el getter y el setter
 * Si es val, solo tenemos el getter
 * Por defecto son públicas, si no queremos que se acceda a ellas, podemos declararlas como privadas
 * Se pueden personalizar getter y setter
 * https://kotlinlang.org/docs/properties.html
 */

class Coche(var marca: String = "", val año: Int = 2022) {
    // Con la la propiedad string podemos personalizar el getter y el setter, solo uno o ambos
    // https://kotlinlang.org/docs/properties.html#getters-and-setters
    var color: String = ""
        get() = field.uppercase()
        set(value) {
            field = value.lowercase()
        }

    // Tambien podemos usar backing fields para personalizar el getter y el setter
    // importante es field, para referernos al campo actual
    // https://kotlinlang.org/docs/properties.html#backing-fields
    var contador = 0 // the initializer assigns the backing field directly
        set(value) {
            if (value >= 0)
                field = value
        }

    // Propiedades calculadas
    val isViejo: Boolean
        get() = this.año < 2000

    // Tambien puedo usar backing properties
    // https://kotlinlang.org/docs/properties.html#backing-properties
    // Por ejemplo, si quiero tener algo interno que es mutable porque voy a operar con ello,
    // pero para el exterior es inmutable
    private var _lista = mutableListOf<String>()
    val lista: List<String>
        get() = _lista
}

// Muy importante saber como y cuando inicializar las propiedades
/*
Normalmente, las propiedades declaradas con un tipo no nulo deben inicializarse en el constructor.
Sin embargo, a menudo sucede que hacerlo no es conveniente. Por ejemplo, las propiedades se pueden
inicializar a través de la inyección de dependencia o en el método de configuración de una prueba unitaria.
En estos casos, no puede proporcionar un inicializador no nulo en el constructor,
pero aún desea evitar las comprobaciones nulas al hacer referencia a la propiedad dentro del cuerpo de una clase.
Con la palabra clave lateinit, puede indicar que la propiedad se inicializará más tarde.
No puede usar lateinit para propiedades locales, solo para propiedades de clase.
Nos debemos asegurar que se va a inicializar antes de usarla, si no Excepción
Si usas lateinit es que te has comprometido
Siempre se usa en variables mutables: var, no se puede usar en val
 */

class Motocicleta(val marca: String) {
    // Si no inicializo la propiedad, tengo que ponerle un signo de interrogación
    // para que sepa que puede ser nulo
    // var color: String // Esto no puedo hacerlo, no puedo dejar algo sin asignar
    lateinit var color: String // no se puede usar en val, solo en var, obviamente la tenemos que modificar al inicializarse

    // Prueba a comentar esto y ejecutar
    init {
        println("Se ha creado una motocicleta de la marca $marca")
        color = listOf("rojo", "azul", "verde").random()
    }

    fun inicializarColor(color: String) {
        this.color = color
    }
}

fun main() {
    val coche = Coche("Renault", 2021)
    coche.color = "Rojo"
    println(coche.color)
    coche.contador = 10
    println(coche.contador)
    coche.contador = -10
    println(coche.contador)
    println(coche.isViejo)
    // coche._lista.add("Hola") // error
    println(coche.lista)

    val moto = Motocicleta("Yamaha")
    println(moto.color) // Qué pasa??? //
    moto.inicializarColor("Rojo")
    println(moto.color)

}
package `07-Intercambio`

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import nl.adaptivity.xmlutil.serialization.XML
import nl.adaptivity.xmlutil.serialization.XmlElement
import java.io.File
import java.util.*


@Serializable
@SerialName("personas") // Si queremos cambiar el nombre de la etiqueta
data class Person(
    val id: String = UUID.randomUUID().toString(),
    @XmlElement(true)
    val name: String,
    @XmlElement(true)
    val age: Int,
    @XmlElement(true) // Formzamos a que sea un elemento
    val email: String? = null
)

@Serializable
@SerialName("equipo")
data class Team(
    val id: String = UUID.randomUUID().toString(),
    @XmlElement(true) // Es obligatorio que aparezca
    val members: List<Person>,
    val colors: List<String> = listOf()
)

fun main() {
    val t = Team(
        members = listOf(
            Person(name = "Joe", age = 15),
            Person(name = "Jane", age = 15, email = "pepe@pepe.com")
        ),
        colors = listOf("red", "blue")
    )
    val xml = XML { indentString = "  " }
    val fichero = File(System.getProperty("user.dir") + File.separator + "data" + File.separator + "intercambio.xml")
    // println("XML salida:\n${encodedString.prependIndent("    ")}\n")
    fichero.writeText(xml.encodeToString(t))

    // Leemos
    val t2 = XML.decodeFromString<Team>(fichero.readText())
    println(t2)
}
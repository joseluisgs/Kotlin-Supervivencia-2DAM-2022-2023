package `07-Intercambio`

import mu.KotlinLogging
import org.w3c.dom.DOMException
import org.w3c.dom.Element
import org.xml.sax.SAXException
import java.io.IOException
import java.util.concurrent.Executors
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import kotlin.system.measureTimeMillis

data class Noticia(
    var titulo: String = "",
    var link: String = "",
    var descripcion: String = "",
    var autor: String = "",
    var contenido: String = "",
    var fecha: String = "",
    var imagen: String = ""
)

private val logger = KotlinLogging.logger {}

fun getNoticias(uri: String): MutableList<Noticia> {
    // Parser de XMl, recorremos el DOM
    val factory = DocumentBuilderFactory.newInstance()
    // Lista de noticias, mutable
    val noticias = mutableListOf<Noticia>()

    try {
        // Filtramos por elementos del RSS
        val builder = factory.newDocumentBuilder()
        val document = builder.parse(uri)
        val items = document.getElementsByTagName("item")

        // Recorremos los elementos
        for (i in 0 until items.length) {
            val nodo = items.item(i)
            val noticia = Noticia()
            // Vamos a contar las imagenes que hay
            var contadorImagenes = 0
            var n = nodo.firstChild
            while (n != null) {
                when (n.nodeName) {
                    "title" -> {
                        noticia.titulo = n.textContent
                    }

                    "link" -> {
                        noticia.link = n.textContent
                    }

                    "description" -> {
                        noticia.descripcion = n.textContent
                    }

                    "pubDate" -> {
                        noticia.fecha = n.textContent
                    }

                    "dc:creator" -> {
                        noticia.autor = n.textContent
                    }

                    "content:encoded" -> {
                        noticia.contenido = n.textContent
                    }

                    "enclosure" -> {
                        val imagen: String = (n as Element).getAttribute("url")
                        //Controlamos que solo rescate una imagen
                        if (contadorImagenes == 0) {
                            noticia.imagen = imagen
                        }
                        contadorImagenes++
                    }
                }
                // Vamos al siguiente nodo
                n = n.nextSibling
            }
            noticias.add(noticia)
        }
        // Log.d("Noticias", "Noticias Controller tam: " + noticias.size.toString())
        return noticias
    } catch (e: ParserConfigurationException) {
        logger.debug("Noticias", "Error: " + e.message)
    } catch (e: IOException) {
        logger.debug("Noticias", "Error: " + e.message)
    } catch (e: DOMException) {
        logger.debug("Noticias", "Error: " + e.message)
    } catch (e: SAXException) {
        logger.debug("Noticias", "Error: " + e.message)
    }
    return noticias
}

fun main() {
    val executorService = Executors.newSingleThreadExecutor()

    measureTimeMillis {
        println("Obteniendo noticias")
        val future = executorService.submit<List<Noticia>> {
            getNoticias("https://elpais.com/rss/elpais/portada.xml")
        }

        while (!future.isDone) {
            println("Tarea no terminada...")
            Thread.sleep(500)
        }
        val noticias = future.get()
        executorService.shutdown()
        println("Noticias obtenidas: ${noticias.size}")

        noticias?.forEachIndexed { index, noticia ->
            println("Nº ${index + 1}: $noticia")
        }
    }.also { println("Tiempo de ejecución: $it ms") }
}

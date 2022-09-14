package `03-ProgFuncional`

/**
 * DSL para crera un lenguaje orientado al dominio y
 * type safe builders
 */

open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()
    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        // Here is the code that adds the child to the list of children
        child.init()
        children.add(child)
    }

    override fun toString() =
        "<$name>${children.joinToString("")}</$name>"
}

fun table(init: TABLE.() -> Unit): TABLE = TABLE().apply(init)
class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)
}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")

fun createTable() =
    table {
        tr {
            for (i in 1..2) {
                td { }
            }
        }
    }

fun main() {
    println(createTable())
//<table><tr><td></td><td></td></tr></table>
}
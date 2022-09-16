package `03-ProgFuncional`

/*
Usamos un lambda con reciver porque
De esta manera conseguimos que en el cuerpo de la lambda
podamos acceder a las propiedades y métodos del objeto receptor.
*/

data class Course(val name: String, val students: List<Student>)
data class Student(val name: String)

// CourseBuilder es un builder para Course
// usa la funcion studentpara añaadir estudiantes
// usa la funcion build para devolver el objeto Course
class CourseBuilder(val name: String) {
    private val students = mutableListOf<Student>()
    fun student(name: String) = students.add(Student(name))
    fun build() = Course(name, students)
}

// buildCourse es una funcion que recibe un CourseBuilder
// toma el CourseBuilder como Lambda con receptor
fun buildCourse(name: String, init: CourseBuilder.() -> Unit): Course {
    return CourseBuilder(name).apply { init() }.build()
}

// Llama a buildCurse, pasando los la lambda con receptor
// la lambda es llamada con la instancia de CourseBuilder
// usamos la funcion student para añadir estudiantes a CourseBuilder
fun kotlinCourse() = buildCourse("Kotlin") {
    student("Alice")
    student("Bob")
    for (i in 1..2) {
        student("Anonymous #$i")
    }
}

fun main() {
    println(kotlinCourse())
}
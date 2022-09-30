import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    // Para generar modelos de DataFrames
    id("org.jetbrains.kotlinx.dataframe") version "0.8.1"
    // para serializar Json y otros
    kotlin("plugin.serialization") version "1.7.10"
    application
}

group = "es.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Relfexion
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.10")
    // DataFrames de Kotlin Jetbrains
    implementation("org.jetbrains.kotlinx:dataframe:0.8.1")
    // Para hacer logs
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
    implementation("ch.qos.logback:logback-classic:1.4.1")
    // LetsPlot
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin:4.0.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.4.0")
    // Serializadores de Kotlin
    // https://github.com/Kotlin/kotlinx.serialization/blob/master/formats/README.md
    // Serializa Json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    // Serializa a XML con Serialization  para jvm
    // https://github.com/pdvrieze/xmlutil
    implementation("io.github.pdvrieze.xmlutil:core-jvm:0.84.3")
    implementation("io.github.pdvrieze.xmlutil:serialization-jvm:0.84.3")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

// Data Schema generator
// Make IDE aware of the generated code:
kotlin.sourceSets.getByName("main").kotlin.srcDir("build/generated/ksp/main/kotlin/")
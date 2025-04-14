val fastUtil: String by project

plugins {
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

dependencies {
    implementation(project(":protocol"))
    implementation(project(":api"))

    implementation("org.jline:jline:3.29.0")
    implementation("org.tinylog:tinylog-impl:2.8.0-M1")
    implementation("org.tinylog:slf4j-tinylog:2.8.0-M1")

    implementation(project(":plugins:debug"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ink.reactor.server.ReactorMain"
    }
}
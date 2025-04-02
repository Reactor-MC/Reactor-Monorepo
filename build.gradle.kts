plugins {
    java
    id("io.freefair.lombok") version "8.13.1" apply false
}

subprojects {
    plugins.apply("java")
    plugins.apply("io.freefair.lombok")

    group = "ink.reactor.$name"
    version = "0.0.1"

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

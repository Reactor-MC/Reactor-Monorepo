plugins {
    id("java")
}

group = "ink.reactor"
version = "1.0.0"

subprojects {
    apply(plugin = "java")

    repositories {
        mavenCentral()
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.36")
        annotationProcessor("org.projectlombok:lombok:1.18.36")

        testCompileOnly("org.projectlombok:lombok:1.18.36")
        testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

        testImplementation(platform("org.junit:junit-bom:5.10.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks.test {
        useJUnitPlatform()
    }
}

allprojects {
    apply<JavaPlugin>()

    tasks.withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(23)
    }

    configure<JavaPluginExtension> {
        toolchain.languageVersion.set(JavaLanguageVersion.of(23))
    }
}
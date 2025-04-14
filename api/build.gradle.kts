plugins {
    id("java-library")
}

val fastJson: String by project
val fastUtil: String by project

dependencies {
    api(fastJson)
    api(fastUtil)

    api("org.yaml:snakeyaml:2.4")
    api("org.tinylog:tinylog-api:2.8.0-M1")

    api(project(":libs:util"))
    api(project(":libs:buffer"))
    api(project(":libs:nbt"))
    api(project(":libs:chat"))
    api(project(":libs:zlib"))
    api(project(":libs:entity"))
    api(project(":libs:item"))
    api(project(":libs:world"))
    api(project(":libs:command"))
}
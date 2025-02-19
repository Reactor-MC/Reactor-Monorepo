val fastUtil: String by project

plugins {
    id("java-library")
}

dependencies {
    api("io.netty:netty-all:4.2.0.RC3")
    compileOnly(project(":api"))
    compileOnly(fastUtil)
}
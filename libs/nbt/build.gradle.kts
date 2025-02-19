val fastUtil: String by project

dependencies {
    compileOnly(project(":libs:util"))
    compileOnly(fastUtil)
    testImplementation(fastUtil)
    testImplementation(project(":libs:util"))
}
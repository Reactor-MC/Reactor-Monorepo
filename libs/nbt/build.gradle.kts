val fastUtil: String by project

dependencies {
    compileOnly(project(":libs:util"))
    compileOnly(project(":libs:buffer"))
    compileOnly(fastUtil)
    testImplementation(fastUtil)
    testImplementation(project(":libs:util"))
    testImplementation(project(":libs:buffer"))
}
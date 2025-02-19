val fastUtil: String by project

dependencies {
    val utils = project(":libs:util")

    compileOnly(fastUtil)
}
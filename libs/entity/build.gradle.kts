val fastUtil: String by project

dependencies {
    val utils = project(":libs:util")
    val items = project(":libs:item")

    compileOnly(fastUtil)
    compileOnly(utils)
    compileOnly(items)
}
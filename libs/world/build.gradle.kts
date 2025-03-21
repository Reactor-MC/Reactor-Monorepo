val fastUtil: String by project

dependencies {
    val utils = project(":libs:util")
    val nbt = project(":libs:nbt")

    compileOnly(utils)
    compileOnly(nbt)
    compileOnly(fastUtil)

    testImplementation(fastUtil)
    testImplementation(utils)
    testImplementation(nbt)
}
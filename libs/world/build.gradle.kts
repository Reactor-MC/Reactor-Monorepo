val fastUtil: String by project

dependencies {
    val buffer = project(":libs:buffer")
    val nbt = project(":libs:nbt")
    val util = project(":libs:util")

    compileOnly(buffer)
    compileOnly(nbt)
    compileOnly(fastUtil)
    compileOnly(util)

    testImplementation(fastUtil)
    testImplementation(buffer)
    testImplementation(nbt)
    testImplementation(util)
}
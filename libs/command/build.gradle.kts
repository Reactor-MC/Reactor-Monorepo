val fastUtil: String by project

dependencies {
    val utils = project(":libs:util")
    val chat = project(":libs:chat")

    compileOnly(utils)
    compileOnly(chat)
    compileOnly(fastUtil)

    testImplementation(fastUtil)
    testImplementation(utils)
}
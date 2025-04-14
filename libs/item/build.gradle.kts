val fastUtil: String by project

dependencies {
    val utils = project(":libs:util")
    val nbt = project(":libs:nbt")
    val chat = project(":libs:chat")
    val buffer = project(":libs:buffer")

    compileOnly(utils)
    compileOnly(nbt)
    compileOnly(chat)
    compileOnly(buffer)
    compileOnly(fastUtil)
}
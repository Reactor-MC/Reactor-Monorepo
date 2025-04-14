rootProject.name = "reactor"

val libs = arrayOf("util", "nbt", "chat", "item", "entity", "world", "zlib", "buffer", "command")
for (lib in libs) {
    include("libs:$lib")
}

include("api")
include("protocol")
include("server")
include("data-parser")

val plugins = arrayOf("debug", "atom-chat")
for (plugin in plugins) {
    include("plugins:$plugin")
}
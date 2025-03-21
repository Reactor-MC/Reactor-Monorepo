rootProject.name = "reactor"

val libs = arrayOf("util", "nbt", "chat", "item", "entity", "world", "zlib", "command")
for (lib in libs) {
    include("libs:$lib")
}

include("api")
include("protocol")
include("server")
include("data-parser")

val features = arrayOf("debug", "atom-chat")
for (feature in features) {
    include("features:$feature")
}

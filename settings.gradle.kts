rootProject.name = "reactor"

val libs = arrayOf("util", "nbt", "chat", "item", "entity", "zlib")
for (lib in libs) {
    include("libs:$lib")
}

include("api")
include("protocol")
include("server")

val features = arrayOf("debug")
for (feature in features) {
    include("features:$feature")
}

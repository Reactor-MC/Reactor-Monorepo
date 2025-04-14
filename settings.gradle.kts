rootProject.name = "reactor"

include("api")
include("protocol")
include("server")
include("data-parser")

file("libs").listFiles()?.forEach { libDir ->
    if (libDir.isDirectory) {
        include("libs:${libDir.name}")
    }
}

file("plugins").listFiles()?.forEach { pluginDir ->
    if (pluginDir.isDirectory) {
        include("plugins:${pluginDir.name}")
    }
}
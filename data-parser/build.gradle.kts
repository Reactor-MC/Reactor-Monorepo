val fastJson: String by project

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(fastJson)
    implementation("com.github.Reactor-Minecraft:Fission:1.0.6")
}
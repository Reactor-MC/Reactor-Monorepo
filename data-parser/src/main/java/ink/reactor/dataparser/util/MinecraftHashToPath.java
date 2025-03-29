package ink.reactor.dataparser.util;

import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Util for transform minecraft hash to folder location.
 * Minecraft hash: ~/.minecraft/assets/indexes
 * 
 * Example: {"minecraft/lang/en_PT.lang": {"hash": "64f5643da27da4218b87fae8352cbd65e98a6494", "size": 90201}
 * Location: ~/.minecraft/assets/objects/64/64f5643da27da4218b87fae8352cbd65e98a6494
 */
public final class MinecraftHashToPath {

    public static void main(String[] args) {
        final String hash = "054ce6faf33806879f0f1f38204ee9eb080388ec"; // Example of sounds.json in 1.21.4

        final Path filePath = getMinecraftAssetPath(hash);

        System.out.println("File in: " + filePath);
    }

    public static Path getMinecraftAssetPath(final String hash) {
        if (hash == null || hash.length() < 2) {
            throw new IllegalArgumentException("The hash need be minimum 2 of length.");
        }
        final String subDir = hash.substring(0, 2);

        final String basePath = "~/.minecraft/assets/objects";

        return Paths.get(basePath, subDir, hash);
    }
}

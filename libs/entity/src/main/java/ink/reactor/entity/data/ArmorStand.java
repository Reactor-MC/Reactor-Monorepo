package ink.reactor.entity.data;

import ink.reactor.entity.data.adapter.LivingMetadata;
import ink.reactor.entity.data.adapter.MinecraftEntityMetadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ArmorStand implements LivingMetadata, MinecraftEntityMetadata {
    private final Living living = new Living();
    private final MinecraftEntity minecraftEntity = new MinecraftEntity();

    private boolean small, hasArms, basePlate;
    private BodyPart head, body, leftArm, rightArm;

    @Getter
    @Setter
    public static final class BodyPart {
        private int rotationX, rotationY, rotationZ;
        private Object item;
    }
}
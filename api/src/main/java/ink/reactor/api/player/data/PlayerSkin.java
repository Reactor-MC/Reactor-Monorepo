package ink.reactor.api.player.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public final class PlayerSkin {

    private String signature, textures;

    private boolean 
        cape, jacket,
        leftSleeve, rightSleeve,
        leftPantsLeg, rightPantsLeg,
        hat;

    public void fromBitmask(final int bitmask) {
        this.cape = (bitmask & 0x01) != 0;
        this.jacket = (bitmask & 0x02) != 0;
        this.leftSleeve = (bitmask & 0x04) != 0;
        this.rightSleeve = (bitmask & 0x08) != 0;
        this.leftPantsLeg = (bitmask & 0x10) != 0;
        this.rightSleeve = (bitmask & 0x20) != 0;
        this.hat = (bitmask & 0x40) != 0;
    }

    public byte toBitmask() {
        byte bitmask = 0;

        if (cape) bitmask |= 0x01;
        if (jacket) bitmask |= 0x02;
        if (leftSleeve) bitmask |= 0x04;
        if (rightSleeve) bitmask |= 0x08;
        if (leftPantsLeg) bitmask |= 0x10;
        if (rightPantsLeg) bitmask |= 0x20;
        if (hat) bitmask |= 0x40;

        return bitmask;
    }
}
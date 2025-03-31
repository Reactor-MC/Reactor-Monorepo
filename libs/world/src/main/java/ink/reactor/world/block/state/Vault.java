package ink.reactor.world.block.state;

public final class Vault {
    public static int of(final char id, final BlockFacing facing, final boolean ominous, final BlockVaultState vaultState) {
        final String sentence = facing+"-"+ominous+"-"+vaultState;
        return switch(sentence) {
            case "NORTH-true-INACTIVE" -> id-4;
            case "NORTH-true-ACTIVE" -> id-3;
            case "NORTH-true-UNLOCKING" -> id-2;
            case "NORTH-true-EJECTING" -> id-1;
            case "NORTH-false-ACTIVE" -> id+1;
            case "NORTH-false-UNLOCKING" -> id+2;
            case "NORTH-false-EJECTING" -> id+3;
            case "SOUTH-true-INACTIVE" -> id+4;
            case "SOUTH-true-ACTIVE" -> id+5;
            case "SOUTH-true-UNLOCKING" -> id+6;
            case "SOUTH-true-EJECTING" -> id+7;
            case "SOUTH-false-INACTIVE" -> id+8;
            case "SOUTH-false-ACTIVE" -> id+9;
            case "SOUTH-false-UNLOCKING" -> id+10;
            case "SOUTH-false-EJECTING" -> id+11;
            case "WEST-true-INACTIVE" -> id+12;
            case "WEST-true-ACTIVE" -> id+13;
            case "WEST-true-UNLOCKING" -> id+14;
            case "WEST-true-EJECTING" -> id+15;
            case "WEST-false-INACTIVE" -> id+16;
            case "WEST-false-ACTIVE" -> id+17;
            case "WEST-false-UNLOCKING" -> id+18;
            case "WEST-false-EJECTING" -> id+19;
            case "EAST-true-INACTIVE" -> id+20;
            case "EAST-true-ACTIVE" -> id+21;
            case "EAST-true-UNLOCKING" -> id+22;
            case "EAST-true-EJECTING" -> id+23;
            case "EAST-false-INACTIVE" -> id+24;
            case "EAST-false-ACTIVE" -> id+25;
            case "EAST-false-UNLOCKING" -> id+26;
            case "EAST-false-EJECTING" -> id+27;
            default -> id;
        };
    }
}
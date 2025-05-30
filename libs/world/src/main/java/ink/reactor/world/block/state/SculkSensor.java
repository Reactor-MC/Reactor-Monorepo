package ink.reactor.world.block.state;

public final class SculkSensor {
    public static int of(final char id, final long power, final BlockSculkSensorPhase sculkSensorPhase, final boolean waterlogged) {
        final String sentence = power+"-"+sculkSensorPhase+"-"+waterlogged;
        return switch(sentence) {
            case "0-INACTIVE-true" -> id-1;
            case "0-ACTIVE-true" -> id+1;
            case "0-ACTIVE-false" -> id+2;
            case "0-COOLDOWN-true" -> id+3;
            case "0-COOLDOWN-false" -> id+4;
            case "1-INACTIVE-true" -> id+5;
            case "1-INACTIVE-false" -> id+6;
            case "1-ACTIVE-true" -> id+7;
            case "1-ACTIVE-false" -> id+8;
            case "1-COOLDOWN-true" -> id+9;
            case "1-COOLDOWN-false" -> id+10;
            case "2-INACTIVE-true" -> id+11;
            case "2-INACTIVE-false" -> id+12;
            case "2-ACTIVE-true" -> id+13;
            case "2-ACTIVE-false" -> id+14;
            case "2-COOLDOWN-true" -> id+15;
            case "2-COOLDOWN-false" -> id+16;
            case "3-INACTIVE-true" -> id+17;
            case "3-INACTIVE-false" -> id+18;
            case "3-ACTIVE-true" -> id+19;
            case "3-ACTIVE-false" -> id+20;
            case "3-COOLDOWN-true" -> id+21;
            case "3-COOLDOWN-false" -> id+22;
            case "4-INACTIVE-true" -> id+23;
            case "4-INACTIVE-false" -> id+24;
            case "4-ACTIVE-true" -> id+25;
            case "4-ACTIVE-false" -> id+26;
            case "4-COOLDOWN-true" -> id+27;
            case "4-COOLDOWN-false" -> id+28;
            case "5-INACTIVE-true" -> id+29;
            case "5-INACTIVE-false" -> id+30;
            case "5-ACTIVE-true" -> id+31;
            case "5-ACTIVE-false" -> id+32;
            case "5-COOLDOWN-true" -> id+33;
            case "5-COOLDOWN-false" -> id+34;
            case "6-INACTIVE-true" -> id+35;
            case "6-INACTIVE-false" -> id+36;
            case "6-ACTIVE-true" -> id+37;
            case "6-ACTIVE-false" -> id+38;
            case "6-COOLDOWN-true" -> id+39;
            case "6-COOLDOWN-false" -> id+40;
            case "7-INACTIVE-true" -> id+41;
            case "7-INACTIVE-false" -> id+42;
            case "7-ACTIVE-true" -> id+43;
            case "7-ACTIVE-false" -> id+44;
            case "7-COOLDOWN-true" -> id+45;
            case "7-COOLDOWN-false" -> id+46;
            case "8-INACTIVE-true" -> id+47;
            case "8-INACTIVE-false" -> id+48;
            case "8-ACTIVE-true" -> id+49;
            case "8-ACTIVE-false" -> id+50;
            case "8-COOLDOWN-true" -> id+51;
            case "8-COOLDOWN-false" -> id+52;
            case "9-INACTIVE-true" -> id+53;
            case "9-INACTIVE-false" -> id+54;
            case "9-ACTIVE-true" -> id+55;
            case "9-ACTIVE-false" -> id+56;
            case "9-COOLDOWN-true" -> id+57;
            case "9-COOLDOWN-false" -> id+58;
            case "10-INACTIVE-true" -> id+59;
            case "10-INACTIVE-false" -> id+60;
            case "10-ACTIVE-true" -> id+61;
            case "10-ACTIVE-false" -> id+62;
            case "10-COOLDOWN-true" -> id+63;
            case "10-COOLDOWN-false" -> id+64;
            case "11-INACTIVE-true" -> id+65;
            case "11-INACTIVE-false" -> id+66;
            case "11-ACTIVE-true" -> id+67;
            case "11-ACTIVE-false" -> id+68;
            case "11-COOLDOWN-true" -> id+69;
            case "11-COOLDOWN-false" -> id+70;
            case "12-INACTIVE-true" -> id+71;
            case "12-INACTIVE-false" -> id+72;
            case "12-ACTIVE-true" -> id+73;
            case "12-ACTIVE-false" -> id+74;
            case "12-COOLDOWN-true" -> id+75;
            case "12-COOLDOWN-false" -> id+76;
            case "13-INACTIVE-true" -> id+77;
            case "13-INACTIVE-false" -> id+78;
            case "13-ACTIVE-true" -> id+79;
            case "13-ACTIVE-false" -> id+80;
            case "13-COOLDOWN-true" -> id+81;
            case "13-COOLDOWN-false" -> id+82;
            case "14-INACTIVE-true" -> id+83;
            case "14-INACTIVE-false" -> id+84;
            case "14-ACTIVE-true" -> id+85;
            case "14-ACTIVE-false" -> id+86;
            case "14-COOLDOWN-true" -> id+87;
            case "14-COOLDOWN-false" -> id+88;
            case "15-INACTIVE-true" -> id+89;
            case "15-INACTIVE-false" -> id+90;
            case "15-ACTIVE-true" -> id+91;
            case "15-ACTIVE-false" -> id+92;
            case "15-COOLDOWN-true" -> id+93;
            case "15-COOLDOWN-false" -> id+94;
            default -> id;
        };
    }
}
package ink.reactor.world.block.state;

public final class CaveVines {
    public static int of(final char id, final long age, final boolean berries) {
        final String sentence = age+"-"+berries;
        return switch(sentence) {
            case "0-true" -> id-1;
            case "1-true" -> id+1;
            case "1-false" -> id+2;
            case "2-true" -> id+3;
            case "2-false" -> id+4;
            case "3-true" -> id+5;
            case "3-false" -> id+6;
            case "4-true" -> id+7;
            case "4-false" -> id+8;
            case "5-true" -> id+9;
            case "5-false" -> id+10;
            case "6-true" -> id+11;
            case "6-false" -> id+12;
            case "7-true" -> id+13;
            case "7-false" -> id+14;
            case "8-true" -> id+15;
            case "8-false" -> id+16;
            case "9-true" -> id+17;
            case "9-false" -> id+18;
            case "10-true" -> id+19;
            case "10-false" -> id+20;
            case "11-true" -> id+21;
            case "11-false" -> id+22;
            case "12-true" -> id+23;
            case "12-false" -> id+24;
            case "13-true" -> id+25;
            case "13-false" -> id+26;
            case "14-true" -> id+27;
            case "14-false" -> id+28;
            case "15-true" -> id+29;
            case "15-false" -> id+30;
            case "16-true" -> id+31;
            case "16-false" -> id+32;
            case "17-true" -> id+33;
            case "17-false" -> id+34;
            case "18-true" -> id+35;
            case "18-false" -> id+36;
            case "19-true" -> id+37;
            case "19-false" -> id+38;
            case "20-true" -> id+39;
            case "20-false" -> id+40;
            case "21-true" -> id+41;
            case "21-false" -> id+42;
            case "22-true" -> id+43;
            case "22-false" -> id+44;
            case "23-true" -> id+45;
            case "23-false" -> id+46;
            case "24-true" -> id+47;
            case "24-false" -> id+48;
            case "25-true" -> id+49;
            case "25-false" -> id+50;
            default -> id;
        };
    }
}
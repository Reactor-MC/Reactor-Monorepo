package ink.reactor.world.block.state;

public final class ChiseledBookShelf {
    public static int of(final char id, final BlockFacing facing, final boolean slot0Occupied, final boolean slot1Occupied, final boolean slot2Occupied, final boolean slot3Occupied, final boolean slot4Occupied, final boolean slot5Occupied) {
        final String sentence = facing+"-"+slot0Occupied+"-"+slot1Occupied+"-"+slot2Occupied+"-"+slot3Occupied+"-"+slot4Occupied+"-"+slot5Occupied;
        return switch(sentence) {
            case "NORTH-true-true-true-true-true-true" -> id-63;
            case "NORTH-true-true-true-true-true-false" -> id-62;
            case "NORTH-true-true-true-true-false-true" -> id-61;
            case "NORTH-true-true-true-true-false-false" -> id-60;
            case "NORTH-true-true-true-false-true-true" -> id-59;
            case "NORTH-true-true-true-false-true-false" -> id-58;
            case "NORTH-true-true-true-false-false-true" -> id-57;
            case "NORTH-true-true-true-false-false-false" -> id-56;
            case "NORTH-true-true-false-true-true-true" -> id-55;
            case "NORTH-true-true-false-true-true-false" -> id-54;
            case "NORTH-true-true-false-true-false-true" -> id-53;
            case "NORTH-true-true-false-true-false-false" -> id-52;
            case "NORTH-true-true-false-false-true-true" -> id-51;
            case "NORTH-true-true-false-false-true-false" -> id-50;
            case "NORTH-true-true-false-false-false-true" -> id-49;
            case "NORTH-true-true-false-false-false-false" -> id-48;
            case "NORTH-true-false-true-true-true-true" -> id-47;
            case "NORTH-true-false-true-true-true-false" -> id-46;
            case "NORTH-true-false-true-true-false-true" -> id-45;
            case "NORTH-true-false-true-true-false-false" -> id-44;
            case "NORTH-true-false-true-false-true-true" -> id-43;
            case "NORTH-true-false-true-false-true-false" -> id-42;
            case "NORTH-true-false-true-false-false-true" -> id-41;
            case "NORTH-true-false-true-false-false-false" -> id-40;
            case "NORTH-true-false-false-true-true-true" -> id-39;
            case "NORTH-true-false-false-true-true-false" -> id-38;
            case "NORTH-true-false-false-true-false-true" -> id-37;
            case "NORTH-true-false-false-true-false-false" -> id-36;
            case "NORTH-true-false-false-false-true-true" -> id-35;
            case "NORTH-true-false-false-false-true-false" -> id-34;
            case "NORTH-true-false-false-false-false-true" -> id-33;
            case "NORTH-true-false-false-false-false-false" -> id-32;
            case "NORTH-false-true-true-true-true-true" -> id-31;
            case "NORTH-false-true-true-true-true-false" -> id-30;
            case "NORTH-false-true-true-true-false-true" -> id-29;
            case "NORTH-false-true-true-true-false-false" -> id-28;
            case "NORTH-false-true-true-false-true-true" -> id-27;
            case "NORTH-false-true-true-false-true-false" -> id-26;
            case "NORTH-false-true-true-false-false-true" -> id-25;
            case "NORTH-false-true-true-false-false-false" -> id-24;
            case "NORTH-false-true-false-true-true-true" -> id-23;
            case "NORTH-false-true-false-true-true-false" -> id-22;
            case "NORTH-false-true-false-true-false-true" -> id-21;
            case "NORTH-false-true-false-true-false-false" -> id-20;
            case "NORTH-false-true-false-false-true-true" -> id-19;
            case "NORTH-false-true-false-false-true-false" -> id-18;
            case "NORTH-false-true-false-false-false-true" -> id-17;
            case "NORTH-false-true-false-false-false-false" -> id-16;
            case "NORTH-false-false-true-true-true-true" -> id-15;
            case "NORTH-false-false-true-true-true-false" -> id-14;
            case "NORTH-false-false-true-true-false-true" -> id-13;
            case "NORTH-false-false-true-true-false-false" -> id-12;
            case "NORTH-false-false-true-false-true-true" -> id-11;
            case "NORTH-false-false-true-false-true-false" -> id-10;
            case "NORTH-false-false-true-false-false-true" -> id-9;
            case "NORTH-false-false-true-false-false-false" -> id-8;
            case "NORTH-false-false-false-true-true-true" -> id-7;
            case "NORTH-false-false-false-true-true-false" -> id-6;
            case "NORTH-false-false-false-true-false-true" -> id-5;
            case "NORTH-false-false-false-true-false-false" -> id-4;
            case "NORTH-false-false-false-false-true-true" -> id-3;
            case "NORTH-false-false-false-false-true-false" -> id-2;
            case "NORTH-false-false-false-false-false-true" -> id-1;
            case "SOUTH-true-true-true-true-true-true" -> id+1;
            case "SOUTH-true-true-true-true-true-false" -> id+2;
            case "SOUTH-true-true-true-true-false-true" -> id+3;
            case "SOUTH-true-true-true-true-false-false" -> id+4;
            case "SOUTH-true-true-true-false-true-true" -> id+5;
            case "SOUTH-true-true-true-false-true-false" -> id+6;
            case "SOUTH-true-true-true-false-false-true" -> id+7;
            case "SOUTH-true-true-true-false-false-false" -> id+8;
            case "SOUTH-true-true-false-true-true-true" -> id+9;
            case "SOUTH-true-true-false-true-true-false" -> id+10;
            case "SOUTH-true-true-false-true-false-true" -> id+11;
            case "SOUTH-true-true-false-true-false-false" -> id+12;
            case "SOUTH-true-true-false-false-true-true" -> id+13;
            case "SOUTH-true-true-false-false-true-false" -> id+14;
            case "SOUTH-true-true-false-false-false-true" -> id+15;
            case "SOUTH-true-true-false-false-false-false" -> id+16;
            case "SOUTH-true-false-true-true-true-true" -> id+17;
            case "SOUTH-true-false-true-true-true-false" -> id+18;
            case "SOUTH-true-false-true-true-false-true" -> id+19;
            case "SOUTH-true-false-true-true-false-false" -> id+20;
            case "SOUTH-true-false-true-false-true-true" -> id+21;
            case "SOUTH-true-false-true-false-true-false" -> id+22;
            case "SOUTH-true-false-true-false-false-true" -> id+23;
            case "SOUTH-true-false-true-false-false-false" -> id+24;
            case "SOUTH-true-false-false-true-true-true" -> id+25;
            case "SOUTH-true-false-false-true-true-false" -> id+26;
            case "SOUTH-true-false-false-true-false-true" -> id+27;
            case "SOUTH-true-false-false-true-false-false" -> id+28;
            case "SOUTH-true-false-false-false-true-true" -> id+29;
            case "SOUTH-true-false-false-false-true-false" -> id+30;
            case "SOUTH-true-false-false-false-false-true" -> id+31;
            case "SOUTH-true-false-false-false-false-false" -> id+32;
            case "SOUTH-false-true-true-true-true-true" -> id+33;
            case "SOUTH-false-true-true-true-true-false" -> id+34;
            case "SOUTH-false-true-true-true-false-true" -> id+35;
            case "SOUTH-false-true-true-true-false-false" -> id+36;
            case "SOUTH-false-true-true-false-true-true" -> id+37;
            case "SOUTH-false-true-true-false-true-false" -> id+38;
            case "SOUTH-false-true-true-false-false-true" -> id+39;
            case "SOUTH-false-true-true-false-false-false" -> id+40;
            case "SOUTH-false-true-false-true-true-true" -> id+41;
            case "SOUTH-false-true-false-true-true-false" -> id+42;
            case "SOUTH-false-true-false-true-false-true" -> id+43;
            case "SOUTH-false-true-false-true-false-false" -> id+44;
            case "SOUTH-false-true-false-false-true-true" -> id+45;
            case "SOUTH-false-true-false-false-true-false" -> id+46;
            case "SOUTH-false-true-false-false-false-true" -> id+47;
            case "SOUTH-false-true-false-false-false-false" -> id+48;
            case "SOUTH-false-false-true-true-true-true" -> id+49;
            case "SOUTH-false-false-true-true-true-false" -> id+50;
            case "SOUTH-false-false-true-true-false-true" -> id+51;
            case "SOUTH-false-false-true-true-false-false" -> id+52;
            case "SOUTH-false-false-true-false-true-true" -> id+53;
            case "SOUTH-false-false-true-false-true-false" -> id+54;
            case "SOUTH-false-false-true-false-false-true" -> id+55;
            case "SOUTH-false-false-true-false-false-false" -> id+56;
            case "SOUTH-false-false-false-true-true-true" -> id+57;
            case "SOUTH-false-false-false-true-true-false" -> id+58;
            case "SOUTH-false-false-false-true-false-true" -> id+59;
            case "SOUTH-false-false-false-true-false-false" -> id+60;
            case "SOUTH-false-false-false-false-true-true" -> id+61;
            case "SOUTH-false-false-false-false-true-false" -> id+62;
            case "SOUTH-false-false-false-false-false-true" -> id+63;
            case "SOUTH-false-false-false-false-false-false" -> id+64;
            case "WEST-true-true-true-true-true-true" -> id+65;
            case "WEST-true-true-true-true-true-false" -> id+66;
            case "WEST-true-true-true-true-false-true" -> id+67;
            case "WEST-true-true-true-true-false-false" -> id+68;
            case "WEST-true-true-true-false-true-true" -> id+69;
            case "WEST-true-true-true-false-true-false" -> id+70;
            case "WEST-true-true-true-false-false-true" -> id+71;
            case "WEST-true-true-true-false-false-false" -> id+72;
            case "WEST-true-true-false-true-true-true" -> id+73;
            case "WEST-true-true-false-true-true-false" -> id+74;
            case "WEST-true-true-false-true-false-true" -> id+75;
            case "WEST-true-true-false-true-false-false" -> id+76;
            case "WEST-true-true-false-false-true-true" -> id+77;
            case "WEST-true-true-false-false-true-false" -> id+78;
            case "WEST-true-true-false-false-false-true" -> id+79;
            case "WEST-true-true-false-false-false-false" -> id+80;
            case "WEST-true-false-true-true-true-true" -> id+81;
            case "WEST-true-false-true-true-true-false" -> id+82;
            case "WEST-true-false-true-true-false-true" -> id+83;
            case "WEST-true-false-true-true-false-false" -> id+84;
            case "WEST-true-false-true-false-true-true" -> id+85;
            case "WEST-true-false-true-false-true-false" -> id+86;
            case "WEST-true-false-true-false-false-true" -> id+87;
            case "WEST-true-false-true-false-false-false" -> id+88;
            case "WEST-true-false-false-true-true-true" -> id+89;
            case "WEST-true-false-false-true-true-false" -> id+90;
            case "WEST-true-false-false-true-false-true" -> id+91;
            case "WEST-true-false-false-true-false-false" -> id+92;
            case "WEST-true-false-false-false-true-true" -> id+93;
            case "WEST-true-false-false-false-true-false" -> id+94;
            case "WEST-true-false-false-false-false-true" -> id+95;
            case "WEST-true-false-false-false-false-false" -> id+96;
            case "WEST-false-true-true-true-true-true" -> id+97;
            case "WEST-false-true-true-true-true-false" -> id+98;
            case "WEST-false-true-true-true-false-true" -> id+99;
            case "WEST-false-true-true-true-false-false" -> id+100;
            case "WEST-false-true-true-false-true-true" -> id+101;
            case "WEST-false-true-true-false-true-false" -> id+102;
            case "WEST-false-true-true-false-false-true" -> id+103;
            case "WEST-false-true-true-false-false-false" -> id+104;
            case "WEST-false-true-false-true-true-true" -> id+105;
            case "WEST-false-true-false-true-true-false" -> id+106;
            case "WEST-false-true-false-true-false-true" -> id+107;
            case "WEST-false-true-false-true-false-false" -> id+108;
            case "WEST-false-true-false-false-true-true" -> id+109;
            case "WEST-false-true-false-false-true-false" -> id+110;
            case "WEST-false-true-false-false-false-true" -> id+111;
            case "WEST-false-true-false-false-false-false" -> id+112;
            case "WEST-false-false-true-true-true-true" -> id+113;
            case "WEST-false-false-true-true-true-false" -> id+114;
            case "WEST-false-false-true-true-false-true" -> id+115;
            case "WEST-false-false-true-true-false-false" -> id+116;
            case "WEST-false-false-true-false-true-true" -> id+117;
            case "WEST-false-false-true-false-true-false" -> id+118;
            case "WEST-false-false-true-false-false-true" -> id+119;
            case "WEST-false-false-true-false-false-false" -> id+120;
            case "WEST-false-false-false-true-true-true" -> id+121;
            case "WEST-false-false-false-true-true-false" -> id+122;
            case "WEST-false-false-false-true-false-true" -> id+123;
            case "WEST-false-false-false-true-false-false" -> id+124;
            case "WEST-false-false-false-false-true-true" -> id+125;
            case "WEST-false-false-false-false-true-false" -> id+126;
            case "WEST-false-false-false-false-false-true" -> id+127;
            case "WEST-false-false-false-false-false-false" -> id+128;
            case "EAST-true-true-true-true-true-true" -> id+129;
            case "EAST-true-true-true-true-true-false" -> id+130;
            case "EAST-true-true-true-true-false-true" -> id+131;
            case "EAST-true-true-true-true-false-false" -> id+132;
            case "EAST-true-true-true-false-true-true" -> id+133;
            case "EAST-true-true-true-false-true-false" -> id+134;
            case "EAST-true-true-true-false-false-true" -> id+135;
            case "EAST-true-true-true-false-false-false" -> id+136;
            case "EAST-true-true-false-true-true-true" -> id+137;
            case "EAST-true-true-false-true-true-false" -> id+138;
            case "EAST-true-true-false-true-false-true" -> id+139;
            case "EAST-true-true-false-true-false-false" -> id+140;
            case "EAST-true-true-false-false-true-true" -> id+141;
            case "EAST-true-true-false-false-true-false" -> id+142;
            case "EAST-true-true-false-false-false-true" -> id+143;
            case "EAST-true-true-false-false-false-false" -> id+144;
            case "EAST-true-false-true-true-true-true" -> id+145;
            case "EAST-true-false-true-true-true-false" -> id+146;
            case "EAST-true-false-true-true-false-true" -> id+147;
            case "EAST-true-false-true-true-false-false" -> id+148;
            case "EAST-true-false-true-false-true-true" -> id+149;
            case "EAST-true-false-true-false-true-false" -> id+150;
            case "EAST-true-false-true-false-false-true" -> id+151;
            case "EAST-true-false-true-false-false-false" -> id+152;
            case "EAST-true-false-false-true-true-true" -> id+153;
            case "EAST-true-false-false-true-true-false" -> id+154;
            case "EAST-true-false-false-true-false-true" -> id+155;
            case "EAST-true-false-false-true-false-false" -> id+156;
            case "EAST-true-false-false-false-true-true" -> id+157;
            case "EAST-true-false-false-false-true-false" -> id+158;
            case "EAST-true-false-false-false-false-true" -> id+159;
            case "EAST-true-false-false-false-false-false" -> id+160;
            case "EAST-false-true-true-true-true-true" -> id+161;
            case "EAST-false-true-true-true-true-false" -> id+162;
            case "EAST-false-true-true-true-false-true" -> id+163;
            case "EAST-false-true-true-true-false-false" -> id+164;
            case "EAST-false-true-true-false-true-true" -> id+165;
            case "EAST-false-true-true-false-true-false" -> id+166;
            case "EAST-false-true-true-false-false-true" -> id+167;
            case "EAST-false-true-true-false-false-false" -> id+168;
            case "EAST-false-true-false-true-true-true" -> id+169;
            case "EAST-false-true-false-true-true-false" -> id+170;
            case "EAST-false-true-false-true-false-true" -> id+171;
            case "EAST-false-true-false-true-false-false" -> id+172;
            case "EAST-false-true-false-false-true-true" -> id+173;
            case "EAST-false-true-false-false-true-false" -> id+174;
            case "EAST-false-true-false-false-false-true" -> id+175;
            case "EAST-false-true-false-false-false-false" -> id+176;
            case "EAST-false-false-true-true-true-true" -> id+177;
            case "EAST-false-false-true-true-true-false" -> id+178;
            case "EAST-false-false-true-true-false-true" -> id+179;
            case "EAST-false-false-true-true-false-false" -> id+180;
            case "EAST-false-false-true-false-true-true" -> id+181;
            case "EAST-false-false-true-false-true-false" -> id+182;
            case "EAST-false-false-true-false-false-true" -> id+183;
            case "EAST-false-false-true-false-false-false" -> id+184;
            case "EAST-false-false-false-true-true-true" -> id+185;
            case "EAST-false-false-false-true-true-false" -> id+186;
            case "EAST-false-false-false-true-false-true" -> id+187;
            case "EAST-false-false-false-true-false-false" -> id+188;
            case "EAST-false-false-false-false-true-true" -> id+189;
            case "EAST-false-false-false-false-true-false" -> id+190;
            case "EAST-false-false-false-false-false-true" -> id+191;
            case "EAST-false-false-false-false-false-false" -> id+192;
            default -> id;
        };
    }
}
package ink.reactor.chat.util;

public final class ColorConverter {

    public static int[] cymkToRgb(final float cyan, final float yellow, final float magenta, final float key) {
        final int r = Math.round(255 * (1 - cyan) * (1 - key));
        final int g = Math.round(255 * (1 - magenta) * (1 - key));
        final int b = Math.round(255 * (1 - yellow) * (1 - key));
        return new int[] {r, g, b};
    }

    public static double[] rgbToCYMK(final int red, final int green, final int blue) {
        final double rNorm = red / 255.0D;
        final double gNorm = green / 255.0D;
        final double bNorm = blue / 255.0D;
    
        final double k = 1 - Math.max(Math.max(rNorm, gNorm), bNorm);
    
        float c = (float) ((1 - rNorm - k) / (1 - k));
        float m = (float) ((1 - gNorm - k) / (1 - k));
        float y = (float) ((1 - bNorm - k) / (1 - k));
        if (k == 1) {
            c = 0;
            m = 0;
            y = 0;
        }
        return new double[] {c, m, y, k};
    }

    public static int[] hexToRGB(String hex) {
        if (hex.charAt(0) == '#') {
            hex = hex.substring(1);
        }
        int red, green, blue;
        if (hex.length() == 3) {
            // RGB to RRGGBB
            red = Integer.parseInt(hex.substring(0, 1) + hex.substring(0, 1), 16);
            green = Integer.parseInt(hex.substring(1, 2) + hex.substring(1, 2), 16);
            blue = Integer.parseInt(hex.substring(2, 3) + hex.substring(2, 3), 16);
        } else if (hex.length() == 6) {
            // RRGGBB
            red = Integer.parseInt(hex.substring(0, 2), 16);
            green = Integer.parseInt(hex.substring(2, 4), 16);
            blue = Integer.parseInt(hex.substring(4, 6), 16);
        } else {
            red = 255;
            green = 255;
            blue = 255;
        }
        return new int[] {red, green, blue};
    }

    public static double[] rgbToHsl(final int red, final int green, final int blue) {
        final double rNorm = red / 255.0f;
        final double gNorm = green / 255.0f;
        final double bNorm = blue / 255.0f;

        final double cMax = Math.max(rNorm, Math.max(gNorm, bNorm));
        final double cMin = Math.min(rNorm, Math.min(gNorm, bNorm));
        final double delta = cMax - cMin;

        double h = 0;
        if (delta == 0) {
            h = 0;
        } else if (cMax == rNorm) {
            h = (60 * ((gNorm - bNorm) / delta) + 360) % 360;
        } else if (cMax == gNorm) {
            h = (60 * ((bNorm - rNorm) / delta) + 120) % 360;
        } else if (cMax == bNorm) {
            h = (60 * ((rNorm - gNorm) / delta) + 240) % 360;
        }

        final double l = (cMax + cMin) / 2;

        double s = 0;
        if (delta != 0) {
            s = delta / (1 - Math.abs(2 * l - 1));
        }

        return new double[]{Math.round(h), s, l};
    }
}

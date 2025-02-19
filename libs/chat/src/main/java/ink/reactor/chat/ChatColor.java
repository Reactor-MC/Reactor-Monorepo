package ink.reactor.chat;

import ink.reactor.chat.util.ColorConverter;
import lombok.Getter;

/**
 * The {@code ChatColor} class represents a color in a chat system.
 * It provides methods to create colors from different formats (RGB, CMYK, HEX, etc.)
 * and also includes a set of predefined colors.
 */
@Getter
public final class ChatColor {

    /** Constant character used to indicate a color code in chat. */
    public static final char COLOR_CHAR = '\u00A7';

    /** Constants representing different color formats. */
    public static final char 
        RGB = 0,
        HEX_CODE = 1,
        CMYK = 2,
        GRAY_TYPE = 3;

    public static final ChatColor
        BLACK = new ChatColor("black", '0', 0,0,0),
        DARK_BLUE = new ChatColor("dark_blue", '1', 0, 0, 170),
        DARK_GREEN = new ChatColor("dark_green", '2', 0, 170, 0),
        DARK_AQUA = new ChatColor("dark_aqua", '3', 0, 170, 170),
        DARK_RED = new ChatColor("dark_red", '4', 170,0,0),
        DARK_PURPLE = new ChatColor("dark_purple", '5', 170, 0, 170),
        GOLD = new ChatColor("gold", '6', 255, 170, 0),
        GRAY = new ChatColor("gray", '7', 170, 170, 170),
        DARK_GRAY = new ChatColor("dark_gray", '8', 85, 85, 85),
        BLUE = new ChatColor("blue", '9', 85, 85, 255),
        GREEN = new ChatColor("green", 'a', 85, 255, 85),
        AQUA = new ChatColor("aqua", 'b', 85, 255, 255),
        RED = new ChatColor("red", 'c', 255,85,85),
        LIGHT_PURPLE = new ChatColor("light_purple", 'd', 255, 85, 255),
        YELLOW = new ChatColor("yellow", 'e', 255, 255, 85),
        WHITE = new ChatColor("white", 'f', 255, 255, 255);

    public static final ChatColor[] LEGACY_COLORS = {
        BLACK, DARK_BLUE, DARK_GREEN, DARK_AQUA, DARK_RED, DARK_PURPLE, GOLD,
        GRAY, DARK_GRAY, BLUE, GREEN, AQUA, RED, LIGHT_PURPLE, YELLOW, WHITE
    };

    /** The character code associated with the color. */
    private final char code;

    private final int red, blue, green;

    /** The name of the color. */
    private String name;

    /** The cached byte array representation of the color name. */
    private byte[] cachedName;

    /**
     * Private constructor used to initialize predefined colors.
     *
     * @param name  The name of the color.
     * @param code  The character code associated with the color.
     * @param red   The red component of the color.
     * @param blue  The blue component of the color.
     * @param green The green component of the color.
     */
    private ChatColor(final String name, final char code, final int red, final int blue, final int green) {
        this.name = name;
        this.code = code;
        this.red = red;
        this.blue = blue;
        this.green = green;
        this.cachedName = name.getBytes();
    }

    /**
     * Private constructor used to create colors from RGB values and a format code.
     *
     * @param red   The red component of the color.
     * @param green The green component of the color.
     * @param blue  The blue component of the color.
     * @param code  The format code (e.g., RGB, HEX_CODE, etc.).
     */
    private ChatColor(int red, int green, int blue, char code) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.code = code;
    }

    /**
     * Creates a color from integer RGB values.
     *
     * @param red   The red component (0-255).
     * @param green The green component (0-255).
     * @param blue  The blue component (0-255).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor rgb(final int red, final int green, final int blue) {
        return new ChatColor(red & 0xFF, green & 0xFF, blue & 0xFF, RGB);
    }

    /**
     * Creates a color from floating-point RGB values (0.0 to 1.0).
     *
     * @param red   The red component (0.0 to 1.0).
     * @param green The green component (0.0 to 1.0).
     * @param blue  The blue component (0.0 to 1.0).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor rgb(final float red, final float green, final float blue) {
        return rgb((int)(255 * red), (int)(255 * green), (int)(255 * blue));
    }

    /**
     * Creates a color from CMYK values (0.0 to 1.0).
     *
     * @param cyan    The cyan component (0.0 to 1.0).
     * @param magenta The magenta component (0.0 to 1.0).
     * @param yellow  The yellow component (0.0 to 1.0).
     * @param key     The key (black) component (0.0 to 1.0).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor cmyk(final float cyan, final float magenta, final float yellow, final float key) {
        final int[] rgb = ColorConverter.cymkToRgb(cyan, yellow, magenta, key);
        return new ChatColor(rgb[0], rgb[1], rgb[2], CMYK);
    }

    /**
     * Creates a color from a hexadecimal integer value.
     *
     * @param hex The hexadecimal value representing the color.
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor hex(final int hex) {
        return new ChatColor((hex >> 16) & 0xFF, (hex >> 8) & 0xFF, hex & 0xFF, HEX_CODE);
    }

    /**
     * Creates a color from a hexadecimal string (e.g., "#RRGGBB" or "RRGGBB").
     * But don't if the name equals exactly to hex code. Example, if the parameter is "hello" the name of the chatcolor is "hello"
     *
     * @param hex The hexadecimal string representing the color.
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor hexFast(String hex) {
        String name;
        if (hex.charAt(0) == '#') {
            name = hex;
            hex = hex.substring(1);
        } else {
            name = '#'+hex;
        }
        final int[] rgb = ColorConverter.hexToRGB(hex);
        return new ChatColor(name, HEX_CODE, rgb[0], rgb[1], rgb[2]);
    }

    /**
     * Creates a color from a hexadecimal string (e.g., "#RRGGBB" or "RRGGBB").
     *
     * @param hex The hexadecimal string representing the color.
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor hex(final String hex) {
        final int[] rgb = ColorConverter.hexToRGB(hex);
        final String name = rgb(rgb[0], rgb[1], rgb[2]).toHex();
        return new ChatColor(name, HEX_CODE, rgb[0], rgb[1], rgb[2]);
    }

    /**
     * Creates a grayscale color from a gray type value (0-255).
     *
     * @param grayType The gray value (0-255).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor gray(final int grayType) {
        final int gray = grayType & 0xFF;
        return new ChatColor(gray, gray, gray, GRAY_TYPE);
    }

    /**
     * Creates a color with only the red component.
     *
     * @param redType The red value (0-255).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor red(final int redType) {
        return new ChatColor(redType & 0xFF, 0, 0, RGB);
    }

    /**
     * Creates a color with only the green component.
     *
     * @param greenType The green value (0-255).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor green(final int greenType) {
        return new ChatColor(0, greenType & 0xFF, 0, RGB);
    }

    /**
     * Creates a color with only the blue component.
     *
     * @param blueType The blue value (0-255).
     * @return A new {@code ChatColor} instance.
     */
    public static ChatColor blue(final int blueType) {
        return new ChatColor(0, 0, blueType & 0xFF, RGB);
    }

    /**
     * Retrieves a predefined color based on a legacy character code.
     *
     * @param code The legacy character code.
     * @return The corresponding {@code ChatColor} instance, or {@code null} if not found.
     */
    public static ChatColor byLegacyCode(final char code) {
        return switch (code) {
            case '4' -> DARK_RED;
            case 'c' -> RED;
            case '6' -> GOLD;
            case 'e' -> YELLOW;
            case '2' -> DARK_GREEN;
            case 'a' -> GREEN;
            case 'b' -> AQUA;
            case '3' -> DARK_AQUA;
            case '1' -> DARK_BLUE;
            case '9' -> BLUE;
            case 'd' -> LIGHT_PURPLE;
            case '5' -> DARK_PURPLE;
            case 'f' -> WHITE;
            case '7' -> GRAY;
            case '8' -> DARK_GRAY;
            case '0' -> BLACK;
            default -> null;
        };
    }

    /**
     * Retrieves a predefined color based on a legacy name.
     *
     * @param code The legacy color name.
     * @return The corresponding {@code ChatColor} instance, or {@code null} if not found.
     */
    public static ChatColor byLegacyName(final String name) {
        return switch(name.toLowerCase()) {
            case "black" -> BLACK;
            case "dark_blue" -> DARK_BLUE;
            case "dark_green" -> DARK_GREEN;
            case "dark_aqua" -> DARK_AQUA;
            case "dark_red" -> DARK_RED;
            case "dark_purple" -> DARK_PURPLE;
            case "gold" -> GOLD;
            case "gray" -> GRAY;
            case "dark_gray" -> DARK_GRAY;
            case "blue" -> BLUE;
            case "green" -> GREEN;
            case "aqua" -> AQUA;
            case "red" -> RED;
            case "light_purple" -> LIGHT_PURPLE;
            case "yellow" -> YELLOW;
            case "white" -> WHITE;
            default -> null;
        };
    }

    /**
     * Checks if the color is a legacy color (based on character codes '0'-'f').
     *
     * @return {@code true} if the color is legacy, {@code false} otherwise.
     */
    public boolean isLegacy() {
        return code >= '0' && code <= 'f';
    }

    /**
     * Returns a string representation of the color based on its format.
     *
     * @return A string representation of the color.
     */
    @Override
    public String toString() {
        return switch(code) {
            case RGB -> toRgb();
            case HEX_CODE -> getName();
            case CMYK -> toCymk();
            case GRAY_TYPE -> String.valueOf(red);
            default -> COLOR_CHAR + String.valueOf(code);
        };
    }

    /**
     * Compares this color to another object for equality.
     *
     * @param object The object to compare.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object object) {
        return (object == this) || (object instanceof ChatColor chatColor
            && chatColor.red == this.red
            && chatColor.green == this.green
            && chatColor.blue == this.blue);
    }

    /**
     * Returns the RGB representation of the color as a string.
     *
     * @return A string in the format "red, green, blue".
     */
    public String toRgb() {
        return (red) + ", " + (green ) + ", " + (blue );
    }

    /**
     * Returns the CMYK representation of the color as a string.
     *
     * @return A string in the format "cyan, magenta, yellow, key".
     */
    public String toCymk() {
        final double[] cymk = ColorConverter.rgbToCYMK(red, green, blue);
        return cymk[0] + ", " + cymk[1] + ", " + cymk[2] + ", " + cymk[3];
    }

    /**
     * Returns the grayscale value of the color as a string.
     *
     * @return A string representing the grayscale value.
     */
    public String toGray() {
        return String.valueOf( (red + blue + green) / 3);
    }

    /**
     * Returns the HSL representation of the color as a string.
     *
     * @return A string in the format "hue, saturation, lightness".
     */
    public String toHSL() {
        final double[] hsl = ColorConverter.rgbToHsl(red, green, blue);
        return hsl[0] + ", " + hsl[1] + ", " + hsl[2];
    }

    /**
     * Returns the hexadecimal representation of the color as a string.
     *
     * @return A string in the format "#RRGGBB".
     */
    public String toHex() {
        if (this.isLegacy()) {
            return String.format("#%02X%02X%02X", red, green, blue);
        }
        return getName();
    }

    /**
     * Returns the name of the color. If the name is not set, it generates one from the RGB values.
     *
     * @return The name of the color.
     */
    public String getName() {
        if (name != null) {
            return name;
        }
        return (this.name = String.format("#%02X%02X%02X", red, green, blue));
    }

    /**
     * Returns the cached byte array representation of the color name.
     *
     * @return The cached byte array.
     */
    public byte[] getCachedName() {
        if (cachedName != null) {
            return cachedName;
        }
        return (this.cachedName = getName().getBytes());
    }

    public static ChatColor approximateToLegacy(final ChatColor color) {
        if (color.isLegacy()) {
            return color;
        }
    
        ChatColor closest = null;
        double minDistance = Double.MAX_VALUE;

        for (final ChatColor legacy : ChatColor.LEGACY_COLORS) {
            final double distance =
                Math.pow(color.red - legacy.red, 2) +
                Math.pow(color.green - legacy.green, 2) +
                Math.pow(color.blue - legacy.blue, 2);

            if (distance < minDistance) {
                minDistance = distance;
                closest = legacy;
            }
        }

        return closest;
    }
}
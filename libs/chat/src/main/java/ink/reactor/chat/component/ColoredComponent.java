package ink.reactor.chat.component;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.serializer.ColoredSerializer;
import ink.reactor.nbt.NBT;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ColoredComponent implements ChatComponent {

    private String text;

    private ChatColor color;
    private byte bold, italic, underlined, obfuscated, strikethrough;

    public ColoredComponent(String text) {
        this.text = text;
    }

    public ColoredComponent(String text, ChatColor color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public byte[] toJsonBytes() {
        return ColoredSerializer.toJson(this);
    }

    @Override
    public void writeNBT(final NBT nbt) {
        nbt.addString("text", text);
        if (color != null) {
            nbt.addString("color", color.getName());
        }

        add(nbt, "bold", bold);
        add(nbt, "italic", italic);
        add(nbt, "underlined", underlined);
        add(nbt, "obfuscated", obfuscated);
        add(nbt, "strikethrough", strikethrough);
    }

    private static void add(final NBT nbt, final String key, final byte value) {
        if (value != UNDEFINED) {
            nbt.addBoolean(key, value == TRUE);
        }
    }

    @Override
    public ChatComponent copy() {
        return new ColoredComponent(text, color, bold, italic, underlined, obfuscated, strikethrough);
    }

    public boolean hasFontStyle() {
        return
            bold == FALSE &&
            italic == FALSE &&
            obfuscated == FALSE &&
            strikethrough == FALSE &&
            underlined == FALSE;
    }

    public int getAmountStyles(final byte state) {
        int size = 0;
        if (bold == state) size++;
        if (italic == state) size++;
        if (obfuscated == state) size++;
        if (strikethrough == state) size++;
        if (underlined == state) size++;
        return size;
    }

    public final void reset() {
        bold = FALSE;
        color = ChatColor.WHITE;
        italic = FALSE;
        obfuscated = FALSE;
        strikethrough = FALSE;
        underlined = FALSE;
    }

    public final void setText(String newText) {
        this.text = newText;
    }

    public final void setBold(boolean bold) {
        this.bold = (bold) ? TRUE : FALSE;
    }

    public final void setColor(ChatColor color) {
        this.color = color;
    }

    public final void setItalic(boolean italic) {
        this.italic = (italic) ? TRUE : FALSE;
    }

    public final void setObfuscated(boolean obfuscated) {
        this.obfuscated = (obfuscated) ? TRUE : FALSE;
    }

    public final void setStrikethrough(boolean strikethrough) {
        this.strikethrough = (strikethrough) ? TRUE : FALSE;
    }

    public final void setUnderlined(boolean underlined) {
        this.underlined = (underlined) ? TRUE : FALSE;
    }

    public final boolean isBold() {
        return this.bold == TRUE;
    }
   
    public final boolean isItalic() {
        return this.italic == TRUE;
    }

    public final boolean isObfuscated() {
        return this.obfuscated == TRUE;
    }

    public final boolean isStrikethrough() {
        return this.strikethrough == TRUE;
    }

    public final boolean isUnderlined() {
        return this.underlined == TRUE;
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public void setToDefault() {
        this.text = "";
        this.color = null;
        this.bold = UNDEFINED;
        this.italic = UNDEFINED;
        this.obfuscated = UNDEFINED;
        this.strikethrough = UNDEFINED;
        this.underlined = UNDEFINED;
    }
}
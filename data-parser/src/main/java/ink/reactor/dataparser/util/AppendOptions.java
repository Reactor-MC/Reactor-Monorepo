package ink.reactor.dataparser.util;

import lombok.Getter;

@Getter
public final class AppendOptions {
    public static final AppendOptions DEFAULT = new AppendOptions();

    private boolean
        appendNullable = true,
        addStringQuoteMarks = true,
        startInNewLine = false;
    
    private int spacesInNewLine = 4;
    
    public AppendOptions setAddStringQuoteMarks(boolean addStringQuoteMarks) {
        this.addStringQuoteMarks = addStringQuoteMarks;
        return this;
    }

    public AppendOptions setAppendNullable(boolean appendNullable) {
        this.appendNullable = appendNullable;
        return this;
    }

    public AppendOptions setSpacesInNewLine(int spacesInNewLine) {
        this.spacesInNewLine = spacesInNewLine;
        return this;
    }

    public AppendOptions setStartInNewLine(boolean startInNewLine) {
        this.startInNewLine = startInNewLine;
        return this;
    }
}

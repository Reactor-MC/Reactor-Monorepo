package ink.reactor.server.console;

import java.util.regex.Pattern;

import org.jline.reader.Highlighter;
import org.jline.reader.LineReader;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import ink.reactor.chat.ChatColor;

final class ConsoleHighlighter implements Highlighter {

    private final AttributedStyle unknownCommand = new AttributedStyle()
        .foreground(ChatColor.RED.getRed(), ChatColor.RED.getGreen(), ChatColor.RED.getBlue());

    @Override
    public AttributedString highlight(LineReader reader, String buffer) {
        // TODO: Command highlighter
        if (!buffer.startsWith("stop")) {
            return new AttributedString(buffer, unknownCommand);
        }
        return new AttributedString(buffer);
    }

    public void setErrorPattern(Pattern errorPattern) {}
    public void setErrorIndex(int errorIndex) {}
}
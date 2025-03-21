package ink.reactor.server.console;

import ink.reactor.command.CommandSender;
import org.jline.jansi.Ansi;

import ink.reactor.chat.ChatColor;
import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.ColoredComponent;
import ink.reactor.chat.component.RawComponent;
import ink.reactor.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Console implements CommandSender {

    private final ConsoleLineReader lineReader;

    @Override
    public void sendMessage(ChatComponent[] components) {
        for (final ChatComponent component : components) {
            System.out.print(componentToAnsi(component));
        }
        System.out.println();
    }

    @Override
    public void sendMessage(ChatComponent component) {
        System.out.println(componentToAnsi(component));
    }

    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void sendMessage(String... messages) {
        System.out.println(StringUtil.combine(messages));
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public boolean hasPermission(final String permission) {
        return true;
    }

    @Override
    public boolean isOp() {
        return true;
    }

    public void stop() {
        lineReader.stop();
    }

    public void run() {
        lineReader.run();
    }

    private static Ansi componentToAnsi(final ChatComponent chatComponent) {
        if (chatComponent instanceof RawComponent rawComponent) {
            return Ansi.ansi().a(rawComponent.getText());
        }
        final ColoredComponent component = (ColoredComponent)chatComponent;
        final Ansi ansi = Ansi.ansi();
        if (component.getColor() != null) {
            final ChatColor color = component.getColor();
            ansi.fgRgb(color.getRed(), color.getGreen(), color.getBlue());
        }
        if (component.isBold()){ 
            ansi.bold();
        }
        if (component.isItalic()) {
            ansi.a(Ansi.Attribute.ITALIC);
        }
        if (component.isUnderlined()) {
            ansi.a(Ansi.Attribute.UNDERLINE);
        }
        if (component.isStrikethrough()) {
            ansi.a(Ansi.Attribute.STRIKETHROUGH_ON);
        }
        if (component.isObfuscated()) {
            ansi.a(Ansi.Attribute.BLINK_SLOW);
        }
        ansi.a(component.getText());
        ansi.a(Ansi.Attribute.RESET);
        return ansi;
    }
}
package ink.reactor.server.console;

import java.io.IOException;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.MaskingCallback;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp.Capability;

import org.tinylog.Logger;

import ink.reactor.api.Reactor;

final class ConsoleLineReader {

    private final Terminal terminal;
    private final LineReader reader;

    private boolean run = true;

    ConsoleLineReader(Terminal terminal, LineReader reader) {
        this.terminal = terminal;
        this.reader = reader;
    }

    void run() {
        while(run) {
            final String line;
            try {
                line = reader.readLine("> ", null, (MaskingCallback) null, null).trim();
            } catch (UserInterruptException | EndOfFileException e) {
                Reactor.getServer().stop();
                continue;
            }

            if (line.isEmpty()) {
                continue; 
            }
            switch (line) {
                case "stop":
                    Reactor.getServer().stop();
                    return;
                case "clear":
                    terminal.puts(Capability.clear_screen);
                    break;
            }
        }
    }

    void stop() {
        try {
            terminal.close();
        } catch (IOException e) {
            Logger.info("Error trying to stop the terminal");
            Logger.error(e);
        } finally {
            run = false;
        }
    }
}
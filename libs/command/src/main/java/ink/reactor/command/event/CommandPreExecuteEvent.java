package ink.reactor.command.event;

import ink.reactor.command.Command;
import ink.reactor.command.CommandSender;
import ink.reactor.command.argument.Arguments;
import ink.reactor.util.event.Cancellable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public final class CommandPreExecuteEvent implements Cancellable {
    private final CommandSender sender;
    private final Arguments arguments;
    private final String prefix;
    private final Command command;
    private boolean cancelled;
}
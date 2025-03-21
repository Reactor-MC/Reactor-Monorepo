package ink.reactor.command.event;

import ink.reactor.util.event.Cancellable;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
public final class CommandTabEvent implements Cancellable {
    private Collection<?> output;
    private boolean cancelled;
}
package ink.reactor.command.storage;

import java.util.function.Consumer;

import ink.reactor.command.argument.ArgumentsOptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class CommandOptions {
    public static final byte
        NO_OVERRIDE = 0,
        ALLOW_OVERRIDE = 1,
        CANT_REMOVE_AND_OVERRIDE = 2; 

    private byte overrideOption = ALLOW_OVERRIDE;
    private Consumer<CommandData> onOverride = null;
    private ArgumentsOptions argumentsOptions;
}
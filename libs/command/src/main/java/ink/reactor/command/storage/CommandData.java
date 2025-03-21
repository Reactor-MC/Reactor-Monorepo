package ink.reactor.command.storage;

import ink.reactor.command.Command;

public record CommandData(
    String[] aliases,
    Command command,
    CommandOptions options
) {}
package ink.reactor.command.argument;

import ink.reactor.command.argument.suggestion.CommandSuggestion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public final class ArgumentBuilder {
    private final List<ArgumentType> args = new ArrayList<>();
    private int next = 0;
    private CommandSuggestion suggestion;

    public static ArgumentBuilder builder() {
        return new ArgumentBuilder();
    }

    public ArgumentBuilder setAutomaticSuggest(final CommandSuggestion suggestion) {
        this.suggestion = suggestion;
        return this;
    }

    public ArgumentBuilder addInt() {
        return add(ArgumentType.ID_INT);
    }

    public ArgumentBuilder addLong() {
        return add(ArgumentType.ID_LONG);
    }

    public ArgumentBuilder addDouble() {
        return add(ArgumentType.ID_DOUBLE);
    }

    public ArgumentBuilder addFloat() {
        return add(ArgumentType.ID_FLOAT);
    }

    public ArgumentBuilder addBoolean() {
        return add(ArgumentType.ID_BOOLEAN);
    }

    public ArgumentBuilder addString() {
        return add(ArgumentType.ID_STRING);
    }

    public ArgumentBuilder addString(final int repeat) {
        for (int i = 0; i < repeat; i++) {
            addString();
        }
        return this;
    }

    public ArgumentBuilder setInt(final int arg) {
        return set(arg, ArgumentType.ID_INT);
    }

    public ArgumentBuilder setLong(final int arg) {
        return set(arg, ArgumentType.ID_LONG);
    }

    public ArgumentBuilder setDouble(final int arg) {
        return set(arg, ArgumentType.ID_DOUBLE);
    }

    public ArgumentBuilder setFloat(final int arg) {
        return set(arg, ArgumentType.ID_FLOAT);
    }

    public ArgumentBuilder setBoolean(final int arg) {
        return set(arg, ArgumentType.ID_BOOLEAN);
    }

    public ArgumentBuilder setString(final int arg) {
        return set(arg, ArgumentType.ID_STRING);
    }

    public ArgumentBuilder setNextArg(final int arg) {
        this.next = arg;
        return this;
    }

    public ArgumentBuilder of(final byte... ids) {
        for (final byte id : ids) {
            args.add(new ArgumentType(next++, id));
        }
        return this;
    }

    private ArgumentBuilder set(int arg, byte id) {
        args.add(new ArgumentType(arg, id));
        if (next < arg) {
            next = arg+1;
        }
        return this;
    }

    private ArgumentBuilder add(byte id) {
        args.add(new ArgumentType(next++, id));
        return this;
    }

    public ArgumentsOptions build() {
        return new ArgumentsOptions(suggestion, args);
    }
}
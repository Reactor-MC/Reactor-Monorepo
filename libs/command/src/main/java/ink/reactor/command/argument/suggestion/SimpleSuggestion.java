package ink.reactor.command.argument.suggestion;

import ink.reactor.command.argument.ArgumentType;
import ink.reactor.command.storage.CommandData;

import it.unimi.dsi.fastutil.booleans.BooleanArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleSuggestion implements CommandSuggestion {

    public static final SimpleSuggestion INSTANCE = new SimpleSuggestion();

    @Override
    public Collection<?> suggest(final int index, final CommandData data) {
        final List<ArgumentType> argumentTypes = data.options().getArgumentsOptions().argumentTypes();
        final ArgumentType argumentType;

        if (index > argumentTypes.size() || (argumentType = argumentTypes.get(index)) == null) {
            return null;
        }

        return switch (argumentType.id()) {
            case ArgumentType.ID_BOOLEAN -> suggestBoolean();
            case ArgumentType.ID_INT, ArgumentType.ID_LONG -> suggestInt();
            case ArgumentType.ID_DOUBLE, ArgumentType.ID_FLOAT -> suggestDecimal();
            case ArgumentType.ID_STRING -> List.of("string");
            default -> null;
        };
    }

    public static BooleanArrayList suggestBoolean() {
        return BooleanArrayList.of(true, false);
    }

    public static IntArrayList suggestInt() {
        return IntArrayList.of(1, 2, 3, 4);
    }

    public static DoubleArrayList suggestDecimal() {
        return DoubleArrayList.of(0.0, 0.2, 0.5, 1.0);
    }
}

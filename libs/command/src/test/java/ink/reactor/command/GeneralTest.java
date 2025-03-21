package ink.reactor.command;

import ink.reactor.command.argument.ArgumentBuilder;
import ink.reactor.command.argument.Arguments;
import ink.reactor.command.argument.ArgumentsOptions;
import ink.reactor.command.argument.suggestion.SimpleSuggestion;
import ink.reactor.command.storage.CommandStorage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public final class GeneralTest {

    private static final TestCommand TEST_COMMAND = new TestCommand();
    private static final CommandStorage storage = new CommandStorage();
    static {
        storage.register(TEST_COMMAND, "test");
    }

    @Test
    public void testCommandExecuting() {
        Assertions.assertTrue(CommandExecutor.execute(storage, null, "test -123 512 -125"));
    }

    @Test
    public void testCommandTab() {
        Collection<?> tabResult = CommandExecutor.tab(storage, null, "test");
        Assertions.assertNotNull(tabResult);
        Assertions.assertEquals(SimpleSuggestion.suggestInt(), tabResult);

        tabResult = CommandExecutor.tab(storage, null, "test 1");
        Assertions.assertNotNull(tabResult);
        Assertions.assertEquals(SimpleSuggestion.suggestDecimal(), tabResult);

        tabResult = CommandExecutor.tab(storage, null, "test 2 1.0");
        Assertions.assertNotNull(tabResult);
        Assertions.assertEquals(SimpleSuggestion.suggestBoolean(), tabResult);
    }

    private static final class TestCommand implements Command {

        @Override
        public void onExecute(final CommandSender sender, final Arguments args) {
            final int x = args.nextInt(), y = args.nextInt(), z = args.nextInt();
            Assertions.assertEquals(-123, x);
            Assertions.assertEquals(512, y);
            Assertions.assertEquals(-125, z);
        }

        @Override
        public ArgumentsOptions getArgs() {
            return ArgumentBuilder.builder()
                    .setAutomaticSuggest(SimpleSuggestion.INSTANCE)
                    .addInt()
                    .addDouble()
                    .addBoolean()
                    .build();
        }
    }
}

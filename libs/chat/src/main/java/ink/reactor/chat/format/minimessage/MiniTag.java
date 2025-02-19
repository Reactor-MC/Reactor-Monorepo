package ink.reactor.chat.format.minimessage;

import java.util.List;

import ink.reactor.chat.component.ChatComponent;
import ink.reactor.chat.component.FullComponent;

public interface MiniTag {
    
    /**
     * @param fullComponent The component with text inside of tag, example: "<b>test" is "test"
     * @param args The arg 0 is the prefix, example: "bold" or "b". Next are args splitted by ':'. Example: "<color:arg1:arg2>"
     * @param output A list of all output chat components
     */
    void parse(
        FullComponent fullComponent,
        List<String> args,
        List<ChatComponent> output
    );

    /**
     * @param nextComponent The next component to be added
     */
    void onClose(FullComponent nextComponent);

    /**
     * {@code true} Executes once and discard
     * {@code false} Executes in next components until the tag is closed
     */
    default boolean autoCloseableTag() {
        return false;
    }
}
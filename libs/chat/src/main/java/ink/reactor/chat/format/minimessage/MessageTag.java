package ink.reactor.chat.format.minimessage;

import java.util.List;

import ink.reactor.chat.component.ChatComponent;

public interface MessageTag {

    /**
     * @param isCloseTag Indicate if the tag is open or close tag
     * @param message The text inside of tag, example: "<b>test" is "test"
     * @param args The arg 0 is the prefix, example: "bold" or "b". Next are args splitted by ':'. Example: "<color:arg1:arg2>"
     * @param output A list of all output chat components
     */
    void parse(
        final boolean isCloseTag,
        final String message,
        final List<String> args,
        final List<ChatComponent> output
    );
}

package ink.reactor.dataparser.append;

public class CustomAppender {
    public static void appendString(final String string, final StringBuilder builder) {
        builder.append('"');
        builder.append(string);
        builder.append('"');
    }

    public static void append(final StringBuilder builder, final Object... objects) {
        append(AppendOptions.DEFAULT, builder, objects);
    }

    public static void append(final AppendOptions options, final StringBuilder builder, final Object... objects) {
        int i = 0;
        for (final Object object : objects) {
            if (object == null && !options.isAppendNullable()) {
                if (++i != objects.length) {
                    builder.append(',');
                }
                continue;
            }
            if (object instanceof String string && options.isAddStringQuoteMarks()) {
                appendString(string, builder);
            } else {
                builder.append(object);
            }
            if (++i != objects.length) {
                builder.append(',');
            }
            if (options.isStartInNewLine()) {
                builder.append('\n');
                for (int s = 0; s < options.getSpacesInNewLine(); s++) {
                    builder.append(' ');
                }
            }
        }
    }
}

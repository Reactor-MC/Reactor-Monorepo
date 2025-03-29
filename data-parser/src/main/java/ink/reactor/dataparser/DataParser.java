package ink.reactor.dataparser;

import java.io.IOException;

public interface DataParser {
    void parse(String packageName) throws IOException;
}

package ink.reactor.dataparser.common;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.NonNull;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class ParserFiles {

    public static final File HOME = new File("");
    public static final File OUTPUT = new File("data-parser/src/main/java/ink/reactor/output");

    public static void writeJava(final String fileContent, final String className) throws IOException {
        write(fileContent, className + ".java");
    }

    public static void write(final String fileContent, final String out) throws IOException {
        final File outputFile = new File(OUTPUT, out);
        if (!OUTPUT.exists()) {
            OUTPUT.mkdirs();
        }
        outputFile.delete();
        outputFile.createNewFile();
        Files.write(outputFile.toPath(), fileContent.getBytes());
    }

    @NonNull
    public static JSONObject loadJsonObject(final @NonNull String filePath) throws IOException {
        try(final InputStream inputStream = ParserFiles.class.getClassLoader().getResourceAsStream(filePath)){
            if (inputStream == null) {
                throw new IOException("Can't found the file " + filePath + " inside of the .jar");
            }
            return JSON.parseObject(new BufferedInputStream(inputStream));
        }
    }

    @NonNull
    public static JSONArray loadJsonArray(final @NonNull String filePath) throws IOException {
        try(final InputStream inputStream = ParserFiles.class.getClassLoader().getResourceAsStream(filePath)){
            if (inputStream == null) {
                throw new IOException("Can't found the file " + filePath + " inside of the .jar");
            }
            return JSON.parseArray(new BufferedInputStream(inputStream));
        }
    }
}

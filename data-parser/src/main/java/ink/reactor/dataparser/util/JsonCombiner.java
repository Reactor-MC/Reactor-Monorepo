package ink.reactor.dataparser.util;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;

/*
 * Combine multiples jsons files in 1.
 * Support folders and output is with pretty format
 * 
 * Example of usage: Combine all blocks tags files in one
 */
public final class JsonCombiner {

    public static void main(String[] args) {
        final boolean orderAlphabetic = true;
        final boolean combineJsonObjectsInOne = true;

        final File input = new File("data-parser/src/main/resources/combiner/input");
        final File output = new File("data-parser/src/main/resources/combiner/output");

        if (!input.exists()) {
            System.out.println("Don't exist any files in " + input);
            return;
        }
        output.mkdir();

        final File[] files = input.listFiles();
        final Map<String, Object> data;

        if (orderAlphabetic) {
            data = new LinkedHashMap<>();
            Arrays.sort(files, (f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()));
        } else {
            data = new HashMap<>();
        }

        for (final File file : files) {
            if (combineJsonObjectsInOne) {
                loadCombine(data, file, "");
                continue;
            }
            load(data, file);
        }

        try(
            final JSONWriter writer = JSONWriter.ofPretty();
            final OutputStream outputStream = Files.newOutputStream(new File(output, "output" + ".json").toPath());
        ) {
            writer.write(data);
            outputStream.write(writer.getBytes());
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load(final Map<String, Object> data, final File file) {
        if (!file.isDirectory()) {
            final JSONObject jsonObject;
            try {
                jsonObject = JSON.parseObject(Files.newInputStream(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            data.put(file.getName().replace(".json", ""), jsonObject);
            return;
        }

        final Map<String, Object> subData = new HashMap<>();
        final String fileName = file.getName();
        data.put(fileName, subData);
        final File[] files = file.listFiles();

        if (files != null) {
            for (final File subFile : files) {
                load(subData, subFile);
            }
        }
    }

    /*
     * Example input:    ------------------->    Output:
     * 
     * "test": {                                 "test/one": {
     *     "one":{                                   "hi": "world"
     *         "hi": "world"                     },
     *     },                                    "test/two": {
     *     "two": {                                  "hi": "world2"
     *         "hi": "world2"                    }
     *     }
     * }
     */
    private static void loadCombine(final Map<String, Object> data, final File file, final String parentKey) {
        if (!file.isDirectory()) {
            final JSONObject jsonObject;
            try {
                jsonObject = JSON.parseObject(Files.newInputStream(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            final String key = parentKey.isEmpty()
                ? file.getName().replace(".json", "") 
                : parentKey + "/" + file.getName().replace(".json", "");
            data.put(key, jsonObject);
            return;
        }

        final File[] files = file.listFiles();
        if (files != null) {
            for (final File subFile : files) {
                loadCombine(data, subFile, parentKey.isEmpty() ? file.getName() : parentKey + "/" + file.getName());
            }
        }
    }
}
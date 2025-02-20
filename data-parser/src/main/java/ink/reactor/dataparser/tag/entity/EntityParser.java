package ink.reactor.dataparser.tag.entity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import ink.reactor.dataparser.Parser;

public final class EntityParser implements Parser {

    @Override
    public void load() throws IOException {
        final Map<String, Integer> entityIds = parseTypes();
        parseCategories(entityIds);
    }

    private Map<String, Integer> parseTypes() throws IOException {
        final String template = loadTemplate("tag/entity/type");
        final JSONArray array = loadJsonArray("tag/entity/types.json");
        final StringBuilder builder = new StringBuilder();
        final Map<String, Integer> entityIds = new HashMap<>(array.size());

        int i = 0;
        for (final Object entry : array) {
            if (!(entry instanceof JSONObject entity)) {
                continue;
            }
            final String name = entity.getString("name");
            final int id = entity.getIntValue("id");
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(name));
            builder.append(" = new EntityType(");

            append(builder,
                name,
                id,
                entity.getDouble("width"),
                entity.getDouble("height")
            );

            builder.append(')');

            if (++i != array.size()) {
                builder.append(',');
            }

            entityIds.put(name, id);
        }

        writeFile(getClass(), template
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
        , "EntityType");

        return entityIds;
    }

    private void parseCategories(Map<String, Integer> entityIds) throws IOException {
        final String template = loadTemplate("tag/entity/category");
        final JSONObject categories = loadJsonObject("tag/entity/categories.json");
        final Map<String, List<String>> categoriesValues = new HashMap<>();

        for (final Entry<String, Object> object : categories.entrySet()) {
            if (!(object.getValue() instanceof JSONObject category)) {
                continue;
            }
            categoriesValues.put(object.getKey(), category.getList("values", String.class));
        }

        for (final List<String> value : categoriesValues.values()) {
            combineValues(value, categoriesValues);
        }

        final StringBuilder builder = new StringBuilder();
        int i = 0;
        for (final Entry<String, List<String>> category : categoriesValues.entrySet()) {
            builder.append('\n');
            builder.append("        ");
            builder.append(toFieldName(category.getKey()));
            builder.append(" = new EntityCategory(");
            append(builder, i, category.getKey());
            builder.append(',');
            int c = 0;
            for (final String value : category.getValue()) {
                final Integer id = entityIds.get(value.split(":")[1]);
                if (id == null) {
                    System.out.println("Can't found the id for the value " + value);
                    c++;
                    continue;
                }
                builder.append((int)id);
                if (++c != category.getValue().size()) {
                    builder.append(',');
                }
            }
            System.out.println(category.getKey() + " - " + category.getValue());

            builder.append(')');

            if (++i != categoriesValues.size()) {
                builder.append(',');
            }
        }


        writeFile(getClass(), template
            .replace("{OBJECT_FIELD_TYPE}", builder.toString())
            .replace("{ALL_CATEGORY_FIELD}", String.valueOf(categoriesValues.size()))
        , "EntityCategory");
    }

    private void combineValues(final List<String> values, final Map<String, List<String>> categories) {
        for (int i = 0; i < values.size(); i++) {
            final String value = values.get(i);
            if (value.charAt(0) != '#') {
                continue;
            }
            final List<String> othervalues = categories.get(value.split(":")[1]);
            if (othervalues == null) {
                System.out.println("Can't found the value " + value + ".");
                continue;
            }
            combineValues(othervalues, categories);

            values.addAll(othervalues);
            values.remove(i);
        }
    }
}

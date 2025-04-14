package ink.reactor.api.config;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class ConfigSection {
    private Map<String, Object> data;

    public ConfigSection() {
        data = new Object2ObjectOpenHashMap<>();
    }

    public Object get(final String key) {
        return data.get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final String key, final Class<T> clazz) {
        final Object object = data.get(key);
        return (object != null && object.getClass().equals(clazz)) ? (T) object : null;
    }

    public <T> T getOrDefault(final String key, final T defaultValue) {
        @SuppressWarnings("unchecked")
        final T value = get(key, (Class<T>) defaultValue.getClass());
        return (value == null) ? defaultValue : value;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(final String key, final Class<T> clazz) {
        if (!(data.get(key) instanceof List<?> list)) {
            return null;
        }
        list.removeIf(next -> next == null || !(next.getClass().equals(clazz)));
        return (List<T>) list;
    }

    public List<String> getStringList(final String key) {
        return getList(key, String.class);
    }

    public String getString(final String key) {
        final Object object = data.get(key);
        return (object != null) ? object.toString() : null;
    }

    public int getInt(final String key) {
        return (data.get(key) instanceof Number number) ? number.intValue() : 0;
    }

    public int getIntMax(final String key, final int max) {
        return Math.max(getInt(key), max);
    }

    public boolean getBoolean(final String key) {
        return (data.get(key) instanceof Boolean b) ? b : false;
    }

    public long getLong(final String key) {
        return (data.get(key) instanceof Number number) ? number.longValue() : 0;
    }

    public double getDouble(final String key) {
        return (data.get(key) instanceof Number number) ? number.doubleValue() : 0;
    }

    public float getFloat(final String key) {
        return (data.get(key) instanceof Number number) ? number.floatValue() : 0;
    }

    @SuppressWarnings("unchecked")
    public ConfigSection getSection(final String key) {
        final Object object = data.get(key);
        return (data.get(key) instanceof Map) ? new ConfigSection((Map<String, Object>) object) : null;
    }

    public Object set(final String key, final Object object) {
        return data.put(key, object);
    }

    // Example: set("key1", "value", "key2", 25, "key3", "value3");
    public void set(final Object... keyAndValues) {
        if (keyAndValues.length % 2 != 0) {
            throw new RuntimeException("Key and values format: key, value, key, value.");
        }
        String keyString = null;
        for (Object object : keyAndValues) {
            if (keyString == null) {
                keyString = object.toString();
                continue;
            }
            data.put(keyString, object);
            keyString = null;
        }
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
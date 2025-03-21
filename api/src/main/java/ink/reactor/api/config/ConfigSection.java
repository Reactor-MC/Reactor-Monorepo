package ink.reactor.api.config;

import java.util.List;
import java.util.Map;

public record ConfigSection(Map<String, Object> data) {

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

    @Override
    public String toString() {
        return data.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (obj instanceof ConfigSection other && other.data.equals(this.data));
    }
}
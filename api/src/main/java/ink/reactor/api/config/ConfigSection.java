package ink.reactor.api.config;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConfigSection  {

    private final Map<String, Object> map;

    public ConfigSection(Map<String, Object> map) {
        this.map = map;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(final String key, final Class<T> clazz) {
        final Object object = map.get(key);
        if (object == null) {
            return null;
        }
        return object.getClass().equals(clazz) ? (T)object : null;
    }

    public Object get(final String key) {
        return map.get(key);
    }

    public <T> T getOrDefault(final String key, final T defaultValue) {
        @SuppressWarnings("unchecked")
        final T value = get(key, (Class<T>)defaultValue.getClass());
        return (value == null) ? defaultValue : value;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(final String key, final Class<T> clazz) {
        if (!(map.get(key) instanceof List list)) {
            return null;
        }
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next == null || !(next.getClass().equals(clazz))) {
                iterator.remove();
            }
        }
        return (List<T>)list;
    }

    @SuppressWarnings("unchecked")
    public List<String> getStringList(final String key) {
        if (!(map.get(key) instanceof List list)) {
            return null;
        }
        final Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next == null || !(next instanceof String)) {
                iterator.remove();
            }
        }
        return (List<String>)list;
    }

    public String getString(final String key) {
        final Object object = map.get(key);
        return (object instanceof String) ? ((String)object) : null;
    }

    public int getInt(final String key) {
        final Object object = map.get(key);
        return (object instanceof Number) ? ((Number)object).intValue() : 0;
    }

    public boolean getBoolean(final String key) {
        final Object object = map.get(key);
        return (object instanceof Boolean) ? ((Boolean)object) : false;
    }

    public double getDouble(final String key) {
        final Object object = map.get(key);
        return (object instanceof Number) ? ((Number)object).doubleValue() : 0;
    }

    @SuppressWarnings("unchecked")
    public ConfigSection getSection(final String key) {
        final Object object = map.get(key);
        return (object instanceof Map) ? new ConfigSection((Map<String, Object>)object) : null;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ConfigSection other) ? other.map.equals(this.map) : false;
    }
}
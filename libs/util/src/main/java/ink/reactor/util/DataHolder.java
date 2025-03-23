package ink.reactor.util;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataHolder<T> {
    private T object;

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }
}

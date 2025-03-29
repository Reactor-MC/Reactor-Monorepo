package ink.reactor.dataparser.common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DataHolder<T> {
    private T object;

    public T get() {
        return object;
    }

    public void set(T object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return object.toString();
    }
}

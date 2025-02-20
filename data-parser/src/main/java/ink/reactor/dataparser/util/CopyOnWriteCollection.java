package ink.reactor.dataparser.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public final class CopyOnWriteCollection<E> implements Collection<E> {

    private E[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public CopyOnWriteCollection(int initialSize) {
        this.array = (E[])new Object[initialSize];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (final E e : array) {
            if (e.equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new CopyOnWriteIterator<E>(this);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    public E[] getArray() {
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <V> V[] toArray(V[] a) {
        if (a.length < array.length) {
            return (V[]) Arrays.copyOf(array, array.length, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, array.length);
        if (a.length > array.length) {
            a[array.length] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        if (size != array.length) {
            array[size++] = e;
            return true;
        }
        @SuppressWarnings("unchecked")
        final E[] copy = (E[]) new Object[array.length+1];
        System.arraycopy(array, 0, copy, 0, array.length);
        copy[array.length] = e;
        this.array = copy;
        return true;
    }

    public E get(final int index) {
        return (index >= array.length || index < 0) ? null : array[index];
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(o)) {
                @SuppressWarnings("unchecked")
                final E[] copy = (E[]) new Object[array.length - 1];
                System.arraycopy(array, 0, copy, 0, i);
                System.arraycopy(array, i + 1, copy, i, array.length - i - 1);
                this.array = copy;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object object : c) {
            if (!contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        int newSize = size+c.size();
        
        if (newSize >= array.length) {
            @SuppressWarnings("unchecked")
            final E[] copy = (E[]) new Object[newSize];
            System.arraycopy(array, 0, copy, 0, array.length);
            this.array = copy;
        }

        for (final E e : c) {
            array[newSize++] = e;
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        for (final Object obj : c) {
            while (remove(obj)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        @SuppressWarnings("unchecked")
        final E[] copy = (E[]) new Object[array.length];
        int index = 0;
        boolean modified = false;
        for (final E e : array) {
            if (c.contains(e)) {
                copy[index++] = e;
            } else {
                modified = true;
            }
        }
        if (modified) {
            this.array = Arrays.copyOf(copy, index);
        }
        return modified;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        array = (E[])new Object[0];
    }

    private static final class CopyOnWriteIterator<E> implements Iterator<E> {

        private final CopyOnWriteCollection<E> collection;

        CopyOnWriteIterator(CopyOnWriteCollection<E> collection) {
            this.collection = collection;
        }

        private E next;
        private int index;

        @Override
        public boolean hasNext() {
            if (index == collection.size) {
                next = null;
                return false;
            }
            next = collection.array[index++];
            return true;
        }

        @Override
        public E next() {
            return next;
        }
    }
}
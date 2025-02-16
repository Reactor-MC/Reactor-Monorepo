package ink.reactor.nbt.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import ink.reactor.nbt.TagNBT;

/**
 * Collection specializated in fast add and iterate
 * @see ink.reactor.nbt.type.NBTFastAdd
 * 
 * Don't recommended for get, remove or addSet operations
 */
public final class NBTCollection implements Collection<TagNBT> {

    private TagNBT[] array;
    private int size;

    private final int increaseThreshold;

    public NBTCollection() {
        this.array = new TagNBT[16];
        this.increaseThreshold = 5;
    }

    public NBTCollection(int initialSize, int increaseThreshold) {
        this.array = new TagNBT[initialSize];
        this.increaseThreshold = increaseThreshold;
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
        for (final TagNBT e : array) {
            if (o.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<TagNBT> iterator() {
        return new NBTIterator(this);
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    public TagNBT[] getArray() {
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
    public boolean add(TagNBT e) {
        if (size != array.length) {
            array[size++] = e;
            return true;
        }
        final TagNBT[] copy = new TagNBT[array.length + increaseThreshold];
        System.arraycopy(array, 0, copy, 0, array.length);
        copy[array.length] = e;
        this.array = copy;
        return true;
    }

    public boolean addOrSet(final TagNBT e) {
        for (int i = 0; i < array.length; i++) {
            final TagNBT tag = array[i];
            if (tag != null && tag.getId() == e.getId() && tag.getKey().equals(e.getKey())) {
                array[i] = e;
                return true;
            }
        }
        if (size+1 == array.length) {
            final TagNBT[] copy = new TagNBT[array.length + increaseThreshold];
            System.arraycopy(array, 0, copy, 0, array.length);
            array = copy;
        }
        array[size++] = e;
        return false;
    }

    public TagNBT get(final int index) {
        return (index >= array.length || index < 0) ? null : array[index];
    }

    public TagNBT get(final Object key) {
        for (final TagNBT tag : array) {
            if (tag != null && tag.getKey().equals(key)) {
                return tag;
            }
        }
        return null;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < array.length; i++) {
            if (o.equals(array[i])) {
                final TagNBT[] copy = new TagNBT[array.length - 1];
                System.arraycopy(array, 0, copy, 0, i);
                System.arraycopy(array, i + 1, copy, i, array.length - i - 1);
                this.array = copy;
                size--;
                return true;
            }
        }
        return false;
    }

    public TagNBT removeKey(final Object key) {
        for (int i = 0; i < array.length; i++) {
            final TagNBT tag = array[i];
            if (tag != null && tag.getKey().equals(key)) {
                final TagNBT[] copy = new TagNBT[array.length - 1];
                System.arraycopy(array, 0, copy, 0, i);
                System.arraycopy(array, i + 1, copy, i, array.length - i - 1);
                this.array = copy;
                size--;
                return tag;
            }
        }
        return null;
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
    public boolean addAll(final Collection<? extends TagNBT> c) {
        final int needSize = size + c.size();
        if (needSize >= array.length) {
            final TagNBT[] copy = new TagNBT[needSize + increaseThreshold];
            System.arraycopy(array, 0, copy, 0, array.length);
            this.array = copy;
        }

        for (final TagNBT e : c) {
            array[size++] = e;
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        boolean modified = false;
        for (final Object obj : c) {
            if (remove(obj)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        final TagNBT[] copy = new TagNBT[array.length];

        int index = 0;
        boolean modified = false;
        for (final TagNBT e : array) {
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

    @Override
    public void clear() {
        array = new TagNBT[0];
        size = 0;
    }

    private static final class NBTIterator implements Iterator<TagNBT> {

        private final NBTCollection collection;

        NBTIterator(NBTCollection collection) {
            this.collection = collection;
        }

        private TagNBT next;
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
        public TagNBT next() {
            return next;
        }
    }

    @Override
    public boolean equals(final Object obj) {
        return obj == this || (obj instanceof NBTCollection nbtCollection && Arrays.equals(nbtCollection.array, this.array));
    }
}
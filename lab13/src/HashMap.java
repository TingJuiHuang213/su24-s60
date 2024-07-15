import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashMap<K, V> implements Map61BL<K, V> {
    private LinkedList<Entry<K, V>>[] array;
    private int size;
    private double loadFactor;

    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) other;
            return key.equals(entry.key) && value.equals(entry.value);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    public HashMap() {
        this(16, 0.75);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, 0.75);
    }

    public HashMap(int initialCapacity, double loadFactor) {
        this.array = (LinkedList<Entry<K, V>>[]) new LinkedList[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (array[index] != null) {
            for (Entry<K, V> entry : array[index]) {
                if (entry.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        if (array[index] != null) {
            for (Entry<K, V> entry : array[index]) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        if ((double) size / array.length >= loadFactor) {
            resize();
        }
        int index = getIndex(key);
        if (array[index] == null) {
            array[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : array[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        array[index].add(new Entry<>(key, value));
        size++;
    }

    @Override
    public V remove(K key) {
        int index = getIndex(key);
        if (array[index] != null) {
            Iterator<Entry<K, V>> iterator = array[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.getKey().equals(key)) {
                    V value = entry.getValue();
                    iterator.remove();
                    size--;
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public boolean remove(K key, V value) {
        int index = getIndex(key);
        if (array[index] != null) {
            Iterator<Entry<K, V>> iterator = array[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.getKey().equals(key) && entry.getValue().equals(value)) {
                    iterator.remove();
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        array = (LinkedList<Entry<K, V>>[]) new LinkedList[array.length];
        size = 0;
    }

    public int capacity() {
        return array.length;
    }

    private int getIndex(K key) {
        return Math.floorMod(key.hashCode(), array.length);
    }

    private void resize() {
        int newCapacity = array.length * 2;
        LinkedList<Entry<K, V>>[] newArray = (LinkedList<Entry<K, V>>[]) new LinkedList[newCapacity];
        for (LinkedList<Entry<K, V>> bucket : array) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    int newIndex = Math.floorMod(entry.getKey().hashCode(), newCapacity);
                    if (newArray[newIndex] == null) {
                        newArray[newIndex] = new LinkedList<>();
                    }
                    newArray[newIndex].add(entry);
                }
            }
        }
        array = newArray;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private class HashMapIterator implements Iterator<K> {
        private int currentBucket;
        private Iterator<Entry<K, V>> bucketIterator;

        HashMapIterator() {
            currentBucket = 0;
            bucketIterator = getBucketIterator();
        }

        private Iterator<Entry<K, V>> getBucketIterator() {
            while (currentBucket < array.length && (array[currentBucket] == null || array[currentBucket].isEmpty())) {
                currentBucket++;
            }
            return (currentBucket < array.length) ? array[currentBucket].iterator() : null;
        }

        @Override
        public boolean hasNext() {
            if (bucketIterator == null) return false;
            if (bucketIterator.hasNext()) return true;
            currentBucket++;
            bucketIterator = getBucketIterator();
            return bucketIterator != null && bucketIterator.hasNext();
        }

        @Override
        public K next() {
            if (!hasNext()) throw new NoSuchElementException();
            return bucketIterator.next().getKey();
        }
    }
}

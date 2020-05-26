import java.util.List;
import java.util.Set;
/**
 * Your implementation of HashMap.
 * 
 * @author Daniel Tadesse
 * @version 1.0
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    @SuppressWarnings("unchecked")
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("You can not use null key or"
                    + " value");
        }
        if (size + 1 >= table.length *  MAX_LOAD_FACTOR) {
            resizeBackingTable((table.length * 2) + 1);
        }

        int h = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> currentEntry = table[h];
        int numProbes = 1;
        int currentIndex = h;
        int firstRemovedIndex = -1;
        while (currentEntry != null) {
            if (currentEntry.getKey().equals(key)) {
                V oldValue = currentEntry.getValue();
                table[currentIndex].setValue(value);
                if (currentEntry.isRemoved()) {
                    table[currentIndex].setRemoved(false);
                    size++;
                    return null;
                }
                return oldValue;
            }
            if (firstRemovedIndex < 0) {
                if (currentEntry.isRemoved()) {
                    firstRemovedIndex = currentIndex;
                }
            }
            if (numProbes > table.length * MAX_LOAD_FACTOR) {
                resizeBackingTable((table.length * 2) + 1);
                numProbes = 0;
                h = Math.abs(key.hashCode()) % table.length;
            }
            currentIndex = (h + (numProbes * numProbes)) % table.length;
            currentEntry = table[currentIndex];
            numProbes++;
        }
        if (firstRemovedIndex != -1) {
            table[firstRemovedIndex] = new MapEntry<>(key, value);
        } else {
            table[currentIndex] = new MapEntry<>(key, value);
        }
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key value not allowed");
        }
        int h = Math.abs(key.hashCode()) % table.length;
        int currentIndex = h;
        int numProbes = 1;
        MapEntry<K, V> currentEntry = table[currentIndex];
        while (currentEntry != null && numProbes <= table.length) {
            if (currentEntry.getKey().equals(key)) {
                if (!currentEntry.isRemoved()) {
                    table[currentIndex].setRemoved(true);
                    --size;
                    return currentEntry.getValue();
                }
                throw new java.util.NoSuchElementException("Key is already"
                        + " removed");
            }
            currentIndex = h + (numProbes * numProbes);
            if (currentIndex >= table.length) {
                currentIndex = currentIndex % table.length;
            }
            currentEntry = table[currentIndex];
            numProbes++;
        }
        throw new java.util.NoSuchElementException("Key does not exist");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key value not allowed");
        }
        int h = Math.abs(key.hashCode()) % table.length;
        int currentIndex = h;
        int numProbes = 1;
        MapEntry<K, V> currentEntry = table[currentIndex];
        while (currentEntry != null && numProbes <= table.length) {
            if (currentEntry.getKey().equals(key)) {
                if (!currentEntry.isRemoved()) {
                    return currentEntry.getValue();
                }
                throw new java.util.NoSuchElementException("Key does not"
                        + " exist");
            }
            currentIndex = (h + (numProbes * numProbes)) % table.length;
            currentEntry = table[currentIndex];
            numProbes++;
        }
        throw new java.util.NoSuchElementException("Key does not exist");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Null key value not allowed");
        }
        int h = Math.abs(key.hashCode()) % table.length;
        int currentIndex = h;
        int numProbes = 1;
        MapEntry<K, V> currentEntry = table[currentIndex];
        while (currentEntry != null && numProbes <= table.length) {
            if (currentEntry.getKey().equals(key)) {
                return !currentEntry.isRemoved();
            }
            currentIndex = (h + (numProbes * numProbes)) % table.length;
            currentEntry = table[currentIndex];
            numProbes++;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new java.util.HashSet<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isRemoved()) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new java.util.ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isRemoved()) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("Invalid length value"
                    + " length <= 0 || length < size ");
        }
        MapEntry<K, V>[] newTable = new MapEntry[length];
        for (MapEntry<K, V> entry : table) {
            if (entry != null) {
                int h = Math.abs(entry.getKey().hashCode()) % newTable.length;
                MapEntry<K, V> currentEntry = newTable[h];
                int numProbes = 1;
                int currentIndex = h;
                while (currentEntry != null) {
                    currentIndex = h + (numProbes * numProbes);
                    currentEntry = newTable[(h + (numProbes * numProbes))
                            % newTable.length];
                    numProbes++;
                }
                newTable[currentIndex % newTable.length] = entry;
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }
}

package project20280.hashtable;

import project20280.interfaces.AbstractMap;
import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a map using an unsorted table.
 */

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    /**
     * Underlying storage for the map of entries.
     */
    private final ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    /**
     * Constructs an initially empty map.
     */
    public UnsortedTableMap() {
    }

    // private utility

    /**
     * Returns the index of an entry with equal key, or -1 if none found.
     */
    private int findIndex(K key) {
        int n = table.size();
        for(int j = 0; j < n; j++) {
            if(table.get(j).getKey().equals(key)) {
                return j;
            }
        }
        return -1;
    }

    // public methods

    /**
     * Returns the number of entries in the map.
     *
     * @return number of entries in the map
     */
    @Override
    public int size() {
        return table.size();
    }

    /**
     * Returns the value associated with the specified key, or null if no such entry
     * exists.
     *
     * @param key the key whose associated value is to be returned
     * @return the associated value, or null if no such entry exists
     */
    // Returns value of the key that is specified.
    @Override
    public V get(K key) {
        int i = findIndex(key);
        if(i == -1) {
            return null;
        }
        return table.get(i).getValue();
    }

    /**
     * Associates the given value with the given key. If an entry with the key was
     * already in the map, this replaced the previous value with the new one and
     * returns the old value. Otherwise, a new entry is added and null is returned.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the key (or null, if no such
     * entry)
     */
    // This replaces the value of the specified key
    @Override
    public V put(K key, V value) {
        int i = findIndex(key);
        // If there is no (hash)map
        if(i == -1) {
            // Creates a new one
            table.add(new MapEntry<>(key, value));
            return null;
        } else {
            // Go to the position of i in the table and set it to the
            // new value.
            return table.get(i).setValue(value);
        }
    }

    /**
     * Removes the entry with the specified key, if present, and returns its value.
     * Otherwise does nothing and returns null.
     *
     * @param key the key whose entry is to be removed from the map
     * @return the previous value associated with the removed key, or null if no
     * such entry exists
     */
    @Override
    public V remove(K key) {
        int i = findIndex(key);
        int n = size();
        if(i < -1) {
            return null;
        }
        V removed = table.get(i).getValue();
        //
        if(i != n - 1) {
            table.set(i, table.get(n-1));
            table.remove(n-1);
        }
        return removed;
    }

    // ---------------- nested EntryIterator class ----------------
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;

        public boolean hasNext() {
            return j < table.size();
        }

        public Entry<K, V> next() {
            if (j == table.size())
                throw new NoSuchElementException("No further entries");
            return table.get(j++);
        }

        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    } // ----------- end of nested EntryIterator class -----------

    // ---------------- nested EntryIterable class ----------------
    private class EntryIterable implements Iterable<Entry<K, V>> {
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    } // ----------- end of nested EntryIterable class -----------

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    public String toString() {
        return table.toString();
    }
}

package project20280.priorityqueue;

/*
 */

import project20280.interfaces.Entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {

    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();

    /**
     * Creates an empty priority queue based on the natural ordering of its keys.
     */
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    // Creates a priority queue with given-value pairs
    public HeapPriorityQueue(K[] keys, V[] values) {
        super();
        for(int j = 0; j < Math.min(keys.length, values.length); j++)
            heap.add(new PQEntry<>(keys[j], values[j]));
        heapify();
    }

    // protected utilities
    protected int parent(int j) {
        return (j-1)/2;
    }

    protected int left(int j) {
        return 2*j +1;
    }

    protected int right(int j) {
        return 2*j +2;
    }

    protected boolean hasLeft(int j) {
        return left(j) < heap.size();
    }

    protected boolean hasRight(int j) {
        return right(j) < heap.size();
    }

    /**
     * Exchanges the entries at indices i and j of the array list.
     */
    protected void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    // Note to self: Goes up the heap
    protected void upheap(int j) {
        while(j > 0) {
            int p = parent(j);
            if(compare(heap.get(j), heap.get(p)) >= 0) break;
            swap(j, p);
            j=p;
        }
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    // Note to self: Goes down the heap
    protected void downheap(int j) {
        while(hasLeft(j)) {
            int leftIndex = left(j);
            int smallChildIndex = leftIndex;
            if(hasRight(j)) {
                int rightIndex = right(j);
                if(compare(heap.get(rightIndex), heap.get(leftIndex)) < 0)
                    smallChildIndex = rightIndex;
            }
            if (compare(heap.get(j), heap.get(smallChildIndex)) <= 0)
                break;
            swap(j, smallChildIndex);
            j = smallChildIndex;
        }
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
       int startIndex = parent(size() - 1);
       for(int j = startIndex; j >= 0; j--)
           downheap(j);
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return heap.size();
    }

    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min() {
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        upheap(heap.size() - 1);
        return newest;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        if(heap.isEmpty()) return null;
        Entry<K,V> answer = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        downheap(0);
        return answer;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    //Q6: Checking time complexity
    // PQsort:
    // 1. Insert all elements into PQ
    // 2. Repeatedly remove top element from PQ -> append on output list
    // -> Output list will be then in sorted order.
    public static <K, V> void PQsort(K[] array, V[] values) {
        // 1. Creates new heap by inserting elements (using a for loop)
        HeapPriorityQueue<K, V> pq= new HeapPriorityQueue<>();
        for(int i = 0; i < array.length; i++) {
            pq.insert(array[i], values[i]);
        }

        // 2. Extract minimum elements in order
        for(int i = 0; i < array.length; i++) {
            Entry<K, V> entry = pq.removeMin();
            array[i] = entry.getKey();
        }
    }

    public static void measureTimePQsort() {
        int[] sizes = {1000, 10000, 1000000};
        for(int n : sizes) {
            Integer[] data = new Integer[n];
            for(int i = 0; i < n; i++)
                data[i] = (int) (Math.random() * n);
            long startTime = System.currentTimeMillis();
            PQsort(data, data);
            long endTime = System.currentTimeMillis();

            System.out.println("Size n, "+ n + " took "+ (endTime - startTime) + "ms");
        }
    }

    // Q7: in-place heapSort
    // Sorts the array without using extra memory for another
    // data structure within the same array.
    public static <K> void heapSortInPlace(K[] array, Comparator<K> comp) {
        int n = array.length;

        // This turns random array to "Max-Heap" structure
        // "Max-Heap": for every node i, the value of the parent is greater than/
        // equals to value of its children
        // Therefore, it sets the highest priority item as the root down to the
        // lowest priority.

        // it starts from the last internal node and work upwards to the root
        for (int i = n / 2 - 1; i >= 0; i--) {
            downheapMax(array, n, i, comp);
        }

        // To sort, it then repeatedly moves the root (max) to the end and restore the heap
        for (int i = n - 1; i > 0; i--) {
            // Swap the current max (at index 0) with the last unsorted element
            K temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // this fixes the heap property for the remaining reduced array
            downheapMax(array, i, 0, comp);
        }
    }

    // This keeps the most important item at the top
    // It starts with a low priority value and checks with its children and see who is the biggest
    // then if the child is bigger, they exchange places
    // -> bigger value moves up (vice versa)
    private static <K> void downheapMax(K[] array, int size, int i, Comparator<K> comp) {
        while (true) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            // Check children
            if (left < size && comp.compare(array[left], array[largest]) > 0)
                largest = left;
            if (right < size && comp.compare(array[right], array[largest]) > 0)
                largest = right;

            // If the parent is already the biggest, stop!
            if (largest == i) break;

            // Otherwise, swap and move down
            K temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;

            i = largest; // Update i to the new position to keep looping
        }
    }

    public static void measureTimeheapSortInPlace() {
        int[] sizes = {1000, 10000, 1000000};
        for(int n : sizes) {
            Integer[] data = new Integer[n];
            for(int i = 0; i < n; i++)
                data[i] = (int) (Math.random() * n);
            long startTime = System.currentTimeMillis();
            heapSortInPlace(data, Integer::compare);
            long endTime = System.currentTimeMillis();

            System.out.println("Size n, "+ n + " took "+ (endTime - startTime) + "ms");
        }
    }

    public static void main(String[] args) {
        Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        System.out.println("elements: " + Arrays.toString(rands));
        System.out.println("after adding elements: " + pq);

        System.out.println("min element: " + pq.min());

        pq.removeMin();
        System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]

        // According to my findings,
        // the larger the size of the PQ is, the longer it takes for the
        // PQ sort to sort the array
        System.out.println("PQsort: ");
        measureTimePQsort();
        System.out.println("In Place heap-sort");
        measureTimeheapSortInPlace();
    }
}

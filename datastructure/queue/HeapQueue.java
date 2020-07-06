package datastructure.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * an implementation of PriorityQueue
 * @author sqzhang
 * @year 2020
 */
@SuppressWarnings("unchecked")
public class HeapQueue<T> implements Queue<T> {

    private Object[] queue;

    private int size = 0;

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    private final Comparator<? super T> comparator;

    public HeapQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public HeapQueue(int capacity) {
        this(capacity, null);
    }

    public HeapQueue(Comparator<? super T> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public HeapQueue(int capacity, Comparator<? super T> comparator) {
        this.queue = new Object[capacity];
        this.comparator = comparator;
    }

    private void grow() {
        int oldCapacity = queue.length;
        // Double size if small; else grow by 50%
        int newCapacity = oldCapacity + ((oldCapacity < 64) ?
            (oldCapacity + 2) :
            (oldCapacity >> 1));
        queue = Arrays.copyOf(queue, newCapacity);
    }

    @Override
    public void offer(T e) {
        if (e == null)
            throw new NullPointerException();
        int i = size;
        if (i >= queue.length)
            grow();
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
    }

    private void siftUp(int k, T x) {
        if (comparator != null)
            siftUpUsingComparator(k, x);
        else
            siftUpComparable(k, x);
    }

    private void siftUpComparable(int k, T x) {
        Comparable<? super T> key = (Comparable<? super T>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((T) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    private void siftUpUsingComparator(int k, T x) {
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (comparator.compare(x, (T) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = x;
    }

    @Override
    public T poll() {
        if (size == 0)
            return null;
        int s = --size;
        T result = (T) queue[0];
        T x = (T) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }

    private void siftDown(int k, T x) {
        if (comparator != null)
            siftDownUsingComparator(k, x);
        else
            siftDownComparable(k, x);
    }

    private void siftDownComparable(int k, T x) {
        Comparable<? super T> key = (Comparable<? super T>)x;
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Comparable<? super T>) c).compareTo((T) queue[right]) > 0)
                c = queue[child = right];
            if (key.compareTo((T) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    private void siftDownUsingComparator(int k, T x) {
        int half = size >>> 1;
        while (k < half) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                comparator.compare((T) c, (T) queue[right]) > 0)
                c = queue[child = right];
            if (comparator.compare(x, (T) c) <= 0)
                break;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    @Override
    public T peek() {
        return (size == 0) ? null : (T) queue[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i = 0;i < size;i++) {
            sb.append(queue[i].toString());
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public static void main(String[] args) {
        HeapQueue<Integer> minHeapQueue = new HeapQueue<>();
        HeapQueue<Integer> maxHeapQueue = new HeapQueue<>((a, b) -> b - a);
        Random rand = new Random();
        for(int i = 0;i < 10;i++) {
            minHeapQueue.offer(rand.nextInt(100));
            maxHeapQueue.offer(rand.nextInt(100));
        }
        System.out.println(minHeapQueue);


        for(int i = 0;i < 10;i++) {
            System.out.print(minHeapQueue.poll() + ",");
        }
        System.out.println();

        System.out.println(maxHeapQueue);
        for(int i = 0;i < 10;i++) {
            System.out.print(maxHeapQueue.poll() + ",");
        }
        System.out.println();
    }
}

package datastructure.queue;

/**
 * @author sqzhang
 * @year 2020
 */
public interface Queue<T> {
    void offer(T ele);
    T poll();
    T peek();
    int size();
    boolean isEmpty();
}

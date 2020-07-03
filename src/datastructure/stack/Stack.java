package datastructure.stack;

/**
 * @author sqzhang
 * @year 2020
 */
public interface Stack<T> {
    int size();

    boolean isEmpty();

    void push(T ele);

    T pop();

    T peek();
}

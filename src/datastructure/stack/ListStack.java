package datastructure.stack;

import datastructure.linkedlist.DoubleLinkedList;
import java.util.EmptyStackException;
import java.util.Random;

/**
 * @author sqzhang
 * @year 2020
 */
public class ListStack<T> implements Stack<T> {

    DoubleLinkedList<T> list = new DoubleLinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(T ele) {
        list.addLast(ele);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.peekLast();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public static void main(String[] args) {
        ListStack<Integer> stack = new ListStack<>();
        Random rand = new Random();
        for(int i = 0;i < 10;i++) {
            stack.push(rand.nextInt(100));
        }
        System.out.println(stack);

        for(int i = 0;i < 10;i++) {
            System.out.print(stack.pop() + ",");
        }
        System.out.println();
    }
}

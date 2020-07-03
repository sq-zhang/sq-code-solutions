package datastructure.queue;

/**
 * an implementation of Array Queue
 * @author sqzhang
 * @year 2020
 */
public class IntQueue implements Queue<Integer> {

    private int[] data;
    private int front, end;
    private int size;

    public IntQueue(int capacity) {
        data = new int[capacity];
        front = end = size = 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public void offer(Integer value) {
        if (isFull()) {
            throw new RuntimeException("queue is full");
        }
        data[end++] = value;
        size++;
        end = end % data.length;
    }

    @Override
    public Integer poll() {
        if (isEmpty()) {
            throw new RuntimeException("queue is full");
        }
        size--;
        front = front % data.length;
        return data[front++];
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        }
        return data[front % data.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {

        IntQueue q = new IntQueue(5);

        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);

        System.out.println(q.poll()); // 1
        System.out.println(q.poll()); // 2
        System.out.println(q.poll()); // 3
        System.out.println(q.poll()); // 4

        System.out.println(q.isEmpty()); // false

        q.offer(1);
        q.offer(2);
        q.offer(3);

        System.out.println(q.poll()); // 5
        System.out.println(q.poll()); // 1
        System.out.println(q.poll()); // 2
        System.out.println(q.poll()); // 3

        System.out.println(q.isEmpty()); // true
    }
}

package datastructure.linkedlist;

/**
 * an implementation of double end linked list
 * @author sqzhang
 * @year 2020
 */
public class DoubleLinkedList<T> implements Iterable<T> {

    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    private static class Node<T> {
        private T data;
        private Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(T ele) {
        addLast(ele);
    }

    public void addLast(T ele) {
        Node<T> node = new Node<>(ele, null, null);
        if (isEmpty()) {
            head = tail = node;
            head.next = tail;
            tail.prev = head;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        }
        size++;
    }

    public void addFirst(T ele) {
        Node<T> node = new Node<>(ele, null, null);
        if (isEmpty()) {
            head = tail = node;
            head.next = tail;
            tail.prev = head;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            addFirst(data);
            return;
        }

        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> target = head;
        for (int i = 0; i < index - 1; i++) {
            target = target.next;
        }
        Node<T> node = new Node<>(data, target, target.next);
        target.next.prev = node;
        target.next = node;

        size++;
    }

    public T peekFirst() {
        return isEmpty() ? null : head.data;
    }

    public T peekLast() {
        return isEmpty() ? null : tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty list");
        }

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    private T remove(Node<T> node) {
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        node.next.prev = node.prev;
        node.prev.next = node.next;

        --size;
        return node.data;
    }

    public boolean remove(Object obj) {
        Node<T> cur;

        if (obj == null) {
            for (cur = head; cur != null; cur = cur.next) {
                if (cur.data == null) {
                    remove(cur);
                    return true;
                }
            }
        } else {
            for (cur = head; cur != null; cur = cur.next) {
                if (obj.equals(cur.data)) {
                    remove(cur);
                    return true;
                }
            }
        }
        return false;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> cur;

        if (index < size / 2) {
            for (i = 0, cur = head; i != index; i++) {
                cur = cur.next;
            }
        } else{
            for (i = size - 1, cur = tail; i != index; i--) {
                cur = cur.prev;
            }
        }

        return remove(cur);
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<T> cur = head;

        if (obj == null) {
            for (; cur != null; cur = cur.next, index++) {
                if (cur.data == null) {
                    return index;
                }
            }
        } else
            for (; cur != null; cur = cur.next, index++) {
                if (obj.equals(cur.data)) {
                    return index;
                }
            }

        return -1;
    }

    // Check is a value is contained within the linked list
    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> cur = head;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public T next() {
                T data = cur.data;
                cur = cur.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        if (head == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node<T> cur = head;
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) {
                sb.append(" -> ");
            }
            cur = cur.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.add(1);
        list.add(2);
        list.addFirst(0);
        list.addLast(3);

        System.out.println(list);
        list.remove(2);
        list.removeAt(2);

        System.out.println(list);
        list.removeAt(0);
        list.removeAt(0);
        System.out.println(list);
    }
}

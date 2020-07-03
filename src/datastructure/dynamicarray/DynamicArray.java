package datastructure.dynamicarray;

/**
 * an implementation of dynamic array
 * @author sqzhang
 * @year 2020
 */
@SuppressWarnings("unchecked")
public class DynamicArray<T> {

    private T[] data;
    private int size;
    private int capacity;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity can not be negative");
        }
        this.capacity = capacity;
        this.data = (T[]) new Object[capacity];
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return data[index];
    }

    public void set(int index, T ele) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = ele;
    }

    public void clear() {
        for(int i = 0;i < size;i++) {
            data[i] = null;
        }
        size = 0;
    }

    public void add(T ele) {
        if (size + 1 >= capacity) {
            if (capacity == 0) {
                capacity = 1;
            } else {
                capacity <<= 1;
            }
            T[] arr = (T[]) new Object[capacity];
            System.arraycopy(data, 0, arr, 0, size);
            data = arr;
        }
        data[size++] = ele;
    }

    public boolean remove(T obj) {
        int index = indexOf(obj);
        if (index == -1) {
            return false;
        }
        removeAt(index);
        return true;
    }

    public T removeAt(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        T ele = data[index];
        T[] arr = (T[]) new Object[size - 1];
        for (int i = 0, j = 0; i < size; i++, j++)
            if (i == index) {
                j--;
            } else {
                arr[j] = data[i];
            }
        data = arr;
        capacity = --size;
        return ele;
    }

    public int indexOf(T obj) {
        for (int i = 0; i < size; i++) {
            if (obj == null) {
                if (data[i] == null) {
                    return i;
                }
            } else {
                if (obj.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean contains(T obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder().append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        return sb.append(data[size - 1]).append("]").toString();
    }

    public static void main(String[] args) {
        DynamicArray<String> dynamicArray = new DynamicArray<>();
        dynamicArray.add("dynamic");
        dynamicArray.add("array");

        System.out.println(dynamicArray);

        int index = dynamicArray.indexOf("array");
        dynamicArray.removeAt(index);

        System.out.println(dynamicArray);

        dynamicArray.remove("dynamic");

        System.out.println(dynamicArray);
    }
}

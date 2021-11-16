package train.com.array;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {
    private Object[] arr = new Object[10];
    //size=lastnamber+1;
    private int size = 0;

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    @Override
    public void add(Object element) {
        if (size == arr.length) {
            increaseSize();
        }
        arr[size] = element;
        size++;
    }

    @Override
    public void set(int index, Object element) {
        if (index >= 0 & index < size) {
            arr[index] = element;
        }
    }

    @Override
    public Object get(int index) {
        if (index < size & index >= 0) {
            return arr[index];
        }
        return null;
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(arr[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index >= 0 & index < size) {
            for (int i = index; i < size - 1; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
        }
    }

    @Override
    public String toString() {
        String out = "[";
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                out += arr[i] + ", ";
            } else
                out += arr[i];
        }
        out += "]";
        return out;
    }

    private void increaseSize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    public static void main(String[] args) {
        ArrayImpl arr = new ArrayImpl();
        arr.add("A");
        arr.add("B");
        arr.add("C");
        arr.add("D");
        arr.add("E");
        arr.add("F");
        System.out.println(arr);
        System.out.println("size result: " + arr.size());
        arr.set(2, "K");
        System.out.println("set result: " + arr);
        System.out.println("get result: " + arr.get(2));
        System.out.println("indexOf result: " + arr.indexOf("E"));
        arr.remove(1);
        System.out.println("remove result: " + arr);
        System.out.println("work with iterator: ");
        Iterator iterator = arr.iterator();
        while (iterator.hasNext()) {
            System.out.print(" " + iterator.next());
        }
        System.out.println();
        arr.clear();
        System.out.println("clear result: " + arr);

    }


    class IteratorImpl implements Iterator {
        private int iter = 0;

        @Override
        public boolean hasNext() {
            if (iter < size) {
                return true;
            } else
                return false;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("array out of bounds");
            }
            Object nextElem = arr[iter];
            iter++;
            return nextElem;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}
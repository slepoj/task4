package train.com.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {
    private Node head;
    private Node last;
    private int size = 0;


    @Override
    public void clear() {
        for (; head != null; head = head.nextNode){
            Node itr = head;
            itr.obj = null;
            itr.nextNode = null;
        }
        last = null;
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
    public void enqueue(Object element) {
        Node node = new Node(element, null);
        if (last == null){
            head = node;
        } else {
            last.nextNode = node;
        }
        last = node;
        size++;
    }

    @Override
    public Object dequeue() {
        Object obj = head.obj;
        head = head.nextNode;
        size--;
        return obj;
    }

    @Override
    public Object top() {
        return head.obj;
    }

    @Override
    public String toString() {
        String str = "[";
        Node itr = head;
        while (itr != null){
            if (itr.nextNode != null){
                str += itr.obj + ", ";
            } else {
                str += itr.obj;
            }
            itr = itr.nextNode;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        System.out.println("Queue demo:");
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        System.out.println(queue);
        System.out.println("size result: " + queue.size);
        System.out.println("top result: " + queue.top());
        System.out.println("dequeue result: " + queue.dequeue());
        System.out.println(queue);
        System.out.println("iterator result:");
        Iterator itr = queue.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        queue.clear();
        System.out.println("clear result: " + queue);
        System.out.println();
    }

    private class Node {
        private Object obj;
        private Node nextNode;

        public Node(Object obj, Node nextNode) {
            this.obj = obj;
            this.nextNode = nextNode;
        }
    }

    class IteratorImpl implements Iterator {
        private Node iter = head;

        @Override
        public boolean hasNext() {
            if (iter != null) {
                return true;
            } else
                return false;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException("array out of bounds");
            }
            Node nextNode = iter;
            iter = nextNode.nextNode;
            return nextNode.obj;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

}

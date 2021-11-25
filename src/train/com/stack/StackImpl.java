package train.com.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {
    private Node head;
    private int size = 0;


    @Override
    public void clear() {
        for (; head != null; head = head.nextNode){
            Node itr = head;
            itr.obj = null;
            itr.nextNode = null;
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
    public void push(Object element) {
        Node node = new Node(element, null);
        if (head != null){
            node.nextNode= head;
        }
        head = node;
        size++;
    }

    @Override
    public Object pop() {
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
        String str = "";
        Node itr = head;
        while (itr != null){
            if (!str.equals("")){
                str = itr.obj + ", " + str;
            } else {
                str = itr.obj + str;
            }
            itr = itr.nextNode;
        }
        str ="[" + str + "]";
        return str;
    }

    public static void main(String[] args) {
        System.out.println("Stack demo:");
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.push("E");
        stack.push("F");
        System.out.println(stack);
        System.out.println("size result: " + stack.size);
        System.out.println("top result: " + stack.top());
        System.out.println("pop result: " + stack.pop());
        System.out.println(stack);
        System.out.println("iterator result:");
        Iterator itr = stack.iterator();
        while (itr.hasNext()){
            System.out.print(itr.next() + " ");
        }
        System.out.println();
        stack.clear();
        System.out.println("clear result: " + stack);
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

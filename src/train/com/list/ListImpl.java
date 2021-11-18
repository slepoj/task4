package train.com.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node nodeFirst;
    private Node nodeLast;
    private int size = 0;

    @Override
    public void clear() {
        Node itr = nodeFirst;
        while (itr.nextNode != null) {
            itr = itr.nextNode;
            itr.prevsNode.nextNode = null;
            itr.prevsNode.prevsNode = null;
            itr.prevsNode.obj = null;
        }
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
    public void addFirst(Object element) {
        Node node = new Node(element, nodeFirst, null);
        if (nodeLast == null) {
            nodeLast = node;
        } else nodeFirst.prevsNode = node;
        nodeFirst = node;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node node = new Node(element, null, nodeLast);
        if (nodeLast == null) {
            nodeFirst = node;
        } else nodeLast.nextNode = node;
        nodeLast = node;
        size++;
    }

    @Override
    public void removeFirst() {
        if (nodeFirst != null) {
            if (nodeFirst.equals(nodeLast)) {
                nodeFirst = null;
                nodeLast = null;
            } else {
                nodeFirst = nodeFirst.nextNode;
                nodeFirst.prevsNode = (null);
            }
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (nodeFirst != null) {
            if (nodeFirst.equals(nodeLast)) {
                nodeFirst = null;
                nodeLast = null;
            } else {
                nodeLast = nodeLast.prevsNode;
                nodeLast.nextNode = (null);
            }
            size--;
        }
    }

    @Override
    public Object getFirst() {
        return nodeFirst.obj;
    }

    @Override
    public Object getLast() {
        return nodeLast.obj;
    }

    @Override
    public Object search(Object element) {
        Node itr = nodeFirst;
        while (itr != null) {
            if (itr.obj.equals(element)) {
                return itr.obj;
            }
            itr = itr.nextNode;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        Node itr = nodeFirst;
        for (; itr != null; itr = itr.nextNode) {
            if (itr.obj.equals(element)) {
                unlink(itr);
            }
        }

        /*if (nodeFirst.obj.equals(element)) {
            removeFirst();
            return true;
        }
        if (nodeLast.obj.equals(element)) {
            removeLast();
            return true;
        }
        Node itr = nodeFirst;
        while (itr != null) {
            if (itr.obj.equals(element)) {
                itr.prevsNode.nextNode = itr.nextNode;
                itr.nextNode.prevsNode = itr.prevsNode;
                return true;
            }
            itr = itr.nextNode;
        }*/
        return false;
    }

    private void unlink(Node delEL) {
        if (delEL.prevsNode == null) {
            nodeFirst = delEL.nextNode;
        } else
            delEL.prevsNode.nextNode = delEL.nextNode;
        if (delEL.nextNode == null) {
            nodeLast = delEL.prevsNode;
        } else
            delEL.nextNode.prevsNode = delEL.prevsNode;

        delEL.prevsNode = null;
        delEL.nextNode = null;
        delEL.obj = null;
        size--;
    }

    @Override
    public String toString() {
        String str = "[";
        Node itr = nodeFirst;
        while (itr != null) {
            if (itr.nextNode == null) {
                str += itr.obj;
            } else str += itr.obj + ", ";
            itr = itr.nextNode;
        }
        str += "]";
        return str;
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        LinkedList list1 = new LinkedList<>();
        /*list.addFirst("A");
        list.addFirst("B");
        list.addFirst("C");
        list.addLast("D");
        list.addLast("E");
        System.out.println(list);
        System.out.println("size is " + list.size);
        list.remove("C");
        list.removeFirst();
        list.removeLast();
        System.out.println("remove result: " + list);
        System.out.println("getFirst result: " + list.getFirst());
        System.out.println("getLast result: " + list.getLast());
        System.out.println("search result: " + list.search("D"));
        System.out.println("work with iterator: ");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        list.clear();
        System.out.println("clear result: " + list);*/
    }

    class IteratorImpl implements Iterator {
        private Node iter = nodeFirst;

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

    private static class Node {
        private Object obj;
        private Node nextNode;
        private Node prevsNode;

        public Node(Object obj, Node nextNode, Node prevsNode) {
            this.obj = obj;
            this.nextNode = nextNode;
            this.prevsNode = prevsNode;
        }
    }
}

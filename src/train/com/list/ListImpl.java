package train.com.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {
    private Node nodeFirst;
    private Node nodeLast;
    private int size = 0;

    @Override
    public void clear() {
        nodeLast = null;
        nodeFirst = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        IteratorImpl iter = new IteratorImpl(nodeFirst);
        return iter;
    }

    @Override
    public void addFirst(Object element) {
        if (nodeFirst == null){
            Node node = new Node(element);
            nodeFirst = node;
            nodeLast = node;
            size++;
            return;
        }
        Node node = new Node(element, nodeFirst, null);
        nodeFirst.setPrevsNode(node);
        nodeFirst = node;
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (nodeLast == null){
            addFirst(element);
            return;
        }
        Node node = new Node(element, null, nodeLast);
        nodeLast.setNextNode(node);
        nodeLast = node;
        size++;
    }

    @Override
    public void removeFirst() {
        nodeFirst = nodeFirst.getNextNode();
        nodeFirst.setPrevsNode(null);
    }

    @Override
    public void removeLast() {
        nodeLast = nodeLast.getPrevsNode();
        nodeLast.setNextNode(null);
    }

    @Override
    public Object getFirst() {
        return nodeFirst;
    }

    @Override
    public Object getLast() {
        return nodeLast;
    }

    @Override
    public Object search(Object element) {
        Node itr = nodeFirst;
        while (itr!=null){
            if (itr.getObj().equals(element)){
                return itr;
            } itr = itr.getNextNode();
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (nodeFirst.getObj().equals(element)){
            removeFirst();
            return true;
        }
        if (nodeLast.getObj().equals(element)){
            removeLast();
            return true;
        }
        Node itr = nodeFirst;
        while (itr!=null){
            if (itr.getObj().equals(element)){
                itr.getPrevsNode().setNextNode(itr.getNextNode());
                itr.getNextNode().setPrevsNode(itr.getPrevsNode());
                return true;
            } itr = itr.getNextNode();
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "[";
        Node itr = nodeFirst;
        while (itr!=null){
            if (itr.getNextNode() == null){
                str += itr.getObj() + "]";
                return str;
            } str += itr.getObj() + ", ";
            itr = itr.getNextNode();
        } str += "]";
        return str;
    }

    public static void main(String[] args) {
        ListImpl list = new ListImpl();
        list.addFirst("A");
        list.addLast("B");
        list.addLast("C");
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
        Node serh = (Node) list.search("D");
        System.out.println("search result: " + serh.getObj());
        System.out.println("work with iterator: ");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+ " ");
        }
        System.out.println();
        list.clear();
        System.out.println("clear result: " + list);

    }

    class IteratorImpl implements Iterator {
        private Node iter;

        public IteratorImpl(Node iter) {
            this.iter = iter;
        }

        @Override
        public boolean hasNext() {
            if (iter != null){
                return true;
            } else
            return false;
        }

        @Override
        public Object next() {
            if (iter == null){
                throw new NoSuchElementException("array out of bounds");
            }
            Node nextNode = iter;
            iter = nextNode.getNextNode();
            return nextNode.obj;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

    }

    static class Node {
        private Object obj;
        private Node nextNode = null;
        private Node prevsNode = null;

        public Node(Object obj) {
            this.obj = obj;
        }

        public Node(Object obj, Node nextNode, Node prevsNode) {
            this.obj = obj;
            this.nextNode = nextNode;
            this.prevsNode = prevsNode;
        }

        public Object getObj() {
            return obj;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevsNode() {
            return prevsNode;
        }

        public void setPrevsNode(Node prevsNode) {
            this.prevsNode = prevsNode;
        }
    }
}

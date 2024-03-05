package linkedlists;

import javax.swing.text.ElementIterator;
import java.util.Iterator;

public class CircularlyLinkedList<E>  implements Iterable<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }

        public E getElement(){
            return element;
        }
        public Node<E> getNext(){
            return next;
        }

        public void setNext(Node<E> n){
            next = n;
        }


    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public E first(){
        if(isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last(){
        if (isEmpty()) return null;
        return tail.getElement();
    }
    public void rotate(){
        if(tail != null)
            tail = tail.getNext();
    }
    public void addFirst(E e){
        if (size == 0){
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    public void addLast(E e){
        addFirst(e);
        rotate();
    }
    public E removeFirst(){
        if(isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (tail == head) tail = null;
        else tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }
    public String toString() {
        if(tail == null) return "()";
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = tail;
        do {
            walk = walk.getNext();
            sb.append(walk.getElement());
            if(walk==tail)
                sb.append(")");
            } while (walk != tail);
        return sb.toString();
    }

    public Iterator<E> iterator() {
        return new ElementIterator(this);
    }

    private class ElementIterator implements Iterator<E>{
        private CircularlyLinkedList cll;
        private int cursor;
        private Node<E> cursorPointer;
        public ElementIterator(CircularlyLinkedList<E> m){
            cll = m;
            cursor = 0;
            cursorPointer = cll.tail;
        }
        @Override
        public boolean hasNext() {
            return cursor < cll.size;
        }
        @Override
        public E next() {
            cursor += 1;
            cursorPointer = cursorPointer.getNext();
            return cursorPointer.getElement();
        }
    }
    public static void main(String[] args)
    {

        //(LAX, MSP, ATL, BOS)
        CircularlyLinkedList<String> circularList = new CircularlyLinkedList<String>();
        circularList.addFirst("LAX");
        circularList.addLast("MSP");
        circularList.addLast("ATL");
        circularList.addLast("BOS");
        //

        //example loop directly using "hasNext()"
        Iterator<String> it = circularList.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();

        //example loop using "special for" (often called "for-each" loop)
        for(String s : circularList) {
            System.out.println(s);
        }
    }
}


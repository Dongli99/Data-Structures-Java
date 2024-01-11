package linkedlists;
public class DoublyLinkedList<E> {
    // PROPERTIES
    private Node<E> header;
    private Node<E> trailer;
    private int size=0;

    // getters
    public Node<E> getHeader() { return header;	}
    public Node<E> getTrailer() { return trailer; }
    public int getSize() { return size; }

    // CONSTRUCTORS
    public DoublyLinkedList() { // a basic constructor
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(header, null, null);
        header.setNext(trailer);
    }

    @SafeVarargs
    public DoublyLinkedList(E... elements) {
        // a second constructor which can build
        this();
        for(E e: elements) {
            this.addLast(e);
        }
    }

    // METHODS

    // check if the list is empty
    public boolean isEmpty() { return size==0; }

    // add
    public void addFirst(E e) {
        Node<E> second = header.getNext();
        addBetween(e, header, second);
    }

    public void addLast(E e) {
        Node<E> penultimate = trailer.getPrev();
        addBetween(e, penultimate, trailer);
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<E>(predecessor, e, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    // remove
    public E removeFirst() {
        if (isEmpty())	return null;
        Node<E> first = header.getNext();
        return remove(first);
    }

    public E removeLast() {
        if (isEmpty())	return null;
        Node<E> last = trailer.getPrev();
        return remove(last);
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        if (predecessor != null) predecessor.setNext(successor); // except header removal
        if(successor != null) successor.setPrev(predecessor); // except trailer removal
        if (predecessor != null && successor != null)size--; // except header or trailer removal
        return node.getElem();
    }

    // to string
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = header.getNext();
        while (walk != trailer) {
            sb.append(walk.getElem());
            walk = walk.getNext();
            if (walk != trailer)
                sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }

    // concatenate
    public DoublyLinkedList<E> concat(DoublyLinkedList<E> secondList) {
        // identify the nodes that need to be connected
        Node<E> firstEnd = this.trailer.getPrev();
        Node<E> secondStart = secondList.getHeader().getNext();
        // remove trailer from firstList, and header from secondList
        this.remove(trailer);
        secondList.remove(secondList.getHeader());
        // connect the two nodes.
        firstEnd.setNext(secondStart);
        secondStart.setPrev(firstEnd);
        // set trailer of the first to the trailer of the second.
        this.trailer = secondList.getTrailer();
        // increase the size.
        this.size+=secondList.getSize();
        return this;
    }

    // PRIVATE NODE CLASS
    private class Node<E> {
        // 3 parts of one node
        private Node<E> prev;
        private E elem;
        private Node<E> next;

        // constructor
        public Node (Node<E> prev, E elem, Node<E> next){
            this.prev = prev;
            this.elem = elem;
            this.next = next;
        }

        // getters and setters

        public Node<E> getPrev() { return prev; }
        public void setPrev(Node<E> prev) { this.prev = prev; }
        public E getElem() { return elem; }
        public void setElem(E elem) { this.elem = elem; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> next) { this.next = next; }
    }
}

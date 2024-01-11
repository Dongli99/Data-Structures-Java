package linkedlists;

/**
 * A DoublyLinkedList is a list can be traversed either forwards or backwards
 * @param <E> The class utilize Generic type.
 */
public class DoublyLinkedList<E> {
    // PROPERTIES
    /**
     * The header node starts the doublyList with prev null.
     */
    private Node<E> header;
    /**
     * The trailer node ends the doublyList with next null.
     */
    private Node<E> trailer;
    /**
     * The number of nodes in the list (exclude header and trailer);
     */
    private int size=0;

    // getters

    /**
     * Gets the header node of the doubly linked list.
     * @return The header node.
     */
    public Node<E> getHeader() { return header;	}
    /**
     * Gets the trailer node of the doubly linked list.
     * @return The trailer node.
     */
    public Node<E> getTrailer() { return trailer; }
    /**
     * Gets the size of the doubly linked list.
     * @return The size of the list.
     */
    public int getSize() { return size; }

    // CONSTRUCTORS
    /**
     * Constructs a basic DoublyLinkedList with header and trailer nodes.
     */
    public DoublyLinkedList() { // a basic constructor
        header = new Node<E>(null, null, null);
        trailer = new Node<E>(header, null, null);
        header.setNext(trailer);
    }
    /**
     * Constructs a DoublyLinkedList with the provided elements.
     * @param elements Elements to be added to the list.
     */
    @SafeVarargs
    public DoublyLinkedList(E... elements) {
        // a second constructor which can build
        this();
        for(E e: elements) {
            this.addLast(e);
        }
    }

    // METHODS

    /**
     * Checks if the doubly linked list is empty.
     * @return True if the list is empty, false otherwise.
     */
    public boolean isEmpty() { return size==0; }

    /**
     * Adds an element to the beginning of the doubly linked list.
     * @param e The element to be added.
     */    public void addFirst(E e) {
        Node<E> second = header.getNext();
        addBetween(e, header, second);
    }
    /**
     * Adds an element to the end of the doubly linked list.
     * @param e The element to be added.
     */
    public void addLast(E e) {
        Node<E> penultimate = trailer.getPrev();
        addBetween(e, penultimate, trailer);
    }
    /**
     * Adds an element between two existing nodes in the doubly linked list.
     * @param e           The element to be added.
     * @param predecessor The node that comes before the new node.
     * @param successor   The node that comes after the new node.
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newNode = new Node<E>(predecessor, e, successor);
        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    /**
     * Removes and returns the first element from the doubly linked list.
     * @return The removed element, or null if the list is empty.
     */
    public E removeFirst() {
        if (isEmpty())	return null;
        Node<E> first = header.getNext();
        return remove(first);
    }
    /**
     * Removes and returns the last element from the doubly linked list.
     * @return The removed element, or null if the list is empty.
     */
    public E removeLast() {
        if (isEmpty())	return null;
        Node<E> last = trailer.getPrev();
        return remove(last);
    }

    /**
     * Removes a node from the doubly linked list and returns its stored element.
     * @param node The node to be removed.
     * @return The element stored in the removed node, or null if the list is empty.
     */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        if (predecessor != null) predecessor.setNext(successor); // except header removal
        if(successor != null) successor.setPrev(predecessor); // except trailer removal
        if (predecessor != null && successor != null)size--; // except header or trailer removal
        return node.getElem();
    }

    /**
     * Returns a string representation of the doubly linked list.
     * @return The string representation of the list.
     */
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

    /**
     * Concatenates the current doubly linked list with another one.
     * @param secondList The second doubly linked list to be concatenated.
     * @return The current doubly linked list after concatenation.
     */
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

    /**
     * Represents a node in the doubly linked list.
     *
     * @param <E> The type of data stored in the node.
     */
    private static class Node<E> {
        // 3 parts of one node
        private Node<E> prev;
        private E elem;
        private Node<E> next;

        /**
         * Constructs a node with the given previous node, element, and next node.
         *
         * @param prev The previous node.
         * @param elem The element stored in the node.
         * @param next The next node.
         */
        public Node (Node<E> prev, E elem, Node<E> next){
            this.prev = prev;
            this.elem = elem;
            this.next = next;
        }

        // getters and setters
        /**
         * Gets the previous node.
         * @return The previous node.
         */
        public Node<E> getPrev() { return prev; }
        /**
         * Sets the previous node.
         * @param prev The new previous node.
         */
        public void setPrev(Node<E> prev) { this.prev = prev; }
        /**
         * Gets the element stored in the node.
         * @return The element stored in the node.
         */
        public E getElem() { return elem; }
        /**
         * Sets the element stored in the node.
         * @param elem The new element to be stored in the node.
         */
        public void setElem(E elem) { this.elem = elem; }
        /**
         * Gets the next node.
         *
         * @return The next node.
         */
        public Node<E> getNext() { return next; }
        /**
         * Sets the next node.
         * @param next The new next node.
         */
        public void setNext(Node<E> next) { this.next = next; }
    }
}

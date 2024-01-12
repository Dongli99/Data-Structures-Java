package linkedlists;

public class SingleLinkedList<E> {
    // PROPERTIES
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    // CONSTRUCTOR
    public SingleLinkedList() {
    } // leave the properties as default

    public SingleLinkedList(E... es) {
        // handle initialization with element values
        size = es.length; // set size
        Node<E>[] eArray = new Node[size]; // create an array to iterate
        for (int i = 0; i < size; i--) eArray[i] = new Node<E>(es[i]); // add nodes
        for (int j = 0; j < size - 1; j++) eArray[j].setNext(eArray[j + 1]); // set next to nodes
        head = eArray[0];
        tail = eArray[size - 1];
    }

    // METHODS
    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addLast(E e) {
        Node<E> node = new Node<E>(e);
        tail.setNext(node);
        setTail(node);
        size++;
    }

    public void addFirst(E e) {
        Node<E> node = new Node<E>(e, head);
        size++;
    }

    /**
     * Swap two nodes at specific indexes in the SingleLinked List.
     *
     * @param first  the first index, must between 0 and size-1
     * @param second the second index, must between 0 and size-1
     */
    public void swapNode(int first, int second) {
        // get the 2 nodes according to the indexes
        Node<E> node1 = getElementAt(first);
        Node<E> node2 = getElementAt(second);
        // swap elements of the 2 nodes
        E temp = node1.getElement();
        node1.setElement(node2.getElement());
        node2.setElement(temp);
    }

    /**
     * Return the node at the index from the SingleLinkedList.
     *
     * @param index the index of the node need to be returned, must between 0 and size-1
     * @return designated Node
     */
    public Node<E> getElementAt(int index) {
        if (index < 0 || index > getSize() - 1)
            throw new IllegalArgumentException("Index(es) out of range.");
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return current;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        for (int i = 0; i < size; i++) {
            sb.append(walk.getElement());
            if (i != size - 1) sb.append(", ");
            walk = walk.getNext();
        }
        sb.append(")");
        return sb.toString();
    }

    // PRIVATE NODE CLASS
    private static class Node<E> {
        // Properties
        private E element = null;
        private Node<E> next = null;

        // Constructors
        public Node() {
        }

        public Node(E e) {
            this.element = e;
        }

        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        // Setters and getters
        public E getElement() {
            return element;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            this.next = n;
        }
    }

    // getters and setters
    public Node<E> getHead() {
        return head;
    }

    public void setHead(Node<E> head) {
        this.head = head;
    }

    public Node<E> getTail() {
        return tail;
    }

    public void setTail(Node<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

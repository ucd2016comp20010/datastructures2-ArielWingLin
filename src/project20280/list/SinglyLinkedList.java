package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element; // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next; // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            this.element = e;
            this.next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            this.next = next;
        }
    } // ----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null; // head node of the list (or null if empty)

    /**
     * Number of nodes in the list
     */
    private int size = 0; // number of nodes in the list

    public SinglyLinkedList() {
    } // constructs an initially empty list

    @Override
    public int size() {
        int size = 0;
        Node<E> curr = head;
        while (curr != null) {
            size += 1;
            curr = curr.getNext();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }
        return false;
    }

    @Override
    public E get(int position) {
        if (position < 0 || position >= size()) {
            System.out.println("Empty LinkedList");
        }
        if (head == null) {
            throw new IllegalArgumentException("Empty LinkedList!");
        }
        int currPosition = 0;
        Node<E> curr = head;

        while (currPosition != position) {
            curr = curr.getNext();
            currPosition++;
        }
        return curr.getElement();
    }

    @Override
    public void add(int position, E e) {
        if (position < 0 || position > size()) {
            throw new IllegalArgumentException("Position is out of bounce!");
        }

        if (position == 0) {
            head = new Node<E>(e, head);
            return;
        }

        Node<E> curr = head;
        for (int currPosition = 0; currPosition < position - 1; currPosition++) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<E>(e, curr.getNext()));
    }

    @Override
    public void addFirst(E e) {
        if (head == null) {
            head = new Node<E>(e, head);
            return;
        }

        Node<E> newNode = head;
        Node<E> newHead = new Node<E>(e, newNode);
        head = newHead;
        return;
    }

    @Override
    public void addLast(E e) {
        if (head == null) {
            head = new Node<E>(e, head);
            return;
        }

        int listSize = size();
        Node<E> curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<E>(e, null));

    }

    @Override
    public E remove(int position) {
        if (position < 0 || position > size()) {
            throw new IllegalArgumentException("Size out of bounce!");
        } else if (head == null) {
            throw new IllegalArgumentException("No node to remove!");
        }

        E removedElement;

        if (position == 0) {
            removedElement = head.getElement();
            head = head.getNext();
        }

        Node<E> prev = head;
        for (int currPos = 0; currPos < position - 1; currPos++) {
            prev = prev.getNext();
        }

        Node<E> toRemove = prev.getNext();
        removedElement = toRemove.getElement();

        prev.setNext(toRemove.getNext());
        return removedElement;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            throw new IllegalArgumentException("Empty Linked list! Nothing to remove :(");
        }

        E removedElement;
        Node<E> toRemove = head;
        removedElement = toRemove.getElement();

        head = toRemove.getNext();
        return removedElement;
    }

    @Override
    public E removeLast() {
        if (head == null) {
            throw new IllegalArgumentException("Empty Linked list! Nothing to remove :(");
        }

        E removedElement;

        if (size() == 1) {
            removedElement = head.getElement();
            head = null;
        } else {
            Node<E> prev = head;
            // Could do while(prev.getNext().getNext() != null)
            for (int currPos = 0; currPos < size() - 2; currPos++) {
                prev = prev.getNext();
            }
            Node<E> toRemove = prev.getNext();
            removedElement = toRemove.getElement();

            prev.setNext(null);
        }

        return removedElement;
    }

    // @Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        // LinkedList<Integer> ll = new LinkedList<Integer>();

        // Testing size()
        System.out.println(ll);
        System.out.println(ll.size());
        System.out.println(ll.isEmpty());

        ll.addFirst(2);
        ll.addFirst(1);
        ll.addFirst(4);
        System.out.println(ll);
        System.out.println(ll.size());
        ll.add(1, 3);
        System.out.println(ll);
        ll.addLast(4);
        System.out.println(ll);

        ll.remove(1);
        System.out.println(ll);
        ll.removeFirst();
        System.out.println(ll);
        ll.removeLast();
        System.out.println(ll);

    }
}

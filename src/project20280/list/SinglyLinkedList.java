package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E extends Comparable<E>> implements List<E> {

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
            this.next = n;
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

    // Q9:
    public SinglyLinkedList<E> sortedMerge(SinglyLinkedList<E> l2) {
        SinglyLinkedList<E> result = new SinglyLinkedList<>();

        Node<E> curr1 = this.head;
        Node<E> curr2 = l2.head;

        while(curr1 != null && curr2 != null) {
            if (curr1.getElement().compareTo(curr2.getElement()) <= 0) {
                result.addLast(curr1.getElement());
                curr1 = curr1.getNext();
            } else {
                result.addLast(curr2.getElement());
                curr2 = curr2.getNext();
            }
        }

            while(curr1 != null) {
                result.addLast(curr1.getElement());
                curr1 = curr1.getNext();
            }

            while(curr2 != null) {
                result.addLast(curr2.getElement());
                curr2 = curr2.getNext();
            }
        return result;
    }

    // Q10: Reverse a linked list
    public SinglyLinkedList<E> reverseLink() {
        Node<E> curr = head;
        SinglyLinkedList reverse = new SinglyLinkedList<>();
        for(int count = 0; count < size(); count++) {
            reverse.addFirst(curr.getElement());
            curr = curr.getNext();
        }
        return reverse;
    }

    // Q11: Cloning a linked list:
    public SinglyLinkedList<E> cloneList() {
        SinglyLinkedList<E> newCloned = new SinglyLinkedList<>();
        Node<E> curr = head;

        while(curr != null) {
            newCloned.addLast(curr.getElement());
            curr = curr.getNext();
        }
        return newCloned;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
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
            throw new IllegalArgumentException("Position is out of bounds!");
        }

        if (position == 0) {
            head = new Node<E>(e, head);
            ++size;
            return;
        }

        Node<E> curr = head;
        for (int currPosition = 0; currPosition < position - 1; currPosition++) {
            curr = curr.getNext();
        }
        ++size;
        Node<E> new_node = new Node<E>(e, curr.getNext());
        curr.setNext(new_node);
//        System.out.println("Start");
//        System.out.println(curr.getElement());
//        System.out.println(curr.getNext().getElement());
//        System.out.println(curr.getNext().getNext().getElement());
//
//        System.out.println(new_node.getElement());
    }

    @Override
    public void addFirst(E e) {
        if (head == null) {
            head = new Node<E>(e, head);
            ++size;
            return;
        }

        Node<E> newNode = head;
        Node<E> newHead = new Node<E>(e, newNode);
        head = newHead;
        ++size;
    }

    @Override
    public void addLast(E e) {
        if (head == null) {
            head = new Node<E>(e, head);
            ++size;
            return;
        }

        int listSize = size();
        Node<E> curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        curr.setNext(new Node<E>(e, null));
        ++size;
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
        --size;
        return removedElement;
    }

    @Override
    public E removeFirst() {
        if (head == null) {
            return null;
        }

        E removedElement;
        Node<E> toRemove = head;
        removedElement = toRemove.getElement();

        head = toRemove.getNext();
        --size;
        return removedElement;
    }

    @Override
    public E removeLast() {
//        System.out.println("In removeLast:");
        if (head == null) {
//            System.out.println(1);
            throw new IllegalArgumentException("Empty Linked list! Nothing to remove :(");
        }

        E removedElement;

        if (size() == 1) {
//            System.out.println(2);
            removedElement = head.getElement();
            head = null;
        } else {
//            System.out.println(3);
            Node<E> prev = head;
            // Could do while(prev.getNext().getNext() != null
            for (int currPos = 0; currPos < size() - 2; currPos++) {
//                System.out.print(prev.getElement());
//                System.out.println(" - " + currPos);
                prev = prev.getNext();
            }
            Node<E> toRemove = prev.getNext();
            removedElement = toRemove.getElement();

            prev.setNext(null);
        }
        --size;
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
        System.out.println("Size: " + ll.size());


        ll.add(1, 3);
        System.out.println(ll);
        System.out.println("Size: " + ll.size());
        ll.addLast(4);
        System.out.println(ll);
        System.out.println("Size: " + ll.size());

        ll.remove(1);
        System.out.println(ll);
        System.out.println("Size: " + ll.size());
        ll.removeFirst();
        System.out.println(ll);
        System.out.println("Size: " + ll.size());
        ll.removeLast();
        System.out.println(ll);
        System.out.println("Size: " + ll.size());

        SinglyLinkedList l1 = new SinglyLinkedList();
        SinglyLinkedList l2 = new SinglyLinkedList();

        l1.addLast(2);
        l1.addLast(6);
        l1.addLast(20);
        l1.addLast(24);

        l2.addLast(1);
        l2.addLast(3);
        l2.addLast(5);
        l2.addLast(8);
        l2.addLast(12);
        l2.addLast(19);
        l2.addLast(25);

        System.out.println(l1.sortedMerge(l2));
        System.out.println(l1.reverseLink());
        System.out.println("Original: " + l1);
        System.out.println("Cloned: " + l1.cloneList());
    }
}

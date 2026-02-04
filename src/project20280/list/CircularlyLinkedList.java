package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return tail == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int i) {
        E element;
        if(i < 0 || i >= size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        Node<E> curr = tail.next;
        for(int j = 0; j < i; j++) {
            curr = curr.getNext();
        }
        element = curr.getData();
        return element;
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     //* @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */

    @Override
    public void addFirst(E e) {
        if(size == 0){
            tail = new Node<E>(e, null);
            tail.next = tail;
            ++size;
            return;
        }

        Node<E> newNode = new Node<E>(e, tail.next);
        tail.next = newNode;
        ++size;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    @Override
    public void add(int i, E e) {
        if(i < 0 || i > size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        if (i == 0) {
            addFirst(e);
        } else if(i == size()) {
            addLast(e);
        }

        Node<E> curr = tail;
        for(int count = 0; count < i; count++) {
            curr = curr.next;
//            System.out.println(curr.getData());
        }
        Node<E> newNode_ = new Node<E>(e, curr.next);
//        System.out.println(newNode_.getData());
        curr.next = newNode_;
        ++size;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }

        E removedElement;
        if(tail.next == tail){
            removedElement = tail.getData();
            tail = null;
            --size;
            return removedElement;
        }

        Node<E> head = tail.getNext();
        removedElement = tail.next.getData();
        tail.next = head.next;
        --size;
        return removedElement;
    }

    @Override
    public E removeLast() {
        if(isEmpty()){
            throw new IllegalArgumentException("Empty LinkedList!");
        }

        E removedElement;
        if(tail.next == tail){
            removedElement = tail.getData();
            tail = null;
            --size;
            return removedElement;
        }

        Node<E> curr = tail;
        while(curr.getNext() != tail) {
            curr = curr.getNext();
        }
        removedElement = tail.getData();
        curr.next = tail.next;
        tail = curr;
        --size;
        return removedElement;
    }

    @Override
    public E remove(int i) {
        if(isEmpty()){
            throw new IllegalArgumentException("Empty LinkedList!");
        }

        if(i == 0) {
            removeFirst();
        } else if(i == size - 1) {
            removeLast();
        }

        Node<E> temp = tail.getNext();
        for(int count = 0; count < i - 1; count++) {
            temp = temp.next;
        }

        E removedElement;
        removedElement = temp.getNext().getData();
        temp.next = temp.getNext().getNext();
        --size;
        return removedElement;
    }

    public void rotate() {
        if(tail != null && tail.next != tail) {
            tail = tail.next;
        }
        return;
    }

    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        ll.add(1, 100);
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}

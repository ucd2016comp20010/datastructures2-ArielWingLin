package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e, Node<E> p, Node<E> n) {
            data = e;
            prev = p;
            next = n;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.next = tail;
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }

    @Override
    public int size() {
        int size = 0;
        Node<E> curr = head;
        while (curr.getData() != null) {
            size += 1;
            curr = curr.getNext();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // i = position
    @Override
    public E get(int i) {
        E element;
        if(i < 0 || i >= size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        if (head == null) {
            throw new IllegalArgumentException("Empty Linked List!!");
        } else {
            if (i == 0) {
                element = head.getData();
                return element;
            }
            Node<E> curr = head;
            for (int j = 0; j < i - 1; j++) {
                curr = curr.getNext();
            }
            element = curr.getData();
        }
        return element;
    }

    @Override
    public void add(int i, E e) {
        if(i < 0 || i >= size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        if(i == 0){
            Node<E> newHead = new Node<E>(e, null, head);
            head.prev = newHead;
            head = newHead;
            ++size;
            return;
         }

        Node<E> curr = head;
        for(int count = 0; count < i - 1; count++){
            curr = curr.getNext();
        }
        ++size;
        Node<E> newNode = new Node<E>(e, curr.getPrev(), curr);

        curr.prev.next = newNode;
        curr.prev = newNode;
    }

    // Use private E remove(Node<E> n)
    @Override
    public E remove(int i) {
        if(i < 0 || i >= size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        } else if (head == null) {
            throw new IllegalArgumentException("No node to remove!");
        }

        E removedElement;
        Node<E> curr = head;
        for(int count = 0; count < i - 1; count++){
            curr = curr.getNext();
        }

        return remove(curr);
    }

    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    // different from public E remove(int i)!
    private E remove(Node<E> n) {
        E removedElement;

        if(head == tail){
            removedElement = head.getData();
            head = null;
            tail = null;
            return removedElement;
        }

        else if (n == head) {
            removedElement = n.getData();
            head = head.getNext();
            head.prev = null;
            return removedElement;
        }

        else if(n == tail) {
            removedElement = n.getData();
            tail.prev.next = null;
            return removedElement;
        }

        else{
            removedElement = n.getData();
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
        return removedElement;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.next.getData();
    }

    public E last() {
        // TODO
        return null;
    }

    @Override
    public E removeFirst() {
        E removedElement;

        if(head == tail){
            removedElement = head.getData();
            head = null;
            tail = null;
            return removedElement;
        }
        return null;
    }

    @Override
    public E removeLast() {
        E removedElement;
        // if there is only 1 Node:

        return null;
    }

    @Override
    public void addLast(E e) {
        if(head == tail){
            Node<E> newHead = new Node<E>(e, null, null);
            head = newHead;
        }

        Node<E> curr = head;
        Node<E> tailToNode = new Node<E>(e, tail.getNext(), null);
        for(int count = 0; count < size(); count++){
            curr = curr.getNext();
        }
    }

    @Override
    public void addFirst(E e) {
        if(head == tail){
            Node<E> newHead = new Node<E>(e, null, null);
            head = newHead;
        }

        Node<E> headToNode = head;
        Node<E> newHead = new Node<E>(e, null, headToNode.getPrev());
        head = newHead;
        ++size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head.next;
        while (curr != tail) {
            sb.append(curr.data);
            curr = curr.next;
            if (curr != tail) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
        // ll.addFirst(0);
        // ll.addFirst(1);
        // ll.addFirst(2);
        // ll.addLast(-1);
        // System.out.println(ll);

        // ll.removeFirst();
        // System.out.println(ll);

        // ll.removeLast();
        // System.out.println(ll);

        // for (Integer e : ll) {
        // System.out.println("value: " + e);
        // }

        System.out.println(ll);
        System.out.println(ll.size());
    }
}
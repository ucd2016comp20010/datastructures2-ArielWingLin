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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (head.next == tail);
    }

    // i = position
    @Override
    public E get(int i) {
        E element;
        if(i < 0 || i >= size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        if (head.next == null) {
            throw new IllegalArgumentException("Empty Linked List!!");
        } else {
            if (i == 0) {
                element = head.next.getData();
                return element;
            }
            Node<E> curr = head;
            for (int j = 0; j <= i; j++) {
                curr = curr.getNext();
            }
            element = curr.getData();
        }
        return element;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
//        return head.next.getData();
        return head.next.getData();
    }

    public E last() {
        if(isEmpty()){
            return null;
        }
        return tail.prev.getData();
    }

    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        if (pred == null || succ == null){
            throw new IllegalArgumentException("cannot add between null nodes!");
        }
        Node<E> newNode = new Node<E>(e, pred, succ);

        pred.next = newNode;
        succ.prev = newNode;
        ++size;
    }

    @Override
    public void add(int i, E e) {
        if(i < 0 || i > size()) {
            throw new IllegalArgumentException("Size out of bounds! :(");
        }

        if(i == 0){
//            Node<E> newHead = new Node<E>(e, null, head);
//            head.prev = newHead;
//            head = newHead;
//            ++size;
//            return;
            addBetween(e, head, head.next);
            return;
         }
        // do you have size++ or ++ size?


        if(i == size()){
            addBetween(e,tail.prev, tail);
            return;
        }

        Node<E> curr = head.next;
        for(int count = 0; count < i; count++){
            curr = curr.next;
        }

//        Node<E> newNode = new Node<E>(e, curr, curr.getNext());
//
//        if(curr.next != null) {
//            curr.prev.next = newNode;
//        }
//        curr.next = newNode;
        addBetween(e, curr.prev, curr);

    }

    @Override
    public void addFirst(E e) {
//        if(head == tail){
//            Node<E> newHead = new Node<E>(e, null, null);
//            head = newHead;
//            tail = newHead;
//            return;
//        }
//
//        Node<E> headToNode = head;
//        Node<E> newHead = new Node<E>(e, null, head);
//        head = newHead;
//        headToNode.prev = head;
//        ++size;
        addBetween(e, head, head.next);
    }

    @Override
    public void addLast(E e) {
//        if(head == tail){
//            Node<E> newHead = new Node<E>(e, null, null);
//            head = newHead;
//            tail = newHead;
//        }
//        Node<E> newTail = new Node<E>(e, tail, null);
//        tail.next = newTail;
//        tail = newTail;
//        ++size;
        addBetween(e, tail.prev, tail);
    }

    private E remove(Node<E> n) {
        E removedElement;

        if(head == tail){
            removedElement = head.getData();
            head = null;
            tail = null;
            size--;
            return removedElement;
        }

        else if (n == head) {
            removedElement = n.getData();
            head = head.getNext();
            head.prev = null;
            size--;
            return removedElement;
        }

        else if(n == tail) {
            removedElement = n.getData();
            tail.prev.next = null;
            size--;
            return removedElement;
        }

        else{
            removedElement = n.getData();
            n.prev.next = n.next;
            n.next.prev = n.prev;
            size--;
        }
        return removedElement;
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
        for(int count = 0; count <= i; count++){
            curr = curr.getNext();
        }

        return remove(curr);
    }

    @Override
    public E removeFirst() {
        E removedElement;

//        if(head == tail){
//            removedElement = head.getData();
//            head = null;
//            tail = null;
//            return removedElement;
//        }
//        removedElement = head.getData();
//        head = head.next;
//        head.prev = null;
//        return removedElement;
        if(isEmpty()){
            return null;
        }
        return remove(head.next);
    }

    @Override
    public E removeLast() {
        E removedElement;
        // if there is only 1 Node:
//        if(head == tail){
//            removedElement = head.getData();
//            head = null;
//            tail = null;
//            return removedElement;
//        }
//        removedElement = tail.getData();
//        tail = tail.getPrev();
//        tail.next = null;
//        return removedElement;
        if(isEmpty()){
            return null;
        }
        return remove(tail.prev);
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
        ll.addFirst(100);
        System.out.println(ll);
        System.out.println(ll.size());

        System.out.println("removed element: " + ll.remove(0));
        System.out.println(ll);
        System.out.println("calling size function: " + ll.size());
        System.out.println("calling size var: " + ll.size);
        System.out.println("calling isEmpty(): " + ll.isEmpty());

        // i don't know the exact issue but it's defo the size thing being wrong. I think all the other functions work fine, but she forgot to either decrement size or incremenet size
        // since her is empty, funcgtion works fine. I think it's somethign else.

    }
}
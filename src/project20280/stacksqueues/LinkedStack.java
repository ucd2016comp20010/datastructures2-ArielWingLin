package project20280.stacksqueues;

import project20280.interfaces.Stack;
import project20280.list.DoublyLinkedList;
import project20280.list.SinglyLinkedList;

import java.security.spec.InvalidKeySpecException;

public class LinkedStack<E> implements Stack<E> {

    DoublyLinkedList<E> ll;

    public static void main(String[] args) {

    }

    public LinkedStack(){
        ll = new DoublyLinkedList<>();
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void push(E e) throws IllegalStateException {
        ll.addFirst(e);
    }

    @Override
    public E top() {
        if(isEmpty()){
            return null;
        }
        return ll.first();
    }

    @Override
    public E pop() throws IllegalStateException {
        if(isEmpty()){
            return null;
        }
        return ll.removeFirst();
    }

    public String toString() {
        return ll.toString();
    }


    //Q4:
    public static String convertToBinary(long decNum){
        LinkedStack<String> forString = new LinkedStack<>();

        if(decNum < 0){
            throw new IllegalArgumentException("Number provided is less than 0 :(");
        }

        else if(decNum == 0){
            return "0";
        }

        // Stack will now contain the Binary form of decNum
        while(decNum > 0 ) {
            long remainder = decNum % 2;
            forString.push(String.valueOf(remainder));
            decNum /= 2;
        }

        StringBuilder results = new StringBuilder();
        while(!forString.isEmpty()){
            results.append(forString.pop());
        }
        return results.toString();
    }

    // Q5

}

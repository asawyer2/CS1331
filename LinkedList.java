
// Created for CS1331 at Georgia Tech
// By Allison Sawyer
// Spring 2019



import java.util.NoSuchElementException;


/**
 *program creates a linked list
 *@author asawyer8
 *@version 1.0
 *@param <T> generic type
 */


public class LinkedList<T> implements SimpleList<T> {

    private Node<T> head;
    private int size;


    /**
     * This method contructs a Linked List object.
     */
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public void addAtIndex(T data, int index) throws IllegalArgumentException {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }

        Node<T> currentNode = head;
        Node<T> newNode;

        //if adding at index 0 (create new head) and empty
        if (!isEmpty()) {
            //if adding at index 0 and not empty
            if (index == 0) {
                newNode = new Node<T>(data, currentNode);
                head = newNode;
            }
            //if adding in the middle
            if (index > 0 && index < size - 1) {
                for (int i = 0; i < index - 1; i++) {
                    currentNode = currentNode.getNext();
                }
                newNode = new Node<T>(data, currentNode.getNext());
                currentNode.setNext(newNode);
            }
            //adding at the end
            if (index != 0 && index == size) {
                newNode = new Node<T>(data, null);
                while (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                }
                currentNode.setNext(newNode);
            }
            size++;
        }
        if (isEmpty()) {
            newNode = new Node<T>(data, null);
            this.head = newNode;
            size = 1;
        }
    }

    @Override
    public T get(int index) {
        //check if empty
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //check for valid index
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        Node<T> currentNode = head;
        T targetData = null;
        T targetDataPrev = null;

        //if first index
        if (index == 0) {
            targetData = currentNode.getData();
        }

        //else other index
        if (index > 0 && index < size) {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            targetDataPrev = currentNode.getData();
            targetData = currentNode.getNext().getData();

            currentNode.setData(targetData);
            currentNode.getNext().setData(targetDataPrev);
        }
        return targetData;
    }

    @Override
    public boolean contains(T data) {
        Node<T> currentNode = head;
        if (isEmpty()) {
            return false;
        } else {
            while (data != currentNode.getData()
                && currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            return (currentNode.getData() == data);
        }
    }

    @Override
    public boolean isEmpty() {
        return (head  == null);
    }

    @Override
    public T removeAtIndex(int index) throws IllegalArgumentException,
    NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        Node<T> currentNode = head;
        T targetData = null;

        if (index == 0) {
            targetData = currentNode.getData();
            head = currentNode.getNext();
        } else {
            for (int i = 0; i < index - 1; i++) {
                currentNode = currentNode.getNext();
            }
            targetData = currentNode.getNext().getData();
            currentNode.setNext(currentNode.getNext().getNext());
        }
        size--;
        return targetData;
    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public T[] toArray() {
        Node<T> currentNode = head;
        T[] newArray = (T[]) new Object[size()];
        for (int i = 0; i < size; i++) {
            newArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return newArray;
    }
}

package gee_p3;
/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */
import java.util.EmptyStackException;
import java.util.Vector;

/**
 * This class implements a generic stack data structure using a linked list.
 *
 * @param <T> The type of elements to be stored in the stack.
 */
public class Stack<T> extends Vector<T> {

    /**
     * Reference to the first node (head) of the linked list representing the stack.
     */
    private Node<T> head;

    public void clear() {
        removeAllElements();
    }

    /**
     * Helper method to remove all the elements from the stack.
     */
    public void removeAllElements() {
        while (isEmpty()) {
           pop();
        }
    }
    /**
     * Pushes an element onto the top of the stack.
     *
     * @param data The element to be added to the stack.
     * @throws NullPointerException if the data is null.
     */
    public void push(T data) {
        if (data == null) {
            throw new NullPointerException("Cannot push null data onto the stack");
        }

        // Create a new node to hold the data
        Node<T> newNode = new Node<>(data);

        // Link the new node to the existing head
        newNode.next = head;

        // Update the head pointer to point to the new node (making it the new top)
        head = newNode;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return The element that was removed from the top of the stack.
     * @throws IllegalStateException if the stack is empty.
     */
    public T pop() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        // Retrieve the data from the current head node
        T data = head.data;

        // Move the head pointer to the next node (removing the top element)
        head = head.next;

        return data;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null; // An empty stack has a null head
    }

    /**
     * **Placeholder method.** This method needs to be implemented to return the
     * number of elements currently in the stack.
     *
     * @return The number of elements in the stack. (Not currently implemented)
     */
    public int getSize() {
        throw new UnsupportedOperationException("Stack size calculation not implemented yet.");
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public T peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return head.data; // Return the data of the top node
    }

    /**
     * A simple Node class representing a single element in the linked list implementation
     * of the stack.
     *
     * @param <T> The type of data stored in the node.
     */
    private static class Node<T> {
        /**
         * The data stored in the node.
         */
        private T data;

        /**
         * Reference to the next node in the linked list.
         */
        private Node<T> next;

        /**
         * Constructor to create a node with given data.
         *
         * @param data The data to be stored in the node.
         */
        public Node(T data) {
            this.data = data;

        }
    }
}

package gee_p3;
/*
 * GEE
 * This is free and unencumbered software released into the public domain.
 */

/**
 * A generic queue implementation using a linked list.
 *
 * @param <T> The type of elements to be stored in the queue.
 */
public class Queue<T> {

    /**
     * Reference to the first node (head) of the linked list.
     */
    private Node<T> front;

    /**
     * Reference to the last node (tail) of the linked list.
     */
    private Node<T> rear;

    /**
     * Adds an element to the rear of the queue.
     *
     * @param data The element to be added.
     */
    public void enqueue(T data) {
        // Create a new node to hold the data
        Node<T> newNode = new Node<>(data);

        // If the queue is empty, both front and rear point to the new node
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            // Otherwise, link the new node to the rear, and update the rear pointer
            rear.next = newNode;
            rear = newNode;
        }
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return The element at the front of the queue.
     * @throws IllegalStateException If the queue is empty.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is Empty");
        }

        // Retrieve the data from the front node
        T data = front.data;

        // Move the front pointer to the next node
        front = front.next;

        // If the queue becomes empty, set rear to null as well
        if (front == null) {
            rear = null;
        }

        return data;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        // An empty queue has both front and rear pointing to null
        return front == null;
    }

    /**
     * A simple Node class representing a single element in the linked list.
     *
     * @param <T> The type of data stored in the node.
     */
    private static class Node<T> {

        /**
         * The data stored in the node.
         */
        private T data;

        /**
         * Reference to the next node in the list.
         */
        private Node<T> next;

        /**
         * Creates a new node with the given data.
         *
         * @param data The data to be stored in the node.
         */
        public Node(T data) {
            this.data = data;
        }
    }
}

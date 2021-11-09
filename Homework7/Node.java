/**
 * Node.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class is used to create a node of generic type E
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class Node<E> {
    // Instance variables of class Node
    E payLoad;
    Node<E> left;
    Node<E> right;
    int counter;

    /**
     * Parameterized constructor of class Node to create
     * a new object of node
     * @param data  Value to store
     */
    public Node(E data) {
        payLoad = data;
        left = null;
        right = null;
        counter = 1;
    }
}

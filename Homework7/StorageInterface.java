/**
 * StorageInterface.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This Interface SortedStorage defines methods which should be implemented
 * by the class which implements it.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
/**
 * The Storage Interface
 * @param <E>   E extends any class/interface which extends or implements Comparable.
 */
public interface StorageInterface<E extends Comparable<E>> {
    boolean add(E x);              // true if it was successfully added, false otherwise
    boolean find(E x);           // true if x could be found, false otherwise
    boolean includesNull();      // true, if the storage include a null element, false otherwise
    boolean delete(E x);         // true if it was successfully deleted, false otherwise
}

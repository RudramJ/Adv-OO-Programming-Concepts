/**
 * SortedStorage.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class SortedStorage implements a binary search tree
 * to store not null objects.
 * This class can store Integer, Strings, Address, LP or
 * any class/interface which extends or implements Comparable.
 * This class is thread safe(The abstract methods are made synchronized)
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class SortedStorage<E extends Comparable<E>> extends Thread implements StorageInterface<E> {
    // Instance Variables
    private Node<E> root;
    private int nullCount = 0;
    private boolean isElementPresent = false;
    private boolean canElementBeDeleted = true;
    private boolean canElementBeAdded = true;

    /**
     * Default constructor of StorageClass
     * Initializes the Node root.
     */
    public SortedStorage() {
        root = null;
    }

    /**
     * This recursive method returns the minimum node in the Binary search tree
     * @param thisNode  Node whose minimum value is to be returned
     * @return  Calls itself until the last node
     */
    private Node<E> minimumElement(Node<E> thisNode) {
        if (thisNode.left == null)
            return thisNode;
        else {
            return minimumElement(thisNode.left);
        }
    }

    /**
     * This recursive method is a helper method to delete an element
     * in the binary search tree.It toggles the
     * instance variable canElementBeDeleted based on result.
     * @param root      root node
     * @param payLoad   Value to be deleted
     * @return          The method calls itself to iterate on left and right
     *                  based on the value of node
     */
    private Node<E> deleteThisElementInTree(Node<E> root, E payLoad) {
        if ( root == null ) {
            canElementBeDeleted = false;
            return null;
        }
        if ( root.payLoad.compareTo(payLoad) > 0  ) {
            root.left = deleteThisElementInTree(root.left, payLoad );
        }
        else if ( root.payLoad.compareTo(payLoad) < 0 ) {
            root.right = deleteThisElementInTree(root.right, payLoad );
        }
        else {
            if(root.counter > 1) {
                root.counter--;
            }
            else {
                if ( (root.left != null) && (root.right != null) ) {
                    Node<E> tmp = root;
                    Node<E> minimumNodeOnRight = minimumElement(tmp.right);
                    root.payLoad = minimumNodeOnRight.payLoad;
                    root.counter = minimumNodeOnRight.counter;
                    root.right = deleteThisElementInTree(root.right, minimumNodeOnRight.payLoad);
                }
                else if (root.left != null) {
                    root = root.left;
                }
                else if (root.right != null) {
                    root = root.right;
                }
                else {
                    root = null;
                }
            }
        }
        return root;
    }

    /**
     * This recursive method is a helper method to add an element
     * in the binary search tree.
     * @param root      root node
     * @param payLoad   Value to add
     * @return  The method calls itself to iterate on left and right
     *          based on the value of node
     */
    private Node<E> addThisElementInTree(Node<E> root, E payLoad) {
        if (root == null) {
            root = new Node<E>(payLoad);
            return root;
        }
        if (root.payLoad.compareTo(payLoad) > 0) {
            root.left = addThisElementInTree(root.left, payLoad);
        }
        else if (root.payLoad.compareTo(payLoad) < 0) {
            root.right = addThisElementInTree(root.right, payLoad);
        }
        else {
            root.counter ++;
        }
        canElementBeAdded = true;
        return root;
    }

    /**
     * This method is a helper method to find an element
     * in the binary search tree. It toggles the
     * instance variable isElementPresent based on result
     * @param root      root node
     * @param payLoad   Value to find
     */
    private void findThisElementInTree(Node<E> root, E payLoad) {
        if (root != null) {
            if (root.payLoad.compareTo(payLoad) > 0) {
                findThisElementInTree(root.left, payLoad);
            }
            else if (root.payLoad.compareTo(payLoad) < 0) {
                findThisElementInTree(root.right, payLoad);
            }
            else {
                isElementPresent = true;
            }
        }
    }

    /**
     * This method adds the element in the binary search tree
     * @param x     Element to be added
     * @return      true if element can be added
     *              else return false
     */
    public synchronized boolean add(E x) {
        if(x != null) {
            this.root = addThisElementInTree(this.root, x);
        }
        else {
            nullCount++;
        }
        return canElementBeAdded;
    }

    /**
     * This method deletes the element present in the binary search tree
     * @param x     Element to be deleted
     * @return      returns true if element can be deleted
     *              else return false
     */
    public synchronized boolean delete(E x) {
        if(x != null) {
            this.root = deleteThisElementInTree(this.root, x);
            return canElementBeDeleted;
        }
        else {
            if(nullCount > 0) {
                nullCount--;
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * This method finds the element stored in the binary search tree
     * @param x     Element to find
     * @return      true if element is present, else false
     */
    public synchronized boolean find(E x) {
        if(x != null) {
            findThisElementInTree(this.root, x);
        }
        else {
            if(nullCount > 0) {
                return true;
            }
        }
        return isElementPresent;
    }

    /**
     * This method indicates the presence of "null" in the binary search tree
     * @return  Returns true if null exists, else false
     */
    public synchronized boolean includesNull() {
        return nullCount > 0;
    }

    /**
     * This recursive method gets the String to be returned by toString method
     * @param root  Root node
     * @return      String values of nodes in Binary search tree
     */
    private String toPrint(Node<E> root) {
        String returnStr = "";
        if (root != null) {
            returnStr += "( l: " + toPrint(root.left);
            returnStr += root.payLoad + "/" +root.counter + " ";
            returnStr += "r: " + toPrint(root.right) + " ) ";
        }
        else {
            returnStr = " null ";
        }
        return returnStr;
    }

    /**
     * This recursive print method prints the node in ascending(in-order) format
     * @param root  Root node
     * @return      String values of nodes in BST
     */
    private String toPrintNewLine(Node<E> root) {
        String returnStr = "";
        if (root != null) {
            returnStr += toPrintNewLine(root.left);
            returnStr += "\n"+root.payLoad + "/" +root.counter + " ";
            returnStr += toPrintNewLine(root.right);
        }
        return returnStr;
    }
    /**
     * The toString method
     * @return Returns the string when the object is printed
     */
    public String toString() {
        String print;
        print = "includes so many null values = "+ nullCount + "\n";
        print += "Value stored: "+toPrintNewLine(this.root);
        return print;
    }

    /**
     * The main method
     * This method tests the Storage class by storing various attributes in it.
     * @param args  None
     */
    public static void main(String[] args) {
        StorageInterface<String>storeString = new SortedStorage<String> ();
        String three = new String("3");
        storeString.add("2");
        storeString.add("40");
        storeString.add("3");
        storeString.add("100");
        storeString.add("800");
        storeString.add(null);
        storeString.add(null);
        System.out.println(storeString);
    }

}

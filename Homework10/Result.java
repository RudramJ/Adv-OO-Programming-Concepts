/**
 * Result.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class is used to store the result of class
 * Palindrome class and implements Comparable interface
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Result implements Comparable<Result> {
    private int numberToCheck;
    private String correspondingOutput;

    /**
     * Stores the number and its corresponding output
     * @param numberToCheck  Number to store
     * @param output         result of number
     */
    public Result(int numberToCheck, String output) {
        this.numberToCheck = numberToCheck;
        this.correspondingOutput = output;
    }

    /**
     * This method returns the string whenever the object
     * of this class is printed
     * @return  resultant string
     */
    public String toString() {
        String returnString = numberToCheck + correspondingOutput;
        return returnString;
    }

    /**
     * This method is used to compare the objects
     * stored in list of objects of this class
     * @param o  object to compare
     * @return   Whether equal, small or large
     */
    @Override
    public int compareTo(Result o) {
        // TODO Auto-generated method stub
        return this.numberToCheck - o.numberToCheck;
    }


}
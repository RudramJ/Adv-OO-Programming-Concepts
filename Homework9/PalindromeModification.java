/**
 * PalindromeModification.java
 *
 * Version:
 * 	   $Id$
 *
 * Revisions:
 * 	   $Log$
 */

import java.util.*;

/**
 * The class Result checks whether a number is Lychrel or not
 * viz. whether the number becomes a palindrome after
 * reversing a number and adding it to original number.
 * This class uses threads for parallel processing, the
 * numbers are divided into blocks based on number of threads
 * provided by the input and prints the result
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class PalindromeModification extends Thread {

    // Input parameters
    final static int START                = 80;
    final static int END                  = 90;
    final static int MINIMUM_DELAYED      = 4;
    final static int MAXIMUM_DELAYED      = 10;
    static List<Result> theResults = Collections.synchronizedList(new ArrayList<Result>());

    // Instance variable
    private int startNumber;
    private int endNumber;

    /**
     * Parameterized constructor
     * This stores the range of numbers needed
     * for each threads to check lychrel property
      * @param start    Start number
     * @param end       End number
     */
    public PalindromeModification(int start, int end) {
        this.startNumber = start;
        this.endNumber = end;
    }

    /**
     * This method returns a reverse of the number
     * in String (To preserve "0" when number ending
     * with "0" is reversed)
     *
     * @param myNum 	Number to reverse
     * @return			Reverse of a number
     */
    private static String getReverseNumber( int myNum ) {
        StringBuilder myString = new StringBuilder( Integer.toString(myNum) );
        return (myString.reverse().toString());
    }

    /**
     * Checks whether a number is Lychrel or not
     * This function runs a loop over a delays.
     * it checks the sum of a number and its reverse is Palindrome
     * And resultant parameters are passed to print method.
     * @param numberToCheck 	Number to check for lychrel
     */
    private void checkForLychrel(int numberToCheck) {
        // Constants for checking Lychrel number
        int straightNum = numberToCheck;
        String reverseNum = getReverseNumber( numberToCheck );
        int myDelay = 0;
        boolean lychrelNum = true;
        for( int index = 1; index <= MAXIMUM_DELAYED; index++) {
            if(index >= MINIMUM_DELAYED) {
                if ( isPalindrome( straightNum + Integer.parseInt(reverseNum) ) ) {
                    lychrelNum = false;
                    myDelay = index;
                    break;
                }
                else {
                    myDelay = index;
                    if( index != MAXIMUM_DELAYED) {
                        straightNum = straightNum + Integer.parseInt(reverseNum);
                        reverseNum = getReverseNumber( straightNum );
                    }
                }
            }
            else {
                straightNum = straightNum + Integer.parseInt(reverseNum);
                reverseNum = getReverseNumber( straightNum );
            }
        }
        // Passing required parameters for printing output
        storeMyResult( numberToCheck, straightNum, reverseNum, myDelay , lychrelNum );
    }

    /**
     * Store the result in synchronized list
     * By taking parameters from "checkForLychrel" method
     *
     * @param numToCheck	Number to check
     * @param myNumber		Number to check for palindrome
     * @param myRevNumber	Reverse of Number to check for palindrome
     * @param noOFDelay		Number of delay required
     * @param isLychrel		Is the number lychrel
     */
    private static void storeMyResult( int numToCheck, int myNumber, String myRevNumber, int noOFDelay, boolean isLychrel ) {
        String toPrint = "";
        if(!isLychrel) {
            toPrint += ":\tdelayed " + noOFDelay + ":\t";
            toPrint += myNumber + " + " + myRevNumber + " = " + (myNumber + Integer.parseInt(myRevNumber));
            theResults.add(new Result(numToCheck, toPrint));
        }
    }

    /**
     * This method sorts and prints the output
     */
    private static void printResult() {
        Collections.sort(theResults);
        for(int i = 0; i < theResults.size(); i++) {
            System.out.println(theResults.get(i));
        }
    }

    /**
     * Checks whether the number is palindrome or not
     *
     * @param number	Number to check for Palindrome
     * @return			Whether Palindrome or not
     */
    private static boolean isPalindrome( int number ) {
        StringBuilder stringOfNumber = new StringBuilder( Integer.toString(number) );
        int reversOfNumber = Integer.parseInt(stringOfNumber.reverse().toString());

        return (reversOfNumber == number);
    }

    /**
     * This methods executes whenever the corresponding thread is
     * assigned by the scheduler
     * This method calls the method to execute the number range
     */
    public void run() {
        for(int i = startNumber; i <= endNumber; i++) {
            try {
                checkForLychrel(i);
            }
            catch (Exception e) {

            }
        }
    }

    /**
     * The main method.
     * Checking for Lychrel Number from limits provided.
     * Divides the number based on threads provided
     * And prints the result
     * @param args Number of Threads
     */
    static public void main( String[] args ) throws InterruptedException {
        int howManyThread = 0;
        int divisionOfBlock = 0;

        if(START < 0 && START > END) {
            System.err.println("Please provide correct start and end number");
            System.exit(0);
        }
        if(args.length == 1) {
            try {
                howManyThread = Integer.parseInt(args[0]);
            }
            catch (Exception e) {
                System.err.println("Error reading arguments");
                System.exit(0);
            }
        }
        else {
            System.err.println("Please provide the correct arguments");
            System.exit(0);
        }

        divisionOfBlock = (END - START)/howManyThread;
        int startTemp = START;
        int endTemp = 0;

        PalindromeModification[] threads = new PalindromeModification[howManyThread];
        for ( int index = 0; index < howManyThread; index ++ )  {
            int tempS = startTemp;
            if((tempS + divisionOfBlock) > END) {
                endTemp = END;
            }
            else {
                endTemp = startTemp + divisionOfBlock;
            }
            threads[index] = new PalindromeModification(tempS, endTemp);
            threads[index].start();
            threads[index].join();
            startTemp = startTemp + divisionOfBlock + 1;
        }
        printResult();
    }
}



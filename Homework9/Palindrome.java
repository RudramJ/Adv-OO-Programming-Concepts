/**
 * Palindrome.java
 *
 * Version:
 * 	   $Id$
 *
 * Revisions:
 * 	   $Log$
 */

/**
 * The class Palindrome checks whether a number is Lychrel or not
 * viz. whether the number becomes a palindrome after
 * reversing a number and adding it to original number.
 * This operation is performed a pre-determined number of times and
 * each number is calculated in each threads.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Palindrome extends Thread {

    // Input parameters
    final static int START                = 78;
    final static int END                  = 88;
    final static int MAXIMUM_DELAYED      = 8;

    // Instance variable
    private int numberToCheck;

    /**
     * Parameterized constructor to store the number to check for
     * lychrel
     * @param number Number to check for lychrel
     */
    public Palindrome(int number) {
        this.numberToCheck = number;
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
     */
    private void checkForLychrel() {
        // Constants for checking Lychrel number
        int straightNum = this.numberToCheck;
        String reverseNum = getReverseNumber( this.numberToCheck );
        int myDelay = 0;
        boolean lychrelNum = true;
        for( int index = 1; index < MAXIMUM_DELAYED + 1; index++) {

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
        // Passing required parameters for printing output
        printMyResult( this.numberToCheck, straightNum, reverseNum, myDelay , lychrelNum );
    }

    /**
     * Prints the result as required
     * By taking parameters from "checkForLychrel" method
     *
     * @param numToCheck	Number to check
     * @param myNumber		Number to check for palindrome
     * @param myRevNumber	Reverse of Number to check for palindrome
     * @param noOFDelay		Number of delay required
     * @param isLychrel		Is the number lychrel
     */
    private static void printMyResult( int numToCheck, int myNumber, String myRevNumber, int noOFDelay, boolean isLychrel ) {

        System.out.print( numToCheck + ":\tdelayed "+ noOFDelay +":\t" );

        if( isLychrel ) {
            System.out.print( "does not become palindromic within "+noOFDelay+" iterations " );
            System.out.printf( "(%d + %s = %d", myNumber, myRevNumber, (myNumber + Integer.parseInt(myRevNumber) ) );
            System.out.printf( ": %d != %s)",(myNumber + Integer.parseInt(myRevNumber)) ,
                    getReverseNumber(myNumber + Integer.parseInt(myRevNumber)) );
        }
        else {
            System.out.printf("%d + %s = %d", myNumber, myRevNumber, (myNumber + Integer.parseInt(myRevNumber) ) );
        }
        System.out.println();
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
     * This method calls the method to execute the number for lychrel
     */
    public void run() {
        checkForLychrel();
    }

    /**
     * The main method.
     * Checking for Lychrel Number from limits provided.
     *
     * @param args None
     */
    static public void main( String[] args ) throws InterruptedException {
        if(START < 0 && START > END) {
            System.err.println("Please provide correct start and end number");
            System.exit(0);
        }
        if(MAXIMUM_DELAYED > 0) {
            try {
                Palindrome[] threads = new Palindrome[END - START + 1];
                for ( int numberToTest = START; numberToTest <= END; numberToTest ++ )  {
                    threads[numberToTest - START] = new Palindrome(numberToTest);
                    threads[numberToTest - START].start();
                    threads[numberToTest - START].join();
                }
            }
            catch (Exception ignored) {
            }
        }
    }
}


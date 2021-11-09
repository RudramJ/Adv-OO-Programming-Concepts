/**
 * Coins.java
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
 * This operation is performed a pre-determined number of times
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Palindrome {
	
	// Input parameters
	final static int START                = 69;
	final static int MAXIMUM              = 91;
	final static int MAXIMUM_DELAYED      = 3;
	
	/**
	 * This method returns a reverse of the number
	 * in String (To preserve "0" when number ending 
	 * with "0" is reversed)
	 * 
	 * @param myNum 	Number to reverse
	 * @return			Reverse of a number 
	 */
	public static String getReverseNumber( int myNum ) {
		StringBuilder myString = new StringBuilder( Integer.toString(myNum) );
		return (myString.reverse().toString());
	}
	
	/**
	 * Checks whether a number is Lychrel or not
	 * This function runs a loop over a delays.
	 * it checks the sum of a number and its reverse is Palindrome
	 * And resultant parameters are passed to print method.
	 * 
	 * @param checkMyNum	Number to check for Lychrel
	 */
	public static void checkForLychrel( int checkMyNum ) {
		// Constants for checking Lychrel number
		int straightNum = checkMyNum;
		String reverseNum = getReverseNumber( checkMyNum );
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
		printMyResult( checkMyNum, straightNum, reverseNum, myDelay , lychrelNum );
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
	public static void printMyResult( int numToCheck, int myNumber, String myRevNumber, int noOFDelay, boolean isLychrel ) {
		
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
	static boolean isPalindrome( int number ) {
		StringBuilder stringOfNumber = new StringBuilder( Integer.toString(number) );
		int reversOfNumber = Integer.parseInt(stringOfNumber.reverse().toString()); 
		
		return (reversOfNumber == number);
    }

    /**
     * The main method.
     * Checking for Lychrel Number from limits provided.
     *
     * @param args
     */
    static public void main( String[] args ) {

    	for( int index = START; index < MAXIMUM; index ++) {
    		checkForLychrel( index );
    	}

    }
}


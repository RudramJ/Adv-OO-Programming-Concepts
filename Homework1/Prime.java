/**
 * Prime.java
 * 
 * Version:
 * 	   $Id$
 * 
 * Revisions:
 * 	   $Log$
 */

/**
 * The class Prime decides if a number has the following
 * property or not:-
 * the number is prime.
 * When the right most digit is removed,
 * then also it is a prime number. This must be true for
 * all the digits in a number
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Prime {
	
	static int lowerLimit = 3;
	static int upperLimit = 73939233;
	
	/**
	 * The main program
	 * 
	 * @param args
	 */
	
	public static void main( String[] args ) {
		
		for ( int index = lowerLimit; index <= upperLimit; index += 2 ) {
			
			// Checks if the given property satisfies or not
			if ( doesSatisfyProperty( index ) )
				System.out.println( index + " has the properties. " );
		}
		
	}
	
	/**
	 * checks whether a number has the property or not
	 * 
	 * @param myNum
	 * @return true if Number has the property, else false
	 */
	
	public static boolean doesSatisfyProperty( int myNum ) {
		
		// run the process until the number is 0
		while( myNum > 0 ) {
			
			// Check whether the number is Prime.
			if ( isPrime( myNum ) ) {
				myNum = removeLastDigit( myNum ); // remove the last digit
			}
			else {
				return false; // if not Prime return False
			}
			
		}
		return true;		
	}
	
	/**
	 * Checks whether number is Prime or not
	 * 
	 * @param n the number to check
	 * @return true if Prime, else false
	 */
	
	public static boolean isPrime(int n) {
		// If we get any divisor of the number
		// We will return false
		if( n == 1 ) {
			return false;
		}
		else if ( n == 2 ) {
			return true;
		}
		else if ( ( n > 2 ) && ( n % 2 == 0 ) ) {
			return false;
		}
		else {
			// Checks for divisor of the number till ( number / 2 )
			for ( int index = 3; index < (int)( n / 2 ); index ++ ) {
				
				if ( n % index  == 0 )
					 return false;
			}
		}
		return true;
	}
	
	/**
	 * Removes the last digit of the number
	 * 
	 * @param myNum
	 * @return myNum with removal of last digit
	 */
	
	public static int removeLastDigit( int myNum ) {
		myNum = (int)( myNum/10 );
		return myNum;
	}
}
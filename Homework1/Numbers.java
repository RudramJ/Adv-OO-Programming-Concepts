/*
 * Numbers.java
 *
 * Version: 
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */

/**
 * This program finds whether sum of individual digits when
 * raised to any power produces the number or not.
 *
 * @author      Rudram Joshi
 * @author      Moinuddin Memon
 */

public class Numbers {
	
	static int LOWER_LIMIT = 0;
	static int UPPER_LIMIT = 10000;
	static int maxPower = 0;

	/**
	 * Checks if the number has the following property.
	 * A number n exists, so such the sum of each (digit^n)
	 * is equal to the number. The number is a sum of ’nth’
	 * power of each digit of a n digit number is equal to 
	 * n digit of a number.
	 * 
	 * @param myNum
	 * @param power
	 * 
	 * @return whether the number has property or not
	 */
	
	public static boolean hasProperty( int myNum, int power ) {
		int x = myNum; 
		int last, sum = 0;
		
		while( x != 0 ) {
			last = x % 10; //gets the last digit of the number
			
			if( (int) Math.pow( last, power ) > myNum ) {
				return false;
			}
			//adds the number raised to power to sum variable
			sum += Math.pow( last, power );	
			x = (int) x / 10;	//removes the last digit 
		}
		// if we get the required sum then return true else return false
		return ( sum == myNum ); 
	}
	
	/**
	 * This prints the number having the property
	 * in the prescribed format.
	 * The number is converted to a character Array
	 * and each digit is printed with the "^" character
	 * along side the power character
	 * 
	 * @param myNum
	 * @param power
	 */
	
	public static void printFormat( int myNum, int power ) {
		char[] chars = ( "" + myNum ).toCharArray();
		System.out.println( myNum +"   ==     "+ myNum + " has the desired property" );
		
		for( int index = 0; index < chars.length; index++ ) {
			
			if( chars.length == 1 ) {
				System.out.print( chars[index] + " ^ " + power );
			}
			else {
				if( index < chars.length-1 ) {
					System.out.print( chars[index] + " ^ " + power );
					System.out.print( " + " );
				}
				else {
					System.out.print( chars[index] +" ^ " + power );
				}
			}
		}
		System.out.println();
	}
	
	/**
	 * The main program.
	 * 
	 * @param    args    command line arguments (ignored) 
     */
	
	public static void main( String args[] ) {	

		// This for loop calculates the maximum power possible
		// Since 2 is the  minimum number, 2^n should be greater
		// than or equal to UPPER_LIMIT.

		for ( int i = 0; i < UPPER_LIMIT; i++ ) {
			int temp = (int)Math.pow( 2, i ); 
			
			if( temp > UPPER_LIMIT ) {
				maxPower = i;
				break;
			}
		}
		
		// The for loop calls the function hasProperty and
		// printFormat with the values of the index between LOWER_LIMIT to UPPER_LIMIT
		// and the power between 1 to maxPower.
		// The value of power can be increased as there is 
		// looping mechanism in hasProperty function which would 
		// return false if any digit raised to any Power gives a value 
		// greater than the number itself.
		
		for( int index = LOWER_LIMIT + 1; index < UPPER_LIMIT; index++ ) {
			for( int power = 1; power <= maxPower; power++ ) {
				
				if( hasProperty( index, power ) ) {
					printFormat( index, power );
					break;
				}			
			}
		}	 
	}	 
}


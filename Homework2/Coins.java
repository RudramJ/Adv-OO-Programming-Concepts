/**
 * Coins.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class Coins gives the least amount of coins in the wallet
 * after paying the required bill amount.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Coins {
  
    // Input parameters
	final static int[] coins   = { 1, 1, 2, 5, 3, 1, 5, 8, 9 };
	final static int[] cashiersCoins = { 2, 1, 2, 5};
	final static int[] toPay   = { 13, 90, 0, 16, 4, 5, 7, 8 };
	
	// Initializing array to merge the input coins
	final static int myArrLength = coins.length + cashiersCoins.length;
	final static int[] totalCoins = new int[myArrLength];
	
	/**
	 * Check for possible combination of coins to pay the required amount
	 * Makes sure maximum number of coins are used from the wallet
	 * 
	 * @param amountToPay	
	 */
	public static void checkForCombination( int amountToPay ) {
		// Possible combination of coins 
		int possibleCombination = (int) Math.pow( 2, myArrLength ) - 1; 
		  
		// Constants to store the combination and number of coins
		int[] coinsBinary = new int[myArrLength];
		int[] maxPossibleCoins = new int[myArrLength];
		int prevNumberOfCoins = 0;
		int numberOfCoins = 0;
		  
		// Ignore for 0 cents
		if (amountToPay != 0) {
			for ( int combination = possibleCombination; combination > 0 ; combination-- ) {         
		          
				//Gets binary equivalent array of the combination
				coinsBinary = getBinaryArray( combination );              
				numberOfCoins = getNumberOfCoins( coinsBinary );
		  
				//Checks for sum of combination of coins 
				if( checkForSum(coinsBinary, amountToPay) ) {   
					// Check and store if maximum coins are used
					if( numberOfCoins > prevNumberOfCoins ) {         
						prevNumberOfCoins = numberOfCoins;
		          
						for ( int i = 0; i < myArrLength; i++ ) {         
							maxPossibleCoins[i] = coinsBinary[i]*totalCoins[i];
						}
					}             
				} 
			}        
		}
		// Passing the required parameters to printSolution method to
		// print the result
		if( prevNumberOfCoins == 0 ) {
			printSolution( maxPossibleCoins, false, amountToPay ); // false indicates cannot pay
		}
		else {
			printSolution( maxPossibleCoins, true, amountToPay ); // true indicates can pay
		}
	}
	 
	 /**
	  * Converts a number into its binary equivalent.
	  * And returns the corresponding array of it.
	  * 
	  * @param numberToConvert  Number to convert into binary
	  * @return         Binary array of number
	  */
	static int[] getBinaryArray( int numberToConvert ) {
		int tempVar = numberToConvert;
		  
		int[] binaryArray = new int[myArrLength];
		int arrayIndex = ( myArrLength - 1 );
		  
		// Checks for remainder when divide by 2 for binary
		while( tempVar > 0 ) {
			// Appending numbers from last index to avoid reversing
			binaryArray[arrayIndex--] = tempVar % 2;      
			tempVar = (int) tempVar / 2;  
		}       
		return binaryArray;	 
	}
	 
	 /**
	  * Counts the number of coins in the given combination.
	  * 
	  * @param anyBinarArray  Possible combination of coins
	  * @return         	  Number of coins present
	  */
	static int getNumberOfCoins( int[] anyBinarArray ) {
		int counter = 0;
		for( int index = 0; index < anyBinarArray.length; index++ ) {
			if( anyBinarArray[ index ] == 1 ) {
				counter++;
			}
		}  
		return counter;
	}
	 
	 /**
	  * This method checks if the sum is possible for given combination
	  * of the coins. And returns the boolean value according to it.
	  * 
	  * @param myArr    	One of the combinations of coins
	  * @param amtToPay		A bill amount from "toPay" array
	  * 
	  * @return Sum possible for the given combination or not.
	  */	 
	static boolean checkForSum( int[] myArr, int amtToPay ) {
		// Variable to store sum of coins
		int tempSum = 0;
		   
		for( int j = 0; j < myArr.length; j++ ) {
			// adding the elements whose index is one in binary to get sum
			if( myArr[j] == 1 ) {
				tempSum += totalCoins[j];         
			}
		}
		// If sum matches with bill amount return true, else false.
		return (tempSum == amtToPay); 
	}
	 
	 /**
	  * This method prints the solution in the given format.
	  * Sorting coins in descending order to match the given output.
	  * Splitting array to get cashier coins and wallet coins.
	  * 
	  * @param requiredCoins    The final combination of coins  
	  *             			including cashier coins
	  * @param satisfied    	Checks if bill can be paid or not
	  * @param amountToPay    	Bill amount
	  */
	public static void printSolution( int[] requiredCoins, boolean satisfied, int amountToPay ) {
		int printOnce = 0;
		//Check if bill can be paid
		if ( satisfied == true ) {
			//Sort coins in descending order
			requiredCoins = sortMyArray( requiredCoins );
			System.out.print( amountToPay+" cents:\tI gave the cashier the following coins " );
		   
			for ( int index = 0; index < requiredCoins.length; index++ ) {            
				//Ignore coins with value 0
				if( requiredCoins[index] > 0 ) {
					System.out.print( requiredCoins[index] + " cents " );
				}
				else if( requiredCoins[index] < 0 ) {
		    
					if( printOnce < 1 ) {
						System.out.print("and the cashier gave me ");
					}
					printOnce = 1;
					System.out.print( requiredCoins[index] * (-1) + " cents " );
				}
			}
			System.out.println();
		}
		else {
			System.out.println( amountToPay+" cents:\tcan not be paid" );
		}
	}	
	 
	 /**
	  * This methods sorts the given array into descending order.
	  * Interchange if elements at (index + 1)
	  * is greater than element at index		 
	  * 
	  * @param sortThisArr  Final combination of coins
	  * 
	  * @return       		Sorted Final combination of coins
	  */	 
	public static int[] sortMyArray( int[] sortThisArr ) {
		for ( int i = 0; i < sortThisArr.length - 1; i++ ) {        
			for ( int j = 0; j < sortThisArr.length - 1; j++ ) {	      
				 
				if( sortThisArr[j + 1] > sortThisArr[j] ) {
					int temp = sortThisArr[j+1];
					sortThisArr[j + 1] = sortThisArr[j];
					sortThisArr[j] = temp;
				}
			}
		}
		return sortThisArr;
	}	
	 
	 /**
	  * The main program. 
	  * Merging cashier coins into consideration by negating it.
	  *
	  * @param    args    command line arguments (ignored)
	  */	     
	public static void main( String []args ) {
		//Negating cashier coins for merging
		for ( int negate = 0; negate < cashiersCoins.length; negate++) {
			cashiersCoins[negate] = cashiersCoins[negate] * (-1);
		}
			 
		//Merging both wallet coins and cashier coins into one array
		System.arraycopy(coins, 0, totalCoins, 0, coins.length);
		System.arraycopy(cashiersCoins, 0, totalCoins, coins.length, cashiersCoins.length);
			 
		// for loop to check sum of all the bill amount in "toPay"
		for( int payIndex = 0; payIndex < toPay.length; payIndex++ ) { 
			checkForCombination( toPay[payIndex] );
		}
	}	     
}


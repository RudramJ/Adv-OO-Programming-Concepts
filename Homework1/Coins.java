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
 * The class Coins gives the maximum number of coins 
 * possible from the coins array to pay the bill in
 * the exact amount.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class Coins {

	static int[] coins   = { 1, 1, 2, 5, 3, 1, 5, 8, 9, 2, 2 };
    static int[] toPay   = { 0, 13, 50, 4, 5, 7, 8 };
    
     /**
     * The main program.
     *
     * @param    args    command line arguments (ignored)
     */
    
     public static void main( String []args ) {         
    	// Length of coins array
     	int myArrLength = coins.length;	
     	// Declaring array to store combinations of coins
     	int[] coinsBinArr = new int[myArrLength];	
        // Number of possible combinations of coins
     	int possibleCombination = (int) Math.pow( 2, myArrLength ) - 1;
     	
     	// for loop to check sum of all the bill amount in "toPay"
     	for( int payIndex = 0; payIndex < toPay.length; payIndex++ ) { 
     		// Variable to store previous no. of coins which sums up to bill amount
     		int prevNoOfElem = 0;
     		// Array to store previous combination of coins which sums up to bill amount
     		int[] maxCoinsReq  = new int[myArrLength];
     		
     		// for loop to get all possible combination of coins
	        for ( int i = possibleCombination; i > 0 ; i-- ) { 	        
	        	int tempVar = i;
	        	// Declaring index for coinsBinArr
	        	int binArrIndex = ( myArrLength - 1 );
	        	// Counts no. of coins in combination
	        	int noOfElem = 0;
	        	
	        	// logic for converting combinations into binary
	        	// Gets an array of combination in binary(1's and 0's)
	        	while( tempVar > 0 ) {	
	        		//Appending numbers from last index to avoid reversing
	        		coinsBinArr[binArrIndex--] = tempVar % 2;   		
	        		tempVar = (int) tempVar / 2;
	        		noOfElem++;	        		
	        	}
	        	
	        	// for loop to add zeros in empty index
	        	// for eg 101 becomes 0101	(equating with the length same as coins array)    	
	        	for( int j = 0; j < ( myArrLength - noOfElem ); j++) { 
	        		coinsBinArr[j] = 0;
	        	}
	        	
	        	//Checks for sum of combination of coins 
	        	if( checkForSum(coinsBinArr, toPay[payIndex]) ) {
	        		
	        		// Comparing with previous no. of coin
	        		// whose sum gave output
	        		if(noOfElem > prevNoOfElem) {
	        			// Update prevNoOfElem with max coins count
	        			prevNoOfElem = noOfElem;
	        			// Update maxCoinsReq array with max coins
		        		for (int k = 0; k < myArrLength; k++) {
		        			// Multiplying with combination to get coins
		        			// For eg. [1 0 1] * [2 3 4] gives [2 0 4]
		        			maxCoinsReq[k] = coinsBinArr[k]*coins[k];
		        		}
	        		}	        		
	        	}	        	     	
	        }
	        //Check if bill can be paid or not
	        if( prevNoOfElem == 0 ) {
	        	printSolution( maxCoinsReq, false, toPay[payIndex] );
	        }
	        else {
	        	printSolution( maxCoinsReq, true, toPay[payIndex] );
	        }
     	}
     }
     
     /**
      * This method checks if the sum is possible for given combination
      * of the coins. And returns the boolean value according to it.
      * 
      * @param myArr 		One of the combinations of coins
      * @param amtToPay 	A bill amount from "toPay" array
      * 
      * @return Sum possible for the given combination or not.
      */
     
     static boolean checkForSum( int[] myArr, int amtToPay ) {
    	 // Variable to store sum of coins
    	 int tempSum = 0;
    	 
    	 for( int j = 0; j < myArr.length; j++ ) {
    		 // adding the elements whose index is one in binary to get sum
    		 // for eg. myArr = [1 0 1], coins = [2 3 4]
    		 // Sum will be 2 + 4 = 6
			 if( myArr[j] == 1 ) {
				 tempSum += coins[j];				 
			 }
		 }
    	 
    	 // If sum matches with bill amount return true, else false.
    	 if( tempSum == amtToPay ) {
			 return true;
		 }
    	 else {
    		 return false;
    	 }    	 
     }
     
     /**
      * This method prints the solution in the given format.
      * The final array is first sorted in descending order to
      * match the given output.
      * 
      * @param requiredCoins   	The final combination of coins	
      * @param satisfied		Checks if bill can be paid or not
      * @param amountToPay		Bill amount
      */
     
     public static void printSolution( int[] requiredCoins, boolean satisfied, int amountToPay ) {
  		
    	 //Check if bill can be paid
    	 if ( satisfied == true ) {
    		 //Sort coins in descending order
    		 requiredCoins = sortMyArray( requiredCoins );
    		 System.out.print( amountToPay+" cents:\tyes; used coins = " );
 	 		 
     		 for ( int index = 0; index < requiredCoins.length; index++ ) {
     			 
     			 //Ignore coins with value 0
     			 if( requiredCoins[index] != 0 ) {
     				System.out.print( requiredCoins[index] + " cents " );
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
      * 
      * @param sortThisArr 	Final combination of coins
      * 
      * @return 			Sorted Final combination of coins
      */
     
     public static int[] sortMyArray( int[] sortThisArr ) {

  		for ( int i = 0; i < sortThisArr.length; i++ ) {  			
  			for ( int j = 0; j < sortThisArr.length - 1; j++ ) {
  				
  				// Interchange if elements at (index + 1)
  				// is greater than element at index
  				if( sortThisArr[j + 1] > sortThisArr[j] ) {
  					int temp = sortThisArr[j+1];
  					sortThisArr[j + 1] = sortThisArr[j];
  					sortThisArr[j] = temp;
  				}
  			}
  		}
  		return sortThisArr;
  	}
}


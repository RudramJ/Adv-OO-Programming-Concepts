/**
 * Flexible.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class Flexible stores as many objects 
 * as memory allows
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.util.Date;

public class Flexible {
	// Global parameters for Flexible array
	static Object[] myArray = new Object[0];
	static int myArrayIndex = 0;
	static boolean checkTillFirstNull = false;
	static int myNullCount = 0;
	static boolean myNullCreated = false;
	static boolean unlimited = true;
	static Date creationTime;
	static Date modifiedTime;
	static String previousClassType = "";
	
	/**
	 * Default constructor of class Flexible
	 */
	public Flexible() {
		creationTime = getTimeStamp();
		modifiedTime = getTimeStamp();
	}
	/**
	 * Get the timeStampValue
	 * @return TimeStamp
	 */
	private static Date getTimeStamp() {
		Date timeStamp = new Date();
		return timeStamp;
	}
	/**
	 * Method to add the element in the array
	 * @param myElement Element to be added
	 * @return	True, if element can be added
	 */
	public static boolean add( Object myElement ) {
		
		if(!checkTillFirstNull) {
			
			myArray = getIncreasedArraySize(myArray);			
			myArray[myArrayIndex] = myElement;
			myArrayIndex++;
			if(myElement != null ) {
				checkTillFirstNull = true;
				previousClassType = myElement.getClass().getName();
			}
			else {
				myNullCount++;
			}
			modifiedTime = getTimeStamp();
			return true;			
		}
		else {
			if(myElement == null) {
				myArray = getIncreasedArraySize(myArray);			
				myArray[myArrayIndex] = myElement;
				myArrayIndex++;
				myNullCount++;	
				modifiedTime = getTimeStamp();
				return true;
				
			}
			else {
				if(previousClassType.equals(myElement.getClass().getName())) {
					myArray = getIncreasedArraySize(myArray);			
					myArray[myArrayIndex] = myElement;
					myArrayIndex++;
					modifiedTime = getTimeStamp();		
					return true;					
				}
			}
		}			
		return false;		
	}
	/**
	 * This method returns the increased array size according
	 * to the element added in run-time
	 * @param increaseArraySize	  Array of which size to be incremented
	 * @return					  Incremented size array
	 */
	public static Object[] getIncreasedArraySize(Object[] increaseArraySize) {
		int oldLength = increaseArraySize.length;
		Object[] returnArray = new Object[oldLength+1];
		
		for(int i = 0; i < oldLength; i++) {
			returnArray[i] = increaseArraySize[i];
		}		
		return returnArray;
	}
	/**
	 * Method to delete the element from an array
	 * @param myElementToDelete Element to be deleted
	 * @return	True, if element can be deleted
	 */
	public static boolean delete( Object myElementToDelete ) {
		for( int i = 0; i < myArrayIndex; i ++) {
			
			if(myArray[i] == null) {
				if(myElementToDelete == null) {
					
					myArray = getDecreasedArraySize(myArray, i);
					myArrayIndex--;
					myNullCount--;
					modifiedTime = getTimeStamp();
					return true;
				}			
			}
			else {
				
				if(myArray[i].equals(myElementToDelete)) {

					myArray = getDecreasedArraySize(myArray, i);
					myArrayIndex--;
					modifiedTime = getTimeStamp();
					return true;
				}				
			}
		}					
		return false;				
	}
	/**
	 * This method returns the decreased array size according
	 * to the element to be deleted in run-time
	 * @param decreaseArraySize		Array of which size to be decremented
	 * @param indexToLeave			Index of array to be deleted
	 * @return						Decremented size array
	 */
	public static Object[] getDecreasedArraySize(Object[] decreaseArraySize, int indexToLeave) {
		int oldLength = decreaseArraySize.length;
		Object[] returnArray = new Object[oldLength-1];
		
		for(int i = 0; i < oldLength-1; i++) {
			if(i < indexToLeave) {
				returnArray[i] = decreaseArraySize[i];
			}
			else if(i >= indexToLeave) {
				returnArray[i] = decreaseArraySize[i+1];
			}
		}		
		return returnArray;
	}
	/**
	 * Returns the maximum number of elements can be stored
	 * @return
	 */
	public static int getMax() {
		return myArray.length;
	}
	/**
	 * Returns the current number of elements stored
	 * @return
	 */
	public static int size() {
		return myArray.length;
	}
	/**
	 * Returns true if no more elements can be stored unless an element is deleted
	 * @return
	 */
	public static boolean isFull() {
		return false;
	}
	/**
	 * Returns true if 0 ore elements are stored
	 * @return
	 */
	public static boolean isEmpty() {
		if(myArrayIndex == 0) {
			return true;
		}
		else {
			return false;
		}		
	}
	/**
	 * Returns true if a particular element is stored
	 * @param checkForElement	Element to check in array
	 * @return	True/False depending on presence of element			
	 */
	public static boolean contains( Object checkForElement) {
		for( int i = 0; i < myArrayIndex; i ++) {
			if(checkForElement != null) {
				if(myArray[i].equals(checkForElement)) {
					
					return true;
				}
			}
			else {
				if(myArray[i] == null) {
					return true;
				}
			}			
		}		
		return false;
	}
	/**
	 * toString method, overrides the method of object toString
	 */
	public String toString() {
		if(myNullCount > 0) {
			myNullCreated = true;
		}
		else {
			myNullCreated = false;
		}
		
		String toPrint = "";
		toPrint = "name: "+ this.getClass().getName() +"\n";
		toPrint += "creationTime: "+ creationTime +"\n";
		toPrint += "unlimited: "+ unlimited +"\n";
		toPrint += "soMany = "+ myArrayIndex +"\n";
		toPrint += "nullObjectCreated = "+ myNullCreated +"\n";
		
		toPrint += "modificationTime: "+ modifiedTime +"\n";

		for( int i = 0; i < myArrayIndex; i ++) {
			if(myArray[i] != null) {
				toPrint += (myArray[i]) + ", ";
			}			
		}
		return toPrint + "\n";
	}
	/**
	 * The main method
	 * @param args
	 */
	public static void main( String args[]) {

		Flexible myObj = new Flexible();		
		String one 		= String.valueOf(1);
		String two 		= String.valueOf(2);
		String three	= String.valueOf(3);
		myObj.add(one);
		myObj.add(two);
		myObj.add(three);
		myObj.add(null);
		myObj.add("a");
		System.out.println(myObj);	
	}
}


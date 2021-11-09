/**
 * Array.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * The class Array stores the fixed amount of 
 * objects 
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */


import java.util.Date;

public class Array {
	// Global parameters for Array
	private final static int MY_LIMITED_SIZE = 4;
	static Object[] myArray = new Object[MY_LIMITED_SIZE];
	static int myArrayIndex = 0;
	static int myNullCount = 0;
	static boolean myNullCreated = false;
	static boolean checkTillFirstNull = false;
	static boolean unlimited = false;
	static Date creationTime;
	static String previousClassType = "";
	
	/**
	 * Default constructor of class Array
	 * Updates the time-stamp creation time of the object
	 */
	public Array() {
		creationTime = getTimeStamp();
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
			
			if(myArrayIndex < MY_LIMITED_SIZE) {
				
				myArray[myArrayIndex] = myElement;
				myArrayIndex++;
				if(myElement != null ) {
					checkTillFirstNull = true;
					previousClassType = myElement.getClass().getName();
				}
				else {
					myNullCount++;
				}				
				return true;
			}
		}
		else {
			if(myElement == null) {
				if(myArrayIndex < MY_LIMITED_SIZE) {
					
					myArray[myArrayIndex] = myElement;
					myArrayIndex++;
					myNullCount++;	
					return true;
				}
			}
			else {
				if(previousClassType.equals(myElement.getClass().getName())) {
					if(myArrayIndex < MY_LIMITED_SIZE) {
						
						myArray[myArrayIndex] = myElement;
						myArrayIndex++;
								
						return true;
					}
				}
			}
		}			
		return false;		
	}
	/**
	 * Method to delete the element from an array
	 * @param myElementToDelete Element to be deleted
	 * @return	True, if element can be deleted
	 */
	public static boolean delete( Object myElementToDelete ) {
		
		for( int i = 0; i < myArrayIndex; i ++) {
			
			if(myArray[i] == null ) {
				if(myElementToDelete == null) {
					
					for(int j = i; j < myArrayIndex-1; j++) {
						myArray[j] = myArray[j+1];
					}
					myArrayIndex--;
					myNullCount--;
					return true;
				}			
			}
			else {
				
				if(myArray[i].equals(myElementToDelete)) {
					for(int j = i; j < myArrayIndex-1; j++) {
						myArray[j] = myArray[j+1];
					}
					myArrayIndex--;
					return true;
				}				
			}
		}					
		return false;			
	}
	/**
	 * Gives the maximum size of array
	 * @return	Max size of array
	 */
	public static int getMax() {
		return MY_LIMITED_SIZE;
	}
	/**
	 * Gives the current number of elements stored
	 * @return  Current number of elements in array
	 */
	public static int size() {
		return myArrayIndex;
	}
	
	/**
	 * Returns true if no more elements can be stored unless an element is deleted
	 * @return	
	 */
	public static boolean isFull() {
		if(myArrayIndex >= MY_LIMITED_SIZE-1) {
			return true;
		}
		else {
			return false;
		}
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
	 * @param checkForElement 	Element to check in array
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
		toPrint += "soMany = "+ (myArrayIndex) +"\n";
		toPrint += "nullObjectCreated = "+ myNullCreated +"\n";
		
		toPrint += "modificationTime: "+ getTimeStamp() +"\n";

		for( int i = 0; i < myArrayIndex; i ++) {
			if(myArray[i] != null) {
				toPrint += (myArray[i]) + ", ";
			}			
		}
		return toPrint + "\n";
	}
	
	public static void main( String args[]) {
		
		Array myObj = new Array();
		Integer one 		= Integer.valueOf(1);
		Integer two 		= Integer.valueOf(2);
		Integer three 		= Integer.valueOf(3);

		myObj.add(one);
		myObj.add(two);
		myObj.add(two);
		myObj.add(null);
		
		System.out.println(myObj);
	}
}

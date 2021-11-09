/**
 * Address.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class stores the attributes of Address.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class Address implements Comparable<Address> {
    // Instance Variable
	private Integer blockNumber;
	private String streetName;
	private String cityName;
	private String stateName;
	private Integer pinNumber;

    /**
     * The parameterized constructor of Address
     * @param blockNumber   Block number
     * @param streetName    Name of Street
     * @param cityName      Name of City
     * @param stateName     Name of State
     * @param pinNumber     Pin number
     */
	public Address(int blockNumber, String streetName, String cityName, String stateName, int pinNumber) {
		this.blockNumber = blockNumber;
		this.streetName = streetName;
		this.cityName = cityName;
		this.stateName = stateName;
		this.pinNumber = pinNumber;
	}

    /**
     * The toString method
     * @return Returns the string when the object is printed
     */
	public String toString() {
		String returnString = "";
		returnString += this.blockNumber + "/";
		returnString += this.streetName + "/";
		returnString += this.cityName + "/";
		returnString += this.stateName + "/";
		returnString += this.pinNumber;
		return returnString;
	}

    /**
     * The method of interface Comparable
     * @param o     Object of class Address
     * @return      a negative integer, zero, or a positive integer as this object
     *              is less than, equal to, or greater than the specified object.
     */
	public int compareTo(Address o) {
		String myValue = String.valueOf(blockNumber);
		String compareValue = String.valueOf(o.blockNumber);
		return myValue.compareTo(compareValue);
	}
}

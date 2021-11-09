/**
 * ParentOfAstronomicalObject.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * This class is the parent class of all the astronomical objects
 * like Planet, Asteroid and Binaries.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class ParentOfAstronomicalObject {
	// Instance variable which is common to all astronomical objects
	public String name = "";
	public double density = 0;
	/**
	 * Default Constructor
	 */
	public ParentOfAstronomicalObject() {
		
	}
	/**
	 * Parameterized Constructor 
	 * @param name			Name of celestial body
	 * @param density		Density of celestial body
	 */
	public ParentOfAstronomicalObject(String name, double density) {
		this.name = name;
		this.density = density;
	}
    /**
     * This method returns the name of celestial body
     * @return		Name of celestial body
     */
	public String getName() {
		return this.name;
	}
	/**
	 * This method returns the density of celestial body
	 * @return		Density of celestial body
	 */
    public double getDensity() {
		return this.density;
	}
    /**
     * This method sets the name of celestial body
     * @param name		Name of celestial body
     */
    public void setName(String name) {
    	this.name = name;
	}
    /**
     * This method sets the density of celestial body
     * @param density	Density of celestial body
     */
    public void setDensity(double density) {
    	this.density = density;
	}
    /**
     * returns string on printing the object of class
     */
    public String toString() {
    	return this.name + "/" + this.density;
    }
}	

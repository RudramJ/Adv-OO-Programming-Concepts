/**
 * Binaries.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * This class is the child class of ParentOfAstronomicalObject.
 * This class stores the attributes of Binaries like name, density
 * discoverer and satellites.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class Binaries extends ParentOfAstronomicalObject{
	// Instance Variable
	String  discoverer;
    int satellites;
    /**
	 * Default constructor
	 */
    public Binaries()	{
	}
    /**
	 * Parameterized constructor with object of same type
	 * @param myBinariesObject	Object of class Binaries
	 */
    public Binaries(Binaries myBinariesObject) {
		super(myBinariesObject.getName(), myBinariesObject.getDensity());		
		this.discoverer =  myBinariesObject.getDiscoverer();
		this.satellites =  myBinariesObject.getSatelites();
	}
    /**
	 * Parameterized Constructor 
	 * @param name				Name of Binaries
	 * @param density			Density of Binaries
	 * @param discoverer		discoverer of Binaries
	 * @param satellites		satellites of Binaries
	 */
  	public Binaries(String name, double density, String  discoverer, int satellites)	{
  		super(name, density);
  		this.discoverer = discoverer;
  		this.satellites = satellites;
	}
  	/**
     * This method returns the name of Binaries
     * @return		Name of Binaries
     */
    public String getName() {
    	return super.getName();
    }
    /**
     * This method returns the Density of Binaries
     * @return		Density of Binaries
     */
    public double getDensity() {
    	return super.getDensity();
    }
    /**
     * This method returns the satellites of Binaries
     * @return		satellites of Binaries
     */
    public int getSatelites() {
    	return this.satellites;
    }
    /**
     * This method returns the discoverer of Binaries
     * @return		discoverer of Binaries
     */
    public String getDiscoverer() {
    	return this.discoverer;
    }
    /**
	 * This method sets the satellites of the Binaries
	 * @param satellites		satellites of Binaries
	 */
    public void setSatelites(int satellites) {
    	this.satellites = satellites;
    }
    /**
	 * This method sets the discoverer of the Binaries
	 * @param discoverer		discoverer of Binaries
	 */
    public void setDiscoverer(String discoverer) {
    	this.discoverer = discoverer;
    }
    /**
   	 * This method sets the name of the Binaries
   	 * @param name		name of Binaries
   	 */
    public void setName(String name) {
    	super.name = name;
    }
    /**
   	 * This method sets the density of the Binaries
   	 * @param density		density of Binaries
   	 */
    public void setDensity(double density) {
    	super.density = density;
    }
    /**
	 * returns string on printing the object of class
	 */
    public String toString() {
    	return super.toString() + "/" + discoverer + "/" + satellites;
    }
    /**
	 * The main method
	 * @param args
	 */
    public static void main(String args[])	{
		Binaries aBinaries = new Binaries("AP35", 5.427, "RuD", 4);
		System.out.println(aBinaries);
	}
}

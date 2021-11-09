/**
 * Asteroid.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * This class is the child class of ParentOfAstronomicalObject.
 * This class stores the attributes of Asteroid like name, density
 * and discoverer.
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class Asteroid extends ParentOfAstronomicalObject{
	// Instance Variable
    String  discoverer;
    /**
	 * Default constructor
	 */
    public Asteroid()	{
	}
    /**
	 * Parameterized constructor with object of same type
	 * @param myAsteroidObject		Object of class Asteroid
	 */
    public Asteroid(Asteroid myAsteroidObject) {
		super(myAsteroidObject.getName(), myAsteroidObject.getDensity());
		this.discoverer =  myAsteroidObject.getDiscoverer();
	}
    /**
	 * Parameterized Constructor 
	 * @param name				Name of asteroid
	 * @param density			Density of asteroid
	 * @param discoverer		name of discoverer
	 */
  	public Asteroid(String name, double density, String  discoverer)	{
  		super(name, density);
		this.discoverer =  discoverer;
	}
  	/**
     * This method returns the name of asteroid
     * @return		Name of asteroid
     */
  	public String getName() {
  		return super.getName();
    }
  	/**
     * This method returns the density of asteroid
     * @return		Density of asteroid
     */
    public double getDensity() {
    	return super.getDensity();
    }
    /**
     * This method returns the Discoverer of asteroid
     * @return		Discoverer of asteroid
     */
    public String getDiscoverer() {
    	return discoverer;
    }
    /**
	 * This method sets the Discoverer of the asteroid
	 * @param discoverer		Discoverer of asteroid
	 */
    public void setDiscoverer(String discoverer) {
    	this.discoverer = discoverer;
    }
    /**
	 * This method sets the name of the asteroid
	 * @param name		name of asteroid
	 */
    public void setName(String name) {
    	super.name = name;
    }
    /**
	 * This method sets the density of the asteroid
	 * @param density		density of asteroid
	 */
    public void setDensity(double density) {
    	super.density = density;
    }
    /**
	 * returns string on printing the object of class
	 */
    public String toString() {
    	return super.toString() + "/" + discoverer;
    }
    /**
     * The main method
     * @param args
     */
    public static void main(String args[])	{
    	Asteroid aBinaries = new Asteroid("NR225", 44.57, "Nolan");
		System.out.println(aBinaries);
	}
}

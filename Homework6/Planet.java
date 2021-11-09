/**
 * Planet.java
 * 
 * Version:
 *     $Id$
 * 
 * Revisions:
 *     $Log$
 */

/**
 * This class is the child class of ParentOfAstronomicalObject.
 * This class stores the attributes of Planet like name, density
 * orbital period and number of moons.
 * 
 * 
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
public class Planet extends ParentOfAstronomicalObject{
	// Instance Variable
	double  orbitalPeriod;
	int    numberOfMoons;
	/**
	 * Default constructor
	 */
	public Planet() {
		
	}
	/**
	 * Parameterized constructor with object of same type
	 * @param myPlanetObject	Object of class Planet
	 */
	public Planet(Planet myPlanetObject) {
		super(myPlanetObject.getName(), myPlanetObject.getDensity());
		this.orbitalPeriod =  myPlanetObject.getOrbitalPeriod();
		this.numberOfMoons =  myPlanetObject.getNumberOfMoons();
	}
	/**
	 * Parameterized Constructor 
	 * @param name				Name of planet
	 * @param density			Density of planet
	 * @param orbitalPeriod		orbital period of planet
	 * @param numberOfMoons		number of moons of planet
	 */
  	public Planet(String name, double density, double orbitalPeriod, int numberOfMoons)	{

  		super(name, density);
		this.orbitalPeriod =  orbitalPeriod;
		this.numberOfMoons =  numberOfMoons;
	}
  	/**
     * This method returns the name of planet
     * @return		Name of planet
     */
	public String getName()	{
		return super.getName();
  	}
	/**
     * This method returns the density of planet
     * @return		Density of planet
     */
	public double getDensity()	{
		return super.getDensity();
  	}
	/**
     * This method returns the orbital period of planet
     * @return		orbital period of planet
     */
	public double getOrbitalPeriod()	{
		return orbitalPeriod;
  	}
	/**
     * This method returns the number of moons of planet
     * @return		number of moons of planet
     */
	public int getNumberOfMoons()	{
		return numberOfMoons;
  	}
	/**
	 * This method sets the name of the planet
	 * @param name		Name of planet
	 */
	public void setName(String name)	{
		super.name =  name;
  	}
	/**
	 * This method sets the density of the planet
	 * @param density		density of planet
	 */
	public void setDensity(double density)	{
		super.density =  density;
  	}
	/**
	 * This method sets the orbital period of the planet
	 * @param orbitalPeriod		orbital period of planet
	 */
	public void setOrbitalPeriod(double orbitalPeriod)	{
		this.orbitalPeriod =  orbitalPeriod;
  	}
	/**
	 * This method sets the number of moons of the planet
	 * @param numberOfMoons		number of moons of planet
	 */
	public void setNumberOfMoons(int numberOfMoons)	{
		this.numberOfMoons =  numberOfMoons;
  	}
	/**
	 * returns string on printing the object of class
	 */
	public String toString()	{
        return super.toString() + "/" + this.orbitalPeriod + "/" + this.numberOfMoons;
	}
	/**
	 * The main method
	 * @param args
	 */
	public static void main(String args[])	{
		Planet aPlanet = new Planet("Mercury", 5.427, 87.97, 10);
		System.out.println(aPlanet);
		
		Planet bPlanet = new Planet();
		bPlanet.setName("Venus");
		bPlanet.setDensity(2.2);
		bPlanet.setOrbitalPeriod(33.33);
		bPlanet.setNumberOfMoons(3);
		System.out.println(bPlanet);

	}
}

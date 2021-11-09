
/**
 * Planet class contains all the Attributes of a Planet and methods
 * @author Moinuddin Memon,Rudram Joshi
 *
 */
public class Planet {
	String name;
	double density;
	double orbitalPeriod;
	int numberOfMoons;
	/**
	 * Parameterize constructor to initialize a Planet Object
	 * @param name
	 * @param density
	 * @param orbitalPeriod
	 * @param numberOfMoons
	 */
	Planet(String name, double density, double orbitalPeriod, int numberOfMoons) {
		if(density<0 || orbitalPeriod<0 || numberOfMoons<0) {
			System.err.println("Values cannot be negative");
			System.exit(1);
		}
		else {
			this.name = name;
			this.density = density;
			this.orbitalPeriod = orbitalPeriod;
			this.numberOfMoons = numberOfMoons;
		}
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected double getDensity() {
		return density;
	}

	protected void setDensity(double density) {
		if(density<0) {
			System.err.println("Density cannot be negative");
			System.exit(1);
		}
		else {
			this.density = density;
		}
	}

	protected double getOrbitalPeriod() {
		return orbitalPeriod;
	}

	protected void setOrbitalPeriod(double orbitalPeriod) {
		if(orbitalPeriod<0) {
			System.err.println("Orbital Period cannot be negative");
			System.exit(1);
		}
		else {
			this.orbitalPeriod = orbitalPeriod;
		}

	}

	protected int getNumberOfMoons() {
		return numberOfMoons;
	}

	protected void setNumberOfMoons(int numberOfMoons) {
		if(numberOfMoons<0) {
			System.err.println("Number of Moons cannot be negative");
			System.exit(1);
		}
		else {
			this.numberOfMoons = numberOfMoons;
		}
	}
	@Override
	public String toString() {
		return "Planet [name=" + name + ", density=" + density + ", orbitalPeriod=" + orbitalPeriod + ", numberOfMoons="
				+ numberOfMoons + "]";
	}
	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String args[])  {
        Planet aPlanet = new Planet("Mercury", 5.427, 87.97, 0);
        System.out.println(aPlanet);
        aPlanet.setName("Saturn");
        aPlanet.setDensity(0.687);
        aPlanet.setOrbitalPeriod(10759.22);
        aPlanet.setNumberOfMoons(82);
        System.out.println(aPlanet);

        System.out.println("1: " + aPlanet.getName() );
        System.out.println("2: " + aPlanet.getDensity() );
        System.out.println("3: " + aPlanet.getOrbitalPeriod() );
        System.out.println("4: " + aPlanet.getNumberOfMoons() );

	}
}

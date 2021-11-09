/**
 * LP.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class stores the attributes of Album.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

public class LP implements Comparable<LP>{
	// Instance Variable
	private Integer yearOfAlbum;
	private String nameOfAlbum;
	private String nameOfBand;
	private float averageLengthOfSong;
	private Integer numberOfSongs;

	/**
	 * The parameterized constructor of LP
	 * @param year						Year of album released
	 * @param albumName					Name of album
	 * @param bandName					Name of Band
	 * @param averageLengthOfSong		Some Float Value
	 * @param numberOfSongs				Number of songs
	 */
	public LP(int year, String albumName, String bandName, float averageLengthOfSong, int numberOfSongs) {
		this.yearOfAlbum = year;
		this.nameOfAlbum = albumName;
		this.nameOfBand = bandName;
		this.averageLengthOfSong = averageLengthOfSong;
		this.numberOfSongs = numberOfSongs;
	}
	/**
	 * The toString method
	 * @return Returns the string when the object is printed
	 */
	public String toString() {
		String returnString = "";
		returnString += this.yearOfAlbum + "/";
		returnString += this.nameOfAlbum + "/";
		returnString += this.nameOfBand + "/";
		returnString += this.averageLengthOfSong + "/";
		returnString += this.numberOfSongs;
		return returnString;
	}
	/**
	 * The method of interface Comparable
	 * @param o     Object of class LP
	 * @return      a negative integer, zero, or a positive integer as this object
	 *              is less than, equal to, or greater than the specified object.
	 */
	public int compareTo(LP o) {
		String myValue = String.valueOf(yearOfAlbum);
		String compareValue = String.valueOf(o.yearOfAlbum);
		return myValue.compareTo(compareValue);
	}
}

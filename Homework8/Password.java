/**
 * Password.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This is the serialized class whose object which creates the password
 * And the object of this class is stored.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */

import java.io.*;

public class Password implements Serializable {
    // Global Variable
    private final static int ASCII_LIMIT = 597;
    private final static int PASSWORD_LENGTH = 6;
    private String myPassword;
    private final int myPasswordLength;
    private int myPasswordAsciiValue;

    /**
     * The parameterized constructor used to store the password as string
     * @param myPassword Password
     */
    Password( String myPassword ) {
        this.myPassword = myPassword;
        this.myPasswordLength = myPassword.length();
        this.getAttribute();
    }

    /**
     * This method calculates the attribute corresponding to the password
     * In this case the attribute is summation of ASCII Value.
     */
    private void getAttribute() {
        char[] myCharArray = this.myPassword.toCharArray();
        for (char c : myCharArray) {
            this.myPasswordAsciiValue += (int) c;
        }
    }

    /**
     * This defaultWriteObject method is updated based on the password attribute
     * @param s ObjectOutputStream object
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        System.out.println("Password: writeObject");
        s.defaultWriteObject();
        if ( this.myPasswordAsciiValue != ASCII_LIMIT || this.myPasswordLength != PASSWORD_LENGTH) {
            s.writeObject(-1);
            s.writeObject(-1);
        }
        else {
            s.writeObject(myPasswordLength);
            s.writeObject(myPasswordAsciiValue);
        }
    }

    /**
     * This defaultReadObject method is updated based on the password attribute
     * @param s ObjectInputStream object
     * @throws IOException
     */
    private void readObject(ObjectInputStream s) throws IOException {
        System.out.println("Password: readObject");
        try {
            s.defaultReadObject();
            int theLength = (int) s.readObject();
            int theAsciiValue = (int) s.readObject();

            if ( this.myPasswordAsciiValue != theAsciiValue || this.myPasswordLength != theLength)
                this.myPassword = "";
        }
        catch ( ClassNotFoundException e)	{
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The toString method returns the string whenever the object of this class is printed
     * or this method is called
     * @return password in string
     */
    public String toString() {
        return this.myPassword;
    }
}

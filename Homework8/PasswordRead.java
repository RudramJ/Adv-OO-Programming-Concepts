/**
 * PasswordRead.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class is used to read the password from the serialized file
 * using the object of class Password.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.io.*;

public class PasswordRead implements Serializable {
    // Global Variable
    private final static String passwordFileName = "1234.ser";

    /**
     * The main method
     * This object of class Password is created to read the password
     * from the serialized file and printing the same if the attributes
     * matches with the password written to file
     * @param args None
     */
    public static void main(String[] args) {
        // Instance of class Password to read object
        Password passwordToRead;
        try (
                ObjectInputStream objectStream =
                        new ObjectInputStream(new FileInputStream(passwordFileName) );
        ) {
            passwordToRead = (Password) objectStream.readObject();
            System.out.println("The password is: " + passwordToRead);
        }
        catch(ClassNotFoundException | IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

/**
 * Modify.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This class is used to write the modified password into the another serialized file
 * using the object of class Password.
 *
 * @author Rudram Joshi
 * @author Moinuddin Memon
 */
import java.io.*;

public class Modify implements Serializable {
    private final static String passwordFileName = "modify.ser";
    private final static String PASSWORD = "xbcdef";

    /**
     * The main method
     * The object of class Password is created with the appropriate
     * password and write into serialized file.
     * @param args None
     */
    public static void main(String[] args) {
        // Instance of class Password to write object
        Password passwordToWrite = new Password(PASSWORD);
        try (
                FileOutputStream fileStream = new FileOutputStream(passwordFileName);
                ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
        )
        {
            objectStream.writeObject(passwordToWrite);

            objectStream.flush();
        }
        catch ( IOException e)	{
            System.out.println(e.getMessage());
        }
    }

}

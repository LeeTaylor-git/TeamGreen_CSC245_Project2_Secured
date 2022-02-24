import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;
//test


public class CSC245_Project2 {

    public static void main(String[] args) {
        // Read the filename from the command line argument
        //(not yet google standard) canonicalize paths/fixes problems FIO16-J, IDS50-J, IDS01-J, WHITELISTED_PATH
        String filename = Normalizer.normalize("C:\\tmp\\Email_addresses_20210205.txt", Normalizer.Form.NFKC);

        BufferedReader inputStream = null;
        String fileLine;


        try {
            inputStream = new BufferedReader(new FileReader(filename));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
                //(not yet google standard) sets a standard for validating email addresses. Fixes IDS51-J
                if (fileLine.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9+.-](.+)$")) {
                    System.out.println(fileLine);
                }

            }
        }

        catch(IOException io){
            //(not yet google standard) changed exposition of sensitive information. Fixes ERROR01-J.
                System.out.println("Could not open file.");
            } finally{
                // Need another catch for closing
                // the streams
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException io) {
                    //(not yet google standard) changed exposition of sensitive information. Fixes ERROR01-J.
                    System.out.println("Could not close file");
                }

        }
    }

}
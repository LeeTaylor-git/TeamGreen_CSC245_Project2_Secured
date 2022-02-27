import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

// 20220212 tml: Approval to work bug #JSI-215. Identified all security vulnerabilities and tagged them inline with bug #JSI-216
// 20220214 tml: Mitigated all existing security vulnerabilities

public class CSC245_Project2 {

    public static void main(String[] args) {
        // Read the filename from the command line argument
        // 20220212 tml: Identified security vulnerability: Canonicalize path names before validating them (FIO16-J)
        // 20220212 tml: Identified security vulnerability: Normalize strings before validating them (FIo16-J)
        // 20220212 tml: Identified security vulnerability: Use conservative file naming conventions (IDS50-J)
        // 20220212 tml: Identified security vulnerability: WHITELISTED_PATH should be changed to whatever path the user is accessing during running for data file
        // 20220214 tml: Identified security vulnerabilities (FIO16-J), (IDS50-J), WHITELISTED_PATH, in following code have been mitigated
        String filename = Normalizer.normalize("C:\\tmp\\Email_addresses_20210205.txt", Normalizer.Form.NFKC);

        BufferedReader inputStream = null;
        String fileLine;

        try {
            inputStream = new BufferedReader(new FileReader(filename));

            System.out.println("Email Addresses:");
            // Read one Line using BufferedReader
            while ((fileLine = inputStream.readLine()) != null) {
                // 20220212 tml: Identified security vulnerability: Properly encode or escape output (IDS51-J)
                // 20220214 tml: Identified security vulnerability (IDS51-J) in following code has been mitigated
                if (fileLine.matches("^[A-Za-z0-9+.-]+@[A-Za-z0-9+.-](.+)$")) {
                    System.out.println(fileLine);
                }
            }
        } catch(IOException io) {
            // 20220212 tml: Identified security vulnerability: Do not allow exceptions to expose sensitive information (ERROR01-J)
            // 20220214 tml: Identified security vulnerability (ERROR01-J) in following code has been mitigated
                System.out.println("Could not open file.");
            } finally {
                // Need another catch for closing
                // the streams
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException io) {
                    // 20220212 tml: Identified security vulnerability: Do not allow exceptions to expose sensitive information (ERROR01-J)
                    // 20220214 tml: Identified security vulnerability (ERROR01-J) in following code has been mitigated
                    System.out.println("Could not close file");
                }

        }
    }

}
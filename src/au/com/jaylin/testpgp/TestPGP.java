package au.com.jaylin.testpgp;

import java.io.File;

import org.bouncycastle.openpgp.PGPCompressedData;
import org.bouncycastle.openpgp.PGPEncryptedData;

/*
 * Test class enables file-based PGP encryption using the 'bouncy castle library'.
 * 
 * This class enables you to specify a PGP public key file and a plaintext file
 * for encryption and also a PGP private key file (and password) and cyphertext
 * file for decryption.
 * 
 * Comment-out the parts not required on each run.
 * 
 * REQUIREMENTS: PGPFileProcessor, PGPUtils (from same package). 
 */
public class TestPGP {
    private static final String PASSPHRASE = "test";   //this is the private key password

    private static final String DE_INPUT = "pgp_file_output.csv";
    private static final String DE_OUTPUT = "pgp_file_output_decrypted.dat";
    private static final String DE_KEY_FILE = "testPGPPrivate.txt";

    private static final String E_INPUT = "C:\\Users\\jscott\\Documents\\PI\\FPe\\in.xml";
    private static final String E_OUTPUT = "C:\\Users\\jscott\\Documents\\PI\\FPe\\out.xml";
    private static final String E_KEY_FILE = "/au/com/jaylin/testpgp/public.pkr"; //from project


    /*
     * Currently only setup for the decrypt method to work - need to sort out the files for encrypt!
     */
    public static void main(String[] args) {
        String input = "";
        String output = "";
        String secret_key_file = "";

        System.out.println("testing PGP encryption...");	

        File directory = new File (".");
        try {
            System.out.println ("Current directory's canonical path: " + directory.getCanonicalPath()); 
            System.out.println ("Current directory's absolute  path: " + directory.getAbsolutePath());

            input = directory.getCanonicalPath() + "/" + DE_INPUT;
            output = directory.getCanonicalPath() + "/" + DE_OUTPUT;
            secret_key_file = directory.getCanonicalPath() + "/" + DE_KEY_FILE;
        }
        catch (Exception e) {
            System.out.println("Exception is ="+e.getMessage());
        }

        try {
            //	testEncrypt();
            //	System.out.println("done");

            System.out.println("Decrypting...");
            System.out.println("input file:\t\t" + input);
            System.out.println("output file:\t\t" + output);
            System.out.println("secret key file:\t" + secret_key_file);

            testDecrypt(input, output, secret_key_file);
            System.out.println("done");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
	
    public static void testDecrypt(String fileIn, String fileOut, String secretKeyFile) throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(fileIn);
        p.setOutputFileName(fileOut);
        p.setPassphrase(PASSPHRASE);
        p.setSecretKeyFileName(secretKeyFile);
        System.out.println(p.decrypt());
    }

    public static void testEncrypt() throws Exception {
        PGPFileProcessor p = new PGPFileProcessor();
        p.setInputFileName(E_INPUT);
        p.setOutputFileName(E_OUTPUT);
        p.setPassphrase(PASSPHRASE);
        p.setPublicKeyFileName(E_KEY_FILE);
        System.out.println(p.encrypt(PGPCompressedData.ZIP, PGPEncryptedData.CAST5));
    }
}

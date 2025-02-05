import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * The StringUtil class provides utility methods for hashing, signing, and verifying data.
 */
public class StringUtil {
    
    /**
     * Applies SHA-256 to a given input string and returns the hashed result as a hexadecimal string.
     * @param input The input string to be hashed.
     * @return The SHA-256 hash of the input string in hexadecimal format.
     */
    public static String applySha256(String input) {       
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");        
            // Applies SHA-256 to the input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));        
            StringBuffer hexString = new StringBuffer(); // Stores the hash as a hexadecimal string
            
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }  
    
    /**
     * Applies ECDSA Signature and returns the result as a byte array.
     * @param privateKey The private key used to sign the input.
     * @param input The input string to be signed.
     * @return The ECDSA signature as a byte array.
     */
    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        byte[] output = new byte[0];
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes();
            dsa.update(strByte);
            byte[] realSig = dsa.sign();
            output = realSig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }
    
    /**
     * Verifies an ECDSA signature.
     * @param publicKey The public key used for verification.
     * @param data The original data that was signed.
     * @param signature The signature to be verified.
     * @return True if the signature is valid, false otherwise.
     */
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes());
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a cryptographic key to a Base64-encoded string.
     * @param key The cryptographic key to convert.
     * @return The Base64-encoded string representation of the key.
     */
    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
}

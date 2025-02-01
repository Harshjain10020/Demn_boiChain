import java.security.MessageDigest;

/**
 * The StringUtil class provides utility methods for hashing strings using the SHA-256 algorithm.
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
}

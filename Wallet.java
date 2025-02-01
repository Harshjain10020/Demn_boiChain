import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {
    // Private key for the wallet, used for signing transactions
    public PrivateKey privateKey;
    
    // Public key for the wallet, used to receive transactions
    public PublicKey publicKey;

    // Constructor for the Wallet class
    public Wallet(){
        // Generates a key pair (public and private keys) when a Wallet object is created
        generateKeysPair();
    }

    // Method to generate the public and private key pair using the ECDSA algorithm
    public void generateKeysPair(){
        try {
            // Create a KeyPairGenerator instance for ECDSA (Elliptic Curve Digital Signature Algorithm)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");

            // Use SecureRandom for generating random numbers in key generation
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

            // Specify the elliptic curve parameter (prime192v1 is a specific curve for ECDSA)
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");

            // Initialize the KeyPairGenerator with the elliptic curve specification and random number generator
            keyGen.initialize(ecSpec, random);

            // Generate the key pair (public and private keys)
            KeyPair keyPair = keyGen.generateKeyPair();

            // Assign the generated private and public keys to the instance variables
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            // In case of an exception, throw a RuntimeException
            throw new RuntimeException(e);
        }
    }
}

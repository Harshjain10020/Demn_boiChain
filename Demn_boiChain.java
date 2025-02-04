import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Base64;
import java.security.Security;

/**
 * The Demn_boiChain class represents a simple blockchain implementation.
 * It includes methods for adding blocks, mining them, and validating the chain.
 */
public class Demn_boiChain {

    // Difficulty level for mining
    public static int difficulty = 4;
    public static Wallet walletA;
    public static Wallet walletB;

    // Blockchain represented as an ArrayList of Blocks
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        // // Add blocks to the blockchain and mine them
        // blockchain.add(new Block("Hi, I'm the first block", "0"));
        // System.out.println("Trying to Mine block 1... ");
        // blockchain.get(0).MineBlocks(difficulty);

        // blockchain.add(new Block("Yo, I'm the second block", blockchain.get(blockchain.size() - 1).hash));
        // System.out.println("Trying to Mine block 2... ");
        // blockchain.get(1).MineBlocks(difficulty);

        // blockchain.add(new Block("Hey, I'm the third block", blockchain.get(blockchain.size() - 1).hash));
        // System.out.println("Trying to Mine block 3... ");
        // blockchain.get(2).MineBlocks(difficulty);

        // // Validate the blockchain
        // System.out.println("\nBlockchain is Valid: " + isChainValid());

        // // Convert blockchain to JSON format and print it
        // String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        // System.out.println("\nThe blockchain: ");
        // System.out.println(blockchainJson);

        // ***************************************************************
        // Setup Bouncey castle as a Security Provider
        //Setup Bouncey castle as a Security Provider
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 

		// Create the new wallets
		walletA = new Wallet();
		walletB = new Wallet();

		// Test public and private keys
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));

		// Create a test transaction from WalletA to WalletB 
		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
		transaction.generateSignature(walletA.privateKey);

		// Verify the signature works and validate it from the public key
		System.out.println("Is signature verified?");
		System.out.println(transaction.verifiySignature());
    }

    /**
     * Validates the blockchain by checking hashes and proof of work.
     * 
     * @return true if the blockchain is valid, false otherwise.
     */
    public static Boolean isChainValid() {
        Block curBlock;
        Block preBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // Traverse through the blockchain
        for (int i = 1; i < blockchain.size(); i++) {
            curBlock = blockchain.get(i);
            preBlock = blockchain.get(i - 1);

            // Validate the current block's hash
            if (!curBlock.hash.equals(curBlock.calculateHash())) {
                System.out.println("Current hashes not equal");
                return false;
            }

            // Validate the previous block's hash reference
            if (!preBlock.hash.equals(preBlock.calculateHash())) {
                System.out.println("Previous hashes not equal");
                return false;
            }

            // Check if the block has been mined correctly
            if (!curBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }
}

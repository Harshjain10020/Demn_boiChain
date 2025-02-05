import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Base64;
import java.security.Security;

/**
 * The Demn_boiChain class represents a simple blockchain implementation.
 * It provides functionality to create blocks, mine them, and validate the blockchain.
 */
public class Demn_boiChain {

    // Mining difficulty level (higher value means more computational work required)
    public static int difficulty = 4;
    
    // Wallet instances (Currently not utilized, but can be used for transactions)
    public static Wallet walletA;
    public static Wallet walletB;

    // Blockchain represented as a list of Block objects
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        // Creating and mining the first block
        blockchain.add(new Block("Hi, I'm the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).MineBlocks(difficulty);

        // Creating and mining the second block
        blockchain.add(new Block("Yo, I'm the second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).MineBlocks(difficulty);

        // Creating and mining the third block
        blockchain.add(new Block("Hey, I'm the third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).MineBlocks(difficulty);

        // Validating the blockchain integrity
        System.out.println("\nBlockchain is Valid: " + isChainValid());

        // Converting the blockchain to a JSON format and printing it
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain: ");
        System.out.println(blockchainJson);
    }

    /**
     * Validates the blockchain by checking hashes and proof of work.
     * Ensures that each block's hash is correctly calculated and follows the mining rules.
     * 
     * @return true if the blockchain is valid, false otherwise.
     */
    public static Boolean isChainValid() {
        Block curBlock;
        Block preBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        // Iterating through the blockchain to check validity
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

            // Check if the block has been mined correctly (difficulty constraint)
            if (!curBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined correctly");
                return false;
            }
        }
        return true;
    }
}

/*Tried making wallet  */
// import java.security.Security;
// import java.util.ArrayList;
// import java.util.Base64;
// import java.util.HashMap;

// import com.google.gson.GsonBuilder;

// public class Demn_boiChain {
	
// 	public static ArrayList<Block> blockchain = new ArrayList<Block>();
// 	public static HashMap<String,TransactionOutput> UTXOs = new HashMap<String,TransactionOutput>(); //list of all unspent transactions. 
// 	public static int difficulty = 5;
// 	public static float minimumTransaction = 0.1f;
// 	public static Wallet walletA;
// 	public static Wallet walletB;

// 	public static void main(String[] args) {	
// 		//Setup Bouncey castle as a Security Provider
// 		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider()); 
// 		//Create the new wallets
// 		walletA = new Wallet();
// 		walletB = new Wallet();
// 		//Test public and private keys
// 		System.out.println("Private and public keys:");
// 		System.out.println(StringUtil.getStringFromKey(walletA.privateKey));
// 		System.out.println(StringUtil.getStringFromKey(walletA.publicKey));
// 		//Create a test transaction from WalletA to walletB 
// 		Transaction transaction = new Transaction(walletA.publicKey, walletB.publicKey, 5, null);
// 		transaction.generateSignature(walletA.privateKey);
// 		//Verify the signature works and verify it from the public key
// 		System.out.println("Is signature verified");
// 		System.out.println(transaction.verifySignature());
		
// 	}
// }
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

/**
 * The Demn_boiChain class represents a simple blockchain implementation.
 * It includes methods for adding blocks, mining them, and validating the chain.
 */
public class Demn_boiChain {
    
    // Difficulty level for mining
    public static int difficulty = 1;
    
    // Blockchain represented as an ArrayList of Blocks
    public static ArrayList<Block> blockchain = new ArrayList<Block>();

    public static void main(String[] args) {
        // Add blocks to the blockchain and mine them
        blockchain.add(new Block("Hi, I'm the first block", "0"));
        System.out.println("Trying to Mine block 1... ");
        blockchain.get(0).MineBlocks(difficulty);

        blockchain.add(new Block("Yo, I'm the second block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 2... ");
        blockchain.get(1).MineBlocks(difficulty);

        blockchain.add(new Block("Hey, I'm the third block", blockchain.get(blockchain.size() - 1).hash));
        System.out.println("Trying to Mine block 3... ");
        blockchain.get(2).MineBlocks(difficulty);

        // Validate the blockchain
        System.out.println("\nBlockchain is Valid: " + isChainValid());

        // Convert blockchain to JSON format and print it
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain: ");
        System.out.println(blockchainJson);
    }

    /**
     * Validates the blockchain by checking hashes and proof of work.
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

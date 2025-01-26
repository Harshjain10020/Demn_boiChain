import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Demn_boiChain {
	public static int difficulty = 1;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {
		// // to show hash , data of each block
		// Block genesisBlock = new Block("ola i'm the 1st block", "0");
		// System.out.println("Hash for block 1 : " + genesisBlock.hash);

		// Block secondBlock = new Block("Yo im the second(1st loser)
		// block",genesisBlock.hash);
		// System.out.println("Hash for block 2 : " + secondBlock.hash);

		// Block thirdBlock = new Block("Hey im the third(2nd winner)
		// block",secondBlock.hash);
		// System.out.println("Hash for block 3 : " + thirdBlock.hash);

		// add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).MineBlocks(difficulty);

		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).MineBlocks(difficulty);

		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).MineBlocks(difficulty);

		System.out.println("\nBlockchain is Valid: " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.println(blockchainJson);

	}

	// now to validate blocks
	public static Boolean isChainValid() {
		Block curBlock;
		Block preBlock;
		String hashtarget = new String(new char[difficulty]).replace('\0', '0');

		// tranverse thru blocks
		for (int i = 1; i < blockchain.size(); i++) {
			curBlock = blockchain.get(i);
			preBlock = blockchain.get(i - 1);

			// compare hashes

			if (!curBlock.hash.equals(curBlock.calculateHash())) {
				System.out.println("current Hashes not equal");
				return false;
			}

			// for previous hash
			if (!preBlock.hash.equals(preBlock.calculateHash())) {
				System.out.println("previous Hashes not equal");
				return false;
			}
			// check if hash is solved
			if (!curBlock.hash.substring(0, difficulty).equals(hashtarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}

		}
		return true;
	}
}
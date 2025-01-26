import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class Demn_boiChain {
	public static ArrayList<Block> blockchain = new ArrayList<Block>(); 
	public static void main(String[] args) {
		// // to show hash , data of each block
		// Block genesisBlock = new Block("ola i'm the 1st block", "0");
		// System.out.println("Hash for block 1 : " + genesisBlock.hash);
		
		// Block secondBlock = new Block("Yo im the second(1st loser) block",genesisBlock.hash);
		// System.out.println("Hash for block 2 : " + secondBlock.hash);
		
		// Block thirdBlock = new Block("Hey im the third(2nd winner) block",secondBlock.hash);
		// System.out.println("Hash for block 3 : " + thirdBlock.hash);



		//add our blocks to the blockchain ArrayList:
		blockchain.add(new Block("Hi im the first block", "0"));		
		blockchain.add(new Block("Yo im the second block",blockchain.get(blockchain.size()-1).hash)); 
		blockchain.add(new Block("Hey im the third block",blockchain.get(blockchain.size()-1).hash));
		
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);		
		System.out.println(blockchainJson);
		
	}

	//now to validate blocks
	
}
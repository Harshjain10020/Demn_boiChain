import java.util.Date;

public class Block {

    public String hash;// hash pf current block
    public String previousHash; // hash pf previous block
    private String data; // our data in box
    private long timeStamp; // milliseconds
    private int nonce;
    public static int difficulty = 1;

    // now construct Blocks constructor
    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();

    }
    //way to generate a digital fingerprint using SHA256 algorithm 
    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256(previousHash + Long.toString(timeStamp) + data);
        return calculatedhash;
    }

    /*
     * Now lets work with Miners , our proof of work ; to show that this much work
     * has done on this specific blockchain
     */

     public void MineBlocks(int difficulty ){
        String target = new String(new char[difficulty]).replace('\0', '0');//Create a string with difficulty * "0"


        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!: "+ hash);
     }

}

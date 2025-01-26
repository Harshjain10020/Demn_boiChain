import java.util.Date;

public class Block {

    public String hash;// hash pf current block
    public String previousHash; // hash pf previous block
    private String data; //our data in box
    private long timeStamp; // milliseconds 

    public String calculateHash() {
        String calculatedhash = StringUtil.applySha256( 
                previousHash +
                Long.toString(timeStamp) +
                data 
                );
        return calculatedhash;
    }


    //now construct Blocks constructor
    public Block(String data, String previousHash ){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
        
    }

}


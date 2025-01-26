import java.util.Date;

public class Block {

    public String hash;// hash pf current block
    public String previousHash; // hash pf previous block
    private String data; //our data in box
    private long timeStamp; // milliseconds 


    //now construct Blocks constructor
    public Block(String data, String previousHash ){
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        
    }

}
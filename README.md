# Demn_boiChain : Blockchain Ledger in Java

## Introduction
The aim of this tutorial series is to help you understand blockchain technology by developing one from scratch using Java. This is a proof-of-concept implementation designed for educational purposes and is not intended for production use.

## Features
- Create a basic blockchain
- Implement a simple proof-of-work (mining) system
- Understand blockchain fundamentals

> **Note:** This is a proof-of-concept implementation meant for educational purposes, not a production-ready blockchain.

## Prerequisites
- Basic understanding of Object-Oriented Programming (OOP)
- Java Development Kit (JDK) installed
- An IDE such as Eclipse or VS Code
- Google GSON library for JSON serialization

## Setting Up
### 1. Install Java & JDK
Ensure you have Java installed on your system. You can verify this by running:
```sh
java -version
```
If Java is not installed, download it from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html).

### 2. Set Up Your Project
Create a new Java project and a class called `NoobChain`.

```java
import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class NoobChain {
    public static ArrayList<Block> blockchain = new ArrayList<>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        blockchain.add(new Block("Genesis Block", "0"));
        System.out.println("Mining block 1...");
        blockchain.get(0).MineBlocks(difficulty);
    }
}
```

### 3. Create the Block Class
Each block in the blockchain contains:
- A hash (unique identifier)
- A reference to the previous block’s hash
- Data (such as transactions)
- A timestamp
- A nonce (for proof-of-work)

```java
import java.util.Date;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
    }

    public void MineBlocks(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined: " + hash);
    }
}
```

### 4. Implement SHA-256 Hashing
We need a helper class to apply SHA-256 to ensure our blockchain is secure.

```java
import java.security.MessageDigest;

public class StringUtil {
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Running the Blockchain
Compile and run the Java files to see blockchain mining in action:
```sh
javac NoobChain.java Block.java StringUtil.java
java NoobChain
```

## Checking Blockchain Integrity
We need to validate the chain by checking if the previous hashes are correctly linked:

```java
public static boolean isChainValid() {
    for (int i = 1; i < blockchain.size(); i++) {
        Block currentBlock = blockchain.get(i);
        Block previousBlock = blockchain.get(i - 1);

        if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
            System.out.println("Current Hashes are not equal");
            return false;
        }
        if (!previousBlock.hash.equals(currentBlock.previousHash)) {
            System.out.println("Previous Hashes are not equal");
            return false;
        }
    }
    return true;
}
```

## Proof of Work (Mining)
Mining requires adjusting the `nonce` until the hash starts with a specific number of leading zeros.

```java
public void MineBlocks(int difficulty) {
    String target = new String(new char[difficulty]).replace('\0', '0');
    while (!hash.substring(0, difficulty).equals(target)) {
        nonce++;
        hash = calculateHash();
    }
    System.out.println("Block Mined!!: " + hash);
}
```

## Conclusion
This blockchain:
✅ Stores data in linked blocks
✅ Uses SHA-256 for hashing
✅ Implements proof-of-work to prevent tampering
✅ Validates the chain for integrity

### Future Enhancements
- Add transactions and wallets
- Implement a peer-to-peer network
- Improve security and efficiency

Happy coding! 🚀


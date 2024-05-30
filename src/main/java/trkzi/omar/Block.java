package trkzi.omar;

import java.util.Date;

public class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;
    private int nonce;

    public Block(int index, long timestamp, String data, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String input = index + Long.toString(timestamp) + previousHash + Integer.toString(nonce) + data;
        return HashUtil.calculateSHA256(input);
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined! : " + hash);
    }

    public void incrementNonce() {
        this.nonce++;
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setPreviousHash(String hash) {
        this.previousHash = hash;
    }

    @Override
    public String toString() {
        return "Block{" +
                "index=" + index +
                ", timestamp=" + new Date(timestamp) +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                ", data='" + data + '\'' +
                ", nonce=" + nonce +
                '}';
    }
}

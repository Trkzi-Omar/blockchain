package trkzi.omar;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final int difficultyAdjustmentInterval = 10;
    private final int blockGenerationTime = 60000;
    private List<Block> chain;
    private int difficulty;
    private TransactionPool transactionPool;

    public Blockchain(int difficulty) {
        this.chain = new ArrayList<>();
        this.chain.add(createGenesisBlock());
        this.difficulty = difficulty;
        this.transactionPool = new TransactionPool();
    }

    private Block createGenesisBlock() {
        return new Block(0, System.currentTimeMillis(), "Genesis Block", "0");
    }

    public Block getLatestBlock() {
        return this.chain.get(this.chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock.setPreviousHash(this.chain.get(this.chain.size() - 1).getHash());
        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
        this.adjustDifficulty();
    }

    public List<Block> getChain() {
        return this.chain;
    }

    public boolean validateChain() {
        for (int i = 1; i < this.chain.size(); i++) {
            Block currentBlock = this.chain.get(i);
            Block previousBlock = this.chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }

    public void minePendingTransactions(String minerAddress) {
        Block newBlock = new Block(this.chain.size(), System.currentTimeMillis(),
                this.transactionPool.getPendingTransactions().toString(),
                this.getLatestBlock().getHash());
        newBlock.mineBlock(this.difficulty);
        this.chain.add(newBlock);
        this.transactionPool = new TransactionPool(); // Clear the transaction pool
        // Reward the miner with a new transaction
        Transaction rewardTransaction = new Transaction("System", minerAddress, 1.0, "");
        this.transactionPool.addTransaction(rewardTransaction);
    }

    public void addTransaction(Transaction transaction) {
        this.transactionPool.addTransaction(transaction);
    }

    public void adjustDifficulty() {
        if (this.chain.size() % difficultyAdjustmentInterval == 0) {
            long timeExpected = difficultyAdjustmentInterval * blockGenerationTime;
            long timeTaken = this.chain.get(this.chain.size() - 1).getTimestamp() -
                    this.chain.get(this.chain.size() - difficultyAdjustmentInterval).getTimestamp();
            if (timeTaken < timeExpected) {
                difficulty++;
            } else {
                difficulty--;
            }
        }
    }

    public boolean validateBlock(Block block) {
        String calculatedHash = block.calculateHash();
        return block.getHash().equals(calculatedHash) &&
                block.getHash().substring(0, this.difficulty).equals(new String(new char[this.difficulty]).replace('\0', '0'));
    }
}

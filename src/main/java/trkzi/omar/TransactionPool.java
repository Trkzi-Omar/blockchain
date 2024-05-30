package trkzi.omar;

import java.util.ArrayList;
import java.util.List;

public class TransactionPool {
    private List<Transaction> pendingTransactions;

    public TransactionPool() {
        this.pendingTransactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.pendingTransactions.add(transaction);
    }

    public List<Transaction> getPendingTransactions() {
        return this.pendingTransactions;
    }

    public void removeTransaction(Transaction transaction) {
        this.pendingTransactions.remove(transaction);
    }
}

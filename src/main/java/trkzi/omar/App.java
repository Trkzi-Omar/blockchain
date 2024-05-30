package trkzi.omar;

import java.util.Scanner;

public class App {
    private static Blockchain blockchain = new Blockchain(4);
    private static Wallet wallet = new Wallet();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Blockchain Menu:");
            System.out.println("1. Add a new transaction");
            System.out.println("2. Mine a new block");
            System.out.println("3. Display the blockchain");
            System.out.println("4. Validate the blockchain");
            System.out.println("5. Show wallet public key");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addTransaction(scanner);
                    break;
                case 2:
                    mineBlock(scanner);
                    break;
                case 3:
                    displayBlockchain();
                    break;
                case 4:
                    validateBlockchain();
                    break;
                case 5:
                    showWalletPublicKey();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addTransaction(Scanner scanner) {
        System.out.print("Enter sender address: ");
        String sender = scanner.nextLine();
        System.out.print("Enter recipient address: ");
        String recipient = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        Transaction transaction = new Transaction(sender, recipient, amount, "");
        blockchain.addTransaction(transaction);
        System.out.println("Transaction added.");
    }

    private static void mineBlock(Scanner scanner) {
        System.out.print("Enter miner's address: ");
        String minerAddress = scanner.nextLine();

        blockchain.minePendingTransactions(minerAddress);
        System.out.println("New block mined.");
    }

    private static void displayBlockchain() {
        for (Block block : blockchain.getChain()) {
            System.out.println(block);
        }
    }

    private static void validateBlockchain() {
        if (blockchain.validateChain()) {
            System.out.println("Blockchain is valid.");
        } else {
            System.out.println("Blockchain is invalid.");
        }
    }

    private static void showWalletPublicKey() {
        System.out.println("Wallet Public Key: " + wallet.getPublicKeyString());
    }
}

# Blockchain Workshop

## Introduction

Blockchain technology introduces a decentralized and immutable ledger that enables secure and transparent transactions without intermediaries. This project provides a practical guide to building a blockchain from scratch using Java and Spring Boot.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Application Menu](#application-menu)
- [Testing the Application](#testing-the-application)
- [License](#license)

## Features

- Basic blockchain implementation with blocks and transactions.
- Transaction pool for managing pending transactions.
- Proof of Work (PoW) consensus mechanism.
- Blockchain validation and integrity checks.
- Wallet management including key pair generation and transaction signing.

## Installation

### Prerequisites

- Java 21 or higher
- Maven

### Steps

1. Clone the repository:
    ```sh
    git clone https://github.com/Trkzi-Omar/blockchain.git
    cd blockchain
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

## Usage

### Running the Application

1. Navigate to the `target` directory:
    ```sh
    cd target
    ```

2. Run the application:
    ```sh
    java -cp blockchain-1.0-SNAPSHOT.jar Main
    ```

## Application Menu

The application provides a terminal-based menu for interacting with the blockchain:

1. **Add a New Transaction**
    - Prompts for sender, recipient, and amount to create a new transaction.
    - Example:
        ```
        Enter sender address: Omar
        Enter recipient address: Ahmed
        Enter amount: 200
        Transaction added to the pool.
        ```

2. **Mine a New Block**
    - Mines a new block including pending transactions and adds it to the blockchain.
    - Example:
        ```
        Enter miner address: Omar-Miner-101
        Mining...
        Block mined successfully and added to the blockchain.
        ```

3. **Display the Blockchain**
    - Displays the current state of the blockchain.
    - Example:
        ```
        Block 0 [previousHash: 0, currentHash: abc123, data: Genesis Block]
        Block 1 [previousHash: abc123, currentHash: def456, data: Transactions]
        ```

4. **Validate the Blockchain**
    - Validates the integrity of the blockchain.
    - Example:
        ```
        Blockchain is valid.
        ```

5. **Show Wallet Public Key**
    - Displays the public key of the wallet.
    - Example:
        ```
        Wallet Public Key: MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAE1L...
        ```

6. **Exit**
    - Exits the application.

## Testing the Application

### Adding a Transaction

1. Select option `1` from the menu.
2. Enter the sender address, recipient address, and amount.
3. The transaction is added to the transaction pool.

### Mining a Block

1. Select option `2` from the menu.
2. Enter the miner's address.
3. A new block is mined and added to the blockchain, and the miner receives a reward.

### Displaying the Blockchain

1. Select option `3` from the menu.
2. The blockchain's current state is printed to the console.

### Validating the Blockchain

1. Select option `4` from the menu.
2. The application checks the integrity of the blockchain and reports if it is valid or not.

### Showing Wallet Public Key

1. Select option `5` from the menu.
2. The public key of the wallet is displayed.

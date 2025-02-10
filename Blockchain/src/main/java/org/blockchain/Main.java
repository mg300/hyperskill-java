package org.blockchain;


import org.blockchain.Simulator.TransactionSender;
import org.blockchain.User.User;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int WAIT_TIME = 60;

    public static void main(final String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        Blockchain blockchain = new Blockchain();
        ExecutorService executor = Executors.newFixedThreadPool(Config.THREAD_QUANTITY);
        for (int i = 0; i < Config.THREAD_QUANTITY; i++) {
            User.Miner miner = new User("miner" + i, blockchain).new Miner();
            executor.submit(miner);
        }
        TransactionSender transactionSender = new TransactionSender(blockchain);
        transactionSender.sendMessages();
        executor.shutdown();
        try {
            if (executor.awaitTermination(WAIT_TIME, TimeUnit.SECONDS)) {
                System.out.println("Blockchain is valid: " + blockchain.checkBlockchainValidity());
                System.out.println("Nick balance: " + blockchain.getPersonBalance("Nick"));
                System.out.println("Tom balance: " + blockchain.getPersonBalance("Tom"));
                System.out.println("Sarah balance: " + blockchain.getPersonBalance("Sarah"));
                System.out.println("Miner1 balance: " + blockchain.getPersonBalance("miner1"));
                System.out.println("Miner2 balance: " + blockchain.getPersonBalance("miner2"));
                System.out.println("Miner3 balance: " + blockchain.getPersonBalance("miner3"));
                System.out.println("Miner4 balance: " + blockchain.getPersonBalance("miner4"));
                System.out.println("Miner5 balance: " + blockchain.getPersonBalance("miner5"));
                System.out.println("Miner6 balance: " + blockchain.getPersonBalance("miner6"));
                System.out.println("Miner7 balance: " + blockchain.getPersonBalance("miner7"));

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

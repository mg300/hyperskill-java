package org.blockchain.User;

import org.blockchain.Blockchain;
import org.blockchain.Config;
import org.blockchain.Utils.GenerateKeys;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class User {
    public final String name;
    private final PrivateKey privateKey;
    public final PublicKey publicKey;
    public final Blockchain blockchain;

    public User(final String name, final Blockchain blockchain) throws
            NoSuchAlgorithmException, NoSuchProviderException {
        this.name = name;
        this.blockchain = blockchain;
        GenerateKeys gk = new GenerateKeys(Config.ENCRYPTION_KEY_LENGTH).generateKeys();
        gk.createKeys();
        this.publicKey = gk.getPublicKey();
        this.privateKey = gk.getPrivateKey();
    }

    public void send(final String to, final String value) throws Exception {
        Transaction transaction = new Transaction(blockchain.getTransactionId(), this, to, value);
        transaction.sign(privateKey);
        blockchain.addTransaction(transaction);
    }

    public class Miner extends Thread {
        @Override
        public void run() {
            while (blockchain.size() < Config.CAPACITY) {
                int countDown = Config.TRANSACTION_WAITING_PERIODS;
                blockchain.generateBlock(User.this);
                while (!blockchain.hasTransaction()) {
                    try {
                        Thread.sleep(Config.TRANSACTION_WAITING_TIME);
                        countDown--;
                        if (countDown == 0) {
                            return;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

}

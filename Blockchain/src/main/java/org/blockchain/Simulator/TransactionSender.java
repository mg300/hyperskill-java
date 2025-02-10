package org.blockchain.Simulator;

import org.blockchain.Blockchain;
import org.blockchain.Config;
import org.blockchain.User.User;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionSender {
    public Blockchain blockchain;
    private static final Random RANDOM = new Random();
    private static final int INITIAL_TIME = 300;
    static int time = INITIAL_TIME;
    private static final int RECEIVER_INDEX = 4;
    private static final int VALUE_INDEX = 1;
    private static final String[] TRANSACTIONS = new String[]{
            "sent 47 VC to miner3",
            "sent 88 VC to miner6",
            "sent 32 VC to Tom",
            "sent 9 VC to miner1",
            "sent 58 VC to miner5",
            "sent 77 VC to Sarah",
            "sent 19 VC to miner4",
            "sent 63 VC to Nick",
            "sent 14 VC to miner2",
            "sent 50 VC to miner3",
            "sent 28 VC to miner6",
            "sent 95 VC to Sarah",
            "sent 66 VC to Tom",
            "sent 23 VC to miner1",
            "sent 73 VC to Nick",
            "sent 34 VC to miner5",
            "sent 91 VC to miner2",
            "sent 39 VC to miner4",
            "sent 85 VC to miner3",
            "sent 49 VC to miner6",
            "sent 17 VC to Sarah",
            "sent 26 VC to Tom",
            "sent 60 VC to Nick",
            "sent 45 VC to miner1",
            "sent 71 VC to miner5",
            "sent 21 VC to miner2",
            "sent 80 VC to miner4",
            "sent 37 VC to miner3",
            "sent 99 VC to miner6",
            "sent 12 VC to Sarah",
            "sent 54 VC to Tom",
            "sent 78 VC to Nick",
            "sent 6 VC to miner1",
            "sent 89 VC to miner5",
            "sent 42 VC to miner2",
            "sent 33 VC to miner4",
            "sent 57 VC to miner3",
            "sent 98 VC to miner6",
            "sent 29 VC to Sarah",
            "sent 15 VC to Tom",
            "sent 61 VC to Nick",
            "sent 38 VC to miner1",
            "sent 82 VC to miner5",
            "sent 3 VC to miner2",
            "sent 55 VC to miner4",
            "sent 94 VC to miner3",
            "sent 24 VC to miner6",
            "sent 70 VC to Sarah",
            "sent 11 VC to Tom",
            "sent 65 VC to Nick",
            "sent 40 VC to miner1",
            "sent 86 VC to miner5",
            "sent 13 VC to miner2",
            "sent 51 VC to miner4",
            "sent 72 VC to miner3",
            "sent 36 VC to miner6",
            "sent 93 VC to Sarah",
            "sent 8 VC to Tom",
            "sent 62 VC to Nick",
            "sent 41 VC to miner1",
            "sent 83 VC to miner5",
            "sent 18 VC to miner2",
            "sent 53 VC to miner4",
            "sent 75 VC to miner3",
            "sent 30 VC to miner6",
            "sent 97 VC to Sarah",
            "sent 20 VC to Tom",
            "sent 64 VC to Nick",
            "sent 7 VC to miner1",
            "sent 79 VC to miner5",
            "sent 22 VC to miner2",
            "sent 52 VC to miner4",
            "sent 81 VC to miner3",
            "sent 35 VC to miner6",
            "sent 100 VC to Sarah",
            "sent 16 VC to Tom",
            "sent 69 VC to Nick",
            "sent 44 VC to miner1",
            "sent 84 VC to miner5",
            "sent 27 VC to miner2",
            "sent 59 VC to miner4",
            "sent 76 VC to miner3",
            "sent 31 VC to miner6",
            "sent 90 VC to Sarah",
            "sent 10 VC to Tom",
            "sent 67 VC to Nick",
            "sent 5 VC to miner1",
            "sent 87 VC to miner5",
            "sent 25 VC to miner2",
            "sent 56 VC to miner4",
            "sent 74 VC to miner3",
            "sent 48 VC to miner6",
            "sent 92 VC to Sarah",
            "sent 4 VC to Tom",
            "sent 68 VC to Nick"
    };
    private final List<User> userList;

    public TransactionSender(final Blockchain blockchain) {
        this.blockchain = blockchain;
        this.userList = new ArrayList<>();
        initialize();
    }

    public void sendMessages() {
        new Thread(() -> {
            for (String message : TRANSACTIONS) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (blockchain.size() >= Config.CAPACITY) {
                    break;
                }
                int randomUserIndex = RANDOM.nextInt(userList.size());
                try {
                    String[] messArr = message.split(" ");
                    String receiver = messArr[RECEIVER_INDEX];
                    String value = messArr[VALUE_INDEX];
                    userList.get(randomUserIndex).send(receiver, value);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void initialize() {
        userList.add(createUser("Sarah"));
        userList.add(createUser("Tom"));
        userList.add(createUser("Nick"));
    }

    private User createUser(final String name) {
        try {
            return new User(name, blockchain);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}

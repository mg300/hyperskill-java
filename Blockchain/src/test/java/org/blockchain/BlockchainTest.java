package org.blockchain;

import org.blockchain.User.Transaction;
import org.blockchain.User.User;
import org.blockchain.Utils.GenerateKeys;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BlockchainTest {
    private Blockchain blockchain;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        blockchain = new Blockchain();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    void hasTransactionWhenEmptyTest() {
        assertFalse(blockchain.hasTransaction());
    }

    @Test
    void sizeWhenEmptyTest() {
        assertEquals(0, blockchain.size());
    }

    @Test
    void generateFirstTwoBlocksTest() throws Exception {
        User user = new User("miner", blockchain);
        user.send("user2", "10");
        blockchain.generateBlock(user);
        Thread.sleep(200);
        new User("user1", blockchain).send("user2", "10");
        assertTrue(blockchain.hasTransaction());
        blockchain.generateBlock(user);
        assertTrue(outContent.toString().trim().contains("Block data: \n"
                + "user1 sent 10 VC to user2"));
        assertTrue(outContent.toString().trim().contains("Block:\n"
                + "Created by miner # miner\n"
                + "miner gets 100 VC\n"
                + "Id: 2"));
        assertTrue(outContent.toString().trim().contains("Hash of the previous block: \n"
                + "0\n"
                + "Hash of the block: "));

    }

    @Test
    void blockNotGenerateTest() throws Exception {
        User user = new User("miner", blockchain);
        blockchain.generateBlock(user);
        blockchain.addTransaction(new Transaction(1, user, "user2", "123"));
        blockchain.addTransaction(new Transaction(122, user, "user2", "123"));
        user.send("abc", "100000");
        blockchain.generateBlock(user);
        assertFalse(outContent.toString().trim().contains("Block"));

    }

    @Test
    void displayBlockTest() throws NoSuchAlgorithmException, NoSuchProviderException {
        Block block = new Block.Builder()
                .id(123)
                .timeStamp(23431241)
                .hash("sdfansdf@#!$41123412sdafas")
                .prevHash("sdfsadfbhkbasdifbisebfjarbkbe")
                .magicNumber(123123123)
                .generatingTime(321)
                .miner(new User("Client", blockchain))
                .transaction(new ArrayList<>(List.of(
                        new Transaction(1, new User("user1", blockchain), "abc1", "bca1"),
                        new Transaction(2, new User("user2", blockchain), "abc2", "bca2")
                )))
                .build();
        blockchain.displayBlock(block);
        assertEquals("Block:\n"
                + "Created by miner # Client\n"
                + "Client gets 100 VC\n"
                + "Id: 123\n"
                + "Timestamp: 23431241\n"
                + "Magic number: 123123123\n"
                + "Hash of the previous block: \n"
                + "sdfsadfbhkbasdifbisebfjarbkbe\n"
                + "Hash of the block: \n"
                + "sdfansdf@#!$41123412sdafas\n"
                + "Block data: \n"
                + "user1 sent bca1 VC to abc1\n"
                + "user2 sent bca2 VC to abc2\n"
                + "Block was generating for 321 seconds\n"
                + "N stays the same", outContent.toString().trim());
    }

    @Test
    void nWasIncreasedTest() throws NoSuchAlgorithmException, NoSuchProviderException {
        Block block = new Block.Builder()
                .id(123)
                .timeStamp(23431241)
                .hash("sdfansdf@#!$41123412sdafas")
                .prevHash("sdfsadfbhkbasdifbisebfjarbkbe")
                .magicNumber(123123123)
                .generatingTime(1)
                .miner(new User("Client", blockchain))
                .transaction(new ArrayList<>(List.of(
                        new Transaction(1, new User("user1", blockchain), "abc1", "bca1"),
                        new Transaction(2, new User("user2", blockchain), "abc2", "bca2")
                )))
                .build();
        blockchain.displayBlock(block);
        assertTrue(outContent.toString().trim().contains("N was increased to 1"));
    }

    @Test
    void nWasDecreasedTest() throws NoSuchAlgorithmException, NoSuchProviderException {
        blockchain.zerosNum = 4;
        Block block = new Block.Builder()
                .id(123)
                .timeStamp(23431241)
                .hash("sdfansdf@#!$41123412sdafas")
                .prevHash("sdfsadfbhkbasdifbisebfjarbkbe")
                .magicNumber(123123123)
                .generatingTime(100)
                .miner(new User("Client", blockchain))
                .transaction(new ArrayList<>(List.of(
                        new Transaction(1, new User("user1", blockchain), "abc1", "bca1"),
                        new Transaction(2, new User("user2", blockchain), "abc2", "bca2")
                )))
                .build();
        blockchain.displayBlock(block);
        assertFalse(outContent.toString().trim().contains("N was decreased by 1\n"));
    }

    @Test
    void personBalanceTest() throws Exception {
        User user = new User("John", blockchain);
        User user2 = new User("miner", blockchain);
        user.send("miner", "10");
        blockchain.generateBlock(user2);
        Thread.sleep(200);
        user.send("miner", "10");
        blockchain.generateBlock(user2);
        assertEquals(Config.INITIAL_BALANCE - 20, blockchain.getPersonBalance("John"));
        assertEquals(Config.INITIAL_BALANCE + Config.BLOCK_REWARD * 2 + 20, blockchain.getPersonBalance("miner"));
    }

    @Test
    void checkBlockchainValidityTest() throws Exception {
        User user = new User("John", blockchain);
        User user2 = new User("miner", blockchain);
        user.send("miner", "10");
        blockchain.generateBlock(user2);
        Thread.sleep(200);
        user.send("miner", "10");
        blockchain.generateBlock(user2);
        assertTrue(blockchain.checkBlockchainValidity());
    }


}

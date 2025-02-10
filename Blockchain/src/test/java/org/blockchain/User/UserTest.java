package org.blockchain.User;

import org.blockchain.Block;
import org.blockchain.Blockchain;
import org.blockchain.Config;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserTest {

    @Mock
    Blockchain blockchain;

    User user;
    User.Miner miner;

    @BeforeEach
    void setUp() throws NoSuchAlgorithmException, NoSuchProviderException {
        user = new User("Client", blockchain);
        miner = user.new Miner();
    }

    @Test
    void sendTest() throws Exception {
        when(blockchain.getTransactionId()).thenReturn(2);
        user.send("Nick", "100");
        verify(blockchain, atMostOnce()).addTransaction(any(Transaction.class));
    }

    @Test
    void userInitializationTest() {
        assertNotNull(user.name);
        assertNotNull(user.blockchain);
        assertNotNull(user.publicKey);
    }


    @Test
    public void minerRunTest() {
        when(blockchain.size()).thenReturn(2, 3, 200);
        when(blockchain.hasTransaction()).thenReturn(true, true);
        Thread minerThread = new Thread(miner);
        minerThread.start();
        verify(blockchain, atLeast(2)).generateBlock(any(User.class));
        minerThread.interrupt();
    }

    @Test
    public void testMinerWaitAndExit() {
        when(blockchain.size()).thenReturn(1, 2, 200);
        when(blockchain.hasTransaction()).thenReturn(false);
        Thread minerThreads = new Thread(miner);
        minerThreads.start();
        verify(blockchain).generateBlock(any());
        verify(blockchain).size();
    }

    @Test
    public void minerWaitTest() {
        when(blockchain.size()).thenReturn(1, 2, 200);
        when(blockchain.hasTransaction()).thenReturn(false, false, true);
        Thread minerThreads = new Thread(miner);
        minerThreads.start();
        verify(blockchain, after(Config.TRANSACTION_WAITING_TIME * 2).atMost(2)).generateBlock(any(User.class));
    }

    @Test
    public void minerWaitAndEndTest() {
        when(blockchain.size()).thenReturn(1);
        when(blockchain.hasTransaction()).thenReturn(false);
        Thread minerThreads = new Thread(miner);
        minerThreads.start();
        verify(blockchain, after(Config.TRANSACTION_WAITING_TIME * Config.TRANSACTION_WAITING_PERIODS).atMostOnce()).generateBlock(any(User.class));
    }

}

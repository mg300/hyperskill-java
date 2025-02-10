package org.blockchain.Simulator;

import org.blockchain.Blockchain;
import org.blockchain.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionSenderTest {
    @Mock
    Blockchain blockchain;
    @Mock
    User user;
    @InjectMocks
    TransactionSender sender;

    @Test
    void testSendingMessages() {
        when(blockchain.size()).thenReturn(1);
        sender.sendMessages();
        TransactionSender.time = 10;
        verify(blockchain, after(100).atLeast(5)).size();
    }

    @Test
    void testNotSendingMessages() {
        when(blockchain.size()).thenReturn(100);
        sender.sendMessages();
        TransactionSender.time = 10;
        verify(blockchain, after(50).atMostOnce()).size();
    }
}
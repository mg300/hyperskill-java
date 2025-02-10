package org.blockchain;

import org.blockchain.User.Transaction;
import org.blockchain.User.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BlockTest {
    @Mock
    Blockchain blockchain;

    @Test
    void toStringTest() throws NoSuchAlgorithmException, NoSuchProviderException {
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

        String result = block.toString();
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
                + "Block was generating for 321 seconds", result);
    }

    @Test
    void toStringEmptyTransaction() throws NoSuchAlgorithmException, NoSuchProviderException {
        Block block = new Block.Builder()
                .id(123)
                .timeStamp(23431241)
                .hash("sdfansdf@#!$41123412sdafas")
                .prevHash("sdfsadfbhkbasdifbisebfjarbkbe")
                .magicNumber(123123123)
                .generatingTime(321)
                .miner(new User("Client", blockchain))
                .transaction(new ArrayList<>())
                .build();

        String result = block.toString();
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
                + "Block data: no transaction\n"
                + "Block was generating for 321 seconds", result);
    }
}

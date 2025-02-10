package org.blockchain.Utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenerateKeysTest {

    @Test
    void generateKeysTest() {
        GenerateKeys keys = new GenerateKeys(1024).generateKeys();
        keys.createKeys();
        assertFalse(keys.getPublicKey().toString().isEmpty());
        assertFalse(keys.getPrivateKey().toString().isEmpty());
        assertNotNull(keys.getPrivateKey());
        assertNotNull(keys.getPublicKey());
        assertTrue(keys.getPrivateKey().toString().contains("sun.security.rsa.RSAPrivateCrtKey"));
        assertTrue(keys.getPublicKey().toString().contains("Sun RSA public key, 1024 bits"));
    }
}

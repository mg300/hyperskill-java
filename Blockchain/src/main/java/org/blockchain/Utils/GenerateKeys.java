package org.blockchain.Utils;

import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;

public class GenerateKeys {

    private KeyPairGenerator keyGen;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private final int keyLength;

    public GenerateKeys(final int keyLength) {
        this.keyLength = keyLength;
    }

    public GenerateKeys generateKeys() {
        try {
            this.keyGen = KeyPairGenerator.getInstance("RSA");
            this.keyGen.initialize(keyLength);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to initialize KeyPairGenerator", e);
        }
        return this;
    }

    public void createKeys() {
        KeyPair pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}

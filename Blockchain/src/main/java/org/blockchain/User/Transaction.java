package org.blockchain.User;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.Signature;

public class Transaction {
    public final int id;
    public final String to;
    public final String value;
    private byte[] signature;
    public final User sender;

    public Transaction(final int id, final User sender, final String to, final String value) {
        this.id = id;
        this.to = to;
        this.value = value;
        this.sender = sender;
    }

    public void sign(final PrivateKey privateKey) throws Exception {
        String data = id + getString();
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(privateKey);
        rsa.update(data.getBytes(StandardCharsets.UTF_8));
        signature = rsa.sign();
    }

    public boolean verifySignature() throws Exception {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(sender.publicKey);
        sig.update((id + getString()).getBytes(StandardCharsets.UTF_8));
        return sig.verify(signature);
    }

    public String getString() {
        return sender.name + " sent " + value + " VC to " + to;
    }


}

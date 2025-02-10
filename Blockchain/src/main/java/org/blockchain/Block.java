package org.blockchain;

import org.blockchain.User.Transaction;
import org.blockchain.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class Block {
    public final int id;
    public final long timeStamp;
    public final String hash;
    public final String prevHash;
    public final long generatingTime;
    public final long magicNumber;
    public final User miner;
    public final List<Transaction> transaction;

    private Block(final Builder builder) {
        this.id = builder.id;
        this.timeStamp = builder.timeStamp;
        this.hash = builder.hash;
        this.prevHash = builder.prevHash;
        this.magicNumber = builder.magicNumber;
        this.generatingTime = builder.generatingTime;
        this.miner = builder.miner;
        this.transaction = new ArrayList<>(builder.transaction);
    }

    public static class Builder {
        private int id;
        private long timeStamp;
        private String hash;
        private String prevHash;
        private long magicNumber;
        private long generatingTime;
        private User miner;
        private List<Transaction> transaction = new ArrayList<>();

        public Builder id(final int id) {
            this.id = id;
            return this;
        }

        public Builder timeStamp(final long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder hash(final String hash) {
            this.hash = hash;
            return this;
        }

        public Builder prevHash(final String prevHash) {
            this.prevHash = prevHash;
            return this;
        }

        public Builder magicNumber(final long magicNumber) {
            this.magicNumber = magicNumber;
            return this;
        }

        public Builder generatingTime(final long generatingTime) {
            this.generatingTime = generatingTime;
            return this;
        }

        public Builder miner(final User miner) {
            this.miner = miner;
            return this;
        }

        public Builder transaction(final List<Transaction> transaction) {
            this.transaction = new ArrayList<>(transaction);
            return this;
        }

        public Block build() {
            return new Block(this);
        }
    }


    public String toString() {
        return "Block:\n"
                + "Created by miner # " + miner.name + "\n"
                + miner.name + " gets 100 VC\n"
                + "Id: " + id + "\n"
                + "Timestamp: " + timeStamp + "\n"
                + "Magic number: " + magicNumber + "\n"
                + "Hash of the previous block: \n"
                + prevHash + "\n"
                + "Hash of the block: \n"
                + hash + "\n"
                + getTransactionString()
                + "\nBlock was generating for " + generatingTime + " seconds";
    }

    private String getTransactionString() {
        String formattedString = transaction.stream()
                .map(Transaction::getString).collect(Collectors.joining("\n"));
        return "Block data: " + (!formattedString.isEmpty() ? "\n" + formattedString : "no transaction");
    }
}

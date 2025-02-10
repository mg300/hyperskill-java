package org.blockchain;

import org.blockchain.User.Transaction;
import org.blockchain.User.User;
import org.blockchain.Utils.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class Blockchain {
    private static final Random RANDOM = new Random();
    private static final int SECOND = 1000;
    private static final int BASE_NUMBER = 10;
    private final List<Block> blockList;
    private int blockCurrentId;
    private int transactionCurrentId;
    private final List<Transaction> transactionBuffer;
    private List<Transaction> transactionActual;
    int zerosNum;

    public Blockchain() {
        this.blockCurrentId = 1;
        this.blockList = new CopyOnWriteArrayList<>();
        this.transactionCurrentId = 0;
        this.zerosNum = Config.INITIAL_DIFFICULTY;
        this.transactionBuffer = new CopyOnWriteArrayList<>();
        this.transactionActual = new CopyOnWriteArrayList<>();
    }

    public void generateBlock(final User miner) {
        long nowTime = new Date().getTime();
        int size = blockList.size();
        transactionActual = transactionBuffer;
        if (transactionActual.isEmpty()) {
            return;
        }
        while (true) {
            long timeStamp = new Date().getTime();
            String prevHash = !blockList.isEmpty() ? blockList.get(blockList.size() - 1).hash : "0";
            long magicNumber = generateRandomNumber(Config.MAGIC_LENGTH);
            String hash = generateHash(getCurrentId(), prevHash, timeStamp, magicNumber, this.transactionActual);
            if (size != blockList.size() || Config.CAPACITY <= blockList.size()) {
                return;
            }
            if (hasLeadingZeros(hash)) {
                long generatingTime = (new Date().getTime() - nowTime) / SECOND;
                addBlock(new Block.Builder()
                        .id(getCurrentId())
                        .timeStamp(timeStamp)
                        .hash(hash)
                        .prevHash(prevHash)
                        .magicNumber(magicNumber)
                        .generatingTime(generatingTime)
                        .miner(miner)
                        .transaction(transactionActual)
                        .build());
                return;
            }
        }
    }

    private synchronized void addBlock(final Block block) {
        transactionBuffer.removeAll(transactionActual);
        displayBlock(block);
        blockList.add(block);
        blockCurrentId++;
    }

    public void displayBlock(final Block block) {
        System.out.println(block);
        if (block.generatingTime > Config.TIME_TO_DECREASE_ZEROS && zerosNum > 0) {
            zerosNum--;
            System.out.println("N was decreased by 1\n");

        } else if (block.generatingTime < Config.TIME_TO_INCREASE_ZEROS && zerosNum < Config.MAXIMUM_DIFFICULTY) {
            zerosNum++;
            System.out.println("N was increased to " + zerosNum + "\n");
        } else {
            System.out.println("N stays the same\n");
        }
    }

    public String generateHash(final int id, final String prevHash, final long timeStamp,
                               final long magicNumber, final List<Transaction> transactions) {
        String blockString = id + timeStamp + prevHash + magicNumber + transactions;
        return StringUtil.applySha256(blockString);
    }

    public boolean checkBlockchainValidity() {
        for (int i = 1; i < blockList.size(); i++) {
            String prevHash = blockList.get(i - 1).hash;
            Block block = blockList.get(i);
            boolean result = Objects.equals(prevHash, block.prevHash)
                    && Objects.equals(block.hash,
                    generateHash(block.id, prevHash, block.timeStamp, block.magicNumber, block.transaction));
            if (!result) {
                return false;
            }
        }
        return true;
    }

    public void addTransaction(final Transaction transaction) throws Exception {
        int balance = getPersonBalance(transaction.sender.name);
        if (transaction.id > transactionCurrentId || !transaction.verifySignature()
                || Integer.parseInt(transaction.value) > balance) {
            return;
        }
        transactionBuffer.add(transaction);
    }

    private int generateRandomNumber(final int numberOfDigits) {
        int min = (int) Math.pow(BASE_NUMBER, numberOfDigits - 1);
        int max = (int) Math.pow(BASE_NUMBER, numberOfDigits) - 1;
        return RANDOM.nextInt((max - min) + 1) + min;
    }

    private boolean hasLeadingZeros(final String str) {
        String regex = "^0{" + zerosNum + "}.*$";
        return Pattern.compile(regex).matcher(str).matches();
    }

    public int getTransactionId() {
        transactionCurrentId++;
        return transactionCurrentId;
    }

    public int getPersonBalance(final String name) {
        int balance = Config.INITIAL_BALANCE;
        for (Block block : blockList) {
            if (Objects.equals(name, block.miner.name)) {
                balance += Config.BLOCK_REWARD;
            }
            int outcomeBlockchain = block.transaction.stream()
                    .filter(t -> Objects.equals(t.sender.name, name))
                    .mapToInt(t -> Integer.parseInt(t.value)).sum();
            int incomeBlockchain = block.transaction.stream()
                    .filter(t -> Objects.equals(t.to, name))
                    .mapToInt(t -> Integer.parseInt(t.value)).sum();
            int outcomeBuffer = transactionBuffer.stream()
                    .filter(t -> Objects.equals(t.sender.name, name))
                    .mapToInt(t -> Integer.parseInt(t.value)).sum();
            int incomeBuffer = transactionBuffer.stream()
                    .filter(t -> Objects.equals(t.to, name))
                    .mapToInt(t -> Integer.parseInt(t.value)).sum();
            balance = balance + incomeBuffer + incomeBlockchain - outcomeBuffer - outcomeBlockchain;
        }
        return balance;
    }

    private synchronized int getCurrentId() {
        return blockCurrentId;
    }

    public int size() {
        return blockList.size();
    }


    public boolean hasTransaction() {
        return !transactionBuffer.isEmpty();
    }
}

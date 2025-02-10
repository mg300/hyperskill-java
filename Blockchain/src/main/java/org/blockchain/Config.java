package org.blockchain;

public final class Config {
    public static final int TIME_TO_INCREASE_ZEROS = 2;
    public static final int TIME_TO_DECREASE_ZEROS = 30;
    public static final int MAXIMUM_DIFFICULTY = 3;
    public static final int INITIAL_DIFFICULTY = 0;
    public static final int CAPACITY = 15;
    public static final int MAGIC_LENGTH = 8;
    public static final int INITIAL_BALANCE = 500;
    public static final int BLOCK_REWARD = 100;
    public static final int ENCRYPTION_KEY_LENGTH = 1024;
    public static final int TRANSACTION_WAITING_TIME = 400;
    public static final int TRANSACTION_WAITING_PERIODS = 10;
    public static final int THREAD_QUANTITY = 8;

    private Config() {
    }

}

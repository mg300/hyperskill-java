package org.blockchain.Utils;

import java.security.MessageDigest;

public final class StringUtil {
    private static final int BYTE_MASK = 0xff;

    private StringUtil() {
    }


    public static String applySha256(final String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte elem : hash) {
                String hex = Integer.toHexString(BYTE_MASK & elem);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

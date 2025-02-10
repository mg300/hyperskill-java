package org.blockchain.Utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void applySha256Test() {
        String result = StringUtil.applySha256("This is test input");
        assertEquals("6f6b8a026e2fc9e0c8ae094bd7f018f38444945f0fadf7aada8a03ab3bc19811", result);
    }


}

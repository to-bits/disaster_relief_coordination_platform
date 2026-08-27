package com.drcp.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TokenBlacklistServiceTest {

    private TokenBlacklistService tokenBlacklistService;

    @BeforeEach
    void setUp() {
        tokenBlacklistService = new InMemoryTokenBlacklistService();
    }

    @Test
    void testBlacklistTokenAndIsBlacklisted() {
        String token = "sample.jwt.token";

        assertFalse(tokenBlacklistService.isBlacklisted(token));

        tokenBlacklistService.blacklistToken(token, new Date(System.currentTimeMillis() + 10000));

        assertTrue(tokenBlacklistService.isBlacklisted(token));
    }

    @Test
    void testExpiredBlacklistedToken() {
        String token = "expired.jwt.token";

        // Set expiry in the past
        tokenBlacklistService.blacklistToken(token, new Date(System.currentTimeMillis() - 1000));

        assertFalse(tokenBlacklistService.isBlacklisted(token));
    }
}

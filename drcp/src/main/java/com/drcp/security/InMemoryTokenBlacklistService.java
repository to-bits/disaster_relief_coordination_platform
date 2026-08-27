package com.drcp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class InMemoryTokenBlacklistService implements TokenBlacklistService {

    private final Map<String, Long> blacklistedTokens = new ConcurrentHashMap<>();

    @Override
    public void blacklistToken(String token, Date expiryDate) {
        if (token == null || token.isBlank()) {
            return;
        }
        long expirationTime = expiryDate != null ? expiryDate.getTime() : System.currentTimeMillis() + (24 * 60 * 60 * 1000L);
        blacklistedTokens.put(token, expirationTime);
        log.info("Token blacklisted until {}", new Date(expirationTime));
    }

    @Override
    public void blacklistToken(String token) {
        blacklistToken(token, null);
    }

    @Override
    public boolean isBlacklisted(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        Long expiryTime = blacklistedTokens.get(token);
        if (expiryTime == null) {
            return false;
        }
        if (System.currentTimeMillis() > expiryTime) {
            blacklistedTokens.remove(token);
            return false;
        }
        return true;
    }

    @Scheduled(fixedRate = 3600000) // Cleanup every hour
    public void cleanupExpiredTokens() {
        long now = System.currentTimeMillis();
        blacklistedTokens.entrySet().removeIf(entry -> now > entry.getValue());
    }
}

package com.drcp.security;

import java.util.Date;

public interface TokenBlacklistService {

    void blacklistToken(String token, Date expiryDate);

    void blacklistToken(String token);

    boolean isBlacklisted(String token);
}

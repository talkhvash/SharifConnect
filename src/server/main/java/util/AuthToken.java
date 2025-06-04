package server.main.java.util;

import java.security.SecureRandom;
import java.util.Base64;

public class AuthToken {
    private final SecureRandom secureRandom = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getEncoder();

    public String getToken() {
        byte[] randomBytes = secureRandom.generateSeed(16);
        return encoder.encodeToString(randomBytes);
    }

}

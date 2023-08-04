package tw.idv.tibame.tha102.core.util;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtSecretKey {
    private final String base64SecretKey;

    public JwtSecretKey(@Value("${jwt.secret}") String base64SecretKey) {
        this.base64SecretKey = base64SecretKey;
    }

    public SecretKey getKey() {
        byte[] keyByte = Base64.getDecoder().decode(base64SecretKey);
        return new SecretKeySpec(keyByte, SignatureAlgorithm.HS256.getJcaName());
    }
}

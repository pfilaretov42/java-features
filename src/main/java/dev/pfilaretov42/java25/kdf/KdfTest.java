package dev.pfilaretov42.java25.kdf;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.HKDFParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;
import java.util.HexFormat;

public class KdfTest {
    void main() throws GeneralSecurityException {
        before();
        afterSven();
    }

    private void before() throws GeneralSecurityException {
        // Like forging a sword in the crude fires of Mordor
        String password = "the super secret passphrase";
        byte[] salt = generateSalt();
        int iterations = 65_536;
        int keyLength = 256;

        // derive the key - PBKDF2 with homemade parameters
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey key = factory.generateSecret(spec);

        System.out.println("before - derived key: " + HexFormat.of().formatHex(key.getEncoded()));
    }

    private void afterSven() throws GeneralSecurityException {
        KDF hkdf = KDF.getInstance("HKDF-SHA256");
        byte[] password = "the super secret passphrase".getBytes();
        byte[] salt = generateSalt();
        byte[] info = "my derived key".getBytes();
        int length = 32;

        AlgorithmParameterSpec params = HKDFParameterSpec.ofExtract()
                .addIKM(password)
                .addSalt(salt)
                .thenExpand(info, length);

        SecretKey key = hkdf.deriveKey("AES", params);
        System.out.println("after - derived key: " + HexFormat.of().formatHex(key.getEncoded()));
    }

    private byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16]; // 128-bit salt
        random.nextBytes(salt);
        return salt;
    }
}

package dev.pfilaretov42.java25.kdf;

import javax.crypto.KDF;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.HKDFParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HexFormat;

public class KdfTest {
    void main() throws GeneralSecurityException {
        before();
        after();
    }

    private void before() throws GeneralSecurityException {
        String password = "Mellon!";
        byte[] salt = generateSalt();
        int iterations = 65_536;
        int keyLengthBits = 256;

        // derive the key
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLengthBits);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        SecretKey key = factory.generateSecret(spec);

        System.out.println("before - derived key: " + HexFormat.of().formatHex(key.getEncoded()));
    }

    private void after() throws GeneralSecurityException {
        KDF hkdf = KDF.getInstance("HKDF-SHA256");
        byte[] password = "Mellon!".getBytes();
        byte[] salt = generateSalt();
        byte[] info = "Say 'Friend' and enter".getBytes();
        int keyLengthBytes = 32;

        // derive the key
        AlgorithmParameterSpec params = HKDFParameterSpec.ofExtract()
                .addIKM(password)
                .addSalt(salt)
                .thenExpand(info, keyLengthBytes);
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

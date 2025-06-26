package dev.pfilaretov42.java24.ml_kem;

import javax.crypto.Cipher;
import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.HexFormat;

/**
 * Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism Test
 */
public class MlKemTest {

    void main() throws Exception {
        // Wielding the mithril armor of post-quantum cryptography

        // Step 1 (Receiver): Create an ML-KEM public/private key pair:
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ML-KEM");
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey receiverPublicKey = keyPair.getPublic();
        PrivateKey receiverPrivateKey = keyPair.getPrivate();

        // Step 2 (Sender, has the receiver's public key):
        // Create a session key and encapsulate it:
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kem.newEncapsulator(receiverPublicKey);
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();

        SecretKey sessionKey = encapsulated.key();

        byte[] keyEncapsulationMessage = encapsulated.encapsulation();

        // Step 3 (Receiver, has the sender's key encapsulation message):
        // Decapsulate the session key:
        KEM kr = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kr.newDecapsulator(receiverPrivateKey);

        SecretKey decapsulatedSessionKey = decapsulator.decapsulate(keyEncapsulationMessage);

        HexFormat hexFormat = HexFormat.of();
        System.out.println("encapsulated session key: " + hexFormat.formatHex(sessionKey.getEncoded()));
        System.out.println("decapsulated session key: " + hexFormat.formatHex(decapsulatedSessionKey.getEncoded()));
        System.out.println("Secrets match: " +
                MessageDigest.isEqual(sessionKey.getEncoded(), decapsulatedSessionKey.getEncoded()));

        // Now sender and receiver can exchange messages
        // using the securely transmitted session key.
        // . . .
    }
}

class RSAExample {

    // Generate a new RSA key pair (2048 bits)
    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048); // You can use 4096 for stronger keys
        return generator.generateKeyPair();
    }

    // Encrypt a plain text message using a public key
    public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding"); // RSA with padding
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Decrypt an encrypted message using a private key
    public static String decrypt(String encryptedText, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Sample usage
    public static void main() throws Exception {
        KeyPair keyPair = generateKeyPair();

        String message = "Hello, RSA!";
        System.out.println("Original: " + message);

        String encrypted = encrypt(message, keyPair.getPublic());
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, keyPair.getPrivate());
        System.out.println("Decrypted: " + decrypted);
    }
}

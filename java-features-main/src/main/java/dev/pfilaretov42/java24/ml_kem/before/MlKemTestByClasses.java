package dev.pfilaretov42.java24.ml_kem.before;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.HexFormat;

/**
 * Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism Test
 */
public class MlKemTestByClasses {

    /**
     * The Session Key Exchange
     */
    public void main() throws GeneralSecurityException {
        // Elrond prepares his runes of protection (Receiver creates key pair)
        ElrondTheReceiver elrond = new ElrondTheReceiver();

        // Gandalf crafts a secret using Elrond’s rune (Sender uses receiver's public key to encapsulate session key)
        GandalfTheSender gandalf = new GandalfTheSender(elrond.revealPublicRune());
        SecretKey senderSessionKey = gandalf.getSessionKey();

        // Elrond deciphers the sealed whisper from Gandalf (Receiver decapsulates to get the same session key)
        SecretKey receiverSessionKey = elrond.decapsulateWhisper(gandalf.getSealedWhisper());
        boolean secretsMatch = MessageDigest.isEqual(senderSessionKey.getEncoded(), receiverSessionKey.getEncoded());

        // Output for verification:
        HexFormat hex = HexFormat.of();
        System.out.println("Sender session key:   " + hex.formatHex(senderSessionKey.getEncoded()));
        System.out.println("Receiver session key: " + hex.formatHex(receiverSessionKey.getEncoded()));
        System.out.println("Secrets match: " + secretsMatch);

        if (secretsMatch) {
            // Gandalf and Elrond exchange messages using the securely transmitted session key
            // ...
        }
    }
}

/**
 * Receiver generates key pair and decapsulates session key
 */
class ElrondTheReceiver {
    private final KeyPair keyPair;

    public ElrondTheReceiver() throws GeneralSecurityException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(4096);
        keyPair = generator.generateKeyPair();
    }

    public PublicKey revealPublicRune() {
        return keyPair.getPublic();
    }

    public SecretKey decapsulateWhisper(byte[] sealedWhisper) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] keyBytes = cipher.doFinal(sealedWhisper);

        return new SecretKeySpec(keyBytes, "AES");
    }
}

/**
 * Sender uses receiver's public key to generate session key
 */
class GandalfTheSender {
    private final SecretKey sessionKey; // Sender’s secret session key
    private final byte[] sealedWhisper; // The message with session key encapsulated for Receiver

    public GandalfTheSender(PublicKey receiverPublicKey) throws GeneralSecurityException {
        // Generate session key (AES)
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256);
        sessionKey = generator.generateKey();

        // Encrypt (encapsulate) session key with RSA
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);
        sealedWhisper = cipher.doFinal(sessionKey.getEncoded());
    }

    public SecretKey getSessionKey() {
        return sessionKey;
    }

    public byte[] getSealedWhisper() {
        return sealedWhisper;
    }
}

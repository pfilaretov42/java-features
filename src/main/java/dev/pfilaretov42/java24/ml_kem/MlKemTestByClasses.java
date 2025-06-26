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
public class MlKemTestByClasses {
    /**
     * Simulates full exchange
     */
    public void main() throws GeneralSecurityException {
        // Step 1: Receiver creates key pair
        Receiver receiver = new Receiver();

        // Step 2: Sender uses receiver's public key to encapsulate session key
        Sender sender = new Sender(receiver.getPublicKey());
        SecretKey senderSessionKey = sender.getSessionKey();

        // Step 3: Receiver decapsulates to get the same session key
        SecretKey receiverSessionKey = receiver.decapsulate(sender.getKeyEncapsulationMessage());
        boolean secretsMatch = MessageDigest.isEqual(senderSessionKey.getEncoded(), receiverSessionKey.getEncoded());

        // Output for verification
        HexFormat hex = HexFormat.of();
        System.out.println("Sender session key:   " + hex.formatHex(senderSessionKey.getEncoded()));
        System.out.println("Receiver session key: " + hex.formatHex(receiverSessionKey.getEncoded()));
        System.out.println("Secrets match: " + secretsMatch);

        if (secretsMatch) {
            // sender and receiver exchange messages using the securely transmitted session key
        }

    }
}

/**
 * Generates key pair and decapsulates session key
 */
class Receiver {
    private final KeyPair keyPair;

    public Receiver() throws GeneralSecurityException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ML-KEM");
        this.keyPair = generator.generateKeyPair();
    }

    public PublicKey getPublicKey() {
        return keyPair.getPublic();
    }

    public SecretKey decapsulate(byte[] keyEncapsulationMessage) throws GeneralSecurityException {
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kem.newDecapsulator(keyPair.getPrivate());
        return decapsulator.decapsulate(keyEncapsulationMessage);
    }
}

/**
 * Uses receiver's public key to generate session key
 */
class Sender {
    private final SecretKey sessionKey;
    private final byte[] keyEncapsulationMessage;

    public Sender(PublicKey receiverPublicKey) throws GeneralSecurityException {
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kem.newEncapsulator(receiverPublicKey);
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();

        this.sessionKey = encapsulated.key();
        this.keyEncapsulationMessage = encapsulated.encapsulation();
    }

    public SecretKey getSessionKey() {
        return sessionKey;
    }

    public byte[] getKeyEncapsulationMessage() {
        return keyEncapsulationMessage;
    }
}

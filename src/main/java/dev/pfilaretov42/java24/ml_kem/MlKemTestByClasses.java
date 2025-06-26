package dev.pfilaretov42.java24.ml_kem;

import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.HexFormat;

/**
 * Quantum-Resistant Module-Lattice-Based Key Encapsulation Mechanism Test
 */
public class MlKemTestByClasses {
}

/**
 * The Key Exchange
 */
class MiddleEarth {
    public void main() throws GeneralSecurityException {
        // Elrond prepares his runes of protection (Receiver creates key pair)
        Receiver elrond = new Receiver();

        // Gandalf crafts a secret using Elrond’s rune (Sender uses receiver's public key to encapsulate session key)
        Sender gandalf = new Sender(elrond.revealPublicRune());
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
class Receiver {
    private final KeyPair keyPair;

    public Receiver() throws GeneralSecurityException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ML-KEM");
        this.keyPair = generator.generateKeyPair();
    }

    public PublicKey revealPublicRune() {
        return keyPair.getPublic();
    }

    public SecretKey decapsulateWhisper(byte[] sealedWhisper) throws GeneralSecurityException {
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kem.newDecapsulator(keyPair.getPrivate());
        return decapsulator.decapsulate(sealedWhisper);
    }
}

/**
 * Sender uses receiver's public key to generate session key
 */
class Sender {
    private final SecretKey sessionKey; // Sender’s secret session key
    private final byte[] sealedWhisper; // The message with session key encapsulated for Receiver

    public Sender(PublicKey receiverPublicKey) throws GeneralSecurityException {
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kem.newEncapsulator(receiverPublicKey);
        KEM.Encapsulated encapsulated = encapsulator.encapsulate();

        this.sessionKey = encapsulated.key();
        this.sealedWhisper = encapsulated.encapsulation();
    }

    public SecretKey getSessionKey() {
        return sessionKey;
    }

    public byte[] getSealedWhisper() {
        return sealedWhisper;
    }
}

package dev.pfilaretov42.java24.ml_kem;


import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.*;
import java.util.HexFormat;


public class MlKemTestLotr {
}

/**
 * The Key Exchange
 */
class MiddleEarth {
    public void main() throws GeneralSecurityException {
        System.out.println("🌍 The Council of Elrond is called...");

        // 🧝 Elrond prepares his runes of protection
        Elrond elrond = new Elrond();

        // 🧙 Gandalf crafts a secret using Elrond’s rune
        Gandalf gandalf = new Gandalf(elrond.revealPublicRune());
        SecretKey senderSessionKey = gandalf.getSenderSessionKey(); // 🔑 Gandalf's secret

        // 🧝 Elrond deciphers the sealed whisper from Gandalf
        SecretKey receiverSessionKey = elrond.decapsulateWhisper(gandalf.getSealedWhisper()); // 🔓 Elrond's version
        boolean magicBound = MessageDigest.isEqual(senderSessionKey.getEncoded(), receiverSessionKey.getEncoded());

        // 🔍 Compare the shared secrets
        HexFormat hex = HexFormat.of();
        System.out.println("\n🔑 Gandalf’s Secret:  " + hex.formatHex(senderSessionKey.getEncoded()));
        System.out.println("🔓 Elrond’s Secret:   " + hex.formatHex(receiverSessionKey.getEncoded()));
        System.out.println("\n🪄 Magic Bound? " + magicBound);
    }
}


/**
 * The Receiver of the Whisper
 */
class Elrond {
    private final KeyPair keyPair;

    public Elrond() throws GeneralSecurityException {
        // Forge the key pair in Rivendell
        KeyPairGenerator keyForge = KeyPairGenerator.getInstance("ML-KEM");
        this.keyPair = keyForge.generateKeyPair();
    }

    public PublicKey revealPublicRune() {
        return keyPair.getPublic(); // A rune sent across Middle-earth
    }

    public SecretKey decapsulateWhisper(byte[] whisperFromGandalf) throws GeneralSecurityException {
        // Use the private rune to reveal the secret
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Decapsulator decapsulator = kem.newDecapsulator(keyPair.getPrivate());
        return decapsulator.decapsulate(whisperFromGandalf);
    }
}

/**
 * The Sender of the Secret
 */
class Gandalf {
    private final SecretKey senderSessionKey;  // 🔑 Gandalf’s secret
    private final byte[] sealedWhisper;        // The message encapsulated for Elrond

    public Gandalf(PublicKey elvenRune) throws GeneralSecurityException {
        // Enchant the whisper using Elrond's rune
        KEM kem = KEM.getInstance("ML-KEM");
        KEM.Encapsulator encapsulator = kem.newEncapsulator(elvenRune);
        KEM.Encapsulated result = encapsulator.encapsulate();

        this.senderSessionKey = result.key();          // Gandalf’s sacred session key
        this.sealedWhisper = result.encapsulation();   // The sealed message to be sent
    }

    public SecretKey getSenderSessionKey() {
        return senderSessionKey;
    }

    public byte[] getSealedWhisper() {
        return sealedWhisper;
    }
}

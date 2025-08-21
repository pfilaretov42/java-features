package dev.pfilaretov42.java24.ml_dsa.after;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.Signature;

public class MlDsaTestByClasses {

    public void main() throws Exception {
        // Gandalf prepares his spell (Sender creates a public/private key pair)
        GandalfTheSender gandalf = new GandalfTheSender();

        // Sender signs a message using the private key
        String scroll = gandalf.speakWordsOfPower();
        byte[] waxSeal = gandalf.signMessage(scroll);

        // Aragorn receives the scroll and the seal
        AragornTheReceiver aragorn = new AragornTheReceiver(gandalf.revealPublicRune());

        // Verifying the true words of Gandalf (Receiver verifies the message using the sender's public key)
        boolean isTrueScroll = aragorn.verifyMessage(scroll, waxSeal);
        System.out.println("Is the scroll valid: " + isTrueScroll); // true

        // Attempt to fool the ranger with a forged scroll (verification fails for counterfeit message)
        String fakeScroll = """
            A new Power is rising. Against it the old allies and policies will not avail us at all. \
            There is no hope left in Elves or dying Númenor.
            """;
        isTrueScroll = aragorn.verifyMessage(fakeScroll, waxSeal);
        System.out.println("Is the forged scroll valid: " + isTrueScroll); // false
    }
}

/**
 * Sender signs message with private key
 */
class GandalfTheSender {
    private final KeyPair keyPair;

    public GandalfTheSender() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ML-DSA");
        keyPair = generator.generateKeyPair();
    }

    public PublicKey revealPublicRune() {
        return keyPair.getPublic();
    }

    public byte[] signMessage(String message) throws Exception {
        Signature runeEngraver = Signature.getInstance("ML-DSA");
        runeEngraver.initSign(keyPair.getPrivate());
        runeEngraver.update(message.getBytes());
        return runeEngraver.sign();
    }

    public String speakWordsOfPower() {
        return """
            It is not our part here to take thought only for a season, or for a few lives of Men, \
            or for a passing age of the world. We should seek a final end of this menace, \
            even if we do not hope to make one.
            """;
    }
}

/**
 * Receiver verifies the message with public key
 */
class AragornTheReceiver {
    private final PublicKey senderPublicKey;

    public AragornTheReceiver(PublicKey senderPublicKey) {
        this.senderPublicKey = senderPublicKey;
    }

    public boolean verifyMessage(String message, byte[] signature) throws Exception {
        Signature runeChecker = Signature.getInstance("ML-DSA");
        runeChecker.initVerify(senderPublicKey);
        runeChecker.update(message.getBytes());
        return runeChecker.verify(signature);
    }
}

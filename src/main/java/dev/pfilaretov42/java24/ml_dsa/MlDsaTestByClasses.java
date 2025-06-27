package dev.pfilaretov42.java24.ml_dsa;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;

public class MlDsaTestByClasses {
}


class MiddleEarth {

    public void main() throws Exception {
        // Gandalf prepares his spell (Sender creates an ML-KEM public/private key pair)
        GandalfTheSender gandalf = new GandalfTheSender();

        // Sender signs a message using the private key
        String scroll = gandalf.speakWordsOfPower();
        byte[] waxSeal = gandalf.signMessage(scroll);

        // Aragorn receives the scroll and the seal
        PublicKey gandalfKey = KeyFactory.getInstance("ML-DSA")
                .generatePublic(new X509EncodedKeySpec(gandalf.getPublicKey()));
        AragornTheReceiver aragorn = new AragornTheReceiver(gandalfKey);

        // Verifying the true words of Gandalf (Receiver verifies the message using the sender's public key)
        boolean isTrueScroll = aragorn.verifyMessage(scroll, waxSeal);
        System.out.println("Is the scroll valid: " + isTrueScroll);

        // Attempt to fool the ranger with a forged scroll (verification fails for counterfeit message)
        String fakeScroll = "A new power is rising. Its victory is at hand.";
        isTrueScroll = aragorn.verifyMessage(fakeScroll, waxSeal);
        System.out.println("Is the forged scroll valid: " + isTrueScroll);
    }
}

class GandalfTheSender {

    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public GandalfTheSender() throws Exception {
        KeyPairGenerator forge = KeyPairGenerator.getInstance("ML-DSA");
        KeyPair sacredKeyPair = forge.generateKeyPair();
        privateKey = sacredKeyPair.getPrivate();
        publicKey = sacredKeyPair.getPublic();
    }

    public byte[] getPublicKey() {
        return publicKey.getEncoded();
    }

    public byte[] signMessage(String message) throws Exception {
        Signature runeEngraver = Signature.getInstance("ML-DSA");
        runeEngraver.initSign(privateKey);
        runeEngraver.update(message.getBytes());
        return runeEngraver.sign();
    }

    public String speakWordsOfPower() {
        return "So long as there is any life in us, there is hope";
    }
}


class AragornTheReceiver {

    private final PublicKey senderPublicKey;

    public AragornTheReceiver(PublicKey publicKey) {
        senderPublicKey = publicKey;
    }

    public boolean verifyMessage(String message, byte[] signature) throws Exception {
        Signature runeChecker = Signature.getInstance("ML-DSA");
        runeChecker.initVerify(senderPublicKey);
        runeChecker.update(message.getBytes());
        return runeChecker.verify(signature);
    }
}

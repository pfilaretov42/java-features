package dev.pfilaretov42.java24.ml_dsa;

import java.security.*;

public class MlDsaTest {
    void main() throws GeneralSecurityException {
        // Step 1 (Sender): Create an ML-KEM public/private key pair:
        KeyPairGenerator generator = KeyPairGenerator.getInstance("ML-DSA");
        KeyPair keyPair = generator.generateKeyPair();

        PublicKey senderPublicKey = keyPair.getPublic();
        PrivateKey senderPrivateKey = keyPair.getPrivate();

        // Step 2 (Sender): Sign a message using the private key:
        byte[] message = "Roses bloom nightly.".getBytes();
        Signature signer = Signature.getInstance("ML-DSA");
        signer.initSign(senderPrivateKey);
        signer.update(message);
        byte[] signature = signer.sign();

        // Step 3 (Receiver): Verify the message using the sender's public key:
        Signature signatureVerifier = Signature.getInstance("ML-DSA");
        signatureVerifier.initVerify(senderPublicKey);
        signatureVerifier.update(message);
        boolean isValid = signatureVerifier.verify(signature);
        System.out.println("Signature for original message is valid: " + isValid);

        // verification fails for counterfeit message:
        signatureVerifier.initVerify(senderPublicKey);
        signatureVerifier.update("counterfeit message".getBytes());
        isValid = signatureVerifier.verify(signature);
        System.out.println("Signature for counterfeit message is valid: " + isValid);
    }
}

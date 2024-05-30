package trkzi.omar;

import java.security.*;
import java.util.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class Wallet {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public Wallet() {
        generateKeyPair();
    }

    public void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            keyGen.initialize(256, random);
            KeyPair keyPair = keyGen.generateKeyPair();
            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getPublicKeyString() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public String getPrivateKeyString() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    public byte[] signData(byte[] data) {
        Signature ecdsa;
        byte[] output = new byte[0];
        try {
            ecdsa = Signature.getInstance("ECDSA", "BC");
            ecdsa.initSign(privateKey);
            ecdsa.update(data);
            output = ecdsa.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return output;
    }

    public boolean verifySignature(byte[] data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data);
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toString() {
        return "Wallet: \n" +
                "    publicKey: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()) +
                '\n';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class KeyGenerator {
    private KeyPairGenerator keyGen;
    
    private KeyPair pair;
    
    private PrivateKey priv;
    
    
    /**
     * Generates a pair of keys.
     */
    public KeyGenerator() 
    {
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("RSA", "SUN");
            keyGen.initialize(1024, random);
            
            pair = keyGen.generateKeyPair();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void generateSignature() {
        try {
            Signature rsa = Signature.getInstance("RSA");
            buildPrivateKey(rsa);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void buildPrivateKey(Signature rsa) {
        PrivateKey priv = pair.getPrivate();
        try {
            // Initialize the object with a private key
            rsa.initSign(priv);
            
            // Update and Sign data.
            byte[] data = new byte[1024];
            rsa.update(data);
            byte[] sig = rsa.sign();
        } catch (InvalidKeyException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verifySignature(Signature rsa) {
        PublicKey pub = pair.getPublic();
        try {
            // Initialize the object with a public key
            rsa.initVerify(pub);
            byte[] data = new byte[1024];
            //rsa.update(data);
            //boolean verifies = rsa.verify(sig);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(KeyGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

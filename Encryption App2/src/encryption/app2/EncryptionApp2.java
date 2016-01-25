/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption.app2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class EncryptionApp2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidKeySpecException, NoSuchAlgorithmException, InvalidKeyException {
        // TODO code application logic here
        //Path pathKey = Paths.get("private.key");
        Path pathKey = Paths.get("F:\\Documents\\NetBeansProjects\\Encryption\\private.key");
        File fileKey = pathKey.toFile();
        System.out.println(fileKey.toString());
        Path pathInput = Paths.get("INPUT.EXT");
        File fileInput = pathInput.toFile();

        Scanner scan = new Scanner(System.in);
        System.out.println("Give your name: ");
        String name = scan.nextLine();
        String outputFileName = "INPUT(SignedBy" + name + ").EXT";
        Path pathOutput = Paths.get(outputFileName);

        System.out.println(fileKey.toString());
        // Get Key length
        long lengthKey = fileKey.length();
        // Get Key handshake
        byte[] keyBytes = new byte[(int) lengthKey];
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);
        // INPUT.EXT content
        byte[] signature = signWithPrivateKey(privateKey);
        String fileContent;
        BufferedReader br = new BufferedReader(new FileReader("INPUT.EXT"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            fileContent = sb.toString();
        } finally {
            br.close();
        }
        
        String encryptedFileContentString = lengthKey + privateKey.getEncoded().toString() + fileContent;
        byte encryptedFileContent[] = encryptedFileContentString.getBytes();
        
        Files.write(pathOutput, encryptedFileContent);
        
    }
    private static byte[] signWithPrivateKey(PrivateKey privKey) throws InvalidKeyException {
        try {
            Signature rsaSig = Signature.getInstance("RSA");
            rsaSig.initSign(privKey);
            
            //FileInpuStream fis = new FileInputStream();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EncryptionApp2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
}

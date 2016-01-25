/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption.app3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class EncryptionApp3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Path signedFilePath = Paths.get("INPUT(SignedBy" + "Bas" +  ").EXT");
        Path publicFile = Paths.get("public.key");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(signedFilePath.toFile()));
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EncryptionApp3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}

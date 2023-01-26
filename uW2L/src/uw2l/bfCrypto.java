/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.security.*;
import javax.crypto.*;

public class bfCrypto {
    
    public static void main(String[] args) throws Exception{
        
        if(args.length != 1){
            
            System.err.println("Usage: For the project");
            System.exit(1);
        }
        
        String text = args[0];
        
        System.out.println("Generating BF Key...");
        
        //Create BF Key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");
        //initialize with keysize
        keyGenerator.init(128); 
        Key key = keyGenerator.generateKey();
        
        System.out.println("Key Generated!");
        
        // Create a cipher using that key to initialize it
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] plaintext = text.getBytes("UTF8");

        // Print out the bytes of the plaintext
         System.out.println("\nPlaintext: ");
        for (int i=0;i<plaintext.length;i++) {
		System.out.print(plaintext[i]+" ");
	}

        // Perform the actual encryption
        byte[] ciphertext = cipher.doFinal(plaintext);

	// Print out the ciphertext
        System.out.println("\n\nCiphertext: ");
        for (int i=0;i<ciphertext.length;i++) {
		System.out.print(ciphertext[i]+" ");
	}

         // Re-initialize the cipher to decrypt mode
         cipher.init(Cipher.DECRYPT_MODE, key);
    
        // Perform the decryption
         byte[] decryptedText = cipher.doFinal(ciphertext);

        String output = new String(decryptedText,"UTF8");

        System.out.println("\n\nDecrypted text: "+output);

    }
    
}

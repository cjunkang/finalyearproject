/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uw2l;

import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
        

public class encryptdecrypt{
    
    //private static final String ALGORITHM = "Blowfish";
    //private static final String ALGORITHM1 = "AES";
    
    
    
    public static String toEncrypt(String item1) throws Exception{
        
     return  doCryto(Cipher.ENCRYPT_MODE); 
       
    }
    
    public static String toDecrypt() throws Exception{
        
        return doCryto(Cipher.DECRYPT_MODE);
    }
    
    public static String doCryto(int cipherMode) throws Exception{
        
        KeyGenerator keyGen1 = KeyGenerator.getInstance("Blowfish");
        keyGen1.init(128);
        Key key = keyGen1.generateKey();
        
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Paddin");
        
        cipher.init(cipherMode, key);
        
        return null;
            
    }
}

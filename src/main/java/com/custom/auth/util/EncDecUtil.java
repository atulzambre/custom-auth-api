package com.custom.auth.util;

import com.custom.auth.config.SecretProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class EncDecUtil {

    @Autowired
    private SecretProperties secretProperties;

    public String encryptString(String str) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory object
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(secretProperties.getSecret().toCharArray(), secretProperties.getSalt().getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            // Return encrypted string
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            System.out.println("Error while encrypting: "
                    + e);
        }
        return null;
    }


    public String decryptString(String str) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            // Create IvParameterSpec object and assign with
            // constructor
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            // Create SecretKeyFactory Object
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            // Create KeySpec object and assign with
            // constructor
            KeySpec spec = new PBEKeySpec(secretProperties.getSecret().toCharArray(), secretProperties.getSalt().getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            // Return decrypted string
            return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: "
                    + e);
        }
        return null;
    }

}

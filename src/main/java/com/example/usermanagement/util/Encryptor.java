package com.example.usermanagement.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class Encryptor {

    private static Encryptor encryptor;

    StandardPBEStringEncryptor stringEncryptor = new StandardPBEStringEncryptor();

    private Encryptor(){stringEncryptor.setPassword("Avansas");}

    public static Encryptor getInstance(){
        if(encryptor == null) {
            encryptor = new Encryptor();
        }
        return encryptor;
    }

    public String generateSecurePassword(String password){return stringEncryptor.encrypt(password);}

    public boolean verifyPersonnelPassword(String providedPassword, String securedPassword){
        boolean verified = false;
        if(stringEncryptor.decrypt(securedPassword).equals(providedPassword)){ verified=true;}
        return verified;
    }

    public String gerDecryptedPassword(String securedPassword){return stringEncryptor.decrypt(securedPassword);}
}

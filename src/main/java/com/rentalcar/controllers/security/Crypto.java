package com.rentalcar.controllers.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crypto {

    private static Logger log = Logger.getLogger(Crypto.class.getName());

    public static String sha256(String string){
        return getHash(string,"SHA-256");
    }

    public static String md5(String string){
        return getHash(string, "MD5");
    }

    private static String getHash(String string, String algorithn){
        try {
            MessageDigest md = MessageDigest.getInstance(algorithn);
            md.update(string.getBytes());
            log.fine("Hashing "+ algorithn);
            return bytesToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.log(Level.SEVERE, "Exception", e);
            return null;
        }
    }

    private static String bytesToHex(byte[] bytes){
        StringBuilder result = new StringBuilder(64);
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
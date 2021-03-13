package by.gourianova.binocularvision.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class myMD5 {
    public static String md5Encode(String password) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("myMD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);
       while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }
}

package br.com.datamovies.utils;

import br.com.datamovies.exceptions.DataMovieInformationException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DataApiInformationUtils {

    public String md5Hash(String value) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(value.getBytes());
            BigInteger bigInteger = new BigInteger(1, digest);
            String s = bigInteger.toString(16);
            while (s.length() < 32) {
                s = "0" + s;
            }
            return s;
        } catch (NoSuchAlgorithmException e) {
            throw new DataMovieInformationException("Fail to hash string key", e);
        }
    }
}

package com.example.hasla;
import android.app.Application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Global extends Application {
    public Global() {
        super();
    }
    private long userID;
    public synchronized long getUserID() {
        return userID;
    }
    public synchronized void setUserID(long UserID) {
        this.userID = UserID;
    }
    public synchronized void nullUserID() {
        this.userID = 0;
    }
    public static String hashPassword(String password) {
        //Funkcja do hashowania - dokumentacja, w całości skopiowane
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public static final String DB_NAME = "Hasla.db";
}
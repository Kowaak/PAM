package com.example.hasla;
import android.app.Application;

public class Global extends Application {
    public Global() {
        super();
    }
    private int userID;
    public synchronized int getUserID() {
        return userID;
    }
    public synchronized void setUserID(int UserID) {
        this.userID = UserID;
    }
    public static final String DB_NAME = "Hasla.db";
}
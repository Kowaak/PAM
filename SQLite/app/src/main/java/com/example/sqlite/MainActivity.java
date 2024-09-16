package com.example.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText Eusername;
    private EditText Epass;
    private TextView usersTextView;
    private static final String DB_NAME = "user.db";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Eusername = findViewById(R.id.login);
        Epass = findViewById(R.id.haslo);
        usersTextView = findViewById(R.id.uzytko);
        StringBuilder usersString = new StringBuilder();

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT)");

        Button loginButton = findViewById(R.id.log);
        Button rejeButton = findViewById(R.id.rej);
        Button wypisz = findViewById(R.id.wyp);

        wypisz.setOnClickListener(view -> {
            Cursor cursor = db.rawQuery("SELECT * FROM users", null);
            if (cursor.moveToFirst()) {
                do {
                    String username = cursor.getString(1);
                    String password = cursor.getString(2);
                    usersString.append("Username: ").append(username).append(", Password: ").append(password).append("\n");
                } while (cursor.moveToNext());
            }
            cursor.close();
            usersTextView.setText(usersString.toString());
        });
        rejeButton.setOnClickListener(view -> {
            String username = Eusername.getText().toString();
            String pass = Epass.getText().toString();

            if (username.isEmpty() || pass.isEmpty()){
                Toast.makeText(MainActivity.this, "WYPEŁNIJ WSZYSTKIE POLA", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
            if (cursor.moveToFirst()){
                Toast.makeText(MainActivity.this, "NAZWA ZAJETA", Toast.LENGTH_SHORT).show();
                return;
            }
            db.execSQL("INSERT INTO users (username,password) VALUES (?,?)", new String[]{username,pass});
            Toast.makeText(MainActivity.this, "UTWORZONO UŻYTKOWNIKA", Toast.LENGTH_SHORT).show();
            cursor.close();
        });
        loginButton.setOnClickListener(view -> {
            String username = Eusername.getText().toString();
            String pass = Epass.getText().toString();

            Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[]{username,pass});
            if (cursor.moveToFirst()){
                Toast.makeText(MainActivity.this, "GIT", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "NIE GIT", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });
    }
}
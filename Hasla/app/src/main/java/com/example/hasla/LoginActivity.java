package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    //Funkcja sprawdza czy dane podane przy logowaniu są poprawne
    private boolean daneLogowania(String login, String haslo) {
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        // Szyfrowanie hasła przed sprawdzeniem
        String hashedPassword = Global.hashPassword(haslo);
        // Zapytanie które sprawdza Login i Haslo
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE Login = ? AND Haslo = ?", new String[]{login, hashedPassword});
        boolean isValid = cursor.getCount() > 0; // Jeśli count > 0 dane są poprawne
        cursor.close();
        return isValid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //Przypisanie pól do zmiennych
        EditText Elogin = findViewById(R.id.eLogin);
        EditText Ehaslo = findViewById(R.id.eHaslo);
        Button Logowanie = findViewById(R.id.Logowanie);
        //Utworzenie lub otwarcie tabeli w bazie z nazwą określona w zmiennej 'DB_NAME' dziedziczoną z klasy 'Global'
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,Login TEXT UNIQUE, Haslo TEXT )");
        //Wbudowana funkcja
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //Funckja odpowiedzialna za przejście do klasy 'RegisterActivity'
            Button gotoRegister = findViewById(R.id.DoRejestracja);
            gotoRegister.setOnClickListener(v1 -> {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            });
            //Funkcja odpowiedzialna za całe logowanie
            Logowanie.setOnClickListener(v1 -> {
                Global app = (Global) getApplication();
                String Login = Elogin.getText().toString();
                String Haslo = Ehaslo.getText().toString();
                if (daneLogowania(Login, Haslo)) {
                    // Dane są poprawne
                    Cursor cursor = db.rawQuery("SELECT id FROM users WHERE Login = ? AND Haslo = ?", new String[]{Login,Haslo});
                    //long userID = id z bazy i chuj
                    //app.setUserID(userID);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Dane są niepoprawne
                    Toast.makeText(this, "Login lub hasło nie są poprawne", Toast.LENGTH_SHORT).show();
                }
            });
            return insets;
        });
    }
}
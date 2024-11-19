package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;

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
            //Funkcja odpowiedzialna za logowanie
            Logowanie.setOnClickListener(v1 -> {
                Global app = (Global) getApplication();
                String Login = Elogin.getText().toString().trim();
                String Haslo = Ehaslo.getText().toString().trim();

                if (Login.isEmpty() || Haslo.isEmpty()) {
                    Toast.makeText(this, "Proszę wprowadzić login i hasło", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (daneLogowania(Login, Haslo)) {
                    long userID = -1;
                    // Pobierz id uzytkownika z bazy
                    String hashedPassword = Global.hashPassword(Haslo);
                    Cursor cursor = db.rawQuery("SELECT id FROM users WHERE Login = ? AND Haslo = ?", new String[]{Login, hashedPassword});

                    if (cursor != null) {
                        // sprawdz czy jest przynajmniej jeden wynik
                        if (cursor.moveToFirst()) {
                            //pobierz index kolumny id
                            int idColumn = cursor.getColumnIndex("id");
                            if (idColumn != -1) {
                                userID = cursor.getLong(idColumn);
                            }
                        }
                        cursor.close();
                    }
                    if (userID != -1) { //udało się zalogowac, przejdz do main
                        app.setUserID(userID);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Nie znaleziono użytkownika. Proszę sprawdzić dane logowania.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Dane logowania są niepoprawne.", Toast.LENGTH_SHORT).show();
                }
            });
            return insets;
        });
    }
}
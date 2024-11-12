package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        //Przypisanie pól do zmiennych
        EditText Elogin = findViewById(R.id.eLogin);
        EditText Ehaslo = findViewById(R.id.eHaslo);
        //Utworzenie lub otwarcie bazy danych z nazwą określona w zmiennej 'DB_NAME' dziedziczoną z klasy 'Global'
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);  //Kij wie jak zrobić szyfrowanie póki co
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,Login TEXT UNIQUE, Haslo TEXT )");
        //wbudowane
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //Funckja odpowiedzialna za przejście do klasy 'RegisterActivity'
            Button gotoRegister = findViewById(R.id.DoRejestracja);
            gotoRegister.setOnClickListener(v1 -> {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            });

            //Dostęp do zmiennej globalnej ID
            Global app = (Global) getApplication();
            //Ustawienie zmiennej globalnej ID
            app.setUserID(1);
            //Odczytanie zmiennej globalnej ID
            int userId = app.getUserID();

            return insets;
        });
    }
}
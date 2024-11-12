package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        //Przypisanie pól do zmiennych
        EditText Elogin = findViewById(R.id.eLogin);
        EditText Ehaslo = findViewById(R.id.eHaslo);
        //Utworzenie lub otwarcie bazy danych z nazwą określona w zmiennej 'DB_NAME' dziedziczoną z klasy 'Global'
        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,Login TEXT UNIQUE, Haslo TEXT )");
        //Wbudowane
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //Funckja odpowiedzialna za przejście do klasy 'LoginActivity'
            Button gotoLogin = findViewById(R.id.Logowanie);
            gotoLogin.setOnClickListener(v1 -> {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            });
            //Cała funkcja przycisku z id 'Rejestracja'
            Button rejeButton = findViewById(R.id.Rejestracja);
            rejeButton.setOnClickListener(view ->{
                TextView Error = findViewById(R.id.log_error);
                try {
                    String Login = Elogin.getText().toString();
                    String Haslo = Ehaslo.getText().toString();
                    //Walidacja czy pola sa puste
                    if (Login.isEmpty() || Haslo.isEmpty()) {
                        Error.setText(R.string.error_empty);
                        return; //Jeśli są puste to wyjdź
                    }
                    //Walidacja czy login jest unikatowy
                    Cursor cursor = db.rawQuery("SELECT * FROM users WHERE Login = ?", new String[]{Login});
                    if (cursor.getCount() > 0) {
                        //Login już istnieje
                        Error.setText(R.string.error_login_taken);
                    } else {
                        //Insert użytkownika do bazy
                        db.execSQL("INSERT INTO users (Login, Haslo) VALUES (?, ?)", new String[]{Login, Haslo});
                        //Udało się utworzyć użytkownika
                        Toast.makeText(RegisterActivity.this, "UTWORZONO UŻYTKOWNIKA", Toast.LENGTH_SHORT).show();
                        //Przenosi od razu do MainActivity i traktuje jako zalogowanego
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    cursor.close();
                } catch (Exception e) {
                    Error.setText("Błąd: " + e.getMessage());
                }
            });
            return insets;
        });
    }
}
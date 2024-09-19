package com.example.adresowa;

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

    private EditText Eimie;
    private EditText Enazwisko;
    private EditText Eadres;
    private EditText Ephone;
    private TextView usersTextView;
    private static final String DB_NAME = "user.db";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Eimie = findViewById(R.id.imie);
        Enazwisko = findViewById(R.id.nazwisko);
        Eadres = findViewById(R.id.adres);
        Ephone = findViewById(R.id.phone);
        usersTextView = findViewById(R.id.uzytko);
        StringBuilder usersString = new StringBuilder();

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT, adres TEXT, phone text)");

        Button rejeButton = findViewById(R.id.rej);
        Button wypisz = findViewById(R.id.wyp);


        wypisz.setOnClickListener(view -> {

            Cursor cursor = db.rawQuery("SELECT * FROM users", null);
            if (cursor.moveToFirst()) {
                do {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    String surname = cursor.getString(2);
                    String adres = cursor.getString(3);
                    String phone = cursor.getString(4);
                    //usersString.setText(); Wyczyścic stringa rozbic adres na ulica itp.
                    usersString.append("ID: ").append(id).append("Imie: ").append(name).append(", Nazwisko: ").append(surname).
                            append(", adres: ").append(adres).append(", telefon: ").append(phone).append("\n");
                } while (cursor.moveToNext());
            }
            cursor.close();
            usersTextView.setText(usersString.toString());
        });

        rejeButton.setOnClickListener(view -> {
            String name = Eimie.getText().toString();
            String surname = Enazwisko.getText().toString();
            String adres = Enazwisko.getText().toString();
            String phone = Enazwisko.getText().toString();

            if (name.isEmpty() || surname.isEmpty() || adres.isEmpty() || phone.isEmpty()){
                Toast.makeText(MainActivity.this, "WYPEŁNIJ WSZYSTKIE POLA", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cursor = db.rawQuery("SELECT * FROM users ", new String[]{});
            db.execSQL("INSERT INTO users (name,surname,adres,phone) VALUES (?,?,?,?)", new String[]{name,surname,adres,phone});
            Toast.makeText(MainActivity.this, "UTWORZONO UŻYTKOWNIKA", Toast.LENGTH_SHORT).show();
            cursor.close();
        });

    }
}
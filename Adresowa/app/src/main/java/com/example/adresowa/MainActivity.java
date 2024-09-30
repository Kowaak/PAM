package com.example.adresowa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText Eimie;
    private EditText Enazwisko;
    private EditText Eulica;
    private EditText Emiejsc;
    private EditText Enr;
    private EditText Ephone;
    //private TextView usersTextView;
    private static final String DB_NAME = "user2.db";
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Eimie = findViewById(R.id.imie);
        Enazwisko = findViewById(R.id.nazwisko);
        Enr = findViewById(R.id.nr);
        Eulica = findViewById(R.id.ulica);
        Emiejsc = findViewById(R.id.miejsc);
        Ephone = findViewById(R.id.phone);
        //usersTextView = findViewById(R.id.uzytko);
        // StringBuilder usersString = new StringBuilder();

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,surname TEXT, phone text, miejscowosc text, ulica TEXT, nr text)");
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "user_data.txt");

        Button rejeButton = findViewById(R.id.rej);
        Button wypisz = findViewById(R.id.wyp);
        /*
        wypisz.setOnClickListener(view -> {
            Cursor cursor = db.rawQuery("SELECT * FROM users", null);
            if (cursor.moveToFirst()) {
                usersString.setLength(0);
                for (int i = 0; i < cursor.getCount(); i++) {
                    String id = cursor.getString(0);
                    String name = cursor.getString(1);
                    String surname = cursor.getString(2);
                    String phone = cursor.getString(3);
                    String miejsc = cursor.getString(4);
                    String ulica = cursor.getString(5);
                    String nr = cursor.getString(6);
                    usersString.append("ID: ").append(id).append("| Imię: ").append(name).append("| Nazwisko: ").append(surname).
                            append("| Miejscowość: ").append(miejsc).append("| Ulica: ").append(ulica).append("| nr: ").append(nr)
                            .append("| telefon: ").append(phone).append("\n");
                    cursor.moveToNext();
                }
            }
            cursor.close();
            usersTextView.setText(usersString.toString());
        });
        */
        wypisz.setOnClickListener(view -> {
            Cursor cursor = db.rawQuery("SELECT * FROM users", null);
            ListView listView = findViewById(R.id.uzytko);
            listView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            listView.setDividerHeight(1);

            List<String[]> userData = new ArrayList<>();
            if (cursor.moveToFirst()) {
                for (int i = 0; i < cursor.getCount(); i++) {
                    String[] row = new String[7];
                    row[0] = cursor.getString(0);
                    row[1] = cursor.getString(1);
                    row[2] = cursor.getString(2);
                    row[3] = cursor.getString(3);
                    row[4] = cursor.getString(4);
                    row[5] = cursor.getString(5);
                    row[6] = cursor.getString(6);
                    userData.add(row);
                    cursor.moveToNext();
                }
            }
            cursor.close();
            listView.setAdapter(new ArrayAdapter<String[]>(getApplicationContext(), android.R.layout.simple_list_item_1, userData) {
                @SuppressLint("SetTextI18n")
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = view.findViewById(android.R.id.text1);
                    String[] row = userData.get(position);
                    textView.setText("ID: " + row[0] + " | Imię: " + row[1] + " | Nazwisko: " + row[2] + " | Miejscowość: " + row[3] + " | Ulica: " + row[4] + " | nr: " + row[5] + " | Telefon: " + row[6]);
                    textView.setTextColor(Color.WHITE);
                    return view;
                }
            });
        });

        rejeButton.setOnClickListener(view -> {
            String name = Eimie.getText().toString();
            String surname = Enazwisko.getText().toString();
            String phone = Ephone.getText().toString();
            String miejsc = Emiejsc.getText().toString();
            String ulica = Eulica.getText().toString();
            String nr = Enr.getText().toString();

            if (name.isEmpty() || surname.isEmpty() || nr.isEmpty() || phone.isEmpty() || miejsc.isEmpty()|| ulica.isEmpty()){
                Toast.makeText(MainActivity.this, "WYPEŁNIJ WSZYSTKIE POLA", Toast.LENGTH_SHORT).show();
                return;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8, true))) {
                writer.write("ID: " + " | Imię: " + name + " | Nazwisko: " + surname + " | Miejscowość: " + miejsc + " | Ulica: " + ulica + " | nr: " + nr + " | Telefon: " + phone + System.lineSeparator());
            } catch (IOException e) {
                Toast.makeText(MainActivity.this, "BŁĄD ZAPISU", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursor = db.rawQuery("SELECT * FROM users ", new String[]{});
            db.execSQL("INSERT INTO users (name,surname,miejscowosc,ulica,nr,phone) VALUES (?,?,?,?,?,?)", new String[]{name,surname,miejsc,ulica,nr,phone});
            Toast.makeText(MainActivity.this, "UTWORZONO UŻYTKOWNIKA", Toast.LENGTH_SHORT).show();
            cursor.close();


        });

    }
}
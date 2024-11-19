package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> itemList;

    private void showAddRecordDialog() {
        Global app = (Global) getApplication();
        long userID = app.getUserID();
        //Tworzenie okna dialogowego
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dodaj haslo");
        View dialogView = getLayoutInflater().inflate(R.layout.fab_add, null);
        builder.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextLogin = dialogView.findViewById(R.id.editTextLogin);
        EditText editTextValue = dialogView.findViewById(R.id.editTextValue);

        builder.setPositiveButton("Dodaj", (dialog, which) -> {
            String login = editTextLogin.getText().toString().trim();
            String nazwa = editTextName.getText().toString().trim();
            String haslo = editTextValue.getText().toString().trim();
            SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

            ContentValues values = new ContentValues();
            values.put("Login", login);
            values.put("Nazwa", nazwa);
            values.put("Haslo", haslo);
            values.put("userID", userID);

            long newRowId = db.insert("hasla", null, values);
            if (newRowId == -1) {
                Toast.makeText(this, "Bład", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Dodano haslo", Toast.LENGTH_SHORT).show();
            }
            db.close();
        });
        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showRemoveRecordDialog() {
        Global app = (Global) getApplication();
        long userID = app.getUserID();
        //Tworzenie okna dialogowego
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Usuń haslo");
        View dialogView = getLayoutInflater().inflate(R.layout.fab_remove, null);
        builder.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.editTextName);

        builder.setPositiveButton("Usuń", (dialog, which) -> {
            String Nazwa = editTextName.getText().toString().trim();

            SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

            int rowsAffected = db.delete("hasla", "nazwa = ? AND userID = ?", new String[]{Nazwa,String.valueOf(userID)});
            if (rowsAffected > 0) {
                Toast.makeText(this, "Usunięto", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Nie znaleziono", Toast.LENGTH_SHORT).show();
            }
            db.close();
        });
        builder.setNegativeButton("Anuluj", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    @Override
    public void onBackPressed() { //Jest depreacated ale działa i jest 10 razy krótsze i prostsze niż inne opcje
        new AlertDialog.Builder(this).setTitle("Wyloguj").setMessage("Jesteś pewien?").setPositiveButton(android.R.string.yes, (dialog, which) -> {
            Global app = (Global) getApplication();
            app.setUserID(-1);
            super.onBackPressed();
        }).setNegativeButton(android.R.string.no, null).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Cały recycler view z dokumentacji
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new RecyclerView.Adapter<>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                textView.setText(itemList.get(position));
            }
            @Override
            public int getItemCount() {
                return itemList.size();
            }
        });

        Global app = (Global) getApplication();

        //Wbudowane
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS hasla (id INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER, nazwa TEXT, Login TEXT, Haslo TEXT, FOREIGN KEY(userID) REFERENCES users(id))");

            itemList = new ArrayList<>();

            EditText search_bar = findViewById(R.id.search_bar);
            Cursor cursor = db.rawQuery("SELECT id, userID, nazwa, Login, Haslo FROM hasla", null);

            ImageButton fabAdd = findViewById(R.id.fabAdd);
            ImageButton fabRemove = findViewById(R.id.fabRemove);
            fabAdd.setOnClickListener(v1->showAddRecordDialog());
            fabRemove.setOnClickListener(v2->showRemoveRecordDialog());

            int nazwaIndex = cursor.getColumnIndex("nazwa");
            int loginIndex = cursor.getColumnIndex("Login");
            int hasloIndex = cursor.getColumnIndex("Haslo");

            if (cursor.moveToFirst()) {
                do {
                    String nazwa = cursor.getString(nazwaIndex);
                    String login = cursor.getString(loginIndex);
                    String haslo = cursor.getString(hasloIndex);
                    String itemString = "Nazwa: " + nazwa + "\nLogin: " + login + "\nHasło: " + haslo;
                    itemList.add(itemString);
                    itemList.add("------------------------------------------");
                } while (cursor.moveToNext());
            }
            cursor.close();

            ImageButton search = findViewById(R.id.search);
            search.setOnClickListener(v1 -> {
                Cursor search_cursor = db.rawQuery("SELECT id, userID, nazwa, Login, Haslo FROM hasla WHERE nazwa LIKE ?",new String[]{"%"+search_bar.getText().toString()+"%"});
                if (search_cursor.moveToFirst()) {
                    itemList.clear();
                    do {
                        String nazwa = search_cursor.getString(nazwaIndex);
                        String login = search_cursor.getString(loginIndex);
                        String haslo = search_cursor.getString(hasloIndex);
                        String itemString = "Nazwa: " + nazwa + "\nLogin: " + login + "\nHasło: " + haslo;
                        itemList.add(itemString);
                        itemList.add("------------------------------------------");
                    } while (search_cursor.moveToNext());
                }
                recyclerView.requestLayout();
                recyclerView.invalidate();
                recyclerView.getAdapter().notifyDataSetChanged();
                search_cursor.close();
            });

            ImageButton wyloguj = findViewById(R.id.wyloguj);
            wyloguj.setOnClickListener(v1 -> {
                app.setUserID(-1);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            });

            //nie wiem czy potrzebne, czasem działa, czasem nie :/
            ImageButton refreshButton = findViewById(R.id.odswiez);
            refreshButton.setOnClickListener(v1 -> recreate());

            return insets;
        });
    }
}
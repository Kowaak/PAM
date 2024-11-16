package com.example.hasla;

import static com.example.hasla.Global.DB_NAME;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Odczytanie zmiennej globalnej ID
        Global app = (Global) getApplication();
        long userID = app.getUserID();

        SQLiteDatabase db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS hasla (id INTEGER PRIMARY KEY AUTOINCREMENT, userID INTEGER, Login TEXT UNIQUE, Haslo TEXT, FOREIGN KEY(userID) REFERENCES users(id))");
        Cursor cursor = db.rawQuery("SELECT Login,Haslo FROM users WHERE id = ?", new String[]{String.valueOf(userID)});

        //Wbudowane
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            // Initialize the RecyclerView
            recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Initialize the item list
            itemList = new ArrayList<>(); // Make sure to initialize the ArrayList
            itemList.add("Item 1");
            itemList.add("Item 2");
            itemList.add("Item 3");
            itemList.add("Item 4");
            itemList.add("Item 5");

            // Set up the adapter
            recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext())
                            .inflate(android.R.layout.simple_list_item_1, parent, false);
                    return new RecyclerView.ViewHolder(view) {};
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
            return insets;
        });
    }
}
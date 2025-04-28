package com.example.notatki;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            TextView text = findViewById(R.id.text);
            ListView list = findViewById(R.id.list);
            ArrayAdapter<String> adapter;
            Button dodaj = findViewById(R.id.dodaj);


            String notatki[] = {"Zakupy", "do zrobienia", "weekend"};

            ArrayList<String> dane = new ArrayList();
            dane.addAll(Arrays.asList(notatki) );

            adapter = new ArrayAdapter(this, R.layout.list, dane);
            list.setAdapter(adapter);

            dodaj.setOnClickListener(v1 -> {
                String inputText = text.getText().toString().trim();
                if (!inputText.isEmpty()) {
                    dane.add(inputText);
                    adapter.notifyDataSetChanged();
                    text.setText("");
                }
            });
            
            return insets;
        });
    }
}
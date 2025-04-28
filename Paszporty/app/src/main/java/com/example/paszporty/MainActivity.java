package com.example.paszporty;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            EditText e1 = findViewById(R.id.imie);
            EditText e2 = findViewById(R.id.nazwisko);
            EditText e3 = findViewById(R.id.kolor);

            RadioButton r1 = findViewById(R.id.r1);
            RadioButton r2 = findViewById(R.id.r2);

            ImageView i1 = findViewById(R.id.imageView1);
            ImageView i2 = findViewById(R.id.imageView2);

            Spinner dropdown = findViewById(R.id.spinner1);

            String[] items = new String[]{"Nowak", "Kowalski", "Kowalska"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);

            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selectedItem = parent.getItemAtPosition(position).toString();
                    if (selectedItem.equals("Nowak")){
                        e1.setText("Marcin");
                        e2.setText("Nowak");
                        e3.setText("Czarne");
                        r2.setChecked(true);
                        i1.setImageResource(R.drawable.m1);
                        i2.setImageResource(R.drawable.od1);
                    }
                    if (selectedItem.equals("Kowalski")){
                        e1.setText("Mateusz");
                        e2.setText("Kowalski");
                        e3.setText("Niebieskie");
                        r2.setChecked(true);
                        i1.setImageResource(R.drawable.m2);
                        i2.setImageResource(R.drawable.od2);
                    }
                    if (selectedItem.equals("Kowalska")){
                        e1.setText("Martyna");
                        e2.setText("Kowalska");
                        e3.setText("Piwne");
                        r1.setChecked(true);
                        i1.setImageResource(R.drawable.m3);
                        i2.setImageResource(R.drawable.od3);
                    }
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {}
            });
            return insets;
        });
    }
}
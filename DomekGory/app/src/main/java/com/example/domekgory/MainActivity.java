package com.example.domekgory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int polubienia = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Button dodaj = findViewById(R.id.polub);
            Button usun = findViewById(R.id.usun);
            TextView licznik = findViewById(R.id.licznik);

            usun.setOnClickListener( v1 -> {
                if(polubienia > 0) polubienia--;
                licznik.setText(polubienia+" polubień");
            });

            dodaj.setOnClickListener( v1 -> {
                polubienia++;
                licznik.setText(polubienia+" polubień");
            });

            return insets;
        });
    }
}
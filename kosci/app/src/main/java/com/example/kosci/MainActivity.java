package com.example.kosci;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Random rand = new Random();
            int[] tab = new int[6];
            Button losuj = findViewById(R.id.losuj);

            ImageView ko1 = findViewById(R.id.k1);
            ImageView ko2 = findViewById(R.id.k2);
            ImageView ko3 = findViewById(R.id.k3);
            ImageView ko4 = findViewById(R.id.k4);
            ImageView ko5 = findViewById(R.id.k5);
            ImageView ko6 = findViewById(R.id.k6);
            TextView wynik = findViewById(R.id.wynik);

            losuj.setOnClickListener(v1 -> {
                int suma = 0;
                int[] drawables = {R.drawable.k1, R.drawable.k2, R.drawable.k3, R.drawable.k4, R.drawable.k5, R.drawable.k6};
                for (int i = 0; i < 6; i++) {
                    tab[i] = rand.nextInt(6);
                    tab[i] = tab[i] + 1;
                    suma += tab[i];
                }

                ko1.setImageResource(drawables[tab[0] - 1]);
                ko2.setImageResource(drawables[tab[1] - 1]);
                ko3.setImageResource(drawables[tab[2] - 1]);
                ko4.setImageResource(drawables[tab[3] - 1]);
                ko5.setImageResource(drawables[tab[4] - 1]);
                ko6.setImageResource(drawables[tab[5] - 1]);
                wynik.setText("Wynik losowania: " + suma);
            });
            //https://pl.wikipedia.org/wiki/Ko%C5%9Bci_(gra)
            return insets;
        });
    }
}
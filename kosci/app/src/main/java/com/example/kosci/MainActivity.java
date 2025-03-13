package com.example.kosci;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            Random rand = new Random();

            int rand_int1 = rand.nextInt(5);
            int rand_int2 = rand.nextInt(5);
            int rand_int3 = rand.nextInt(5);
            int rand_int4 = rand.nextInt(5);
            int rand_int5 = rand.nextInt(5);
            int rand_int6 = rand.nextInt(5);


            return insets;
        });
    }
}
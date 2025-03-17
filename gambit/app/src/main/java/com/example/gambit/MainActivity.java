package com.example.gambit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final boolean[] isDefaultStyle = {true};
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            ImageView a8 = findViewById(R.id.a8);
            ImageView b8 = findViewById(R.id.b8);
            ImageView c8 = findViewById(R.id.c8);
            ImageView d8 = findViewById(R.id.d8);
            ImageView e8 = findViewById(R.id.e8);
            ImageView f8 = findViewById(R.id.f8);
            ImageView g8 = findViewById(R.id.g8);
            ImageView h8 = findViewById(R.id.h8);

            ImageView a7 = findViewById(R.id.a7);
            ImageView b7 = findViewById(R.id.b7);
            ImageView c7 = findViewById(R.id.c7);
            ImageView d7 = findViewById(R.id.d7);
            ImageView e7 = findViewById(R.id.e7);
            ImageView f7 = findViewById(R.id.f7);
            ImageView g7 = findViewById(R.id.g7);
            ImageView h7 = findViewById(R.id.h7);

            ImageView a2 = findViewById(R.id.a2);
            ImageView b2 = findViewById(R.id.b2);
            ImageView c2 = findViewById(R.id.c2);
            ImageView d2 = findViewById(R.id.d2);
            ImageView e2 = findViewById(R.id.e2);
            ImageView f2 = findViewById(R.id.f2);
            ImageView g2 = findViewById(R.id.g2);
            ImageView h2 = findViewById(R.id.h2);

            ImageView a1 = findViewById(R.id.a1);
            ImageView b1 = findViewById(R.id.b1);
            ImageView c1 = findViewById(R.id.c1);
            ImageView d1 = findViewById(R.id.d1);
            ImageView e1 = findViewById(R.id.e1);
            ImageView f1 = findViewById(R.id.f1);
            ImageView g1 = findViewById(R.id.g1);
            ImageView h1 = findViewById(R.id.h1);

            ImageView a6 = findViewById(R.id.a6);
            ImageView b6 = findViewById(R.id.b6);
            ImageView c6 = findViewById(R.id.c6);
            ImageView d6 = findViewById(R.id.d6);
            ImageView e6 = findViewById(R.id.e6);
            ImageView f6 = findViewById(R.id.f6);
            ImageView g6 = findViewById(R.id.g6);
            ImageView h6 = findViewById(R.id.h6);

            ImageView a5 = findViewById(R.id.a5);
            ImageView b5 = findViewById(R.id.b5);
            ImageView c5 = findViewById(R.id.c5);
            ImageView d5 = findViewById(R.id.d5);
            ImageView e5 = findViewById(R.id.e5);
            ImageView f5 = findViewById(R.id.f5);
            ImageView g5 = findViewById(R.id.g5);
            ImageView h5 = findViewById(R.id.h5);

            ImageView a3 = findViewById(R.id.a3);
            ImageView b3 = findViewById(R.id.b3);
            ImageView c3 = findViewById(R.id.c3);
            ImageView d3 = findViewById(R.id.d3);
            ImageView e3 = findViewById(R.id.e3);
            ImageView f3 = findViewById(R.id.f3);
            ImageView g3 = findViewById(R.id.g3);
            ImageView h3 = findViewById(R.id.h3);

            ImageView a4 = findViewById(R.id.a4);
            ImageView b4 = findViewById(R.id.b4);
            ImageView c4 = findViewById(R.id.c4);
            ImageView d4 = findViewById(R.id.d4);
            ImageView e4 = findViewById(R.id.e4);
            ImageView f4 = findViewById(R.id.f4);
            ImageView g4 = findViewById(R.id.g4);
            ImageView h4 = findViewById(R.id.h4);
            Handler handler = new Handler();
            Button ruchy = findViewById(R.id.ruchy);
            ruchy.setOnClickListener(v1 -> {
                if (isDefaultStyle[0]) {
                    Runnable szewczyk = new Runnable() {
                        int iteration = 0;
                        @Override
                        public void run() {
                            switch (iteration) {
                                case 0:
                                    applyStyle(e2, R.style.Szachownica);
                                    applyStyle(e4, R.style.Szachownica_wp);
                                    break;
                                case 1:
                                    applyStyle(e7, R.style.Szachownica_Kolor);
                                    applyStyle(e5, R.style.Szachownica_Kolor_bp);
                                    break;
                                case 2:
                                    applyStyle(f1, R.style.Szachownica);
                                    applyStyle(c4, R.style.Szachownica_wb);
                                    break;
                                case 3:
                                    applyStyle(b8, R.style.Szachownica_Kolor);
                                    applyStyle(c6, R.style.Szachownica_bn);
                                    break;
                                case 4:
                                    applyStyle(d1, R.style.Szachownica);
                                    applyStyle(f5, R.style.Szachownica_wq);
                                    break;
                                case 5:
                                    applyStyle(f8, R.style.Szachownica_Kolor);
                                    applyStyle(c5, R.style.Szachownica_Kolor_bb);
                                    break;
                                case 6:
                                    applyStyle(f5, R.style.Szachownica);
                                    applyStyle(f7, R.style.Szachownica_wq);
                                    break;
                                default:
                                    return;
                            }
                            iteration++;
                            handler.postDelayed(this, 1000);
                        }
                    };
                    handler.post(szewczyk);
                }  else {
                    applyStyle(a8, R.style.Szachownica_br);
                    applyStyle(b8, R.style.Szachownica_Kolor_bn);
                    applyStyle(c8, R.style.Szachownica_bb);
                    applyStyle(d8, R.style.Szachownica_Kolor_bq);
                    applyStyle(e8, R.style.Szachownica_bk);
                    applyStyle(f8, R.style.Szachownica_Kolor_bb);
                    applyStyle(g8, R.style.Szachownica_bn);
                    applyStyle(h8, R.style.Szachownica_Kolor_br);

                    applyStyle(a7, R.style.Szachownica_Kolor_bp);
                    applyStyle(b7, R.style.Szachownica_bp);
                    applyStyle(c7, R.style.Szachownica_Kolor_bp);
                    applyStyle(d7, R.style.Szachownica_bp);
                    applyStyle(e7, R.style.Szachownica_Kolor_bp);
                    applyStyle(f7, R.style.Szachownica_bp);
                    applyStyle(g7, R.style.Szachownica_Kolor_bp);
                    applyStyle(h7, R.style.Szachownica_bp);

                    applyStyle(a2, R.style.Szachownica_wp);
                    applyStyle(b2, R.style.Szachownica_Kolor_wp);
                    applyStyle(c2, R.style.Szachownica_wp);
                    applyStyle(d2, R.style.Szachownica_Kolor_wp);
                    applyStyle(e2, R.style.Szachownica_wp);
                    applyStyle(f2, R.style.Szachownica_Kolor_wp);
                    applyStyle(g2, R.style.Szachownica_wp);
                    applyStyle(h2, R.style.Szachownica_Kolor_wp);

                    applyStyle(a1, R.style.Szachownica_Kolor_wr);
                    applyStyle(b1, R.style.Szachownica_wn);
                    applyStyle(c1, R.style.Szachownica_Kolor_wb);
                    applyStyle(d1, R.style.Szachownica_wq);
                    applyStyle(e1, R.style.Szachownica_Kolor_wk);
                    applyStyle(f1, R.style.Szachownica_wb);
                    applyStyle(g1, R.style.Szachownica_Kolor_wn);
                    applyStyle(h1, R.style.Szachownica_wr);

                    applyStyle(a5, R.style.Szachownica_Kolor);
                    applyStyle(b5, R.style.Szachownica);
                    applyStyle(c5, R.style.Szachownica_Kolor);
                    applyStyle(d5, R.style.Szachownica);
                    applyStyle(e5, R.style.Szachownica_Kolor);
                    applyStyle(f5, R.style.Szachownica);
                    applyStyle(g5, R.style.Szachownica_Kolor);
                    applyStyle(h5, R.style.Szachownica);

                    applyStyle(a6, R.style.Szachownica);
                    applyStyle(b6, R.style.Szachownica_Kolor);
                    applyStyle(c6, R.style.Szachownica);
                    applyStyle(d6, R.style.Szachownica_Kolor);
                    applyStyle(e6, R.style.Szachownica);
                    applyStyle(f6, R.style.Szachownica_Kolor);
                    applyStyle(g6, R.style.Szachownica);
                    applyStyle(h6, R.style.Szachownica_Kolor);

                    applyStyle(a4, R.style.Szachownica);
                    applyStyle(b4, R.style.Szachownica_Kolor);
                    applyStyle(c4, R.style.Szachownica);
                    applyStyle(d4, R.style.Szachownica_Kolor);
                    applyStyle(e4, R.style.Szachownica);
                    applyStyle(f4, R.style.Szachownica_Kolor);
                    applyStyle(g4, R.style.Szachownica);
                    applyStyle(h4, R.style.Szachownica_Kolor);

                    applyStyle(a3, R.style.Szachownica_Kolor);
                    applyStyle(b3, R.style.Szachownica);
                    applyStyle(c3, R.style.Szachownica_Kolor);
                    applyStyle(d3, R.style.Szachownica);
                    applyStyle(e3, R.style.Szachownica_Kolor);
                    applyStyle(f3, R.style.Szachownica);
                    applyStyle(g3, R.style.Szachownica_Kolor);
                    applyStyle(h3, R.style.Szachownica);

                }
                isDefaultStyle[0] = !isDefaultStyle[0];
            });
            Button figury = findViewById(R.id.figury);
            figury.setOnClickListener(v1 -> {
                if (isDefaultStyle[0]) {
                    applyStyle(a8, R.style.Szachownica_br);
                    applyStyle(b8, R.style.Szachownica_Kolor_bn);
                    applyStyle(c8, R.style.Szachownica_bb);
                    applyStyle(d8, R.style.Szachownica_Kolor_bq);
                    applyStyle(e8, R.style.Szachownica_bk);
                    applyStyle(f8, R.style.Szachownica_Kolor_bb);
                    applyStyle(g8, R.style.Szachownica_bn);
                    applyStyle(h8, R.style.Szachownica_Kolor_br);

                    applyStyle(a7, R.style.Szachownica_Kolor_bp);
                    applyStyle(b7, R.style.Szachownica_bp);
                    applyStyle(c7, R.style.Szachownica_Kolor_bp);
                    applyStyle(d7, R.style.Szachownica_bp);
                    applyStyle(e7, R.style.Szachownica_Kolor_bp);
                    applyStyle(f7, R.style.Szachownica_bp);
                    applyStyle(g7, R.style.Szachownica_Kolor_bp);
                    applyStyle(h7, R.style.Szachownica_bp);

                    applyStyle(a2, R.style.Szachownica_wp);
                    applyStyle(b2, R.style.Szachownica_Kolor_wp);
                    applyStyle(c2, R.style.Szachownica_wp);
                    applyStyle(d2, R.style.Szachownica_Kolor_wp);
                    applyStyle(e2, R.style.Szachownica_wp);
                    applyStyle(f2, R.style.Szachownica_Kolor_wp);
                    applyStyle(g2, R.style.Szachownica_wp);
                    applyStyle(h2, R.style.Szachownica_Kolor_wp);

                    applyStyle(a1, R.style.Szachownica_Kolor_wr);
                    applyStyle(b1, R.style.Szachownica_wn);
                    applyStyle(c1, R.style.Szachownica_Kolor_wb);
                    applyStyle(d1, R.style.Szachownica_wq);
                    applyStyle(e1, R.style.Szachownica_Kolor_wk);
                    applyStyle(f1, R.style.Szachownica_wb);
                    applyStyle(g1, R.style.Szachownica_Kolor_wn);
                    applyStyle(h1, R.style.Szachownica_wr);

                    applyStyle(a5, R.style.Szachownica_Kolor);
                    applyStyle(b5, R.style.Szachownica);
                    applyStyle(c5, R.style.Szachownica_Kolor);
                    applyStyle(d5, R.style.Szachownica);
                    applyStyle(e5, R.style.Szachownica_Kolor);
                    applyStyle(f5, R.style.Szachownica);
                    applyStyle(g5, R.style.Szachownica_Kolor);
                    applyStyle(h5, R.style.Szachownica);

                    applyStyle(a6, R.style.Szachownica);
                    applyStyle(b6, R.style.Szachownica_Kolor);
                    applyStyle(c6, R.style.Szachownica);
                    applyStyle(d6, R.style.Szachownica_Kolor);
                    applyStyle(e6, R.style.Szachownica);
                    applyStyle(f6, R.style.Szachownica_Kolor);
                    applyStyle(g6, R.style.Szachownica);
                    applyStyle(h6, R.style.Szachownica_Kolor);

                    applyStyle(a4, R.style.Szachownica);
                    applyStyle(b4, R.style.Szachownica_Kolor);
                    applyStyle(c4, R.style.Szachownica);
                    applyStyle(d4, R.style.Szachownica_Kolor);
                    applyStyle(e4, R.style.Szachownica);
                    applyStyle(f4, R.style.Szachownica_Kolor);
                    applyStyle(g4, R.style.Szachownica);
                    applyStyle(h4, R.style.Szachownica_Kolor);

                    applyStyle(a3, R.style.Szachownica_Kolor);
                    applyStyle(b3, R.style.Szachownica);
                    applyStyle(c3, R.style.Szachownica_Kolor);
                    applyStyle(d3, R.style.Szachownica);
                    applyStyle(e3, R.style.Szachownica_Kolor);
                    applyStyle(f3, R.style.Szachownica);
                    applyStyle(g3, R.style.Szachownica_Kolor);
                    applyStyle(h3, R.style.Szachownica);
                } else {
                    applyStyle(a8, R.style.Szachownica);
                    applyStyle(b8, R.style.Szachownica_Kolor);
                    applyStyle(c8, R.style.Szachownica);
                    applyStyle(d8, R.style.Szachownica_Kolor);
                    applyStyle(e8, R.style.Szachownica);
                    applyStyle(f8, R.style.Szachownica_Kolor);
                    applyStyle(g8, R.style.Szachownica);
                    applyStyle(h8, R.style.Szachownica_Kolor);

                    applyStyle(a7, R.style.Szachownica_Kolor);
                    applyStyle(b7, R.style.Szachownica);
                    applyStyle(c7, R.style.Szachownica_Kolor);
                    applyStyle(d7, R.style.Szachownica);
                    applyStyle(e7, R.style.Szachownica_Kolor);
                    applyStyle(f7, R.style.Szachownica);
                    applyStyle(g7, R.style.Szachownica_Kolor);
                    applyStyle(h7, R.style.Szachownica);

                    applyStyle(a2, R.style.Szachownica);
                    applyStyle(b2, R.style.Szachownica_Kolor);
                    applyStyle(c2, R.style.Szachownica);
                    applyStyle(d2, R.style.Szachownica_Kolor);
                    applyStyle(e2, R.style.Szachownica);
                    applyStyle(f2, R.style.Szachownica_Kolor);
                    applyStyle(g2, R.style.Szachownica);
                    applyStyle(h2, R.style.Szachownica_Kolor);

                    applyStyle(a1, R.style.Szachownica_Kolor);
                    applyStyle(b1, R.style.Szachownica);
                    applyStyle(c1, R.style.Szachownica_Kolor);
                    applyStyle(d1, R.style.Szachownica);
                    applyStyle(e1, R.style.Szachownica_Kolor);
                    applyStyle(f1, R.style.Szachownica);
                    applyStyle(g1, R.style.Szachownica_Kolor);
                    applyStyle(h1, R.style.Szachownica);

                    applyStyle(a6, R.style.Szachownica);
                    applyStyle(b6, R.style.Szachownica_Kolor);
                    applyStyle(c6, R.style.Szachownica);
                    applyStyle(d6, R.style.Szachownica_Kolor);
                    applyStyle(e6, R.style.Szachownica);
                    applyStyle(f6, R.style.Szachownica_Kolor);
                    applyStyle(g6, R.style.Szachownica);
                    applyStyle(h6, R.style.Szachownica_Kolor);

                    applyStyle(a5, R.style.Szachownica_Kolor);
                    applyStyle(b5, R.style.Szachownica);
                    applyStyle(c5, R.style.Szachownica_Kolor);
                    applyStyle(d5, R.style.Szachownica);
                    applyStyle(e5, R.style.Szachownica_Kolor);
                    applyStyle(f5, R.style.Szachownica);
                    applyStyle(g5, R.style.Szachownica_Kolor);
                    applyStyle(h5, R.style.Szachownica);

                    applyStyle(a4, R.style.Szachownica);
                    applyStyle(b4, R.style.Szachownica_Kolor);
                    applyStyle(c4, R.style.Szachownica);
                    applyStyle(d4, R.style.Szachownica_Kolor);
                    applyStyle(e4, R.style.Szachownica);
                    applyStyle(f4, R.style.Szachownica_Kolor);
                    applyStyle(g4, R.style.Szachownica);
                    applyStyle(h4, R.style.Szachownica_Kolor);

                    applyStyle(a3, R.style.Szachownica_Kolor);
                    applyStyle(b3, R.style.Szachownica);
                    applyStyle(c3, R.style.Szachownica_Kolor);
                    applyStyle(d3, R.style.Szachownica);
                    applyStyle(e3, R.style.Szachownica_Kolor);
                    applyStyle(f3, R.style.Szachownica);
                    applyStyle(g3, R.style.Szachownica_Kolor);
                    applyStyle(h3, R.style.Szachownica);
                }
                isDefaultStyle[0] = !isDefaultStyle[0];
            });
            return insets;
        });
    }
    private void applyStyle(View view, int styleResId) {
        Context context = new ContextThemeWrapper(view.getContext(), styleResId);
        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.foreground, android.R.attr.padding});

        if (a.hasValue(0)) {
            Drawable foregroundDrawable = a.getDrawable(0);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setForeground(foregroundDrawable);
            }
        }else {
            view.setForeground(null);
        }
        int padding = a.getDimensionPixelSize(1, 0);
        view.setPadding(padding, padding, padding, padding);
        a.recycle();
    }
}
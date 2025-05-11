package com.example.bandyta;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    private Button losuj;
    private Button showPrizeTableBtn;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mediaPlayer = MediaPlayer.create(this, R.raw.rat_beat);
        mediaPlayer.setLooping(true);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            losuj = findViewById(R.id.play);
            losuj.setOnClickListener(v1 -> {losuj.setEnabled(false);losowanie(0);});

            showPrizeTableBtn = findViewById(R.id.showPrizeTableBtn);
            showPrizeTableBtn.setOnClickListener(v2 -> showPrizeTableDialog());
            return insets;
        });
    }
    TextView budzet_text;
    EditText stawka_text;
    AtomicInteger kol1 = new AtomicInteger();
    AtomicInteger kol2 = new AtomicInteger();
    AtomicInteger kol3 = new AtomicInteger();
    String stawkaString = "";
    AtomicInteger budzet = new AtomicInteger(1000);
    Random random = new Random();
    private Handler handler = new Handler();
    int stawka,delay = 50;
    //Kocham dokumentacje
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
    private void applyStyle(View view, int styleResId) {
        Context context = new ContextThemeWrapper(view.getContext(), styleResId);
        TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.background});
        if (a.hasValue(0)) {
            Drawable backgroundDrawable = a.getDrawable(0);
            view.setBackground(backgroundDrawable);
        } else {
            view.setBackground(null);
        }
        a.recycle();
    }
    private int getStyleForNumber(int number) {
        switch (number) {
            case 2:
                return R.style.Slot_parsnip;
            case 3:
                return R.style.Slot_large_milk;
            case 4:
                return R.style.Slot_rainbow_trout;
            case 5:
                return R.style.Slot_nautilus_shell;
            case 6:
                return R.style.Slot_melon;
            case 7:
                return R.style.Slot_diamond;
            case 8:
                return R.style.Slot_stardrop;
            default:
                return R.style.Slot_cherry;
        }
    }
    private void losowanie(final int iteracja) {
        ImageView k1 = findViewById(R.id.k1);
        ImageView k2 = findViewById(R.id.k2);
        ImageView k3 = findViewById(R.id.k3);

        stawka_text = findViewById(R.id.stawka_edit);
        stawkaString = stawka_text.getText().toString();
        if (!stawkaString.isEmpty()) {
            stawka = Integer.parseInt(stawkaString);
        } else { stawka = 50; }
        if (stawka >= budzet.get()) {
            stawka = budzet.get();
            stawka_text.setText(String.valueOf(stawka));
        }

        if (iteracja < 10) {
            delay+=50;
            kol1.set(random.nextInt(8) + 1);
            kol2.set(random.nextInt(8) + 1);
            kol3.set(random.nextInt(8) + 1);
            applyStyle(k1, getStyleForNumber(kol1.get()));
            applyStyle(k2, getStyleForNumber(kol2.get()));
            applyStyle(k3, getStyleForNumber(kol3.get()));
            handler.postDelayed(() -> losowanie(iteracja + 1), delay);
        }else{ delay = 50; wynik(); }
    }
    private void wynik(){
        losuj = findViewById(R.id.play);
        budzet_text = findViewById(R.id.textView2);

        if (kol1.get() == 1 || kol2.get() == 1 || kol3.get() == 1) {
            // 1 wiśnia * 2
            budzet.addAndGet(stawka * 2);
            budzet_text.setText(budzet.get() +"$");
        } else if ((kol1.get() == 1 && kol2.get() == 1) || (kol1.get() == 1 && kol3.get() == 1) || (kol2.get() == 1 && kol3.get() == 1)) {
            // 2 wiśnie * 3
            budzet.addAndGet(stawka * 3);
        } else if (kol1.get() == 1 && kol2.get() == 1 && kol3.get() == 1) {
            // 3 wiśnie * 500
            budzet.addAndGet(stawka * 500);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 2 && kol2.get() == 2 && kol3.get() == 2) {
            // 3 parsnipy * 5
            budzet.addAndGet(stawka * 5);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 3 && kol2.get() == 3 && kol3.get() == 3) {
            // 3 mleka * 30
            budzet.addAndGet(stawka * 30);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 4 && kol2.get() == 4 && kol3.get() == 4) {
            // 3 ryby * 80
            budzet.addAndGet(stawka * 80);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 5 && kol2.get() == 5 && kol3.get() == 5) {
            // 3 muszle * 120
            budzet.addAndGet(stawka * 120);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 6 && kol2.get() == 6 && kol3.get() == 6) {
            // 3 melony * 200
            budzet.addAndGet(stawka * 200);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 7 && kol2.get() == 7 && kol3.get() == 7) {
            // 3 diamenty * 1000
            budzet.addAndGet(stawka * 1000);
            budzet_text.setText(budzet.get() +"$");
        } else if (kol1.get() == 8 && kol2.get() == 8 && kol3.get() == 8) {
            // 3 stardropy * 2500
            budzet.addAndGet(stawka * 2500);
            budzet_text.setText(budzet.get() +"$");
        } else {
            //Przegrana
            budzet.addAndGet(-stawka);
            //Cały budżet stracony
            if (budzet.get() <= 0) {
                mediaPlayer.pause();
                mediaPlayer = MediaPlayer.create(this, R.raw.bankrut);
                mediaPlayer.start();

                GifImageView gif = findViewById(R.id.gifImageView);
                gif.setImageResource(R.drawable.przegrana);
                budzet_text.setText("Przegrałeś!");

                new Handler().postDelayed(() -> {
                    finish();
                    System.exit(0);
                }, 5100);
            }else budzet_text.setText(budzet.get() +"$");
        }
        losuj.setEnabled(true);
    }
    private void showPrizeTableDialog() {
        //Nienawidze dokumentacji
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.table);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = (int) (getResources().getDisplayMetrics().widthPixels * 0.9);
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        Button closeBtn = dialog.findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }
}
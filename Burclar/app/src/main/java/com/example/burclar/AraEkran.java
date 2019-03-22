package com.example.burclar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AraEkran extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ara_ekran);

        TextView isim = (TextView) findViewById(R.id.isim);
        ImageView resim = (ImageView) findViewById(R.id.imageView);
        Button anasayfa = (Button) findViewById(R.id.button2);
        Button listele = (Button) findViewById(R.id.button3);

        // gönderilen mesajı almak için
        Intent intent = getIntent();
        String mesaj = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        isim.setText(mesaj);   // textview'de gösteriyoruz.

        Intent intent2 = getIntent();
        String parola = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);


        anasayfa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent anasayfayaGecis = new Intent(AraEkran.this, MainActivity.class);
                startActivity(anasayfayaGecis);
            }
        });

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listeyeGecis = new Intent(AraEkran.this, BurcListe.class);
                startActivity(listeyeGecis);
            }
        });
    }
}

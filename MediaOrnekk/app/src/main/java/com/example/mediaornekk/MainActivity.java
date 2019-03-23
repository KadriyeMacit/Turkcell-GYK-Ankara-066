package com.example.mediaornekk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Bu sayfada sadece, xml dosyamızdaki butonları tanımlayıp
    // butonlara tıklama özelliği vererek
    // sayfa geçişlerini yapıyoruz.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button foto = (Button) findViewById(R.id.open_photo_and_video);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Kamera.class);
                startActivity(intent);
            }
        });



        Button sms = (Button) findViewById(R.id.send_sms);

        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SMS.class);
                startActivity(intent);
            }
        });


        Button arama = (Button) findViewById(R.id.dial_call);

        arama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Arama.class);
                startActivity(intent);
            }
        });


        Button ses = (Button) findViewById(R.id.open_voice);

        ses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ses.class);
                startActivity(intent);
            }
        });


        Button harita = (Button) findViewById(R.id.open_map);

        harita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Harita.class);
                startActivity(intent);
            }
        });


        Button internet = (Button) findViewById(R.id.open_web);

        internet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Internet.class);
                startActivity(intent);
            }
        });

    }
}

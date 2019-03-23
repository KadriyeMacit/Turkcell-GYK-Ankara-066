package com.example.mediaornekk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Arama extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arama);

        // xml dosyasında eklediklerimizi tanımlıyoruz.
        final EditText ara_num = (EditText) findViewById(R.id.ara_numara);
        Button ara_buton = (Button) findViewById(R.id.ara_buton);

        // ara butonuna tıkladğımızda yapılacakları yazıyoruz.
        ara_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // editText'ten string şeklinde girilen veriyi alıyoruz.
                String aranilacak_numara = ara_num.getText().toString();

                // intent veri aktarımı yaptığımız için
                // arama işleminde ACTION_DIAL Intent sınıfından servisini tanımlıyoruz
                // ve girilen numaranın yani editTexti tanımladığımız değişkeni ekliyoruz.
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + ara_num));

                // girilen veri kullanılabilir olduğu sürece işlemi başlat.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });


    }
}

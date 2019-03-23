package com.example.mediaornekk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SMS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);


        // xml dosyasına eklediklerimizi tanımlıyoruz.
        final EditText mesaj = (EditText) findViewById(R.id.mesaj);
        final EditText numara = (EditText) findViewById(R.id.numara);
        Button gonder = (Button) findViewById(R.id.gonder);

        gonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // editText'ten string şeklinde girilen verileri alıyoruz.
                String message = mesaj.getText().toString();
                String phoneNumber = numara.getText().toString();


                // kaynak göstereceğimiz yani bağlantıyı yapacağımız
                // numarayı ekliyoruz.
                Uri uri = Uri.parse("smsto:" + phoneNumber);

                // intent veri aktarımı yaptığımız için
                // arama işleminde ACTION_DIAL Intent sınıfından servisini tanımlıyoruz
                // ve girilen numaranın yani editTexti tanımladığımız değişkeni ekliyoruz.
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", message);
                startActivity(intent);

            }
        });


    }
}

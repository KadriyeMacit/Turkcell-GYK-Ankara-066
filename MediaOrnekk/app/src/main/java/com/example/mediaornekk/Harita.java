package com.example.mediaornekk;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Harita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harita);

        // xml'de eklediğimiz elemanları tanımlıyoruz.
        ImageView dunyaharitasi = (ImageView) findViewById(R.id.dunyaa);
        Button haritaAc = (Button) findViewById(R.id.haritayagit);


        // haritaya git butonuna tıklama özelliği veriyoruz.
        haritaAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Uri sınıfından bir değişken tanımlıyoruz.
                // burda "geo" olarak gidilmek istenen enlem ve boylam giriyoruz.
                // örnekte yazdığımız İstanbul'un enlem ve boylam bilgileridir.
                Uri geoLocation = Uri.parse("geo:41.0138400,28.9496600");

                // veri aktarmalarında kullandığımız intent'te ACTION_VIEW servisiyle
                // bunu imageView'e aktarıyoruz.
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(geoLocation);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);

                }
            }
        });
    }
}

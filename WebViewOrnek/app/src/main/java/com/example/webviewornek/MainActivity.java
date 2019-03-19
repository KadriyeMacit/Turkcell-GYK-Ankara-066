package com.example.webviewornek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // arkadaşlar uygulamanın toolbar rengi için
    // app->res->values->colors.xml dosyasını kurcalamanız yeterli


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // öncelikle xml dosyamıza ne ekliyorsak onları java dosyamızda tanımlıyoruz.
        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);


        // butona tıklama özelliği veriyoruz.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           // butona tıkladığımızda diğer aktivitiye geçişini yapıyoruz.
                Intent intent = new Intent(MainActivity.this, Yeni.class);
                startActivity(intent);

            }
        });

    }
}

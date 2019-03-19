package com.example.listviewornek;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // xml dosyasında eklediğimiz her şeyi burada tanımlıyoruz.
        TextView textView = (TextView) findViewById(R.id.textView);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Button button = (Button) findViewById(R.id.button);

        // buton'a tıklama özelliği veriyoruz.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tıklama olayında aktivity geçişini(sayfa geçişi) yapıyoruz.
                Intent intent = new Intent(MainActivity.this, Listemiz.class);
                startActivity(intent);
            }
        });
    }
}

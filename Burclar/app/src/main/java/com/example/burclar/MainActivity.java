package com.example.burclar;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // putExtra, key-value şeklinde çalışır.
    // bunun için paket ismimizi referans gösterek key ismi belirliyoruz.

    public final static String EXTRA_MESSAGE = "com.example.burclar.MESAJ";
    public final static String EXTRA_PASSWORD = "com.example.burclar.PAROLA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView baslik = (TextView) findViewById(R.id.textView);
        final EditText name = (EditText) findViewById(R.id.editText);
        final EditText password = (EditText) findViewById(R.id.editText2);
        Button buton = (Button) findViewById(R.id.button);

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gecis = new Intent(MainActivity.this, AraEkran.class);

                // EditText'te yazılan mesajı göndermek için.
                String mesaj = name.getText().toString();
                gecis.putExtra(EXTRA_MESSAGE, mesaj);

                String parola = password.getText().toString();
                gecis.putExtra(EXTRA_PASSWORD, parola);

                // intentimizi çalıştırıyoruz.

                   startActivity(gecis);


            }
        });
    }
}

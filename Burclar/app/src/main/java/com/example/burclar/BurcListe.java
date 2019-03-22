package com.example.burclar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class BurcListe extends AppCompatActivity {

    private String[] burclar = {"Koç", "Boğa", "İkizler", "Yengeç",
    "Aslan", "Terazi", "Başak", "Akrep", "Yay", "Oğlak", "Kova", "Balık"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burc_liste);

        ListView listemiz = (ListView) findViewById(R.id.listView);

        ArrayAdapter<String> veriAdaptoru = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, burclar);
        listemiz.setAdapter(veriAdaptoru);


        listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BurcListe.this, SonSayfa.class);
                startActivity(intent);
            }
        });
    }
}

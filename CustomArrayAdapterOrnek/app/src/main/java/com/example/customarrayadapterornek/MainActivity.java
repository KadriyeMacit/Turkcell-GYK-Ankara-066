package com.example.customarrayadapterornek;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Kisi> kisiListesi = new ArrayList<Kisi>();

        kisiListesi.add(new Kisi("Damla YILMAZ",R.drawable.kadin));
        kisiListesi.add(new Kisi("Savaş ERDEM",R.drawable.erkek));
        kisiListesi.add(new Kisi("Beyza DEMİR",R.drawable.kadin));
        kisiListesi.add(new Kisi("Metin VURAL",R.drawable.erkek));
        kisiListesi.add(new Kisi("Enes KEZER",R.drawable.erkek));
        kisiListesi.add(new Kisi("Aslı UMUT",R.drawable.kadin));

        ListView listView = (ListView) findViewById(R.id.listView);

        OzelAdapter ozelAdapter = new OzelAdapter(MainActivity.this,kisiListesi);
        listView.setAdapter(ozelAdapter);






    }
}

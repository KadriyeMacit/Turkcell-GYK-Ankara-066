package com.example.listviewornek;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Listemiz extends AppCompatActivity {

    // listview'de listenecek olan listeyi tanımlıyoruz.
    private String[] kisiler =
            {"Ayşe", "Cansu", "Elif", "Elif","Betül",
                    "Sıla", "Esra", "Sena", "Gülşen", "Zehra",
                    "Özge", "Selen", "Nilgün", "Ebru", "Hilal",
                    "Hasret", "Maide", "Sude", "Emine"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listemiz);


        // kullandığımız listview'e id eklemeyi unutmayalım.
        ListView listView = (ListView) findViewById(R.id.lisView1);

        // listview için adapter tanımlıyoruz.
        ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, kisiler);
        listView.setAdapter(veriAdaptoru);


        // listview ile listenen listemize tıklanılabilirlik özelliği ekliyoruz.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //tıkladığımızda karşımıza uyarı ekranı gibi bir pencere çıkmasını sağlıyoruz.
                AlertDialog.Builder diyalogOlusturucu =
                        new AlertDialog.Builder(Listemiz.this);

                diyalogOlusturucu.setMessage(kisiler[position])
                        .setCancelable(false)
                        .setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                diyalogOlusturucu.create().show();

            }
        });


    }
}

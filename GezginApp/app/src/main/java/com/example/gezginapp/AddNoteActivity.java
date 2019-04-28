package com.example.gezginapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNoteActivity extends AppCompatActivity {

    EditText yaz;
    Button ekle;
    Button listele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        yaz = (EditText) findViewById(R.id.notYaz);
        ekle = (Button) findViewById(R.id.notEkle);
        listele = (Button) findViewById(R.id.notListele);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkleFonksiyonu();
            }
        });

        listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notListelemeFonksiyonu();
            }
        });
    }

    private void notEkleFonksiyonu()
    {
        // FirebaseDatabase nesnelerini kullanabilmek için instance yapıyoruz
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // Ekleyeceğimiz notların yolunuz yazıyoruz, her biri
        // GezdiğimYerler adlı child'ın altına eklenecek.
        DatabaseReference myRef = firebaseDatabase.getReference()
                .child("GezdigimYerler");

        // Eklenecek olan notlara id atıyoruz.
        String notesId = myRef.push().getKey();

        // Girilen veriyi, tanımladığımız değişkene atıyoruz
        // işlem yapabilmek için
        String notYaz = yaz.getText().toString();

        // girilen verinin uzunluğunu kontrol ederek, boş mu değil mi bakıyoruz.
        if (notYaz.length() > 0) {

            // yukarıda tanımladığımız GezdiğimYerler bölümünün altına
            // tekrar sehirAdi şeklinde bir bölüm daha oluşturarak
            // girilen veriyi, veritabanına ekliyoruz.
            myRef.child(notesId).child("sehirAdi").setValue(notYaz);

            // showDialog diye kendi oluşturduğumuz fonksiyonla
            // uyarı ekranı çıkıyor.
            showDialog("Başarılı", "Notunuz Kaydedildi!");
        }

        // eğer girilen veri yoksa yine uyarı ekranı gelir.
        else
            {
            showDialog("İşlem Başarısız", "Not alanı boş bırakılamaz!");
        }

        yaz.setText("");
    }

    // yukarıda çağırdığımız alertDialog için fonksiyonumuzu tanımlıyoruz.
    private void showDialog(String title, String message) {

        final AlertDialog.Builder builder =
                new AlertDialog.Builder(AddNoteActivity.this);

        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("TAMAM",
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }



    private void notListelemeFonksiyonu()
    {

    }
}

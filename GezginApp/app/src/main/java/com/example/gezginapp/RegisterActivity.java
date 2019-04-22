package com.example.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    // xml dosyasında kullandıklarımızı tanımlıyoruz
    private EditText yeniUserEmail;
    private EditText yeniUserParola;
    private EditText yeniUserParolaTekrar;
    private Button yeniKayit;

    // Firebase kütüphanesinden değişken tanımlıyoruz
    // kimlik tanıma için
    private FirebaseAuth mAuth;

    // girilen verileri bir değişkene
    // atıp kontrol etmek için tanımlıyoruz
    private String alinanEmail;
    private String alinanParola;
    private String alinanParolaTekrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // id lerini belirtiyoruz işlem yapabilmek için.
        yeniUserEmail = (EditText) findViewById(R.id.yeniEmail);
        yeniUserParola = (EditText) findViewById(R.id.yeniParola);
        yeniUserParolaTekrar = (EditText) findViewById(R.id.yeniParola2);
        yeniKayit = (Button) findViewById(R.id.button3);

        // Firebase için gerekli tanımlama yapılıyor,
        // kullanılacağı belirtiliyor
        mAuth = FirebaseAuth.getInstance();


        yeniKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //karışık ve uzun işlemler yapacağımız için
                // fonksiyon çağırıyoruz
                kontrol();
            }
        });
    }   //onCreate metodu dışında fonksiyonlar tanımlanır.


    //yukarıda çağrılan fonksiyonu tanımlıyoruz
    private  void kontrol()
    {
        // girilen verileri, bizim tanımladığımız
        // değişkenlere atıyoruz. Kontrol edebilmek için.
        alinanEmail = yeniUserEmail.getText().toString().trim();
        alinanParola = yeniUserParola.getText().toString().trim();
        alinanParolaTekrar = yeniUserParolaTekrar.getText().toString().trim();
        // baştaki ve sondaki boşlukları dahil etmemek için trim fonksiyonu kullanılır

        // boş olmadığı sürece aşağıdaki işlemleri yapar.
        if(!alinanEmail.isEmpty() && !alinanParola.isEmpty() && !alinanParolaTekrar.isEmpty())
        {
            // girilen 2 parolanın denkliğine bakar.
            if(alinanParola.equals(alinanParolaTekrar))
            {
                // karışık ve uzun işlemler yapılacağı için
                // fonksiyon çağırıyoruz.
                kayitIslemi();
            }
        }
        else
        {
            // eğer boş alan varsa uyarı yazısı ekrana çıkar.
            Toast.makeText(getApplicationContext(),
                    "Kayıt için tüm alanları doldurunuz",
                    Toast.LENGTH_SHORT).show();
;        }


    }


    // yukarıda çağrılan fonksiyonu tanımlıyoruz
    private void kayitIslemi()
    {
        // Firebase'de ne yapmak istiyorsak onu belirtiyoruz
        // mail ve parola ile yeni kayıt oluşturmak istiyorsak create ile başlarız
        mAuth.createUserWithEmailAndPassword(alinanEmail, alinanParola)

                // hatalarla ilgili özellikleri burda tanımlar, belirtiriz.
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // FirebaseAuthException kütüphanesinden bir hataysa
                        // kimlik denetleme sürecinde gerçekleştirilen hatalardansa yani
                        if(e instanceof FirebaseAuthException)
                        {
                            // eğer eksik parola girilirse, misal 3 haneli vb.
                            if(((FirebaseAuthException) e).getErrorCode().
                                    equals("ERROR_WEAK_PASSWORD")){
                                // bilgilendirme amaçlı kısa bir yazı çıkar.
                                Toast.makeText(getApplicationContext(), "Eksik şifre", Toast.LENGTH_SHORT).show();
                            }

                            // eğer mail formatında yazılmazsa
                            if(((FirebaseAuthException) e).getErrorCode().
                                    equals("ERROR_INVALID_EMAIL")){
                                // bilgilendirme amaçlı kısa bir yazı çıkar.
                                Toast.makeText(getApplicationContext(), "Geçersiz mail", Toast.LENGTH_SHORT).show();
                            }

                            // eğer kayıtlı bir kullanıcıysa
                            if(((FirebaseAuthException) e).getErrorCode().
                                    equals("ERROR_EMAIL_ALREADY_IN_USE")){
                                // bilgilendirme amaçlı kısa bir yazı çıkar.
                                Toast.makeText(getApplicationContext(), "Mail zaten kayıtlı", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                })


                // yapmak istediğimiz eylemleri bu methodun altında tanımlarız
                .addOnCompleteListener(this,
                        new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                // eğer veriler düzgünse
                if(task.isSuccessful())
                {
                    // yeni kullanıcı oluşturulur
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    // bilgilendirme amaçlı kısa bir yazı çıkar
                    Toast.makeText(getApplicationContext(), "Kayıt işlemi yapıldı", Toast.LENGTH_SHORT).show();

                    // kayıt işlemi tamamlandığına göre login ekranına dönebiliriz.
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        }


        );
    }




}

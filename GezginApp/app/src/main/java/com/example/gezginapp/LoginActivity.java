package com.example.gezginapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class LoginActivity extends AppCompatActivity {

    // xml dosyasında kullandıklarımızı tanımlıyoruz
    private EditText userEmail;
    private EditText userPassword;
    private Button giris;
    private Button kayit;

    // Firebase kütüphanesinden bir değişken tanımlıyoruz
    // kimlik denetlemesi için
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // kullanabilmek için hepsine id atıyoruz.
        userEmail = (EditText) findViewById(R.id.editText);
        userPassword = (EditText) findViewById(R.id.editText2);
        giris = (Button) findViewById(R.id.button);
        kayit = (Button) findViewById(R.id.button2);

        // Firebase kimlik doğrulama özelliğini kullanabilmek
        // için bu tanımlamayı yapmak zorundayız.
        mAuth = FirebaseAuth.getInstance();


        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // eğer direk kayit butonuna basılırsa
                // direk kayıt yapılan activitye yönlendirilir.
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // girilen verileri kullanabilmek için
                // tanımladığımız stringlere atıyoruz.
                String alinanMail = userEmail.getText().toString().trim();
                String alinanParola = userPassword.getText().toString().trim();
                //trim() sondaki ve baştaki boşlukları siler.

                // bu girilen veriler boş olmadığı sürece,
                if(!alinanMail.isEmpty() && !alinanParola.isEmpty())
                {
                    // giriş yapması için aşağıda uzun uzadıya tanımlanan giriş fonksiyonu çağrılır
                    loginFonksiyonu(alinanMail, alinanParola);
                }
                else
                {
                    // eğer herhangi biri boş bırakılırsa kısa bir yazı çıkar,
                    // bilgilendirme amaçlı
                    Toast.makeText(getApplicationContext(), "Email ya da parola boş olamaz", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    // yukarıda çağrılan giriş fonksiyonunu tanımlıyoruz.
    private void loginFonksiyonu(String alinanMail, String alinanParola)
    {
        // tanımladığımız Firebase Kimlik Doğrulama özelliği ile
        // ne yapmak istiyorsak onu belirtiyoruz.
        // mail ve parola ile giriş, 2 parametreli ve bu parametreler
        // bizim girilen verileri atadığımız değişkenler
        mAuth.signInWithEmailAndPassword(alinanMail, alinanParola)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // eğer sonuç başarılı ise
                        if(task.isSuccessful())
                        {
                            // MainActivity'e geçiş yapar
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();

                            Log.d("Email", "signInWithEmail:success");
                        }
                        else
                        {
                            // sonuç başarılı değilse giriş yapamadığından
                            // sayfa geçişi olmaz, sadece

                            Log.w("Fail", "signInWithEmail:failure", task.getException());
                        }

                    }
                });
    }



}

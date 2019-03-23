package com.example.mediaornekk;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Internet extends AppCompatActivity {

    // bu kısımda öncelikle Manifest dosyamızda internet izni vermemiz gerekli.
    // ayrıca yine manifest dosyasına BROWSABLE eklenmelidir.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        // xml dosyamızda WebView'e id atamayı unutmayalım.
        WebView webView = (WebView) findViewById(R.id.webView);

        // string şeklinde gidilecek olan linki tanımlıyoruz.
        String gyUrl = "https://gelecegiyazanlar.turkcell.com.tr";

        // WebView ayarları yapılıp sayfa yükleme işlemi yapılıyor.
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(gyUrl);

        // bu sırada progressdialog ekleyerek,
        // sayfa açılırken ekrana çıkacak olan yazıyı belirliyoruz.
        final ProgressDialog progress = ProgressDialog.show(this, "Geleceği Yazanlar", "Yükleniyor....", true);
        progress.show();

        // işlem bittikten sonra yani linki gönderdikten sonra
        // aldığımız cevaba göre yapılacak işlemleri tanımlıyoruz.
        webView.setWebViewClient(new WebViewClient() {

            // eğer işlem başarılıysa
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Toast.makeText(getApplicationContext(), "Sayfa yüklendi", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }

            // sunucudan cevap alamıyorsak
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Bir hata oluştu", Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }


            // Toast, ekrana yazı bastırmamızda kullandığımız bir sınıftır.
        });



    }
}

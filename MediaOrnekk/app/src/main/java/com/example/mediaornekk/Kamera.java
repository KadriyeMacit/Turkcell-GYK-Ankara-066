package com.example.mediaornekk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class Kamera extends AppCompatActivity {

    // aşağıdaki fonksiyonlarda kullanmak üzere özel değişken tanımlıyoruz.
    // 101 -> video, 102-> fotoğraf için özeldir.
    private static final int VIDEO_ACTION_CODE = 101;
    private static final int IMAGE_ACTION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);

        // xml dosyamızda eklediklerimizi tanımlıyoruz.
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        Button foto = (Button) findViewById(R.id.button);
        Button video = (Button) findViewById(R.id.button2);


        // fotoğraf çek butonuna tıkladığımız olacak işlemleri yazıyoruz.
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Intent sınıfı veri aktarımı, sayfa geçişi yapan bir sınıftır.
                // burada da Intent sınıfından MediaStore'un fotoğraf için olan servisini tanımlıyoruz.
                // fotoğrafı sorunsuz bir şekilde çalıştırabilmek için
                // startActivityForResult() fonksiyonunu kullandık,
                // yukarıda tanımladığımız fotoğraf kodu ile.
                Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePhotoIntent, IMAGE_ACTION_CODE);

            }
        });


        // video çek butonuna tıkladığımız olacak işlemleri yazıyoruz.
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // burada da Intent sınıfından MediaStore'un video için olan servisini tanımlıyoruz.
                // startActivityForResult() fonksiyonunu kullandık,
                // yukarıda tanımladığımız video kodu ile.
                Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivityForResult(takePictureIntent, VIDEO_ACTION_CODE);

            }
        });

    }



    // derste yaptığımız gibi kısaca ekranda boş bir yere sağ tıklayıp
    // generate -> override methods -> results yazıyoruz ve
    // onActivityReslut metodunu seçiyoruz.

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode != RESULT_OK) return;


        // eğer video kodu dönerse,
        switch (requestCode) {
            case VIDEO_ACTION_CODE :
                //videoview tanımlıyoruz.
                VideoView videoView = ((VideoView) findViewById(R.id.videoView));
                //videoyu dataya dönüştürüyoruz.
                videoView.setVideoURI(data.getData());
                //medya ayarlarını yapıyoruz.
                videoView.setMediaController(new MediaController(this));
                videoView.requestFocus();
                //başlatıyoruz.
                videoView.start();
                break;

                //eğer fotoğraf kodu dönerse,
            case IMAGE_ACTION_CODE :
                // Bundle da veri aktarımını-dönüşümünü sağlayan sınıflardandır.
                // burada Bundle yani veri dönüşümü için bir değişken atayıp
                // veri alıyoruz.
                Bundle extras = data.getExtras();
                // Imageview'i tanımlarken çektiğimiz fotoğraf verisi
                // Bitmap ile imageview'e dönüştürüp ekliyoruz.
                ((ImageView) findViewById(R.id.imageView)).setImageBitmap((Bitmap) extras.get("data"));
                break;
            default:
                break;
        }



    }
}

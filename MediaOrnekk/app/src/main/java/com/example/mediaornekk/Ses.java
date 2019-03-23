package com.example.mediaornekk;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Ses extends AppCompatActivity implements View.OnClickListener {


    // bu ses özelliğini kullanabilmemiz için
    // manifest dosyasında RECORD_AUDIO ve
    // WRITE_EXTERNAL_STORAGE izni vermemiz gerekir.

    // ses için bir code tanımlıyoruz. 200 -> ses için özeldir.
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 200;


    // Ses kaydetmek ve oynatmak için Media sınıflarından
    // onCreate metodunda kullanmak üzere değişken tanımlıyoruz.
    private MediaRecorder recorder;
    private MediaPlayer player;

    // sesin kaydedilecek yerini belirliyoruz.
    private final String filepath = Environment.getExternalStorageDirectory().getPath() + "/record.3gp";


    // xml'de eklediğimiz elemanları
    // onCreate metodu dışında da kulanabilmek için üst tarafta tanımlıyoruz.
    ImageView mikrofon;
    Button seskaydet;
    Button sesdurdur;
    Button sescal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses);

        // xml'de eklediğimiz elemanları tanımlıyoruz.
       mikrofon = (ImageView) findViewById(R.id.mikrofon);
       seskaydet = (Button) findViewById(R.id.kaydet);
       sesdurdur = (Button) findViewById(R.id.durdur);
       sescal = (Button) findViewById(R.id.cal);


       seskaydet.setOnClickListener(this);
       sesdurdur.setOnClickListener(this);
       sescal.setOnClickListener(this);
    }



     // genel olarak tıklama özelliğini şekillendiriyoruz.
     // kaydet, durdur ve çal işlemlerinde
     // yönlendirdiğimiz fonksiyonları aşağıda kendimiz tanımlıyoruz.

    // fonksiyonları ayrı ayrı tanımlayıp sonradan çağırmak
    // işlem kolaylığı sağlar, karmaşayı önler.
    @Override
    public void onClick(View view) {

        // eğer kaydet butonu ise
        if (view == seskaydet) {
            // izinler kontrol edilir, sonra kaydetme işlemi.
            // kaydetme işleminde yazdığımız fonksiyonları aşağıda tanımlıyoruz.
            if(checkPermissions()){
                startRecording();
            } else {
                requestPermissions();
                startRecording();
            }
        }
        // eğer durdur butonu ise durdurma işlemi.
        else if (view == sesdurdur) {
            stopRecording();
        }
        // eğer çal butonu ise oynatma işlemi.
        else if (view == sescal) {
            startPlaying();
        }

    }


    // kaydetme işleminde neler yapacağımızı yazıyoruz.
    private void startRecording() {

        // en yukarda tanımladığımız MediaRecorder'dan değişkenimizi yazıyoruz.
        recorder = new MediaRecorder();

        // ses ayarları yapılır.
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //kaydedilecek yeri yazıyoruz.
        recorder.setOutputFile(filepath);


        // try-catch metodu, bir hatayla karşılaştığımızda
        // programı bozmadan programın devamlılığını sağlar.
        try {
            // hatasızsa,
            recorder.prepare();
            recorder.start();
            Toast.makeText(getApplicationContext(),"Kayıt Yapılıyor",Toast.LENGTH_LONG).show();
        }
        // eğer hata alırsa
        catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // durdurma işleminde neler yapacağımızı yazıyoruz.
    private void stopRecording() {
        if (recorder != null) {
            Toast.makeText(getApplicationContext(),"Kayıt Durduruldu",Toast.LENGTH_LONG).show();
            recorder.stop();
            recorder.reset();
            recorder.release();
            recorder = null;
        }
    }


    // çal(oynat) işleminde neler yapacağımızı tanımlıyoruz.
    private void startPlaying() {

        // yukarda tanımladığımız değişkenimizi yazıyoruz.
        player = new MediaPlayer();

        // bu değişkene frekans atıyoruz.
        player.setVolume(1.0f, 1.0f);
        try {
            player.setDataSource(filepath);
            player.prepare();
            player.start();
            Toast.makeText(getApplicationContext(),"Kayıt Çalıyor",Toast.LENGTH_LONG).show();
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer arg0) {
                    player.stop();
                    player.release();
                    player = null;
                }
            });
        } catch (Exception e) {
        }
    }



    // izinlerle alakalı gerekli ayarlamaları yapıyoruz.
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {

            // yukarda tanımladığımız code değişkenine göre,
            case REQUEST_AUDIO_PERMISSION_CODE:
                if (grantResults.length> 0) {
                    boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean permissionToStore = grantResults[1] ==  PackageManager.PERMISSION_GRANTED;
                    if (permissionToRecord && permissionToStore) {
                        Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    // izinleri kontrol ediyoruz.
    public boolean checkPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }


    private void requestPermissions() {
        ActivityCompat.requestPermissions(Ses.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }







}

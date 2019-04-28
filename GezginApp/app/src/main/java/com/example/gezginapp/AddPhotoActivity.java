package com.example.gezginapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddPhotoActivity extends AppCompatActivity {

    FirebaseStorage firebaseStorage;

    FirebaseAuth mAuth;

    Uri filePath;

    private ProgressDialog progressDialog;

    ImageView imageView;

    private static final int IMAGE_REQUEST = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        mAuth = FirebaseAuth.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();


        imageView = (ImageView) findViewById(R.id.imageView);
        Button photoSec = (Button) findViewById(R.id.fotoSec);
        Button photoKaydet = (Button) findViewById(R.id.fotoKaydet);

        showPhoto();

        photoSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secmeFonksiyonu();
            }
        });


        photoKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kaydetmeFonksiyonu();
            }
        });

    }

    private void showPhoto(){

        showProgressDialog();

        StorageReference storageRef =
                firebaseStorage.getReference();

        storageRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                dismissProgressDialog();
                Picasso.with(AddPhotoActivity.this)
                        .load(uri).fit().centerCrop().into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                dismissProgressDialog();
                Toast.makeText(AddPhotoActivity.this,
                        "Fotoğraf Yükleme işlemi başarısız",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void secmeFonksiyonu()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Resim Seçiniz"), IMAGE_REQUEST);
    }


    private void kaydetmeFonksiyonu() {

        if (filePath != null) {
            showProgressDialog();

            StorageReference storageRef =
                    firebaseStorage.getReference();

            storageRef
                    .child("userprofilephoto")
                    .putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            dismissProgressDialog();

                            Toast.makeText(AddPhotoActivity.this,
                                    "Fotoğraf başarılı bir şekilde kaydedildi.",
                                    Toast.LENGTH_SHORT).show();
                            onBackPressed();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    dismissProgressDialog();
                    Toast.makeText(AddPhotoActivity.this,
                            "Fotoğraf Kaydedilemedi", Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(AddPhotoActivity.this);
        progressDialog.setMessage("Yükleniyor...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }


    private void  dismissProgressDialog(){
        progressDialog.dismiss();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Picasso.with(AddPhotoActivity.this)
                        .load(filePath).fit().centerCrop().into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}

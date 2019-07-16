package com.trieka.latihanandroid.Menu;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.trieka.latihanandroid.R;

import java.io.ByteArrayOutputStream;

public class CameraActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonCamera;
    private ImageView previewPhoto;

    private int ID_PEMANGGIL = 10;// UNIQUE NUMBER
    private int ID_PERMISSION_CAMERA = 101;// UNIQUE NUMBER

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        // inisialisasi
        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fungsi panggil camera
                if (checkPermissionCamera()){
                    ambilImageDariCamera();
                }

            }
        });

        previewPhoto = (ImageView) findViewById(R.id.previewPhoto);
    }

    // RUNTIME PERMISSION UNTUK ANDROID 6.0 KEATAS
    private boolean checkPermissionCamera(){
        int currentAPIVersion = Build.VERSION.SDK_INT;// CHECK API VERSION

        if (currentAPIVersion >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED){
                //BELUM ADA IZIN AKSES
                requestPermissions(new String[]{Manifest.permission.CAMERA}, ID_PERMISSION_CAMERA);
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }
    //


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ID_PERMISSION_CAMERA){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariCamera();
            }else {
                Toast.makeText(context, "ANDA HARUS MEMBERIKAN IZIN AKSES CAMERA",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    // MEMANGGIL FUNGSI CAMERA
    private void ambilImageDariCamera(){
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, ID_PEMANGGIL);
    }
    // MENGAMBIL PHOTO HASIL DARI CAMRERA (Meng-handle balikan dari intend camera)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CONVER DATA MENJADI BITMAP
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == ID_PEMANGGIL){
                tampilkanImageDariKamera(data);
            }
        }
    }
    // TRANSFORMASI DATA
    private void tampilkanImageDariKamera(Intent data){
        Bitmap bitmap = null;

        // CONVERSI DATA JADI IMAGE (BITMAP)
        if(data != null){
            bitmap = (Bitmap) data.getExtras().get("data");
        }
        // TAMPILKAN DALAM IMAGEVIEW
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, outputStream);
        previewPhoto.setImageBitmap(bitmap);
    }
}

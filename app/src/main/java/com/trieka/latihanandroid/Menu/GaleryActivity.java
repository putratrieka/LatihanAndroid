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

import java.io.IOException;

public class GaleryActivity extends AppCompatActivity {

    private Context context = this;

    private Button buttonGalery;
    private ImageView previewPhotoGalery;
    private  int ID_PERMISSION_GALRY = 29;
    private int ID_PEMANGGIL_GALERY = 290;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galery);

        buttonGalery = (Button) findViewById(R.id.buttonGalery);
        buttonGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermissionGalery()){
                    ambilImageDariGalery();
                }
            }
        });
    }

    private boolean checkPermissionGalery(){
        int currentAPIVersion = Build.VERSION.SDK_INT;// CHECK API VERSION

        if (currentAPIVersion >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED){
                //BELUM ADA IZIN AKSES
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        ID_PERMISSION_GALRY);
                return false;
            }else {
                return true;
            }
        }else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == ID_PERMISSION_GALRY){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                ambilImageDariGalery();
            }else {
                Toast.makeText(context, "ANDA HARUS MEMBERIKAN IZIN AKSES CAMERA",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void ambilImageDariGalery(){
        Intent galery = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galery.setType("image/*");
        galery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(galery, "Pilih Foto"), ID_PEMANGGIL_GALERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            if(requestCode == ID_PEMANGGIL_GALERY){
                tampilkanFotoDariGalery(data);
            }
        }
    }
    private void tampilkanFotoDariGalery(Intent data){
        Bitmap bitmap = null;
        if (data != null){
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context
                                .getContentResolver(),
                        data.getData());
                previewPhotoGalery.setImageBitmap(bitmap);
            }catch (IOException ex){
                System.out.println("Error accessing image : " + ex.getMessage());
            }
        }
    }
}

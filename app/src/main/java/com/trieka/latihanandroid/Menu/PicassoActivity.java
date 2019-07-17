package com.trieka.latihanandroid.Menu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trieka.latihanandroid.R;

public class PicassoActivity extends AppCompatActivity {

    private Context context = this;
    private Button buttonLoadImagePicasso;
    private ImageView previewImageWebPicasso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        buttonLoadImagePicasso = (Button) findViewById(R.id.buttonLoadImagePicasso);
        buttonLoadImagePicasso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageDenganPicasso();
            }
        });
        previewImageWebPicasso = (ImageView) findViewById(R.id.previewImageWebPicasso);
    }
    private void loadImageDenganPicasso(){
        String urlImage = "https://www.freepngimg.com/thumb/mario/20698-7-mario-transparent-background.png";

        // PEMANGGILAN PALING SEDERHANA MENGGUNAKAN PICASSO
        Picasso.get()
                .load(urlImage).placeholder(R.drawable.preview)
                .error(R.drawable.error)
                .into(previewImageWebPicasso);
    }
}

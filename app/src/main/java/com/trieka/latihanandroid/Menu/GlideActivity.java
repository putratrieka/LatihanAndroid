package com.trieka.latihanandroid.Menu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.trieka.latihanandroid.R;

public class GlideActivity extends AppCompatActivity {
    private Context context = this;

    private Button buttonLoadImageGlide;
    private ImageView previewImageGlide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gllide);

        buttonLoadImageGlide = (Button) findViewById(R.id.buttonLoadImageGlide);
        buttonLoadImageGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImageDenganGLide();
            }
        });
        previewImageGlide = (ImageView) findViewById(R.id.previewImageWebGlide);
    }
    private void loadImageDenganGLide(){
        String urlImage = "https://www.asalesguy.com/wp-content/uploads/2016/09/dark-path-970882.jpeg";

        Glide.with(context)
                .load(urlImage)
                .into(previewImageGlide);
    }

}

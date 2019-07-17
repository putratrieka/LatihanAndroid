package com.trieka.latihanandroid.Menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.utility.LoadingClass;

public class VolleyActivity extends AppCompatActivity {
    private Context context = this;
    private Button buttonLoadImage;
    private ImageView previewImageWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        //INISIALISASI
        buttonLoadImage = (Button) findViewById(R.id.buttonLoadImage);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ambilImageDariWeb();
            }
        });
        previewImageWeb = (ImageView) findViewById(R.id.previewImageWeb);


    }
    // REQUEST PERMISSION
    private void ambilImageDariWeb(){
        // LOADING PROGRESS
        final ProgressDialog loading = LoadingClass
                .loadingAnimationAndText(context,"Loading.....");
        loading.show();

        String urlImage = "https://statics.sportskeeda.com/editor/2018/12/6312a-15462039229720-500.jpg";

        ImageRequest imageRequest = new ImageRequest(urlImage,
                // RESPONSE SUKSES
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        loading.dismiss();
                        previewImageWeb.setImageBitmap(response);
                    }
                },
                0,
                0,
                ImageView.ScaleType.FIT_CENTER,
                Bitmap.Config.ARGB_8888,
                //RESPONSE ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context, "Error panggil image : "
                                + error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(context).add(imageRequest);
    }
}

package com.trieka.latihanandroid.Menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.utility.LoadingClass;

public class GetStringActivity extends AppCompatActivity {
    private Context context = this;

    private Button buttonGetString;
    private TextView hasilGetString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_string);

        //INISIALISASI
        buttonGetString = (Button) findViewById(R.id.buttonGetString);
        buttonGetString.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringFormAPI();
            }
        });
        hasilGetString = (TextView) findViewById(R.id.hasilGetString);
    }
    // REQUEST
    private void getStringFormAPI(){
        final ProgressDialog loading = LoadingClass
                .loadingAnimation(context);
        loading.show();

        String urlAPI = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET, // METHOD
                urlAPI, // URL
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        hasilGetString.setText((response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context,"Error get string "
                        + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        );
        Volley.newRequestQueue(context).add(stringRequest);
    }
}

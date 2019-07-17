package com.trieka.latihanandroid.Menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.utility.LoadingClass;

import org.json.JSONException;
import org.json.JSONObject;

public class ParsingJSONActivity extends AppCompatActivity {
    private Context context = this;

    private ImageView avatar;
    private TextView userID, userEmail, userFirstName, userLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parsing_json);

        //INISIALISASI
        avatar = (ImageView) findViewById(R.id.avatar);

        userID = (TextView) findViewById(R.id.userID);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userFirstName = (TextView) findViewById(R.id.userFirstName);
        userLastName = (TextView) findViewById(R.id.userLastName);

        // MEMENGGIL API
        getUserFromAPI();
    }
    private void getUserFromAPI(){
        final ProgressDialog loading = LoadingClass.loadingAnimationAndText(
                context, "Silahkan Tunggu");
        loading.show();

        String urlAPI = "https://reqres.in/api/users/3";

        JsonObjectRequest requestAPI = new JsonObjectRequest(
                Request.Method.GET,
                urlAPI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        //PARSING
                        try {
                            JSONObject objectData = response.getJSONObject("data");
                            int id = objectData.getInt("id");
                            String email = objectData.getString("email");
                            String first_name = objectData.getString("first_name");
                            String last_name = objectData.getString("last_name");
                            String url_avatar = objectData.getString("avatar");

                            userID.setText(""+id);
                            userEmail.setText(email);
                            userFirstName.setText(first_name);
                            userLastName.setText(last_name);
                            Picasso.get().load(url_avatar).into(avatar);

                        }catch (JSONException jx){
                            System.out.println("Error Parsing : "+jx.getMessage());
                            Toast.makeText(context,
                                    "Error Parsing : "+jx.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context, "Error panggil API "
                            + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(context).add(requestAPI);
    }
}

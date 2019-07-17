package com.trieka.latihanandroid.Menu;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.trieka.latihanandroid.Adapter.CustomListUserAdapter;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.model.ModelUser;
import com.trieka.latihanandroid.utility.LoadingClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetListActivity extends AppCompatActivity {
    private Context context = this;

    private Button buttonGetList;
    private ListView listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list);

        //INISIALISASI
        buttonGetList = (Button) findViewById(R.id.buttonGetList);
        buttonGetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                getListUserFromAPI();
            }
        });
        listUser = (ListView) findViewById(R.id.listUser);
    }
    private void getListUserFromAPI(){
        final ProgressDialog loading = LoadingClass.loadingAnimation(context);
        loading.show();
        String urlAPI = "https://reqres.in/api/users?page=3";

        // TAHAP 1: PANGGIL API
        JsonObjectRequest requestAPI = new JsonObjectRequest(
                Request.Method.GET,
                urlAPI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loading.dismiss();
                        // PARSING AND SAVE
                        // TAHAP 2: PARSING JSON
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            int sizeArray = jsonArray.length();

                            if (sizeArray > 0){
                                // TAHAP 3: KONVERSI DARI JSON KE LIST MODEL
                                List<ModelUser> modelUsers = new ArrayList<>();
                                for (int i = 0; i<sizeArray; i++){
                                    JSONObject dataObject = jsonArray.getJSONObject(i);

                                    ModelUser model = new ModelUser();
                                    model.setId(dataObject.getInt("id"));
                                    model.setEmail(dataObject.getString("email"));
                                    model.setFirst_name(dataObject.getString("first_name"));
                                    model.setLast_name(dataObject.getString("last_name"));
                                    model.setAvatar(dataObject.getString("avatar"));

                                    modelUsers.add(model);
                                }
                                // TAHAP 4: ISI MODEL KEDALAM ADAPTER
                                isiListAdapter(modelUsers);
                            }else {
                                Toast.makeText(context, "List User kosong",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }catch (JSONException jx){
                            Toast.makeText(context, "Error Parsing"
                                    +jx.getMessage(),Toast.LENGTH_SHORT).show();
                            System.out.println("Error Parsing"
                                    +jx.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(context,"Error get list user"
                            + error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("Error get list user"
                                + error.getMessage());
                    }
                }
        );
        Volley.newRequestQueue(context).add(requestAPI);
    }
    private void isiListAdapter(List<ModelUser> modelUsers){
        // PANGGIL ADAPTER
        CustomListUserAdapter adapterListUser = new CustomListUserAdapter(context, modelUsers);

        //TAHAP 5: HUBUNGKAN ADAPTER KE LIST VIEW
        listUser.setAdapter(adapterListUser);
    }
}

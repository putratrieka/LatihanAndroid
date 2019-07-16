package com.trieka.latihanandroid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.trieka.latihanandroid.utility.Constanta;
import com.trieka.latihanandroid.utility.SessionManager;

public class HomeActivity extends AppCompatActivity {
    private Context context = this;
    private TextView labelUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        labelUsername = (TextView) findViewById(R.id.labelUsername);



        //tangkap intent extra
        //tangkap username
        if(getIntent().getStringExtra(Constanta.ID_EXTRA_USERNAME) != null){
            String username = getIntent().getStringExtra(Constanta.ID_EXTRA_USERNAME);
            labelUsername.setText(username);
        }
        labelUsername.setText(SessionManager.getUserName(context));
//        labelUsername.setText(SessionManager.getUserName(context));
    }
}

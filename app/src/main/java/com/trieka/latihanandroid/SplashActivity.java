package com.trieka.latihanandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.trieka.latihanandroid.utility.SessionManager;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FULLSCREEN CODE
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // TIMER
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {

                if (SessionManager.cekLogin(context)){
                    //sudah login
                    pindahKeHomeScreen();
                }
                else {
                    pindahKeLoginScreen();
                }
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);
    }
    private void pindahKeLoginScreen(){
        Intent intent = new Intent(context,
                LoginActivity.class);
        startActivity(intent);

        finish();
    }
    private void pindahKeHomeScreen(){
        Intent intent = new Intent(context,
                HomeActivity.class);
        startActivity(intent);

        finish();
    }
}

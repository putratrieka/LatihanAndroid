package com.trieka.latihanandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.trieka.latihanandroid.Menu.CameraActivity;
import com.trieka.latihanandroid.Menu.GaleryActivity;
import com.trieka.latihanandroid.Menu.PicassoActivity;
import com.trieka.latihanandroid.Menu.VolleyActivity;
import com.trieka.latihanandroid.utility.Constanta;
import com.trieka.latihanandroid.utility.SessionManager;

public class HomeActivity extends AppCompatActivity {
    private Context context = this;
    private TextView labelUsername;

    private Button buttonLogout;
    private CardView cardCamera, cardGalery, cardVolley, cardPicasso;

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
        buttonLogout = (Button) findViewById(R.id.buttonLogout);

        // BUTTON LOGOUT
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout
                logoutConfirmation();
            }
        });
        // CAMERA ACCSESS
        cardCamera = (CardView) findViewById(R.id.cardCamera);
        cardCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pindah ke activity camera
                pindahKeActivityCamera();
            }
        });
        //CARD GALERY
        cardGalery = (CardView) findViewById(R.id.cardGalery);
        cardGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahKeActivityGalery();
            }
        });
        // CARD VOLLEY
        cardVolley = (CardView) findViewById(R.id.cardVolley);
        cardVolley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahKeActivityVolley();
            }
        });
        // CARD PICASSO
        cardPicasso = (CardView) findViewById(R.id.cardPicasso);
        cardPicasso.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahKeActivityPicasso();
            }
        }));
    }
    // CALL PICASSO_ACTIVITY
    private void pindahKeActivityPicasso(){
        Intent intent = new Intent(context, PicassoActivity.class);
        startActivity(intent);
    }

    // CALL COLLEY_ACTIVITY
    private void pindahKeActivityVolley(){
        Intent intent = new Intent(context, VolleyActivity.class);
        startActivity(intent);

    }
    // CALL GALERY_ACTIVITY
    private void pindahKeActivityGalery(){
        Intent initen = new Intent(context, GaleryActivity.class);
        startActivity(initen);
    }
    // CALL CAMERA_ACTIVITY
    private void pindahKeActivityCamera(){
        Intent intent = new Intent(context, CameraActivity.class);
        startActivity(intent);
    }
    // LOGOUT CONFIRMATION
    private void logoutConfirmation(){
        AlertDialog.Builder confirmation = new AlertDialog.Builder(context);
        confirmation.setMessage("Apakah Anda yakin ingin Logout?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // aksi jika tekan tombol Ya
                        logoutAplikasi();
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // aksi jika tekan tombol Tidak
                        dialog.cancel();
                    }
                })
                .setCancelable(false);
        AlertDialog showConfirmation = confirmation.create();
        showConfirmation.show();
    }
    // LOGOUT
    private void logoutAplikasi(){
        SessionManager.setLoginFlag(context, false);

        // panggil login screen
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
        // tutup home activity
        finish();

    }
}

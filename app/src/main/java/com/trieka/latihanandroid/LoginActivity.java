package com.trieka.latihanandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.trieka.latihanandroid.utility.Constanta;

public class LoginActivity extends AppCompatActivity {

    // DECLARE VARIABLE
    private Context context = this;
    private int counter_back = 1;

    private EditText inputUsername, inputPassword;
    private CheckBox checkBoxRemember;
    private Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //INISIALISASI
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        //tambahkan action listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deklarasikan aksinya
                validasiInput();
            }
        });
    }
    // VALIDASI INPUT
    private void validasiInput() {
        String valueUsername = inputUsername.getText().toString().trim();
        String valuePassword = inputPassword.getText().toString().trim();

        if (valueUsername.equalsIgnoreCase("")) {
            Toast.makeText(context, "Username masih kosong!", Toast.LENGTH_SHORT).show();
        } else if (valuePassword.length() == 0) {
            Toast.makeText(context, "Password masih kosong!", Toast.LENGTH_SHORT).show();
        } else {
            //validasi sukses
//                boolean remember = checkBoxRemember.isChecked();
//
//                // simpan data login
//                SessionManager.simpanDataLogin(context, valueUsername, valuePassword, remember);

            Intent pindah = new Intent(context, HomeActivity.class);
            pindah.putExtra(Constanta.ID_EXTRA_USERNAME, valueUsername);
            pindah.putExtra(Constanta.ID_EXTRA_PASSWORD, valuePassword);

            startActivity(pindah);

            finish();
        }
    }
}

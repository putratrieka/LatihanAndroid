package com.trieka.latihanandroid.Menu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.model.ModelUser;
import com.trieka.latihanandroid.utility.TemporaryData;

public class DeatailUserActivity extends AppCompatActivity {
    private Context context = this;

    private ImageView avatarUser;
    private TextView fullNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatail_user);

        avatarUser = (ImageView) findViewById(R.id.avatarUser);
        fullNameUser = (TextView) findViewById(R.id.fullNameUser);

        // TANGKAP DAN TAMPILKAN DATA
        ModelUser model = TemporaryData.TEMPORARY_MODEL_USER;
        if (model != null){
            String urlAvatar = model.getAvatar();
            Glide.with(context).load(urlAvatar).into(avatarUser);

            String valueName = model.getFirst_name()+" "+model.getLast_name();
            fullNameUser.setText(valueName);
        }
    }
}

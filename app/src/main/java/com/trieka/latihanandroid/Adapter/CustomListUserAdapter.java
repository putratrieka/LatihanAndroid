package com.trieka.latihanandroid.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trieka.latihanandroid.Menu.DeatailUserActivity;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.model.ModelUser;
import com.trieka.latihanandroid.utility.TemporaryData;

import java.util.List;

public class CustomListUserAdapter extends BaseAdapter {
    private Context context;
    private List<ModelUser>modelUsers;

    //CONSTRUCTOR
    public CustomListUserAdapter(Context context, List<ModelUser> modelUsers){
        this.context = context;
        this.modelUsers = modelUsers;
    }

    @Override
    public int getCount() {
        return this.modelUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolderListUser holder;
        if (convertView == null){
            holder = new ViewHolderListUser();
            convertView = inflater.inflate(R.layout.custom_list_user_layout, null);

            //INISIALISASI ELEMENT LAYOUT
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.fullname = (TextView) convertView.findViewById(R.id.fullName);
            holder.email = (TextView) convertView.findViewById(R.id.email);
            holder.listUserArea = (LinearLayout) convertView.findViewById(R.id.listUserArea);



        }else{
            holder = (ViewHolderListUser) convertView.getTag();
        }
        //SETVALUE KE MASING - MASING ELEMENT VIEW
        String urlAvatar = modelUsers.get(position).getAvatar();
        Picasso.get().load(urlAvatar).into(holder.avatar);
        String valueFullName = modelUsers.get(position).getFirst_name()
                +" "+ modelUsers.get(position).getLast_name();
        holder.fullname.setText(valueFullName);

        String valueEmail = modelUsers.get(position).getEmail();
        holder.email.setText(valueEmail);

        if (position %2 == 0){
            //GENAP
            holder.listUserArea.setBackgroundColor(Color.CYAN);
        }else {
            //GANJIL
            holder.listUserArea.setBackgroundColor(Color.LTGRAY);
        }
        //TAMBAHKAN CLICK LISTENER DI LISTAREA
        holder.listUserArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AKSI
                ModelUser model = modelUsers.get(position);
                TemporaryData.TEMPORARY_MODEL_USER = model;

                Intent intent = new Intent(context, DeatailUserActivity.class);
                context.startActivity(intent);
            }
        });

        convertView.setTag(holder);
        return convertView;
    }
    // INNER CLASS UNTUK HANDLING VIEW
    class ViewHolderListUser{
        ImageView avatar;
        TextView fullname;
        TextView email;
        LinearLayout listUserArea;
    }

}

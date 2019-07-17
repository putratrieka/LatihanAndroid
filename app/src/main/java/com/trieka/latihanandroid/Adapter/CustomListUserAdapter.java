package com.trieka.latihanandroid.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trieka.latihanandroid.R;
import com.trieka.latihanandroid.model.ModelUser;

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
    public View getView(int position, View convertView, ViewGroup parent) {
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

        convertView.setTag(holder);
        return convertView;
    }
    // INNER CLASS UNTUK HANDLING VIEW
    class ViewHolderListUser{
        ImageView avatar;
        TextView fullname;
        TextView email;
    }

}

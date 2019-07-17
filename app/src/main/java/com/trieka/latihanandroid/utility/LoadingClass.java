package com.trieka.latihanandroid.utility;

import android.app.ProgressDialog;
import android.content.Context;

import com.trieka.latihanandroid.R;

public class LoadingClass {
    public static ProgressDialog loadingAnimationAndText(Context context, String text){
        ProgressDialog loading  = new ProgressDialog(context, R.style.CustomLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);
        loading.setMessage(text);

        return loading;
    }
    public static ProgressDialog loadingAnimation(Context context){
        ProgressDialog loading  = new ProgressDialog(context, R.style.CustomLoadingStyle);
        loading.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        loading.setCancelable(false);
        loading.setCanceledOnTouchOutside(false);

        return loading;
    }
}

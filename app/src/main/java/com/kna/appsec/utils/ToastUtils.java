package com.kna.appsec.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.kna.appsec.R;
import com.kna.appsec.base.AppController;

/**
 * @description
 * @author：PhamBien
 */
public class ToastUtils {


    private static String oldMsg;
    private static View layout;

    private static Toast toast = null;
    private static Toast toastView = null;

    private static long oneTime = 0;

    private static long twoTime = 0;

    public static void showToast(String s) {
        try {
            if (null == s) {
                return;
            }
            if (toast == null) {
                toast = Toast.makeText(AppController.getInstance(), s, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();
                if (s.equals(oldMsg)) {
                    if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                        toast.show();
                    }
                } else {
                    oldMsg = s;
                    toast.setText(s);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            oneTime = twoTime;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showToast(Activity activity, @NonNull String res, Drawable icon) {
        if (activity == null) {
            return;
        }
        LayoutInflater inflater = activity.getLayoutInflater();
        View mLayout = inflater.inflate(R.layout.custom_toast_view, activity.findViewById(R.id.rootViewToast));
        TextView textView = mLayout.findViewById(R.id.text);
        textView.setText(res);
        ImageView imageView = mLayout.findViewById(R.id.icon);
        imageView.setImageDrawable(icon);

        if (toastView == null) {
            toastView = new Toast(AppController.getInstance());
            toastView.setDuration(Toast.LENGTH_SHORT);
            toastView.setView(mLayout);
            toastView.setGravity(Gravity.CENTER, 0, 0);
            toastView.show();
            layout = mLayout;
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            TextView text = layout.findViewById(R.id.text);
            if (text.getText().equals(textView.getText())) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toastView.show();
                }
            } else {
                layout = mLayout;
                toastView.setDuration(Toast.LENGTH_SHORT);
                toastView.setView(mLayout);
                toastView.setGravity(Gravity.CENTER, 0, 0);
                toastView.show();
                layout = mLayout;
            }
        }
        oneTime = twoTime;
    }
    public static void showToast(Context context, Integer res) {
        if (null == res) {
            return;
        }
        String s = context.getResources().getString(res);
        showToast(s);
    }

    public static void showToast(Integer res) {
        if (null == res) {
            return;
        }
        String s = AppController.getInstance().getResources().getString(res);
        showToast(s);

    }


    public static void showToast(Context context, String s, int duration) {
        try {
            if (TextUtils.isEmpty(s)) {
                return;
            }
            if (toast == null) {
                toast = Toast.makeText(context, s, duration);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                oneTime = System.currentTimeMillis();
            } else {
                twoTime = System.currentTimeMillis();

                if (s.equals(oldMsg)) {
                    if (twoTime - oneTime > duration) {
                        toast.show();
                    }
                } else {
                    oldMsg = s;
                    toast.setText(s);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
            oneTime = twoTime;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

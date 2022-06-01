package com.kna.appsec.ui.viewcommon;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kna.appsec.R;


public class CustomToast extends Toast {

    public static int SUCCESS = 1;
    public static int WARNING = 2;
    public static int ERROR = 3;
    public static int CONFUSING = 4;

    private static long SHORT = 3000;
    private static long LONG = 7000;

    public CustomToast(Context context) {
        super(context);

    }


    public static Toast makeText(Context context, String message, int duration, int type, boolean androidIcon) {
        Toast toast = new Toast(context);
        toast.setDuration(duration);


        View layout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null, false);
        TextView l1 = layout.findViewById(R.id.tv_toast);
        LinearLayout linearLayout = layout.findViewById(R.id.toast_type);
        ImageView img = layout.findViewById(R.id.toast_icon);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        l1.setText(message);
        switch (type) {
            case 1:
                linearLayout.setBackgroundResource(R.drawable.bg_toast);
                img.setImageResource(R.drawable.ic_done);
                break;
            case 3:
                img.setVisibility(View.GONE);
                break;

        }
        toast.setView(layout);
        return toast;
    }
}

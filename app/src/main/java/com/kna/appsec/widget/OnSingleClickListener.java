package com.kna.appsec.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.databinding.BindingAdapter;

import com.kna.appsec.R;
import com.tsolution.base.listener.AdapterListener;

public abstract class OnSingleClickListener implements View.OnClickListener {


    private static final long MIN_CLICK_INTERVAL = 500;
    private static long current_time = 0l;
    private long mLastClickTime;

    @BindingAdapter({"listener", "object"})
    public static void setOnSingleClickListener(View v, AdapterListener adapterListener, Object o) {
        v.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (checkEnableClick()) {
                    adapterListener.onItemClick(v, o);
                }
            }
        });
    }

    public static boolean checkPermissionLocation(View view) {
        LocationManager lm = (LocationManager) view.getContext().getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        if (!gps_enabled && !network_enabled) {
            // notify user
            new AlertDialog.Builder(view.getContext())
                    .setMessage(R.string.gps_network_not_enabled)
                    .setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            view.getContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton(R.string.Cancel, null)
                    .show();
        }
        return !gps_enabled && !network_enabled;
    }

    @BindingAdapter("listener")
    public static void setOnSingleClickListener(View v, AdapterListener adapterListener) {
        v.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (checkEnableClick()) {
                    Log.e("SINGLE_CLICK", "click");
                    adapterListener.onItemClick(v, null);
                }
            }
        });
    }

    @BindingAdapter({"listener", "isCheckLocationPermission"})
    public static void setOnSingleClickListener(View v, Boolean isCheckLocationPermission, AdapterListener adapterListener) {
        if (checkPermissionLocation(v)) {
            return;
        }
        v.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                if (checkEnableClick()) {
                    Log.e("SINGLE_CLICK", "click");
                    adapterListener.onItemClick(v, null);
                }
            }
        });
    }

    public static boolean checkEnableClick() {
        if (SystemClock.elapsedRealtime() - current_time < 1000) {
            return false;
        }
        current_time = SystemClock.elapsedRealtime();
        return true;
    }

    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
//        if (checkPermissionLocation(v)) {
//            return;
//        }
        long currentClickTime = SystemClock.elapsedRealtime();
        long elapsedTime = currentClickTime - mLastClickTime;
        mLastClickTime = currentClickTime;
        if (elapsedTime <= MIN_CLICK_INTERVAL)
            return;
        onSingleClick(v);
    }

}
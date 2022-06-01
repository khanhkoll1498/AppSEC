package com.kna.appsec.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Pair;

import com.kna.appsec.R;
import com.kna.appsec.utils.NetworkUtils;
import com.kna.appsec.utils.ToastUtils;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.CommonActivity;
import com.workable.errorhandler.ErrorHandler;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;


public class AppController extends MyApplication {
    public static final String APP_INFO = "A31b4c24l3kj35d4AKJQ";
    public static final String DATE_PATTERN = "dd/MM/yyyy";
    public static final String DATE_TO_JSON = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "HH:mm '-' dd/MM/yyyy";
    public static final String TIME_PATTERN = "HH:mm";
    public static final String DATE_PATTERN_GSON = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String ODOO_DATE_TIME = "yyyy-MM-dd '-' HH:mm:ss";
    public static final String CACHE_USER = "CACHE_USER";
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatDate = new SimpleDateFormat(AppController.DATE_PATTERN);
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatDateToJson = new SimpleDateFormat(AppController.DATE_TO_JSON);
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatDateTime = new SimpleDateFormat(AppController.DATE_TIME_PATTERN);
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatTime = new SimpleDateFormat(AppController.TIME_PATTERN);
    @SuppressLint("SimpleDateFormat")
    public static final SimpleDateFormat formatTimeOdooDateTime = new SimpleDateFormat(AppController.ODOO_DATE_TIME);
    public static Long languageId = 1L;
    public static String[] LANGUAGE_CODE = {"vi", "en", "my"};
    private static AppController mInstance;
    HashMap<String, Object> clientCache;
    private SharedPreferences sharedPreferences;
    private DecimalFormat formatter;


    public static synchronized AppController getInstance() {
        return mInstance;
    }

    @SuppressLint("DefaultLocale")
    public static String formatNumberV2(double number) {
        if (number >= 1000000000) {
            return String.format("%.1fT", number / 1000000000.0);
        }

        if (number >= 1000000) {
            return String.format("%.1fB", number / 1000000.0);
        }

        if (number >= 100000) {
            return String.format("%.1fM", number / 100000.0);
        }

        if (number >= 1000) {
            return String.format("%.1fK", number / 1000.0);
        }
        return String.valueOf(number);
    }

    public static String minuteToHour(int minute) {
        String formatted;
        int hours = minute / 60; //since both are ints, you get an int
        int minutes = minute % 60;
        if (hours == 0) {
            formatted = minutes + "'";
        } else {
            formatted = hours + "h, " + minutes + "'";
        }
        return formatted;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        sharedPreferences = getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        clientCache = new HashMap<>();
        ErrorHandler
                .defaultErrorHandler()
                // Always log to a crash/error reporting service
                .always((throwable, errorHandler) -> {
                    throwable.printStackTrace();
                    if (!NetworkUtils.isNetworkConnected(this)) {
                        EventBus.getDefault().post(Constants.DISCONNECTED_NETWORK);
                        ToastUtils.showToast(getResources().getString(R.string.network_error));
                    }
                });

    }

    public boolean isServiceRunning(Class<? extends Service> clazz) {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo service :
                    activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (clazz.getName().equals(service.service.getClassName())) {
                    if (service.foreground) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public boolean checkHighAccuracyLocationMode() {
        try {
            int locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
            if (locationMode == Settings.Secure.LOCATION_MODE_HIGH_ACCURACY) {
                return true;
            }
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public SharedPreferences getSharePre() {
        if (sharedPreferences == null) {
            sharedPreferences = getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        if (sharedPreferences == null) {
            sharedPreferences = getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        }
        return sharedPreferences.edit();
    }

    public String formatNumber(Double money) {
        if (money == null) {
            return "0";
        }
        if (formatter == null) {
            Locale locale = new Locale("en", "UK");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
            formatter = new DecimalFormat("###,###,###.##", symbols);
        }

        return formatter.format(money);
    }

    public String formatNumber(Float money) {
        if (money == null) {
            return "0";
        }
        if (formatter == null) {
            Locale locale = new Locale("en", "UK");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
            formatter = new DecimalFormat("###,###,###.##", symbols);
        }

        return formatter.format(money);
    }

    public void putCache(String key, Object obj) {
        if (clientCache == null) {
            clientCache = new HashMap<>();
        }
        clientCache.put(key, obj);
    }

    public Object getFromCache(String key) {
        if (clientCache == null) {
            return null;
        }
        return clientCache.get(key);
    }

    public void clearCache() {
        clientCache = new HashMap<>();
    }

    public void newActivity(Activity previousActivity, Class fragmentClazz, String extra, Serializable objExtra) {
        Intent intent = new Intent(previousActivity, CommonActivity.class);
        intent.putExtra(StaticData.FRAGMENT, fragmentClazz);
        if (extra != null) {
            intent.putExtra(extra, objExtra);
        }
        previousActivity.startActivity(intent);
    }


    public void newActivity(BaseFragment fragment, Class fragmentClazz, boolean result, int code, Pair<String, Serializable>... objs) {
        Intent intent = new Intent(fragment.getBaseActivity(), CommonActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(StaticData.FRAGMENT, fragmentClazz);
        if (objs != null) {
            for (Pair<String, Serializable> obj : objs) {
                intent.putExtra(obj.first, obj.second);
            }
        }
        if (result) {
            fragment.startActivityForResult(intent, code);
        } else {
            fragment.startActivity(intent);
        }
    }


}

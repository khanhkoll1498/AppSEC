package com.kna.appsec.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kna.appsec.R;
import com.kna.appsec.adapter.XBaseAdapter;
import com.kna.appsec.base.AppController;
import com.kna.appsec.model.Topic;
import com.ns.odoolib_retrofit.utils.OdooDateTime;
import com.tsolution.base.listener.AdapterListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class BindingAdapterUtils {

    @BindingAdapter(value = "imageDrawableRes")
    public static void showImage(ImageView view, String image) {
        RequestOptions options = new RequestOptions().skipMemoryCache(true).override(200, 200).placeholder(R.drawable.ic_avt);
        Glide.with(view.getContext())
                .load(image)
                .apply(options)
                .into(view);
    }

    /**
     * set toDate->FromDate cho item Bidding
     *
     * @param view
     * @param fromTime
     * @param toTime
     */
    @BindingAdapter({"setTimeBidding", "toTime"})
    public static void setTimeBidding(TextView view, OdooDateTime fromTime, OdooDateTime toTime) {
        if (fromTime == null && toTime != null)
            view.setText(AppController.formatDateTime.format(toTime));
        if (fromTime != null && toTime == null)
            view.setText(AppController.formatDateTime.format(fromTime));
        if (fromTime != null && toTime != null)
            view.setText(AppController.formatDateTime.format(fromTime) + " - " + AppController.formatDateTime.format(toTime));
        if (fromTime == null && toTime == null)
            view.setText("");
    }

    public static Long cvDateToTimeMillis(Date maxdate) {
        long millis = maxdate.getTime();
        Calendar calendar = Calendar.getInstance();
        long timeNow = calendar.getTimeInMillis();
        long maxDate = millis - timeNow;
        return maxDate;
    }

    @SuppressLint("DefaultLocale")
    private static String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }

    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        if (StringUtils.isNullOrEmpty(imageUrl)) {
            view.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.not_img));
            return;
        }
        loadImage(view, imageUrl, view.getContext());
    }

    @BindingAdapter({"imageUrl", "hintIcon"})
    public static void loadImage(ImageView view, String imageUrl, Drawable hintIcon) {
        if (StringUtils.isNullOrEmpty(imageUrl)) {
            view.setImageDrawable(hintIcon);
            return;
        }
        loadImage(view, imageUrl, view.getContext());
    }

    private static void loadImage(ImageView imgReward, String icon, Context context) {
//        GlideUrl url = new GlideUrl(AppController.HTTP + icon, new LazyHeaders.Builder()
//                .addHeader("Cookie", StaticData.sessionCookie)
//                .build());
        Glide.with(context)
                .load(icon)
                .error(R.drawable.not_img)
                .into(imgReward);
    }

    private static void setSpanStringColor(TextView view, String fulltext, String subtext, int color, int size) {
        view.setText(fulltext, TextView.BufferType.SPANNABLE);
        Spannable str = (Spannable) view.getText();
        int i = fulltext.indexOf(subtext);
        str.setSpan(new ForegroundColorSpan(color), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (size != 0)
            str.setSpan(new RelativeSizeSpan(0.8f), i, i + subtext.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    @BindingAdapter("child_recycleView")
    public static void child_recycleView(RecyclerView view, List<Topic> list) {
        if (list != null) {
            XBaseAdapter xBaseAdapter = new XBaseAdapter(R.layout.item_topic, list, new AdapterListener() {
                @Override
                public void onItemClick(View view, Object o) {

                }

                @Override
                public void onItemLongClick(View view, Object o) {

                }
            });
            view.setAdapter(xBaseAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
            view.setLayoutManager(layoutManager);
        }
    }
}

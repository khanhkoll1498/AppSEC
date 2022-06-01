package com.kna.appsec.utils;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.kna.appsec.R;
import com.kna.appsec.widget.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class ZoomImage {
    public static void zoom(Activity activity, String url) {
        List<LocalMedia> localMedia = new ArrayList<>();
        LocalMedia media = new LocalMedia();
        media.setPath(url);
        localMedia.add(media);
        try {
            PictureSelector.create(activity)
                    .themeStyle(R.style.picture_WeChat_style)
                    .isWeChatStyle(true)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, localMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zoom(Fragment activity, String url) {
        List<LocalMedia> localMedia = new ArrayList<>();
        LocalMedia media = new LocalMedia();
        media.setPath(url);
        localMedia.add(media);
        try {
            PictureSelector.create(activity)
                    .themeStyle(R.style.picture_WeChat_style)
                    .isWeChatStyle(true)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, localMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zoom(Activity activity, List<String> url) {
        List<LocalMedia> localMedia = new ArrayList<>();
        for (String item : url) {
            LocalMedia media = new LocalMedia();
            media.setPath(item);
            localMedia.add(media);
        }
        try {
            PictureSelector.create(activity)
                    .themeStyle(R.style.picture_WeChat_style)
                    .isWeChatStyle(true)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, localMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void zoom(Fragment activity, List<String> url) {
        List<LocalMedia> localMedia = new ArrayList<>();
        for (String item : url) {
            LocalMedia media = new LocalMedia();
            media.setPath(item);
            localMedia.add(media);
        }
        try {
            PictureSelector.create(activity)
                    .themeStyle(R.style.picture_WeChat_style)
                    .isWeChatStyle(true)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(0, localMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

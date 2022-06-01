package com.kna.appsec.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kna.appsec.R;
import com.kna.appsec.widget.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.tsolution.base.BR;

import java.util.ArrayList;
import java.util.List;

public class ImageBaseAdapter extends RecyclerView.Adapter<ImageBaseAdapter.ViewHolder> {
    private List<String> datas;
    private int layoutItem;
    private List<LocalMedia> localMedia = new ArrayList<>();
    private Activity activity;
    private boolean isClick = true;

    public ImageBaseAdapter(Activity activity, @LayoutRes int item, List<String> lst) {
        this.layoutItem = item;
        this.datas = lst;
        this.activity = activity;
    }


    public ImageBaseAdapter(Activity activity, @LayoutRes int item, List<String> lst, boolean isClick) {
        this.layoutItem = item;
        this.datas = lst;
        this.activity = activity;
        this.isClick = isClick;
    }

    public void notifyDataSetChange() {
        if (datas == null) {
            return;
        }
        super.notifyDataSetChanged();
        localMedia.clear();
        for (String url : datas) {
            LocalMedia urlImage = new LocalMedia();
            urlImage.setPath(url);
            localMedia.add(urlImage);
        }
    }

    public void notifyItemInsert(int position) {
        if (datas == null) {
            return;
        }
        super.notifyItemInserted(position);
        LocalMedia urlImage = new LocalMedia();
        urlImage.setPath(datas.get(position));
        localMedia.add(urlImage);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), this.layoutItem, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        viewHolder.bind(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return this.datas == null ? 0 : (this.datas).size();
    }

    private void previewImage(int position) {
        try {
            PictureSelector.create(activity)
                    .themeStyle(R.style.picture_default_style)
                    .isNotPreviewDownload(true)
                    .loadImageEngine(GlideEngine.createGlideEngine())
                    .openExternalPreview(position, localMedia);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding itemProductBinding;

        public ViewHolder(ViewDataBinding view) {
            super(view.getRoot());
            this.itemProductBinding = view;
            view.getRoot().setOnClickListener(v -> {
                if (isClick) {
                    previewImage(getAdapterPosition());
                }
            });
        }

        public void bind(Object obj) {
            itemProductBinding.setVariable(BR.viewHolder, obj);
            itemProductBinding.executePendingBindings();
        }

    }

}

package com.kna.appsec.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.kna.appsec.BR;
import com.tsolution.base.BaseModel;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.listener.AdapterListener;

import java.util.List;

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.ViewHolder> implements AdapterListener {
    ViewDataBinding binding;
    private final AdapterListener adapterListener;
    private List datas;
    private final int layoutItem;
    private Context mContext;

    public TabAdapter(@LayoutRes int item, List lst, AdapterListener listener) {
        this.layoutItem = item;
        this.datas = lst;
        this.adapterListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        mContext = viewGroup.getContext();
        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), this.layoutItem, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        if (getItemCount() > 0 && getItemCount() < 4) {
            int width = Resources.getSystem().getDisplayMetrics().widthPixels;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width / getItemCount(), LinearLayout.LayoutParams.WRAP_CONTENT);
            viewHolder.itemProductBinding.getRoot().setLayoutParams(layoutParams);
        }
        BaseModel bm = (BaseModel) (this.datas).get(position);
        bm.index = position + 1;
        viewHolder.bind(bm);


    }

    @Override
    public int getItemCount() {
        return this.datas == null ? 0 : datas.size();
    }

    public void onItemClick(View v, Object o) {
        if (adapterListener != null) {
            adapterListener.onItemClick(v, o);
        }
    }

    @Override
    public void onItemLongClick(View view, Object o) {

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setUpDateDat(List datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding itemProductBinding;

        public ViewHolder(ViewDataBinding view) {
            super(view.getRoot());
            this.itemProductBinding = view;
            view.getRoot().setOnLongClickListener(v -> {
                adapterListener.onItemLongClick(v, datas.get(getAdapterPosition()));
                return true;
            });

            view.getRoot().setOnClickListener(v -> {
                adapterListener.onItemClick(v, datas.get(getAdapterPosition()));
            });

        }

        public void bind(Object obj) {
            itemProductBinding.setVariable(BR.viewHolder, obj);
            itemProductBinding.setVariable(BR.listener, adapterListener);
            itemProductBinding.executePendingBindings();
        }

        public void bindWithVM(BaseViewModel viewModel) {
            itemProductBinding.setVariable(BR.viewModel, viewModel);
        }
    }
}

package com.kna.appsec.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.tsolution.base.BR;
import com.tsolution.base.BaseModel;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.listener.AdapterListener;

import java.util.List;

public class XBaseAdapter extends RecyclerView.Adapter<XBaseAdapter.ViewHolder> implements AdapterListener {
    private AdapterListener adapterListener;
    private List datas;
    private int layoutItem;

    public XBaseAdapter(@LayoutRes int item, List lst, AdapterListener listener) {
        this.layoutItem = item;
        this.datas = lst;
        this.adapterListener = listener;
    }

    public void update(List list) {
        this.datas = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), this.layoutItem, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        BaseModel bm = (BaseModel) (this.datas).get(position);
        bm.index = position + 1;
        viewHolder.bind(bm);
    }

    @Override
    public int getItemCount() {
        return this.datas == null ? 0 : (this.datas).size();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewDataBinding itemProductBinding;

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
//            itemProductBinding.setVariable(BR.viewModel, viewModel);
            itemProductBinding.setVariable(BR.listener, adapterListener);
            itemProductBinding.executePendingBindings();
        }

        public void bindWithVM(BaseViewModel viewModel) {
            itemProductBinding.setVariable(BR.viewModel, viewModel);
        }
    }

}

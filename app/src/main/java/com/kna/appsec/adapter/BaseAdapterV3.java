package com.kna.appsec.adapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.kna.appsec.BR;
import com.tsolution.base.listener.AdapterListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BaseAdapterV3<T> extends BaseQuickAdapter<T, BaseDataBindingHolder> implements LoadMoreModule {
    private AdapterListener listenerAdapter;
    private List<T> data;

    public BaseAdapterV3(@LayoutRes int itemLayoutId) {
        super(itemLayoutId);
    }

    public BaseAdapterV3(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    public BaseAdapterV3(@LayoutRes int layoutResId, @Nullable List<T> data, AdapterListener listener) {
        super(layoutResId, data);
        this.listenerAdapter = listener;
        this.data = data;
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder holder, T item, @NotNull List<?> payloads) {
        super.convert(holder, item, payloads);
    }

    @Override
    protected void convert(@NonNull BaseDataBindingHolder holder, T o) {
        //  Binding

        ViewDataBinding binding = holder.getDataBinding();
        if (binding != null) {
            binding.setVariable(BR.viewHolder, o);
            if (listenerAdapter != null) {
                binding.setVariable(BR.listener, this.listenerAdapter);
            }
            binding.executePendingBindings();
        }

    }
}

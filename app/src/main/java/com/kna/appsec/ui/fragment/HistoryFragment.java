package com.kna.appsec.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kna.appsec.R;
import com.kna.appsec.adapter.XBaseAdapter;
import com.kna.appsec.databinding.FragmentHistoryBinding;
import com.kna.appsec.viewmodel.HistoryFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

public class HistoryFragment extends BaseFragment<FragmentHistoryBinding> {
    HistoryFrgVM historyFrgVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        historyFrgVM = (HistoryFrgVM) viewModel;

        initRCViewHistory();

        return v;
    }

    private void initRCViewHistory() {
        XBaseAdapter xBaseAdapter = new XBaseAdapter(R.layout.item_history, historyFrgVM.getList(), this);

        binding.rcHistory.setAdapter(xBaseAdapter);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_history;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return HistoryFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

package com.kna.appsec.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kna.appsec.R;
import com.kna.appsec.adapter.XBaseAdapter;
import com.kna.appsec.databinding.FragmentVocabularyBinding;
import com.kna.appsec.utils.ToastUtils;
import com.kna.appsec.viewmodel.VocaFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

public class VocabularyFragment extends BaseFragment<FragmentVocabularyBinding> {

    VocaFrgVM vocaFrgVM;
    XBaseAdapter xBaseAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        vocaFrgVM = (VocaFrgVM) viewModel;

        initRCVoca();

        vocaFrgVM.getData(this::runUi);

        return v;
    }

    private void initRCVoca() {
        xBaseAdapter = new XBaseAdapter(R.layout.item_word, vocaFrgVM.getList(), this);

        binding.rcVoca.setAdapter(xBaseAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void runUi(Object[] objects) {
        String action = (String) objects[0];
        if (action.equals("getData")) {
            xBaseAdapter.notifyDataSetChanged();
            Log.i(GrammarFragment.class.getName(), "runUi: ...");
        } else {
            ToastUtils.showToast("Error");
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_vocabulary;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return VocaFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

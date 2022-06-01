package com.kna.appsec.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.kna.appsec.R;
import com.kna.appsec.adapter.XBaseAdapter;
import com.kna.appsec.databinding.FragmentTestWordBinding;
import com.kna.appsec.utils.ToastUtils;
import com.kna.appsec.viewmodel.TestWordFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

public class TestWordFragment extends BaseFragment<FragmentTestWordBinding> {

    TestWordFrgVM testWordFrgVM;
    private XBaseAdapter xBaseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        testWordFrgVM = (TestWordFrgVM) viewModel;

        testWordFrgVM.getData(this::runUi);

        initRCTestWord();

        return v;
    }

    private void runUi(Object[] objects) {
        String action = (String) objects[0];
        if (action.equals("getData")) {
            Log.i(GrammarFragment.class.getName(), "runUi: ...");
        } else {
            ToastUtils.showToast("Error");
        }
    }

    private void initRCTestWord() {
        xBaseAdapter = new XBaseAdapter(R.layout.item_test_word, testWordFrgVM.getList(), this);
        binding.rcTestWord.setLayoutManager(new GridLayoutManager(getContext(), 2));

        binding.rcTestWord.setAdapter(xBaseAdapter);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_test_word;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return TestWordFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

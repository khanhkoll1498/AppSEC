package com.kna.appsec.ui.fragment;

import com.kna.appsec.R;
import com.kna.appsec.databinding.FragmentTestBinding;
import com.kna.appsec.viewmodel.TestFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

public class TestFragment extends BaseFragment<FragmentTestBinding> {
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_test;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return TestFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

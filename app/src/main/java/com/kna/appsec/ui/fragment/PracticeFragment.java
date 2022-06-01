package com.kna.appsec.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kna.appsec.R;
import com.kna.appsec.adapter.PagerAdapter;
import com.kna.appsec.databinding.FragmentPracticeBinding;
import com.kna.appsec.viewmodel.PracticeFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

public class PracticeFragment extends BaseFragment<FragmentPracticeBinding> {
    private TestWordFragment mTestWordFragment;
    private HistoryFragment mHistoryFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        initViewPagerTabLayout();

        return v;
    }

    private void initViewPagerTabLayout() {
        PagerAdapter mPagerAdapter = new PagerAdapter(getChildFragmentManager());

        mTestWordFragment = new TestWordFragment();
        mHistoryFragment = new HistoryFragment();
        mPagerAdapter.addFragment(mTestWordFragment);
        mPagerAdapter.addFragment(mHistoryFragment);

        binding.viewPager.setAdapter(mPagerAdapter);
        binding.viewPager.setOffscreenPageLimit(2);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_practice;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return PracticeFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

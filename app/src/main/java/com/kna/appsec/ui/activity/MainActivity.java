package com.kna.appsec.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;

import com.kna.appsec.R;
import com.kna.appsec.adapter.PagerAdapter;
import com.kna.appsec.databinding.ActivityMainBinding;
import com.kna.appsec.ui.fragment.PracticeFragment;
import com.kna.appsec.ui.fragment.HomeFragment;
import com.kna.appsec.ui.fragment.ProgrammesFragment;
import com.kna.appsec.ui.fragment.TestFragment;
import com.kna.appsec.viewmodel.MainActVM;
import com.tsolution.base.BaseActivity;
import com.tsolution.base.BaseViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private MenuItem preItem;
    private HomeFragment mHomeFrg;
    private TestFragment mTestFrg;
    private ProgrammesFragment mProgrammesFrg;
    private PracticeFragment mPracticeFrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        navigationView();
    }

    @SuppressLint("NonConstantResourceId")
    private void navigationView() {
        PagerAdapter myPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mHomeFrg = new HomeFragment();
        mProgrammesFrg = new ProgrammesFragment();
        mPracticeFrg = new PracticeFragment();
        mTestFrg = new TestFragment();

        myPagerAdapter.addFragment(mHomeFrg);
        myPagerAdapter.addFragment(mProgrammesFrg);
        myPagerAdapter.addFragment(mPracticeFrg);
        myPagerAdapter.addFragment(mTestFrg);

        binding.mainViewPager.setAdapter(myPagerAdapter);

        binding.navigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.actionHome:
                    binding.mainViewPager.setCurrentItem(0);
                    break;
                case R.id.actionProgrammes:
                    binding.mainViewPager.setCurrentItem(1);
                    break;
                case R.id.actionPractice:
                    binding.mainViewPager.setCurrentItem(2);
                    break;
                case R.id.actionTest:
                    binding.mainViewPager.setCurrentItem(3);
                    break;
            }
            return false;
        });
        binding.mainViewPager.setOffscreenPageLimit(4);
        binding.mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (preItem != null) {
                    preItem.setChecked(false);
                } else {
                    binding.navigation.getMenu().getItem(0).setChecked(false);
                }
                binding.navigation.getMenu().getItem(position).setChecked(true);
                preItem = binding.navigation.getMenu().getItem(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return MainActVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}
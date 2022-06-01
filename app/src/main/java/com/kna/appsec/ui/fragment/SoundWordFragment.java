package com.kna.appsec.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kna.appsec.R;
import com.kna.appsec.base.Constants;
import com.kna.appsec.databinding.FragmentSoundWordBinding;
import com.kna.appsec.utils.ToastUtils;
import com.kna.appsec.viewmodel.SoundWordFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.CommonActivity;

import java.util.Objects;

public class SoundWordFragment extends BaseFragment<FragmentSoundWordBinding> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        initToolBar();
        return v;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void initToolBar() {
        binding.toolbar.setTitle(R.string.sound_word);
        getBaseActivity().setSupportActionBar(binding.toolbar);
        if (Objects.requireNonNull(getActivity()).getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> getBaseActivity().onBackPressed());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClick(View v, Object o) {
        super.onItemClick(v, o);
        switch (v.getId()) {
            case R.id.tv_stress:
                ToastUtils.showToast("Stress");
                goToFileAssetPDF("grammar/stress.pdf");
                break;
            case R.id.tv_alphabet:
                ToastUtils.showToast("Alphabet");
                goToFileAssetPDF("grammar/IPA.pdf");
                break;
            default:
                break;
        }
    }

    private void goToFileAssetPDF(String path) {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_NOUN, path);
        bundle.putSerializable(Constants.FRAGMENT, ContentFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_sound_word;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return SoundWordFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

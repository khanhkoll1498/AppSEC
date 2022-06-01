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
import com.kna.appsec.databinding.FragmentProgrammesBinding;
import com.kna.appsec.viewmodel.ProgrammesFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.CommonActivity;

public class ProgrammesFragment extends BaseFragment<FragmentProgrammesBinding> {
    private ProgrammesFrgVM programmesFrgVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        programmesFrgVM = (ProgrammesFrgVM) viewModel;
        return v;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onItemClick(View v, Object o) {
        switch (v.getId()) {
            case R.id.tv_grammar:
                goToGrammarFragment();
                break;
            case R.id.tv_vocabulary:
                goToVocabularyFragment();
                break;
        }
    }

    private void goToVocabularyFragment() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, VocabularyFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToGrammarFragment() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, GrammarFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_programmes;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return ProgrammesFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

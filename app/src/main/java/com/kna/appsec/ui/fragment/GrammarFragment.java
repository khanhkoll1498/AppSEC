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
import com.kna.appsec.databinding.FragmentGrammarBinding;
import com.kna.appsec.viewmodel.GrammarFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.CommonActivity;

import java.util.Objects;

public class GrammarFragment extends BaseFragment<FragmentGrammarBinding> {

    GrammarFrgVM grammarFrgVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        grammarFrgVM = (GrammarFrgVM) viewModel;

        initToolBar();
        return v;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void initToolBar() {
        binding.toolbar.setTitle(R.string.Grammar);
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
            case R.id.tv_base_eng:
                goToFrgBaseEnglish();
                break;
            case R.id.tv_clauses:
                goToClauseFragment();
                break;
            case R.id.tv_sound:
                goToAccentFragment();
                break;
            default:
        }
    }

    private void goToAccentFragment() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, BaseEnglishFragment.class);
        bundle.putString(Constants.KEY_BASE,"SOUND");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToClauseFragment() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, BaseEnglishFragment.class);
        bundle.putString(Constants.KEY_BASE,"CLAUSE");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToFrgBaseEnglish() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, BaseEnglishFragment.class);
        bundle.putString(Constants.KEY_BASE,"BASE_ENGLISH");
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_grammar;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return GrammarFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

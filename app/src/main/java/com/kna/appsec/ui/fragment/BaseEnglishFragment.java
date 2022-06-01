package com.kna.appsec.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;

import com.kna.appsec.R;
import com.kna.appsec.adapter.XBaseAdapter;
import com.kna.appsec.base.Constants;
import com.kna.appsec.databinding.FragmentBaseEnglishBinding;
import com.kna.appsec.model.Topic;
import com.kna.appsec.utils.ToastUtils;
import com.kna.appsec.viewmodel.BaseEnglishFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;
import com.tsolution.base.CommonActivity;

import java.util.Objects;

public class BaseEnglishFragment extends BaseFragment<FragmentBaseEnglishBinding> {
    BaseEnglishFrgVM baseEnglishFrgVM;
    XBaseAdapter xBaseAdapter;
    private String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        baseEnglishFrgVM = (BaseEnglishFrgVM) viewModel;
        baseEnglishFrgVM.getData(this::runUi);

        initToolbar();

        getDataBundle();

        if (path.equals("BASE_ENGLISH")) {
            getData(baseEnglishFrgVM.getList());
        }
        if (path.equals("CLAUSE")) {
            getData(baseEnglishFrgVM.getListClause());
        }
        if (path.equals("SOUND")) {
            getData(baseEnglishFrgVM.getListPronunciation());
        }

        return v;
    }

    private void getData(ObservableList<Topic> list) {
        xBaseAdapter = new XBaseAdapter(R.layout.item_topic, list, this);
        binding.rcTopic.setAdapter(xBaseAdapter);
    }

    private void getDataBundle() {
        @SuppressLint("UseRequireInsteadOfGet") Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        if (intent.getExtras() != null) {
            path = intent.getStringExtra(Constants.KEY_BASE);
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private void initToolbar() {
        binding.toolbar.setTitle(R.string.content);
        getBaseActivity().setSupportActionBar(binding.toolbar);
        if (Objects.requireNonNull(getActivity()).getActionBar() != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            getActivity().getActionBar().setDisplayShowHomeEnabled(true);
        }
        binding.toolbar.setNavigationOnClickListener(v -> getBaseActivity().onBackPressed());
    }

    private void runUi(Object[] objects) {
        String action = (String) objects[0];
        if (action.equals("getData")) {
            Log.i(GrammarFragment.class.getName(), "runUi: ...");
        } else {
            ToastUtils.showToast("Error");
        }
    }

    @Override
    public void onItemClick(View v, Object o) {
        super.onItemClick(v, o);
        Topic topic = (Topic) o;

        checkTopic(topic.index);
    }

    private void checkTopic(Integer numberTopic) {
        switch (path) {
            case "BASE_ENGLISH":
                checkNumberTopicBaseEnglish(numberTopic);
                break;
            case "CLAUSE":
                checkNumberTopicClauseEnglish(numberTopic);
                break;
            case "SOUND":
                checkNumberTopicSound(numberTopic);
                break;
            default:
                break;
        }
    }

    private void checkNumberTopicSound(Integer numberTopic) {
        switch (numberTopic) {
            case 1:
                goToFragmentSoundWord();
                break;
            case 2:
                goToFragmentSoundSentences();
                break;
            default:
                break;
        }
    }

    private void goToFragmentSoundSentences() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, SoundSentencesFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void goToFragmentSoundWord() {
        Intent intent = new Intent(getContext(), CommonActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.FRAGMENT, SoundWordFragment.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void checkNumberTopicClauseEnglish(Integer numberTopic) {
        switch (numberTopic) {
            case 1:
                goToFileAssetPDF("grammar/DCadj.pdf");
                break;
            case 2:
                goToFileAssetPDF("grammar/DCadv.pdf");
                break;
            case 3:
                goToFileAssetPDF("grammar/DCn.pdf");
                break;
            case 4:
                goToFileAssetPDF("grammar/Overview_DC.pdf");
                break;
            default:
                break;
        }
    }

    private void checkNumberTopicBaseEnglish(Integer numberTopic) {
        switch (numberTopic) {
            case 1:
                goToFileAssetPDF("grammar/nouns.pdf");
                break;
            case 2:
                goToFileAssetPDF("grammar/adjectives.pdf");
                break;
            case 3:
                goToFileAssetPDF("grammar/verbs.pdf");
                break;
            case 4:
                goToFileAssetPDF("grammar/adverbs.pdf");
                break;
            case 5:
                goToFileAssetPDF("grammar/prepositions.pdf");
                break;
            case 6:
                goToFileAssetPDF("grammar/relevant_knowledge.pdf");
                break;
            case 7:
                goToFileAssetPDF("grammar/spell_irregular_phrasal.pdf");
                break;
            case 8:
                goToFileAssetPDF("grammar/english_overview.pdf");
                break;
            default:
                ToastUtils.showToast("Error");
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
        return R.layout.fragment_base_english;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return BaseEnglishFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

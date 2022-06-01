package com.kna.appsec.ui.viewcommon;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.kna.appsec.R;
import com.kna.appsec.databinding.DialogConfirmBinding;
import com.kna.appsec.utils.StringUtils;

public class DialogConfirm extends DialogFragment {
    private String title;
    private String actionTitle;
    private String msg;
    private View.OnClickListener onClickListener;
    private boolean isCancelable;

    public DialogConfirm(String title, String actionTitle, String msg, boolean isCancelable, View.OnClickListener onClickListener) {
        this.title = title;
        this.actionTitle = actionTitle;
        this.msg = msg;
        this.onClickListener = onClickListener;
        this.isCancelable = isCancelable;
    }

    public DialogConfirm(@NonNull String title, String msg, boolean isCancelable, View.OnClickListener onClickListener) {
        this.msg = msg;
        this.title = title;
        this.onClickListener = onClickListener;
        this.isCancelable = isCancelable;
    }

    public DialogConfirm() {
        this.msg = msg;
        this.title = title;
        this.onClickListener = onClickListener;
        this.isCancelable = true;
    }

    public DialogConfirm(@NonNull String title, String msg, String actionTitle, View.OnClickListener onClickListener) {
        this.msg = msg;
        this.title = title;
        this.onClickListener = onClickListener;
        this.actionTitle = actionTitle;
        this.isCancelable = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogConfirmBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_confirm, container, false);
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        setCancelable(isCancelable);
        binding.btnCancel.setVisibility(isCancelable ? View.VISIBLE : View.GONE);

        binding.txtTitle.setText(title);

        if (actionTitle != null) {
            binding.btnConfirm.setText(actionTitle);
        }
        if (StringUtils.isNullOrEmpty(msg)) {
            binding.txtMsg.setVisibility(View.GONE);
        } else {
            binding.txtMsg.setText(msg);
        }
        binding.btnDismiss.setOnClickListener(v -> this.dismiss());
        binding.btnCancel.setOnClickListener(v -> this.dismiss());
        binding.btnConfirm.setOnClickListener(onClickListener);
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
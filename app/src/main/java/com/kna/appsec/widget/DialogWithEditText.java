package com.kna.appsec.widget;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.kna.appsec.R;
import com.kna.appsec.databinding.DialogWithEditTextBinding;


@SuppressLint("ValidFragment")
public class DialogWithEditText extends DialogFragment {
    DialogWithEditTextBinding binding;
    private String title, msg, hintEdit, edit;
    private OnConfirm onClickListener;
    private int lengthEditText;

    /**
     * @param title           title
     * @param msg             message
     * @param onClickListener event
     */
    public DialogWithEditText(String title, String msg, OnConfirm onClickListener) {
        super();
        this.title = title;
        this.msg = msg;
        this.onClickListener = onClickListener;
    }

    /**
     * @param title           title
     * @param msg             message
     * @param hintEdit        hint
     * @param onClickListener event
     */
    public DialogWithEditText(String title, String msg, String hintEdit, OnConfirm onClickListener) {
        super();
        this.title = title;
        this.msg = msg;
        this.hintEdit = hintEdit;
        this.onClickListener = onClickListener;
    }

    /**
     * @param title           title
     * @param msg             message
     * @param hintEdit        hint
     * @param lengthEditText  length limit
     * @param onClickListener event
     */
    public DialogWithEditText(String title, String msg, String hintEdit, int lengthEditText, OnConfirm onClickListener) {
        super();
        this.title = title;
        this.msg = msg;
        this.hintEdit = hintEdit;
        this.lengthEditText = lengthEditText;
        this.onClickListener = onClickListener;
    }

    /**
     * @param title           title
     * @param msg             message
     * @param hint            hint
     * @param edit            fill text
     * @param lengthEditText  length limit
     * @param onClickListener event
     */
    public DialogWithEditText(String title, String msg, String hint, String edit, int lengthEditText, OnConfirm onClickListener) {
        super();
        this.title = title;
        this.msg = msg;
        this.hintEdit = hint;
        this.edit = edit;
        this.lengthEditText = lengthEditText;
        this.onClickListener = onClickListener;
    }

    /**
     * @param title           title
     * @param msg             message
     * @param hint            hint
     * @param edit            text
     * @param onClickListener event
     */
    public DialogWithEditText(String title, String msg, String hint, String edit, OnConfirm onClickListener) {
        super();
        this.title = title;
        this.msg = msg;
        this.hintEdit = hint;
        this.edit = edit;
        this.onClickListener = onClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_with_edit_text, container, false);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        binding.txtMessage.setText(msg);
        if (hintEdit != null) {
            binding.txtEditText.setHint(hintEdit);
        }
        if (edit != null) {
            binding.txtEditText.setText(edit);
        }
        if (lengthEditText > 0) {
            binding.txtEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(lengthEditText)});
        }

        binding.txtEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    binding.btnConfirm.setEnabled(true);
                } else {
                    binding.btnConfirm.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.btnDismiss.setOnClickListener(v -> this.dismiss());
        binding.btnCancel.setOnClickListener(v -> this.dismiss());
        binding.btnConfirm.setOnClickListener(v -> {
            this.dismiss();
            onClickListener.getEditText(binding.txtEditText);
        });
        binding.txtTitle.setText(title);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public interface OnConfirm {
        void getEditText(EditText editText);
    }

}

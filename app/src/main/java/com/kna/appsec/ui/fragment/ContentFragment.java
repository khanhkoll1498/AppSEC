package com.kna.appsec.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.github.barteksc.pdfviewer.listener.OnRenderListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.kna.appsec.R;
import com.kna.appsec.base.Constants;
import com.kna.appsec.databinding.ContentFragmentBinding;
import com.kna.appsec.utils.ToastUtils;
import com.kna.appsec.viewmodel.ContentFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

import java.util.Objects;

public class ContentFragment extends BaseFragment<ContentFragmentBinding> {
    ContentFrgVM contentFrgVM;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        contentFrgVM = (ContentFrgVM) viewModel;

        getDataBundle();

        return v;
    }

    private void readFile(String path) {
        binding.pdfViewer.fromAsset(path)
                .password(null)
                .defaultPage(4)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .onDraw(new OnDrawListener() {
                    @Override
                    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                    }
                }).onDrawAll(new OnDrawListener() {
            @Override
            public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

            }
        }).onPageError(new OnPageErrorListener() {
            @Override
            public void onPageError(int page, Throwable t) {
                ToastUtils.showToast("Error");
            }
        }).onPageChange(new OnPageChangeListener() {
            @Override
            public void onPageChanged(int page, int pageCount) {

            }
        }).onTap(new OnTapListener() {
            @Override
            public boolean onTap(MotionEvent e) {
                return true;
            }
        }).onRender(new OnRenderListener() {
            @Override
            public void onInitiallyRendered(int nbPages, float pageWidth, float pageHeight) {
                binding.pdfViewer.fitToWidth();
            }
        }).enableAnnotationRendering(true).invalidPageColor(Color.WHITE).load();
    }

    private void getDataBundle() {
        @SuppressLint("UseRequireInsteadOfGet") Intent intent = Objects.requireNonNull(getActivity()).getIntent();
        if (intent.getExtras() != null) {
            String path = intent.getStringExtra(Constants.KEY_NOUN);
            readFile(path);
        }
    }

    @Override
    public int getLayoutRes() {
        return R.layout.content_fragment;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return ContentFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

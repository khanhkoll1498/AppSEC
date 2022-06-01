package com.kna.appsec.widget;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;


public class SnapOnScrollListener extends RecyclerView.OnScrollListener {
    private SnapHelper snapHelper;
    private OnSnapPositionChangeListener listener;


    public SnapOnScrollListener(SnapHelper snapHelper, OnSnapPositionChangeListener listener){
        this.snapHelper = snapHelper;
        this.listener = listener;
    }

    private int snapPosition = RecyclerView.NO_POSITION;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        maybeNotifySnapPositionChange(recyclerView.getLayoutManager());
    }

    private void maybeNotifySnapPositionChange(RecyclerView.LayoutManager layoutManager) {
        int snapPosition = getSnapPosition(layoutManager, snapHelper);
        boolean snapPositionChanged = this.snapPosition != snapPosition;
        if (snapPositionChanged) {
            listener.onSnapPositionChange(snapPosition);
            this.snapPosition = snapPosition;
        }
    }

    public int getSnapPosition(RecyclerView.LayoutManager layoutManager, SnapHelper snapHelper){
        if(layoutManager == null) return RecyclerView.NO_POSITION;
        View snapView = snapHelper.findSnapView(layoutManager);
        if(snapView != null){
            return  layoutManager.getPosition(snapView);
        }
        return RecyclerView.NO_POSITION;
    }

    public interface OnSnapPositionChangeListener{
        void onSnapPositionChange(int position);
    }
}

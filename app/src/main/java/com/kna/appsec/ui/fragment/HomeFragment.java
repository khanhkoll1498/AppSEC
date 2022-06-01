package com.kna.appsec.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kna.appsec.R;
import com.kna.appsec.databinding.FragmentHomeBinding;
import com.kna.appsec.model.Word;
import com.kna.appsec.viewmodel.HomeFrgVM;
import com.tsolution.base.BaseFragment;
import com.tsolution.base.BaseViewModel;

import java.util.List;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {

    HomeFrgVM homeFrgVM;
    private DatabaseReference mDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        homeFrgVM = (HomeFrgVM) viewModel;

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Word Lists").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    List<Word> list = (List<Word>) task.getResult().getValue();
                }
            }
        });

        return v;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    public Class<? extends BaseViewModel> getVMClass() {
        return HomeFrgVM.class;
    }

    @Override
    public int getRecycleResId() {
        return 0;
    }
}

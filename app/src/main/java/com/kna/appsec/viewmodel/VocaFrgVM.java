package com.kna.appsec.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kna.appsec.base.RunUi;
import com.kna.appsec.model.Word;
import com.tsolution.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class VocaFrgVM extends BaseViewModel {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private List<Word> list = new ArrayList<>();

    public VocaFrgVM(@NonNull Application application) {
        super(application);

        DatabaseReference res = mDatabase.child("Word Lists");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    String linkimage = ds.child("linkimage").getValue(String.class);
                    String pronounce = ds.child("pronounce").getValue(String.class);
                    String wordtype = ds.child("wordtype").getValue(String.class);
                    String meaning = ds.child("meaning").getValue(String.class);

                    Word word = new Word(name, linkimage, meaning, pronounce, wordtype);

                    list.add(word);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        res.addValueEventListener(valueEventListener);

    }

    public List<Word> getList() {
        return list;
    }

    public void getData(RunUi runUi) {
        runUi.run("getData");
    }
}

package com.example.bank.ui.extract;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.R;

public class ExtractFragment extends Fragment {

    private ExtractViewModel shareViewModel;
    private ExtractListAdapter mListAdapter;

    public ExtractFragment(){}

    public static Fragment newInstance(){
        return new ExtractFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        shareViewModel = ViewModelProviders.of(this).get(ExtractViewModel.class);
        View root = inflater.inflate(R.layout.fragment_extract, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.extractList);
        if (mListAdapter != null) {
            recyclerView.setAdapter(mListAdapter);
        }
        Toolbar mToolbar = root.findViewById(R.id.toolbarExtract);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((ExtractActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ExtractActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return root;
    }
}
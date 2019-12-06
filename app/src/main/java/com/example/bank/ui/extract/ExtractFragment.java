package com.example.bank.ui.extract;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bank.Model.ExtractModel;
import com.example.bank.R;

import java.util.ArrayList;
import java.util.List;

public class ExtractFragment extends Fragment implements ExtractContract.View {

    ExtractListAdapter mListAdapter;
    ExtractContract.Presenter mActionsListener;


    public ExtractFragment(){}

    static Fragment newInstance(){
        return new ExtractFragment();
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
            mListAdapter = new ExtractListAdapter(new ArrayList<List<ExtractModel>>(0));
            mActionsListener = new ExtractPresenter(this, getContext());
            setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_extract, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.extractList);
        if (mListAdapter != null) {
            recyclerView.setAdapter(mListAdapter);
        }

        String id = getActivity().getIntent().getExtras().getString("id");
        mActionsListener.loadExtract(id);

        int numColumns = 1;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        Toolbar mToolbar = root.findViewById(R.id.toolbarExtract);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ((ExtractActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((ExtractActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        return root;
    }

    @Override
    public void setLoading(boolean isActive) {
        getView();
        //final SwipeRefreshLayout srl = getView().findViewById(R.id.SwipeRefresh);
        //srl.post(() -> srl.setRefreshing(isAtivo));
    }

    @Override
    public void showExtract(ArrayList<List<ExtractModel>> extract) {
        mListAdapter.replaceData(extract);
    }
}
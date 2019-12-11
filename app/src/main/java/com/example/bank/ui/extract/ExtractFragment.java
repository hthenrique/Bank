package com.example.bank.ui.extract;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Model.ExtractModel;
import com.example.bank.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExtractFragment extends Fragment implements ExtractContract.View {

    private ExtractListAdapter mListAdapter;
    private ExtractContract.Presenter mActionsListener;
    private BroadcastReceiver MyReceiver = null;

    public ExtractFragment(){}

    static Fragment newInstance(){
        return new ExtractFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
            mListAdapter = new ExtractListAdapter(new ArrayList<>(0));
            mActionsListener = new ExtractPresenter(this, getContext());
            setHasOptionsMenu(true);
        MyReceiver = new MyReceiver();
        broadcastIntent();
    }
    private void broadcastIntent() {
        Objects.requireNonNull(getActivity()).registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
        //Objects.requireNonNull(getActivity()).unregisterReceiver(MyReceiver);
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

        String id = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getString("id");
        mActionsListener.loadExtract(id);

        int numColumns = 1;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        Toolbar mToolbar = root.findViewById(R.id.toolbarExtract);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((ExtractActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((ExtractActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);
        return root;
    }

    @Override
    public void setLoading(boolean isActive) {
        if (getView() == null){
        }
    }

    @Override
    public void showExtract(List<ExtractModel> extract) {
        mListAdapter.replaceData(extract);
    }
}
package com.example.bank.ui.statement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bank.Model.StatementModel;
import com.example.bank.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatementFragment extends Fragment implements StatementContract.View {

    private StatementListAdapter mListAdapter;
    private StatementContract.Presenter mActionsListener;


    public StatementFragment(){}

    static Fragment newInstance(){
        return new StatementFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
            mListAdapter = new StatementListAdapter(new ArrayList<>(0));
            mActionsListener = new StatementPresenter(this, getContext());
            setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statement, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.statementList);

        if (mListAdapter != null) {
            recyclerView.setAdapter(mListAdapter);
        }

        String id = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getString("id");
        mActionsListener.loadStatement(id);

        int numColumns = 1;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        Toolbar mToolbar = root.findViewById(R.id.toolbarStatement);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        Objects.requireNonNull(((StatementActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((StatementActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.SwipeRefreshStatement);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(() -> mActionsListener.loadStatement(id));
        return root;
    }

    @Override
    public void setLoading(boolean isActive) {
        if (getView() == null){
            return;
        }
        final SwipeRefreshLayout srl = getView().findViewById(R.id.SwipeRefreshStatement);
        srl.post(() -> srl.setRefreshing(isActive));
    }

    @Override
    public void showStatement(List<StatementModel> statement) {
        mListAdapter.replaceData(statement);
    }
}
package com.example.bank.ui.home;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bank.Connection.MyReceiver;
import com.example.bank.Model.GetUserModel;
import com.example.bank.R;
import com.example.bank.ui.extract.ExtractActivity;
import com.example.bank.ui.login.LoginActivity;
import com.example.bank.ui.transfer.TransferActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeContract.View, NavigationView.OnNavigationItemSelectedListener {

    private ImageView profilePic;
    private TextView nameUser;
    private TextView emailUser;
    private TextView balance;
    private String email;
    private String id_user;


    private HomeContract.Presenter presenter;
    private BroadcastReceiver MyReceiver = null;

    public HomeFragment(){}

    static Fragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        MyReceiver = new MyReceiver();
        broadcastIntent();

        NavigationView navigationView = Objects.requireNonNull(getActivity()).findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nameUser = headerView.findViewById(R.id.nameUser);
        emailUser = headerView.findViewById(R.id.emailUser);
        profilePic = headerView.findViewById(R.id.profileView);
        presenter = new HomePresenter(this, getContext());
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void broadcastIntent() {
        Objects.requireNonNull(getActivity()).registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }
    @Override
    public void onPause() {
        super.onPause();
        //unregisterReceiver(MyReceiver);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        email = Objects.requireNonNull(Objects.requireNonNull(getActivity()).getIntent().getExtras()).getString("email");
        balance = root.findViewById(R.id.totalBalance);
        presenter.loadUserDetails(email);

        Button extractButton = root.findViewById(R.id.extractButton);
        extractButton.setOnClickListener(this);
        Button transferButton = root.findViewById(R.id.transferButton);
        transferButton.setOnClickListener(this);
        Button exitButton = root.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);

        SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.SwipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        swipeRefreshLayout.setOnRefreshListener(() -> presenter.loadUserDetails(email));
        return root;
    }

    @Override
    public void setLoading(boolean isActive) {
        if (getView() == null){
            return;
        }
        final SwipeRefreshLayout srl = getView().findViewById(R.id.SwipeRefresh);
        srl.post(() -> srl.setRefreshing(isActive));
    }

    @Override
    public void setId(String id){
        id_user = id;
    }
    @Override
    public void showDetails(GetUserModel userModel) {
        nameUser.setText(userModel.name);
        emailUser.setText(userModel.email);
        balance.setText(userModel.balance);
        Picasso.get()
                .load(userModel.profile)
                .fit()
                .placeholder(R.drawable.ic_insert_photo_white_24dp)
                .into(profilePic);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_Transfer:
                Intent transfer = new Intent(getActivity(), TransferActivity.class);
                transfer.putExtra("id",id_user);
                transfer.putExtra("email",email);
                startActivity(transfer);
                break;
            case R.id.nav_Extract:
                Intent extract = new Intent(getActivity(), ExtractActivity.class);
                extract.putExtra("id",id_user);
                startActivity(extract);
                break;
            case R.id.menu_exit:
                Intent exit = new Intent(getActivity(), LoginActivity.class);
                startActivity(exit);
                Objects.requireNonNull(getActivity()).finish();
                break;
            default: break;
        }
        DrawerLayout drawer = Objects.requireNonNull(getActivity()).findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.extractButton:
                Intent extract = new Intent(getActivity(), ExtractActivity.class);
                extract.putExtra("id",id_user);
                startActivity(extract);
                break;
            case R.id.transferButton:
                Intent transfer = new Intent(getActivity(), TransferActivity.class);
                transfer.putExtra("id",id_user);
                transfer.putExtra("email",email);
                startActivity(transfer);
                break;
            case  R.id.exitButton:
                Intent exit = new Intent(getActivity(), LoginActivity.class);
                startActivity(exit);
                Objects.requireNonNull(getActivity()).finish();
                break;
            default: break;
        }
    }
}
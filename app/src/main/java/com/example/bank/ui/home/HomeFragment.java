package com.example.bank.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.bank.Model.GetUserModel;
import com.example.bank.R;
import com.example.bank.ui.extract.ExtractActivity;
import com.example.bank.ui.extract.ExtractFragment;
import com.example.bank.ui.login.LoginActivity;
import com.example.bank.ui.login.LoginContract;
import com.example.bank.ui.transfer.TransferActivity;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment implements View.OnClickListener, HomeContract.View {

    ImageView profilePic;
    TextView nameUser;
    TextView emailUser;
    TextView balance;

    private HomeContract.Presenter presenter;

    public HomeFragment(){}

    public static Fragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        nameUser = headerView.findViewById(R.id.nameUser);
        emailUser = headerView.findViewById(R.id.emailUser);
        profilePic = headerView.findViewById(R.id.profileView);

        presenter = new HomePresenter(this, getContext());
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //String id = getActivity().getIntent().getExtras().getString("id");
        String email = getActivity().getIntent().getExtras().getString("email");
        balance = root.findViewById(R.id.totalBalance);

        presenter.loadUserDetails(email);

        Button extractButton = root.findViewById(R.id.extractButton);
        extractButton.setOnClickListener(this);
        Button transferButton = root.findViewById(R.id.transferButton);
        transferButton.setOnClickListener(this);
        Button exitButton = root.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.extractButton:
                Intent extract = new Intent(getActivity(), ExtractActivity.class);
                startActivity(extract);
                break;
            case R.id.transferButton:
                Intent transfer = new Intent(getActivity(), TransferActivity.class);
                startActivity(transfer);
                break;
            case  R.id.exitButton:
                Intent exit = new Intent(getActivity(), LoginActivity.class);
                startActivity(exit);
                getActivity().finish();
                break;
        }
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
}
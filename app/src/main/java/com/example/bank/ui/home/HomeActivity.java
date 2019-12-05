package com.example.bank.ui.home;

import android.content.Intent;
import android.os.Bundle;

import com.example.bank.Model.GetUserModel;
import com.example.bank.ui.extract.ExtractActivity;
import com.example.bank.ui.login.LoginActivity;
import com.example.bank.R;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bank.ui.login.LoginFragment;
import com.example.bank.ui.transfer.TransferActivity;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    HomeFragment homeFragment;

    String emailUser;
    TextView nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        if (null == savedInstanceState){
            homeFragment = new HomeFragment();
            initFragment(HomeFragment.newInstance());
        }

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            emailUser = extras.getString("email");
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_Transfer, R.id.nav_Extract)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void initFragment(Fragment loginFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.includeMain, loginFragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_Transfer:
                Intent home = new Intent(HomeActivity.this, TransferActivity.class);
                startActivity(home);
                break;
            case R.id.nav_Extract:
                Intent extract = new Intent(HomeActivity.this, ExtractActivity.class);
                startActivity(extract);
                break;
            case R.id.menu_exit:
                Intent exit = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(exit);
                finish();
                break;
                default: break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

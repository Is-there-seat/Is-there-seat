package com.example.drawer;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import android.view.View;


import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle barDrawerToggle;
    boolean isDrawerOpened;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView =  findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_view);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //원래 getActionBar().set~ 으로 했는데, 그땐 오류가 발생하고 getSupportActionBar()로 바꾸니 깔끔하게 해결되었다.


            barDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                isDrawerOpened = true;
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                isDrawerOpened = false;
            }
        };

        drawerLayout.addDrawerListener(barDrawerToggle);
        barDrawerToggle.syncState();


//        좌석 현황 보여주는 프래그먼트로 전환

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.item_crystal) {
                    Fragment fragmentCL = new crystalLounge();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment, fragmentCL);
                    transaction.commit();
                    System.out.println("클릭됨");
                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_view);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(barDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if(isDrawerOpened) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            finish();
        }
    }
}
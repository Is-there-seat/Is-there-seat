package com.example.isthereseat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SeatActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat);


        //fragment 설정
        sungshin3F sungshin3F = new sungshin3F();
        SujungFragment sujungFragment = new SujungFragment();
        CrystalLoungeFragment crystalLoungeFragment = new CrystalLoungeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, sujungFragment).commitAllowingStateLoss();

        // 가장 먼저 띄워줄 프래그먼트 설정
        Bundle bundle = new Bundle();
        bundle.putInt("noise_level", getIntent().getIntExtra("noise_level",0));
        bundle.putBoolean("mic_TF", getIntent().getBooleanExtra("mic_TF",false));
        bundle.putBoolean("mic_TF_check", getIntent().getBooleanExtra("mic_TF_check",false));
        bundle.putBoolean("keyboard_TF", getIntent().getBooleanExtra("keyboard_TF",false));
        bundle.putBoolean("keyboard_TF_check", getIntent().getBooleanExtra("keyboard_TF_check",false));

        sungshin3F.setArguments(bundle);
        sujungFragment.setArguments(bundle);
        crystalLoungeFragment.setArguments(bundle);

        ImageButton iv_menu = (ImageButton) findViewById(R.id.iv_menu);
        ImageView iv_search = (ImageView) findViewById(R.id.iv_search);

        iv_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(SeatActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });


        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_view);
        navigationView.bringToFront();
        tv_title = (TextView)findViewById(R.id.tv_title);

        //좌석 현황 보여주는 프래그먼트로 전환
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){
                    case R.id.item_sujeong:
                        tv_title.setText("수정관 1층");
                        transaction.replace(R.id.fragment, sujungFragment).commit();
                        break;

                    case R.id.item_sungshin:
                        tv_title.setText("성신관 3층");
                        transaction.replace(R.id.fragment, sungshin3F).commit();
                        break;

                    case R.id.item_crystal:
                        tv_title.setText("크리스탈 라운지");
                        transaction.replace(R.id.fragment, crystalLoungeFragment).commit();
                        break;

                    case R.id.item_rec:
                        Intent intent = new Intent(SeatActivity.this, SearchActivity.class);
                        startActivity(intent);
                        break;
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_view);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}

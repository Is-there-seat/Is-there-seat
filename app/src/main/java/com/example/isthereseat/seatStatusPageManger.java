package com.example.isthereseat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class seatStatusPageManger  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_status_page_manger);
        sungshin3F sungshin3F = new sungshin3F();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, sungshin3F).commitAllowingStateLoss();

        // 가장 먼저 띄워줄 프래그먼트 설정
        Bundle bundle = new Bundle();
        bundle.putInt("noise_level", getIntent().getIntExtra("noise_level",0));
        bundle.putInt("people_count", getIntent().getIntExtra("people_count",0));
        bundle.putBoolean("mic_TF", getIntent().getBooleanExtra("mic_TF",false));
        bundle.putBoolean("keyboard_TF", getIntent().getBooleanExtra("keyboard_TF",false));



        sungshin3F.setArguments(bundle);

    }
}

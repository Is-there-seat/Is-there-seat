package com.example.isthereseat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

// 의문점
// 인원 어떻게 나눌것인지
// 사용중 -> 사용 불가로 바꾸는게 어떤지 (거리두기, 조건에 따른 추천X등을 나누기 위해 사용불가라고 표현하는게 나을듯..?)
//

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //main -> search or seat
        ImageButton goStatus_btn = (ImageButton) findViewById(R.id.goStatus_btn);
        ImageButton goChoice_btn = (ImageButton) findViewById(R.id.goChoice_btn);

        goStatus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SeatActivity.class);
                startActivity(intent);
            }
        });

        goChoice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });


    }




}
package com.example.isthereseat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

// 의문점
// 인원 어떻게 나눌것인지
// 사용중 -> 사용 불가로 바꾸는게 어떤지 (거리두기, 조건에 따른 추천X등을 나누기 위해 사용불가라고 표현하는게 나을듯..?)
// 그리고 옵션 선택했을때 추천좌석은 빨간색으로 표시...? 그러면 조건에 해당하지 않는 좌석은 회색? 그러면 사용가능 좌석은 안뜨고??

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        ImageButton goStatus_btn = (ImageButton) findViewById(R.id.goStatus_btn);
        ImageButton goChoice_btn = (ImageButton) findViewById(R.id.goChoice_btn);

        goStatus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), seatStatusPageManger.class);
                startActivity(intent);
            }
        });


        goChoice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), choiceOptionActivity.class);
                startActivity(intent);
            }
        });

    }

}
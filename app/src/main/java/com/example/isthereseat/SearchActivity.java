package com.example.isthereseat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
//min max
    int applyed_noise = 0;
    boolean applyed_mic;
    boolean applyed_mic_check = false;
    boolean applyed_keyboard;
    boolean applyed_keyboard_check = false;

    boolean noise_a;
    boolean noise_b;
    boolean noise_c;

    public int get_noise_level(){
        return applyed_noise;
    }
    public boolean getMicTF(){
        return applyed_mic;
    }
    public boolean getMicCheckTF(){
        return applyed_mic_check;
    }
    public boolean getKeyboardTF(){
        return applyed_keyboard;
    }
    public boolean getKeyboardCheckTF(){
        return applyed_keyboard_check;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Button btn_noise_a = (Button) findViewById(R.id.btn_noise_a);
        Button btn_noise_b = (Button) findViewById(R.id.btn_noise_b);
        Button btn_noise_c = (Button) findViewById(R.id.btn_noise_c);
        Button btn_mic_a = (Button) findViewById(R.id.btn_mic_a);
        Button btn_mic_b = (Button) findViewById(R.id.btn_mic_b);
        Button btn_mic_c = (Button) findViewById(R.id.btn_mic_c);
        Button btn_keyboard_a = (Button) findViewById(R.id.btn_keyboard_a);
        Button btn_keyboard_b = (Button) findViewById(R.id.btn_keyboard_b);
        Button btn_keyboard_c = (Button) findViewById(R.id.btn_keyboard_c);
        Button btn_initial = (Button) findViewById(R.id.btn_initial);
        Button btn_apply = (Button) findViewById(R.id.btn_apply);

        //noise

        // noise 에서 +- 1등을 왜 사용한지 모르겠음 - 소음에서 중복 선택이 가능한데 이게 맞는지...?

        btn_noise_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_noise_a.isSelected()) {
                    btn_noise_a.setSelected(false);
                    applyed_noise -= 1;
                    noise_a = false;
                }
                else{
                    btn_noise_a.setSelected(true);
                    applyed_noise += 1;
                    noise_a = true;
                }
            }
        });

        btn_noise_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_noise_b.isSelected()) {
                    btn_noise_b.setSelected(false);
                    applyed_noise -= 1;
                    noise_b = false;
                }
                else{
                    btn_noise_b.setSelected(true);
                    applyed_noise += 1;
                    noise_b = true;
                }
            }
        });

        btn_noise_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_noise_c.isSelected()) {
                    btn_noise_c.setSelected(false);
                    applyed_noise -= 1;
                    noise_c = false;
                }
                else{
                    btn_noise_c.setSelected(true);
                    applyed_noise += 1;
                    noise_c = true;
                }
            }
        });

        // mic keyboard 상관없음이 다른 변수값을 가지는데 굳이 그럴필요가 있는지...? 그냥 true / false 두가지 값이 아닌 세가지 값으로 해도 될듯

        btn_mic_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_mic_a.isSelected()) {
                    btn_mic_a.setSelected(false);
                    applyed_mic = false;
                }
                else{
                    if(btn_mic_b.isSelected()){
                        btn_mic_b.setSelected(false);
                        applyed_mic_check = false;
                    }
                    else if(btn_mic_c.isSelected()){
                        btn_mic_c.setSelected(false);
                    }
                    applyed_mic = true;
                    btn_mic_a.setSelected(true);
                }
            }
        });

        btn_mic_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_mic_b.isSelected()) {
                    btn_mic_b.setSelected(false);
                    applyed_mic_check = false;
                    applyed_mic = false;
                }
                else{
                    if(btn_mic_a.isSelected()){
                        btn_mic_a.setSelected(false);
                    }
                    else if(btn_mic_c.isSelected()){
                        btn_mic_c.setSelected(false);
                    }
                    applyed_mic = false;
                    applyed_mic_check = true;
                    btn_mic_b.setSelected(true);
                }
            }
        });

        btn_mic_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_mic_c.isSelected()) {
                    btn_mic_c.setSelected(false);
                    applyed_mic = false;
                }
                else{
                    if(btn_mic_a.isSelected()){
                        btn_mic_a.setSelected(false);
                    }
                    else if(btn_mic_b.isSelected()){
                        btn_mic_b.setSelected(false);
                        applyed_mic_check = false;
                    }
                    applyed_mic = true;
                    btn_mic_c.setSelected(true);
                }
            }
        });

        //keyboard

        btn_keyboard_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_keyboard_a.isSelected()) {
                    btn_keyboard_a.setSelected(false);
                    applyed_keyboard = false;
                }
                else{
                    if(btn_keyboard_b.isSelected()){
                        btn_keyboard_b.setSelected(false);
                        applyed_keyboard_check = false;
                    }
                    else if(btn_keyboard_c.isSelected()){
                        btn_keyboard_c.setSelected(false);
                    }
                    applyed_keyboard = true;
                    btn_keyboard_a.setSelected(true);
                }
            }
        });

        btn_keyboard_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_keyboard_b.isSelected()) {
                    btn_keyboard_b.setSelected(false);
                    applyed_keyboard_check = false;
                    applyed_keyboard = false;
                }
                else{
                    if(btn_keyboard_a.isSelected()){
                        btn_keyboard_a.setSelected(false);
                    }
                    else if(btn_keyboard_c.isSelected()){
                        btn_keyboard_c.setSelected(false);
                    }
                    // 여기수정함
                    applyed_keyboard = false;
                    applyed_keyboard_check = true;
                    btn_keyboard_b.setSelected(true);
                }
            }
        });

        btn_keyboard_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_keyboard_c.isSelected()) {
                    btn_keyboard_a.setSelected(false);
                    applyed_keyboard = false;
                }
                else{
                    if(btn_keyboard_a.isSelected()){
                        btn_keyboard_a.setSelected(false);
                    }
                    else if(btn_keyboard_b.isSelected()){
                        btn_keyboard_b.setSelected(false);
                        applyed_keyboard_check = false;
                    }
                    applyed_keyboard = true;
                    btn_keyboard_c.setSelected(true);
                }
            }
        });

        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(applyed_noise != 0)
                    Log.d("test","선택된 노이즈 정도 : " + applyed_noise);
                else
                    Log.d("test","선택된 노이즈 정도 : " + 0);

                Log.d("test", "마이크 유무 : " + applyed_mic + " 키보드 유무 : "+ applyed_keyboard);
                Log.d("test", "마이크 상관없음 : " + applyed_mic_check + " 키보드 상관없음 : "+ applyed_keyboard_check);

                Intent intent = new Intent(getApplicationContext(), SeatActivity.class);
                intent.putExtra("noise_level", applyed_noise);
                intent.putExtra("mic_TF", applyed_mic);
                intent.putExtra("mic_TF_check", applyed_mic_check);
                intent.putExtra("keyboard_TF", applyed_keyboard);
                intent.putExtra("keyboard_TF_check", applyed_keyboard_check);
                startActivity(intent);
            }

        });

        btn_initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_noise_a.isSelected())
                    btn_noise_a.setSelected(false);
                if(btn_noise_b.isSelected())
                    btn_noise_b.setSelected(false);
                if(btn_noise_c.isSelected())
                    btn_noise_c.setSelected(false);


                if(btn_mic_a.isSelected())
                    btn_mic_a.setSelected(false);
                if(btn_mic_b.isSelected())
                    btn_mic_b.setSelected(false);
                if(btn_mic_c.isSelected())
                    btn_mic_c.setSelected(false);

                if(btn_keyboard_a.isSelected())
                    btn_keyboard_a.setSelected(false);
                if(btn_keyboard_b.isSelected())
                    btn_keyboard_b.setSelected(false);
                if(btn_keyboard_c.isSelected())
                    btn_keyboard_c.setSelected(false);

                applyed_noise = 0;
                applyed_keyboard = false;
                applyed_mic = false;
            }
        });

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
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

public class choiceOptionActivity extends AppCompatActivity {
    RadioButton checked_noise;
    RadioButton checked_peopleCount;
    int applyed_noise;
    int applyed_peopleCount;
    boolean applyed_mic;
    boolean applyed_keyboard;


    public int get_noise_level(){
        return applyed_noise;
    }
    public int get_peopleCount(){
        return applyed_peopleCount;
    }
    public boolean getMicTF(){
        return applyed_mic;
    }
    public boolean getKeyboardTF(){
        return applyed_keyboard;
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_option_page);

        RadioGroup radioGroup_noise = (RadioGroup) findViewById(R.id.radio_group_noise);
        RadioGroup radioGroup_peopleNum = (RadioGroup) findViewById(R.id.radio_group_peopleNum);
        RadioButton mic_clicked = (RadioButton) findViewById(R.id.mic_clicked);
        RadioButton keyboard_clicked = (RadioButton) findViewById(R.id.keybord_clicked);
        Button apply_btn = (Button) findViewById(R.id.apply);
        Button reset_btn = (Button) findViewById(R.id.reset);


        mic_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(applyed_mic == true) {
                    mic_clicked.setChecked(false);
                    applyed_mic = false;
                }
                else
                    applyed_mic = true;
            }
        });

        keyboard_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(applyed_keyboard == true) {
                    keyboard_clicked.setChecked(false);
                    applyed_keyboard = false;
                }
                else
                    applyed_keyboard = true;
            }
        });

        radioGroup_noise.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.noise_level1) {
                    checked_noise = findViewById(i);
                    applyed_noise = 1;
                } else if (i == R.id.noise_level2) {
                    checked_noise = findViewById(i);
                    applyed_noise = 2;
                } else if (i == R.id.noise_level3) {
                    checked_noise = findViewById(i);
                    applyed_noise =3;
                }
            }
        });

        radioGroup_peopleNum.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.people2) {
                    checked_peopleCount = findViewById(i);
                    applyed_peopleCount = 2;
                } else if (i == R.id.people4) {
                    checked_peopleCount = findViewById(i);
                    applyed_peopleCount = 4;
                } else if (i == R.id.people6) {
                    checked_peopleCount = findViewById(i);
                    applyed_peopleCount = 6;
                }
            }
        });


        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(applyed_noise != 0)
                    Log.d("test","선택된 노이즈 정도 : " + applyed_noise);
                else
                    Log.d("test","선택된 노이즈 정도 : " + 0);

                if (applyed_peopleCount != 0 )
                    Log.d("test", "선택된 사람 인수 : " + applyed_peopleCount);
                else
                    Log.d("test","선택된 사람 인수 : " + 0);

                Log.d("test", "마이크 유무 : " + applyed_mic + " 키보드 유무 : "+ applyed_keyboard);

                Intent intent = new Intent(getApplicationContext(), seatStatusPageManger.class);
                intent.putExtra("noise_level", applyed_noise);
                intent.putExtra("people_count", applyed_peopleCount);
                intent.putExtra("mic_TF", applyed_mic);
                intent.putExtra("keyboard_TF", applyed_keyboard);
                startActivity(intent);
            }

        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checked_noise != null)
                    checked_noise.setChecked(false);
                if(checked_peopleCount != null)
                    checked_peopleCount.setChecked(false);
                mic_clicked.setChecked(false);
                keyboard_clicked.setChecked(false);

                applyed_noise = 0;
                applyed_peopleCount = 0;
                applyed_keyboard = false;
                applyed_mic = false;
            }
        });
    }
}

package com.example.isthereseat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {
//min max

    boolean applyOption = false;
    int applyed_noise = 0;
    boolean applyed_mic = false;
    boolean applyed_mic_check = false;
    boolean applyed_keyboard = false;
    boolean applyed_keyboard_check = false;

    public static final String APPLYED_OPTION = "applyOption";
    public static final String APPLYED_NOISE = "applyed_noise";
    public static final String APPLYED_MIC = "applyed_mic";
    public static final String APPLYED_MIC_CHECK = "applyed_mic_check";
    public static final String APPLYED_KEYBOARD = "applyed_keyboard";
    public static final String APPLYED_KEYBOARD_CHECK = "applyed_keyboard_check";



    Button btn_noise_a;
    Button btn_noise_b;
    Button btn_noise_c;
    Button btn_mic_a;
    Button btn_mic_b;
    Button btn_mic_c;
    Button btn_keyboard_a ;
    Button btn_keyboard_b;
    Button btn_keyboard_c;
    Button btn_initial;
    Button btn_apply;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


       btn_noise_a = findViewById(R.id.btn_noise_a);
        btn_noise_b = (Button) findViewById(R.id.btn_noise_b);
         btn_noise_c = (Button) findViewById(R.id.btn_noise_c);
        btn_mic_a = (Button) findViewById(R.id.btn_mic_a);
         btn_mic_b = (Button) findViewById(R.id.btn_mic_b);
        btn_mic_c = (Button) findViewById(R.id.btn_mic_c);
        btn_keyboard_a = (Button) findViewById(R.id.btn_keyboard_a);
        btn_keyboard_b = (Button) findViewById(R.id.btn_keyboard_b);
        btn_keyboard_c = (Button) findViewById(R.id.btn_keyboard_c);
        btn_initial = (Button) findViewById(R.id.btn_initial);
        btn_apply = (Button) findViewById(R.id.btn_apply);

        btn_noise_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_noise_a.isSelected()) {
                    btn_noise_a.setSelected(false);
                    applyed_noise = 0;
                    applyOption = false;
                } else {
                    if (btn_noise_b.isSelected()) {
                        btn_keyboard_b.setSelected(false);
                    } else if (btn_keyboard_c.isSelected()) {
                        btn_keyboard_c.setSelected(false);
                    }
                    btn_noise_a.setSelected(true);
                    btn_noise_b.setSelected(false);
                    btn_noise_c.setSelected(false);
                    applyed_noise = 1;
                    applyOption = true;
                }
            }
        });

        btn_noise_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_noise_b.isSelected()) {
                    btn_noise_b.setSelected(false);
                    applyed_noise = 0;
                    applyOption = false;
                } else {
                    if (btn_noise_a.isSelected()) {
                        btn_keyboard_a.setSelected(false);
                    } else if (btn_keyboard_c.isSelected()) {
                        btn_keyboard_c.setSelected(false);
                    }
                    btn_noise_b.setSelected(true);
                    btn_noise_a.setSelected(false);
                    btn_noise_c.setSelected(false);
                    applyed_noise = 2;
                    applyOption = true;
                }
            }
        });

        btn_noise_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_noise_c.isSelected()) {
                    btn_noise_c.setSelected(false);
                    applyed_noise = 0;
                    applyOption = false;
                } else {
                    if (btn_noise_a.isSelected()) {
                        btn_keyboard_a.setSelected(false);
                    } else if (btn_keyboard_b.isSelected()) {
                        btn_keyboard_b.setSelected(false);
                    }
                    btn_noise_c.setSelected(true);
                    btn_noise_a.setSelected(false);
                    btn_noise_b.setSelected(false);
                    applyed_noise = 3;
                    applyOption = true;
                }
            }
        });

        btn_mic_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_mic_a.isSelected()) {
                    btn_mic_a.setSelected(false);
                    applyOption = false;
                } else {
                    if (btn_mic_b.isSelected()) {
                        btn_mic_b.setSelected(false);
                    } else if (btn_mic_c.isSelected()) {
                        btn_mic_c.setSelected(false);
                    }
                    applyed_mic = true;
                    applyed_mic_check = false;
                    btn_mic_a.setSelected(true);
                    applyOption = true;
                }
            }
        });

        btn_mic_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_mic_b.isSelected()) {
                    btn_mic_b.setSelected(false);
                    applyed_mic_check = false;
                    applyOption = false;
                } else {
                    if (btn_mic_a.isSelected()) {
                        btn_mic_a.setSelected(false);
                    } else if (btn_mic_c.isSelected()) {
                        btn_mic_c.setSelected(false);
                    }
                    applyed_mic = false;
                    applyed_mic_check = false;
                    btn_mic_b.setSelected(true);
                    applyOption = true;
                }
            }
        });

        btn_mic_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_mic_c.isSelected()) {
                    btn_mic_c.setSelected(false);
                    applyed_mic_check = false;
                    applyOption = false;
                } else {
                    if (btn_mic_a.isSelected()) {
                        btn_mic_a.setSelected(false);
                    } else if (btn_mic_b.isSelected()) {
                        btn_mic_b.setSelected(false);
                    }
                    applyed_mic_check = true;
                    btn_mic_c.setSelected(true);
                    applyOption = true;
                }
            }
        });

        //keyboard
        btn_keyboard_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_keyboard_a.isSelected()) {
                    btn_keyboard_a.setSelected(false);
                    applyOption = false;
                } else {
                    if (btn_keyboard_b.isSelected()) {
                        btn_keyboard_b.setSelected(false);
                        applyed_keyboard_check = false;
                    }
                    else if(btn_keyboard_c.isSelected()){
                        btn_keyboard_c.setSelected(false);
                    }
                    applyed_keyboard = true;
                    applyed_keyboard_check = false;
                    btn_keyboard_a.setSelected(true);
                    applyOption = true;
                }
            }
        });

        btn_keyboard_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_keyboard_b.isSelected()) {
                    btn_keyboard_b.setSelected(false);
                    applyed_keyboard_check = false;
                    applyOption = false;
                } else {
                    if (btn_keyboard_a.isSelected()) {
                        btn_keyboard_a.setSelected(false);
                    } else if (btn_keyboard_c.isSelected()) {
                        btn_keyboard_c.setSelected(false);
                    }
                    applyed_keyboard = false;
                    applyed_keyboard_check = false;
                    btn_keyboard_b.setSelected(true);
                    applyOption = true;
                }
            }
        });

        btn_keyboard_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btn_keyboard_c.isSelected()) {
                    btn_keyboard_a.setSelected(false);
                    applyed_keyboard_check = false;
                }
                else{
                    if(btn_keyboard_a.isSelected()){
                        btn_keyboard_a.setSelected(false);
                    }
                    else if(btn_keyboard_b.isSelected()){
                        btn_keyboard_b.setSelected(false);
                    }
                    applyed_keyboard_check = true;
                    btn_keyboard_c.setSelected(true);
                    applyOption = true;
                }
            }
        });



        btn_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((btn_noise_a.isSelected() || btn_noise_b.isSelected()) || (btn_noise_b.isSelected() || btn_noise_c.isSelected()))
                        && ((btn_keyboard_a.isSelected() || btn_keyboard_b.isSelected()) || (btn_keyboard_b.isSelected() || btn_keyboard_c.isSelected()))
                        && ((btn_mic_a.isSelected() || btn_mic_b.isSelected()) || (btn_mic_b.isSelected() || btn_mic_c.isSelected()))) {
                    if (applyed_noise != 0)
                        Log.d("test", "선택된 노이즈 정도 : " + applyed_noise);
                    else
                        Log.d("test", "선택된 노이즈 정도 : " + 0);

                    Log.d("test", "마이크 유무 : " + applyed_mic + " 키보드 유무 : " + applyed_keyboard);
                    Log.d("test", "마이크 상관없음 : " + applyed_mic_check + " 키보드 상관없음 : " + applyed_keyboard_check);

                    Intent intent = new Intent(getApplicationContext(), SeatActivity.class);
                    intent.putExtra("noise_level", applyed_noise);
                    intent.putExtra("mic_TF", applyed_mic);
                    intent.putExtra("mic_TF_check", applyed_mic_check);
                    intent.putExtra("keyboard_TF", applyed_keyboard);
                    intent.putExtra("keyboard_TF_check", applyed_keyboard_check);
                    intent.putExtra("applyedOption", applyOption);
                    startActivity(intent);
                } else
                    Toast.makeText(getApplicationContext(), "검색 조건을 모두 선택해주세요.", Toast.LENGTH_SHORT).show();
            }

        });

        btn_initial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn_noise_a.isSelected())
                    btn_noise_a.setSelected(false);
                if (btn_noise_b.isSelected())
                    btn_noise_b.setSelected(false);
                if (btn_noise_c.isSelected())
                    btn_noise_c.setSelected(false);


                if (btn_mic_a.isSelected())
                    btn_mic_a.setSelected(false);
                if (btn_mic_b.isSelected())
                    btn_mic_b.setSelected(false);
                if (btn_mic_c.isSelected())
                    btn_mic_c.setSelected(false);

                if (btn_keyboard_a.isSelected())
                    btn_keyboard_a.setSelected(false);
                if (btn_keyboard_b.isSelected())
                    btn_keyboard_b.setSelected(false);
                if (btn_keyboard_c.isSelected())
                    btn_keyboard_c.setSelected(false);

                applyed_noise = 0;
                applyed_keyboard = false;
                applyed_mic = false;
                applyOption = false;
            }
        });

        findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifeCycle", "onResume");
        restoreState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifeCycle", "onStop");
        saveState();
    }


    // 저장
    protected void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(APPLYED_OPTION, applyOption);
        editor.putInt(APPLYED_NOISE, applyed_noise);
        editor.putBoolean(APPLYED_MIC, applyed_mic);
        editor.putBoolean(APPLYED_MIC_CHECK, applyed_mic_check);
        editor.putBoolean(APPLYED_KEYBOARD, applyed_keyboard);
        editor.putBoolean(APPLYED_KEYBOARD_CHECK, applyed_keyboard_check);
        editor.commit();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) {
            Log.d("bundle in onRestoreInst", "it's null");
        } else {
            applyed_keyboard = savedInstanceState.getBoolean(APPLYED_OPTION);
            applyed_mic = savedInstanceState.getBoolean(APPLYED_MIC);
            applyOption = savedInstanceState.getBoolean(APPLYED_OPTION);
            applyed_noise = savedInstanceState.getInt(APPLYED_NOISE);
            applyed_keyboard_check = savedInstanceState.getBoolean(APPLYED_KEYBOARD_CHECK);
            applyed_mic_check = savedInstanceState.getBoolean(APPLYED_MIC_CHECK);
            Log.d("bundel in onRestoreInst", "not null. data from prev");
        }
    }
    // 재개
    protected void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        if(pref != null) {
            if(pref.getBoolean(APPLYED_OPTION, false)) {
                applyed_keyboard = pref.getBoolean(APPLYED_KEYBOARD, false );
                if(applyed_keyboard)
                    btn_keyboard_a.setSelected(true);
                else
                    btn_keyboard_b.setSelected(true);

                applyed_mic = pref.getBoolean(APPLYED_MIC, false) ;
                if(applyed_mic)
                    btn_mic_a.setSelected(true);
                else
                    btn_mic_b.setSelected(true);

                applyOption = pref.getBoolean(APPLYED_OPTION, false );

                applyed_noise = pref.getInt(APPLYED_NOISE, 0 );
                if(applyed_noise == 1)
                    btn_noise_a.setSelected(true);
                else if (applyed_noise == 2)
                    btn_noise_b.setSelected(true);
                else if (applyed_noise == 3)
                    btn_noise_c.setSelected(true);

                applyed_keyboard_check =pref.getBoolean(APPLYED_KEYBOARD_CHECK, false);
                if(applyed_keyboard_check)
                    btn_keyboard_c.setSelected(true);

                applyed_mic_check = pref.getBoolean(APPLYED_MIC_CHECK, false );
                if(applyed_mic_check)
                    btn_mic_c.setSelected(true);
            }
        }
    }
}
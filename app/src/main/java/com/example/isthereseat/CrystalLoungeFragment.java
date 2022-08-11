package com.example.isthereseat;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

// 이게 진짜다... 제대로 들어갔다....
public class CrystalLoungeFragment extends Fragment {

    // 마이크, 키보드 사용 불가능
    ArrayList<Double> decibels = new ArrayList<>(Arrays.asList(35.3, 35.0, 34.1, 35.2, 32.4, 31.9, 33.2, 34.2, 35.2, 33.7));
    double decibel;
    TextView time;
    TextView deci_view;
    ArrayList<View> seats = new ArrayList<>();

    // 실시간 시간 출력에 필요한 코드
    private Timer mtimer;
    private final Handler mHandler = new Handler();


    private final Runnable timeTaskThread = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("kk");
            String dateString = format.format(new Date());
            time.setText(dateString);

            int hour = Integer.parseInt(dateString);
            int index = hour - 9;
            if (index <= 0) {
                deci_view.setText("-");
                // 9 시 이전의 경우
            } else if (index < 10) {
                decibel = decibels.get(index); // 10시부터 18시 까지
                deci_view.setText(Double.toString(decibel));
            } else
                deci_view.setText("-");
            // 18시 이후
        }
    };

    class timeTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(timeTaskThread);
        }
    }

    @Override
    public void onDestroy() {
        mtimer.cancel();
        super.onDestroy();
    }

    @Override
    public void onPause() {
        mtimer.cancel();
        super.onPause();
    }

    @Override
    public void onResume() {
        TimerTask timerTask = new CrystalLoungeFragment.timeTask();
        mtimer.schedule(timerTask, 500, 3000);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_crystal_lounge, container, false);

        Bundle option_data = getArguments();
        int noise_level = option_data.getInt("noise_level");
        boolean mic_tf = option_data.getBoolean("mic_TF");
        boolean mic_tf_check = option_data.getBoolean("mic_TF_check");
        boolean keyboard_tf = option_data.getBoolean("keyboard_TF");
        boolean keyboard_tf_check = option_data.getBoolean("keyboard_TF_check");
        boolean applyedOption = option_data.getBoolean("applyedOption");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("crystallounge");

        //좌석 id
        int ids[] = {
                R.id.crystal_seat1_1, R.id.crystal_seat1_2, R.id.crystal_seat1_3, R.id.crystal_seat1_4, R.id.crystal_seat1_5,
                R.id.crystal_seat2_1, R.id.crystal_seat2_2, R.id.crystal_seat2_3, R.id.crystal_seat2_4, R.id.crystal_seat2_5,
                R.id.crystal_seat3_1, R.id.crystal_seat3_2, R.id.crystal_seat3_3, R.id.crystal_seat3_4, R.id.crystal_seat3_5,
                R.id.crystal_seat4_1, R.id.crystal_seat4_2, R.id.crystal_seat4_3, R.id.crystal_seat4_4, R.id.crystal_seat4_5,
                R.id.crystal_seat5_1, R.id.crystal_seat5_2, R.id.crystal_seat5_3, R.id.crystal_seat5_4, R.id.crystal_seat5_5,
                R.id.crystal_seat6_1, R.id.crystal_seat6_2, R.id.crystal_seat6_3, R.id.crystal_seat6_4, R.id.crystal_seat6_5,
                R.id.crystal_seat7_1, R.id.crystal_seat7_2, R.id.crystal_seat7_3, R.id.crystal_seat7_4, R.id.crystal_seat7_5,
                R.id.crystal_seat8_1, R.id.crystal_seat8_2, R.id.crystal_seat8_3, R.id.crystal_seat8_4, R.id.crystal_seat8_5,
                R.id.crystal_seat9_1, R.id.crystal_seat9_2, R.id.crystal_seat9_3, R.id.crystal_seat9_4, R.id.crystal_seat9_5,
                R.id.crystal_seat10_1, R.id.crystal_seat10_2, R.id.crystal_seat10_3, R.id.crystal_seat10_4, R.id.crystal_seat10_5,
                R.id.crystal_seat11_1, R.id.crystal_seat11_2, R.id.crystal_seat11_3, R.id.crystal_seat11_4, R.id.crystal_seat11_5,
                R.id.crystal_seat12_1, R.id.crystal_seat12_2, R.id.crystal_seat12_3, R.id.crystal_seat12_4, R.id.crystal_seat12_5,
                R.id.crystal_seat13_1, R.id.crystal_seat13_2, R.id.crystal_seat13_3, R.id.crystal_seat13_4, R.id.crystal_seat13_5,
                R.id.crystal_seat14_5, R.id.crystal_seat15_5, R.id.crystal_seat16_5,
        };

//
//        if((applyedOption)
//                && ((noise_level == 1) || (noise_level == 0 ))
//                && ((!mic_tf && !keyboard_tf)
//                || ( mic_tf_check && keyboard_tf_check )))

        int seat_size = ids.length;
        ArrayList<Integer> seat_status = new ArrayList<Integer>();


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                     Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                                                     Log.d("readData_in_crystal", "Value is: " + map);
                                                     List<String> keySet = new ArrayList<>(map.keySet());
                                                     Collections.sort(keySet);

                                                     for (String s : keySet) {
                                                         Log.d("readData_in_crystal", " 맵 키 : " + s + " " + map.get(s).toString());
                                                         if (map.get(s).toString() == "")
                                                             seat_status.add(0);
                                                         else
                                                             seat_status.add(Integer.parseInt(map.get(s).toString()));
                                                     }
                                                     for (int i = 0; i < ids.length; i++) {
                                                         if (seat_status.get(i) == -1) {
                                                             //사용 가능
                                                             if ((applyedOption)
                                                                     && ((noise_level == 1) || (noise_level == 0))
                                                                     && ((!mic_tf && !keyboard_tf)
                                                                     || (mic_tf_check && keyboard_tf_check)))
                                                                 v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_recommend));
                                                             else
                                                                 v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_available));

                                                         } else if (seat_status.get(i) == 0) {
                                                             // 사용 중
                                                             v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_using));
                                                         } else {
                                                             // 20
                                                             v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_check));
                                                         }
                                                     }
                                                 }

                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError error) {
                                                 }
                                             }
        );

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildChanged", "ChildEventListener - onChildAdded : " + dataSnapshot.getValue());
                String key = dataSnapshot.getKey();
                int big = Integer.parseInt(key.substring(0, 2));
                int small = Integer.parseInt(key.substring(2));
                int value = Integer.parseInt(dataSnapshot.getValue().toString());

                int id = getResources().getIdentifier("crystal_seat" + big + "_" + small, "id", getContext().getPackageName());

                Log.d("Id : ", "crystal_seat" + big + "_" + small);
                Log.d("value", "value is " + value);

                if (value == -1) {
                    // 사용 가능
                    if ((applyedOption) // 추천 좌석
                            && ((noise_level == 1) || (noise_level == 0))
                            && ((!mic_tf && !keyboard_tf)
                            || (mic_tf_check && keyboard_tf_check))) {
                        v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_recommend));
                        Log.d("color test", "color test1");
                    } else // 추천좌석아닌 사용가능한 좌석
                        v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_available));
                } else if (value == 0) {
                    // 사용 중
                    v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_using));
                    Log.d("color test", "color test2");
                } else {
                    // 20
                    v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_check));
                    Log.d("color test", "color test3");
                }
                String idString = getResources().getString(id);
                Log.d("color", "id 값은 ? " + idString);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("MainActivity", "ChildEventListener - onChildRemoved : " + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d("MainActivity", "ChildEventListener - onChildMoved" + s);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("MainActivity", "ChildEventListener - onCancelled" + databaseError.getMessage());
            }

        });


        for (int i = 0; i < seats.size(); i++) {
            seats.set(i, (View) v.findViewById(ids[i]));
        }


        time = (TextView) v.findViewById(R.id.hour);
        deci_view = (TextView) v.findViewById(R.id.decibel_in_crystalLounge);

        // 실시간 시간 출력
        CrystalLoungeFragment.timeTask timeTask = new CrystalLoungeFragment.timeTask();
        mtimer = new Timer();
        mtimer.schedule(timeTask, 500, 1000);

        return v;

    }
}
package com.example.isthereseat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

// -1:사용가능 0:사용중 1:(20분 대기) -2:사용불가
public class sungshin3F extends Fragment {

    // 마이크 키보드 사용 가능
    // 실시간 시간 출력에 필요한 코드

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // ----

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sungshin3_f, container, false);

        Bundle option_data = getArguments();
        int noise_level = option_data.getInt("noise_level");
        boolean mic_tf = option_data.getBoolean("mic_TF");
        boolean mic_tf_check = option_data.getBoolean("mic_TF_check");
        boolean keyboard_tf = option_data.getBoolean("keyboard_TF");
        boolean keyboard_tf_check = option_data.getBoolean("keyboard_TF_check");
        boolean applyedOption = option_data.getBoolean("applyedOption");

        //좌석 id
        int ids[] = {
                R.id.seat1_1, R.id.seat1_2, R.id.seat1_3, R.id.seat1_4, R.id.seat1_5, R.id.seat1_6, R.id.seat1_7, R.id.seat1_8, R.id.seat1_9, R.id.seat1_10, R.id.seat1_11, R.id.seat1_12,
                R.id.seat2_1, R.id.seat2_2, R.id.seat2_3, R.id.seat2_4, R.id.seat2_5, R.id.seat2_6, R.id.seat2_7, R.id.seat2_8, R.id.seat2_9, R.id.seat2_10, R.id.seat2_11, R.id.seat2_12,
                R.id.seat3_1, R.id.seat3_2, R.id.seat3_3, R.id.seat3_4, R.id.seat3_5, R.id.seat3_6, R.id.seat3_7, R.id.seat3_8, R.id.seat3_9, R.id.seat3_10, R.id.seat3_11, R.id.seat3_12,
                R.id.seat4_1, R.id.seat4_2, R.id.seat4_3, R.id.seat4_4, R.id.seat4_5, R.id.seat4_6, R.id.seat4_7, R.id.seat4_8, R.id.seat4_9, R.id.seat4_10, R.id.seat4_11, R.id.seat4_12,
        };

        ArrayList<Integer> seat_status = new ArrayList<Integer>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("sungshin");

        // 데이터를 처음 한번만 가져오는 코드
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                                 @Override
                                                 public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                     Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                                                     Log.d("readData_in_sungshin", "Value is: " + map);
                                                     List<String> keySet = new ArrayList<>(map.keySet());
                                                     Collections.sort(keySet);

                                                     for (String s : keySet) {
                                                         Log.d("readData_in_sujung", " 맵 키 : " + s + " " + map.get(s).toString());
                                                         if (map.get(s).toString() == "")
                                                             seat_status.add(0);
                                                         else
                                                             seat_status.add(Integer.parseInt(map.get(s).toString()));
                                                     }

                                                     for (int i = 0; i < ids.length; i++) {
                                                         if (seat_status.get(i) == -1) {
                                                             // 사용 가능
                                                             if ((applyedOption)
                                                                     && ((noise_level == 2) || (noise_level == 0))
                                                                     && ((mic_tf && keyboard_tf)
                                                                     || (mic_tf_check && keyboard_tf_check)))
                                                                 v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_recommend));
                                                             else
                                                                 v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_available));

                                                         } else if (seat_status.get(i) == 0) {
                                                             // 사용 중
                                                             v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_using));
                                                         } else if (seat_status.get(i) == 1) {
                                                             // 20
                                                             v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_check));
                                                         }
                                                         else {
                                                             v.findViewById(ids[i]).setBackground(getResources().getDrawable(R.drawable.seat_unavailable));

                                                         }
                                                     }
                                                 }


                                                 @Override
                                                 public void onCancelled(@NonNull DatabaseError error) {
                                                 }
                                             }
        );


        // 데이터에 변화가 생길때마다 읽어오는 코드
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

                int id = getResources().getIdentifier("seat" + big + "_" + small, "id", getContext().getPackageName());

                Log.d("Id : ", "seat" + big + "_" + small);
                Log.d("value", "value is " + value);

                if (value == -1) {
                    // 사용 가능
                    if ((applyedOption) // 추천 좌석
                            && ((noise_level == 2) || (noise_level == 0))
                            && ((mic_tf && keyboard_tf)
                            || (mic_tf_check && keyboard_tf_check))) {
                        v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_recommend));
                        Log.d("color test", "color test1");
                    } else // 추천좌석아닌 사용가능한 좌석
                        v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_available));
                } else if (value == 0) {
                    // 사용 중
                    v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_using));
                    Log.d("color test", "color test2");
                } else if (value == 1 ){
                    // 20
                    v.findViewById(id).setBackground(getResources().getDrawable(R.drawable.seat_check));
                    Log.d("color test", "color test3");
                } else {
                    // 사용불가
                    v.findViewById(id).setBackgroundColor(Color.parseColor("#654A97"));
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

        return v;
    }
}
package com.example.isthereseat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

// 소음 level 2에 해당
// 마이크 키보드 선택 안하면 -> 마이크 키보드가 안되는 곳이 추천... 그러면 애초에 옵션 선택을 안했을 경우엔 상관 없음으로 가?
public class sungshin3F extends Fragment {
    View seats[];
    MainActivity mainActivity;

    public sungshin3F() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sungshin3_f, container, false);

        int noise_level = getArguments().getInt("noise_level");
        int people_count = getArguments().getInt("people_count");
        boolean micTF =  getArguments().getBoolean("mic_TF");
        boolean keyboardTF = getArguments().getBoolean("keyboard_TF");

        Log.d("in3F_fragment","선택된 노이즈 정도 : " + getArguments().getInt("noise_level"));
        Log.d("in3F_fragment","선택된 인원 : " + getArguments().getInt("people_count"));
        Log.d("in3F_fragment","마이크 선택 : " + getArguments().getBoolean("mic_TF"));
        Log.d("in3F_fragment","키보드 선택 : " + getArguments().getBoolean("keyboard_TF"));
        //좌석
        seats = new View[48];

        //좌석 id
        int ids[] = {
                R.id.seat1_1, R.id.seat1_2, R.id.seat1_3, R.id.seat1_4, R.id.seat1_5, R.id.seat1_6, R.id.seat1_7, R.id.seat1_8, R.id.seat1_9, R.id.seat1_10, R.id.seat1_11, R.id.seat1_12,
                R.id.seat2_1, R.id.seat2_2, R.id.seat2_3, R.id.seat2_4, R.id.seat2_5, R.id.seat2_6, R.id.seat2_7, R.id.seat2_8, R.id.seat2_9, R.id.seat2_10, R.id.seat2_11, R.id.seat2_12,
                R.id.seat3_1, R.id.seat3_2, R.id.seat3_3, R.id.seat3_4, R.id.seat3_5, R.id.seat3_6, R.id.seat3_7, R.id.seat3_8, R.id.seat3_9, R.id.seat3_10, R.id.seat3_11, R.id.seat3_12,
                R.id.seat4_1, R.id.seat4_2, R.id.seat4_3, R.id.seat4_4, R.id.seat4_5, R.id.seat4_6, R.id.seat4_7, R.id.seat4_8, R.id.seat4_9, R.id.seat4_10, R.id.seat4_11, R.id.seat4_12,
        };

        for(int i =0; i<seats.length; i++) {
            seats[i] = (View) v.findViewById(ids[i]);
        }

        if ((noise_level != 2)) {
            for(int i =0; i<seats.length; i++) {
                seats[i].setBackgroundColor(Color.parseColor("#929495"));
            }
        }
        return v;
    }
}
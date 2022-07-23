package com.example.isthereseat;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

// 소음 level 2에 해당
// 마이크 키보드 선택 안하면 -> 마이크 키보드가 안되는 곳이 추천... 그러면 애초에 옵션 선택을 안했을 경우엔 상관 없음으로 가?
public class sungshin3F extends Fragment {

    double decibels[] = {36.69, 36.75, 35.73, 38.12, 46.03, 46.87, 42.75, 43.25, 44.17, 41.26};
    double decibel;
    TextView time;
    TextView deci_view;
    View seats[];
    MainActivity mainActivity;

    // 실시간 시간 출력에 필요한 코드
    private Timer mtimer;
    private Handler mHandler = new Handler();

    private Runnable timeTaskThread = new Runnable() {
        @Override
        public void run() {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("kk");
            String dateString = format.format(now);
            time.setText(dateString);
            int hour = Integer.parseInt(dateString);
            int index = hour - 9;
            if (index <= 0) {
                deci_view.setText("-"); // 9 시 이전의 경우
            } else if ((1<= index) && (index < 10)) {
                decibel = decibels[index]; // 10시부터 18시 까지
                deci_view.setText(Double.toString(decibel));
            } else
                deci_view.setText("-");// 18시 이후
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
        TimerTask timerTask = new timeTask();
        mtimer.schedule(timerTask, 500, 3000);
        super.onResume();
    }

    // ----

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

        time = (TextView) v.findViewById(R.id.hour);
        deci_view = (TextView) v.findViewById(R.id.decibel_in_sungshin3F);
        // 실시간 시간 출력
        timeTask timeTask = new timeTask();
        mtimer = new Timer();
        mtimer.schedule(timeTask, 500, 1000);
        // ----------
        if ((noise_level != 2)) {
            for(int i =0; i<seats.length; i++) {
                seats[i].setBackgroundColor(Color.parseColor("#929495"));
            }
        }


        return v;
    }
}
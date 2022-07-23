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
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class SujungFragment extends Fragment {

    ArrayList<Double> decibels = new ArrayList<>(Arrays.asList(47.5, 47.2, 47.3, 47.6, 46.9, 46.6, 49.2, 44.7, 45.1, 44.8));
    double decibel;
    TextView time;
    TextView deci_view;
    ArrayList<View> seats = new ArrayList<>();

    // 실시간 시간 출력에 필요한 코드
    private Timer mtimer;
    private final Handler mHandler = new Handler();


    private Runnable timeTaskThread = new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat format = new SimpleDateFormat("kk");
            String dateString = format.format(new Date());
            time.setText(dateString);

            int hour = Integer.parseInt(dateString);
            int index = hour - 9;
            if (index <= 0) {
                deci_view.setText("-"); // 9 시 이전의 경우
            } else if (index < 10) {
                decibel = decibels.get(index); // 10시부터 18시 까지
                deci_view.setText(Double.toString(decibel));
            } else
                deci_view.setText("-"); // 18시 이후

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
        TimerTask timerTask = new SujungFragment.timeTask();
        mtimer.schedule(timerTask, 500, 3000);
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_sujung, container, false);
/*
        int noise_level = getArguments().getInt("noise_level");
        boolean micTF =  getArguments().getBoolean("mic_TF");
        boolean micTF_check =  getArguments().getBoolean("mic_TF_check");
        boolean keyboardTF = getArguments().getBoolean("keyboard_TF");
        boolean keyboard_tf_check = getArguments().getBoolean("keyboard_TF_check");

        Log.d("sujung_in1F_fragment","선택된 노이즈 정도 : " + getArguments().getInt("noise_level"));
        Log.d("sujung_in1F_fragment","마이크 선택 : " + getArguments().getBoolean("mic_TF"));
        Log.d("sujung_in1F_fragment","마이크 선택 상관없음 : " + getArguments().getBoolean("mic_TF_check"));
        Log.d("sujung_in1F_fragment","키보드 선택 : " + getArguments().getBoolean("keyboard_TF"));
        Log.d("sujung_in1F_fragment","키보드 선택 상관없음 : " + getArguments().getBoolean("keyboard_TF_check"));
*/
        //좌석 id
        int ids[] = {
                R.id.sujung_seat1_1, R.id.sujung_seat1_2,
                R.id.sujung_seat2_1, R.id.sujung_seat2_2,
                R.id.sujung_seat3_1, R.id.sujung_seat3_2,
                R.id.sujung_seat4_1, R.id.sujung_seat4_2,
                R.id.sujung_seat5_1, R.id.sujung_seat5_2,
                R.id.sujung_seat6_1, R.id.sujung_seat6_2,
                R.id.sujung_seat7_1, R.id.sujung_seat7_2,
                R.id.sujung_seat8_1, R.id.sujung_seat8_2,
                R.id.sujung_seat9_1, R.id.sujung_seat9_2,
                R.id.sujung_seat10_1, R.id.sujung_seat10_2,
                R.id.sujung_seat11_1, R.id.sujung_seat11_2
        };

        for(int i =0; i<seats.size(); i++) {
            seats.set(i, (View) v.findViewById(ids[i]));
        }

        time = (TextView) v.findViewById(R.id.hour);
        deci_view = (TextView) v.findViewById(R.id.decibel_in_sujung1F);

        // 실시간 시간 출력
        timeTask timeTask = new timeTask();
        mtimer = new Timer();
        mtimer.schedule(timeTask, 500, 1000);
        // ----------
        /*
        if ((noise_level != 2)) {*/
            for(int i =0; i<seats.size(); i++) {
                seats.get(i).setBackgroundColor(Color.parseColor("#929495"));
            }
        //}

        return v;

    }
}

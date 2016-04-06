package com.example.owner.savvybulb;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Timer extends Fragment {

    int i = 0;
    public Timer() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timer_content, container, false);

        final NumberPicker hr1  = (NumberPicker)v.findViewById(R.id.hrLSD) ;
        final NumberPicker min2 = (NumberPicker)v.findViewById(R.id.minMSD);

        final Button setBtn = (Button) v.findViewById(R.id.setBtn);
        final TextView text = (TextView) v.findViewById(R.id.alarmTxt);

        hr1.setMinValue(0);
        hr1.setMaxValue(10);

        min2.setMinValue(0);
        min2.setMaxValue(60);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                i++; // Can't set more than 1 alarm
                int timeSec = getTime(hr1, min2);
                if (timeSec > 0 && i<=1) {
                    Particle.sendTimer(Particle.light, timeSec);
                    CountDownTimer count = new CountDownTimer(timeSec*1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            text.setText("Seconds remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            text.setText("done!"); i--;
                        }
                    }.start();

                } else
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    public int getTime(NumberPicker hr1, NumberPicker min2)
    {
        int timeSec;
        int hr  = hr1.getValue();
        int min = min2.getValue();
        if(min>60) {
            Toast.makeText(getContext(), "Error: Time out of range", Toast.LENGTH_LONG).show();
            return 0;
        }
        timeSec = hr*3600 + min*60;
        return timeSec;
    }
}

package com.example.owner.savvybulb;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class Timer extends Fragment {

    public Timer() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timer_content, container, false);

        final TimePicker timePick  = (TimePicker)v.findViewById(R.id.timePick);
        final Button setBtn = (Button) v.findViewById(R.id.setBtn);
        final EditText text = (EditText) v.findViewById(R.id.alarmTxt);

        setBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                int timeSec = getTime(timePick);
                if (timeSec > 0) {
                    Particle.sendTimer(Particle.light, timeSec);
                    CountDownTimer count = new CountDownTimer(timeSec*1000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            text.setText("seconds remaining: " + millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            text.setText("done!");
                        }
                    }.start();

                } else
                    Toast.makeText(getActivity(), "Error: Invalid time", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }

    public int getTime(TimePicker timePick)
    {
        int now     = 0;
        int then    = 0;
        int timeSec = 0;

        if (Build.VERSION.SDK_INT >= 23 ) // Fancy new Android
            then = timePick.getHour()*3600 + timePick.getMinute()*60;
        else                              // If your phone is a brick
            then = timePick.getCurrentHour()*3600 + timePick.getCurrentMinute()*60;

        now = Calendar.getInstance(TimeZone.getTimeZone("PST")).get(Calendar.HOUR)*3600 + Calendar.getInstance().get(Calendar.MINUTE)*60;
        timeSec = then-now;

        return timeSec;
    }
}

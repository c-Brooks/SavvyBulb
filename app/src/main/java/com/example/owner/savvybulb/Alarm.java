package com.example.owner.savvybulb;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends Fragment {
    int i = 0;

    public Alarm() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alarm_content, container, false);

        final TimePicker timePick = (TimePicker) v.findViewById(R.id.timePick);
        final DatePicker datePick = (DatePicker) v.findViewById(R.id.datePick);
        final SeekBar    dimBar   = (SeekBar)    v.findViewById(R.id.dimBar)  ;
        final Button     setBtn   = (Button)     v.findViewById(R.id.setBtn)  ;


        setBtn.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                i++; // can only set 6 alarms
                int yr, mth, day, hr, min;
                int timeSec;

                if (i <= 6) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        hr = timePick.getHour();
                        min = timePick.getMinute();
                    } else {
                        hr = timePick.getCurrentHour();
                        min = timePick.getCurrentMinute();
                    }
                    mth = datePick.getMonth();
                    day = datePick.getDayOfMonth();
                    yr = datePick.getYear();

                    timeSec = componentTimeToTimestamp(yr, mth, day, hr, min);
                    Toast.makeText(getContext(), "Alarm set for " + mth + " " + day + ", " + yr + " at " + hr + ":" + min, Toast.LENGTH_LONG).show();
                    Particle.sendAlarm(Particle.light, timeSec, getDim(dimBar));
                }
                else { Toast.makeText(getContext(), "Error: more than 6 alarms set", Toast.LENGTH_LONG).show(); }
            }
        });
        return v;
    }

    int componentTimeToTimestamp(int year, int month, int day, int hour, int minute) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return (int) (c.getTimeInMillis() / 1000L);
    }

    private int getDim(SeekBar dimBar){
        final int[] dim = {0};
        dimBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // FOR TESTING //
                seekBar.setMax(100);
                dim[0] = progress;
            }
        });
        return dim[0];
    }
}
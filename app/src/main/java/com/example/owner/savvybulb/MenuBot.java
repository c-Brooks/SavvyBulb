package com.example.owner.savvybulb;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.*;
import android.widget.Button;


public class MenuBot extends Fragment {
    public MenuBot() {}

    Fragment frag = new Fragment();
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_content, container, true);

        final Button homeBtn  = (Button) v.findViewById(R.id.homeBtn);
        final Button alarmBtn = (Button) v.findViewById(R.id.alarmBtn);
        final Button timerBtn = (Button) v.findViewById(R.id.timerBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                homeBtn.setBackgroundColor(0x0FDFF00);
                alarmBtn.setBackgroundColor(0x0EFCC00);
                timerBtn.setBackgroundColor(0x0EFCC00);
                */
                frag = new Home();
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                fragTrans.replace(R.id.container, frag);
                fragTrans.addToBackStack(null);
                fragTrans.commit();
            }
        });

        alarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                homeBtn.setBackgroundColor(0xEFCC00);
                alarmBtn.setBackgroundColor(0x0FDFF00);
                timerBtn.setBackgroundColor(0x0EFCC00);
                */
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                frag = new Alarm();
                fragTrans.replace(R.id.container, frag);
                fragTrans.addToBackStack(null);
                fragTrans.commit();
            }
        });

        timerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                homeBtn.setBackgroundColor(0xEFCC00);
                alarmBtn.setBackgroundColor(0xEFCC00);
                timerBtn.setBackgroundColor(0x0FDFF00);
                */
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                frag = new Timer();
                fragTrans.replace(R.id.container, frag);
                fragTrans.addToBackStack(null);
                fragTrans.commit();
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

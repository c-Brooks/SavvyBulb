package com.example.owner.savvybulb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;


public class Home extends Fragment {

    public Home() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.home_content, container, false);

        final Button onBtn  = (Button) v.findViewById(R.id.onBtn);
        final Button offBtn = (Button) v.findViewById(R.id.offBtn);

        onBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBtn.setBackgroundColor(0x0FDFF00);
//                offBtn.setBackgroundColor(0x0EFCC00);

                Particle.sendDim(Particle.light, 128);
            }
        });

        offBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                offBtn.setBackgroundColor(0x0FDFF00);
//                onBtn.setBackgroundColor(0x0EFCC00);

                Particle.sendDim(Particle.light, 0);
            }
        });

        final SeekBar dimBar = (SeekBar) v.findViewById(R.id.homeDimBar);

        dimBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBar.setMax(100);
                Particle.sendDim(Particle.light, progress);
            }
        });

        return v;
    }
}
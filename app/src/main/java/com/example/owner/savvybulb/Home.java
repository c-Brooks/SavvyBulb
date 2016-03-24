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

    public Home(/* Add parameter for which light? */) {}


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.home_content, container, false);
        final SeekBar dimBar = (SeekBar) v.findViewById(R.id.homeDimBar);
        final TextView dimText = (TextView) v.findViewById(R.id.dimText);

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
                seekBar.setMax(128);
                dimText.setText("DIM:  " + progress);

                Particle.sendDim(Particle.light(), progress);
                // IMPLEMENT ACTUAL CODE LATER //

            }
        });








        return v;
    }
}
package com.example.owner.savvybulb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Timer extends Fragment {

    Fragment frag;

    public Timer() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timer_content, container, false);

//        return super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }
}

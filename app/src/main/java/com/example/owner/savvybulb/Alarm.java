package com.example.owner.savvybulb;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Alarm extends Fragment {

    Fragment frag;

    public Alarm() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.alarm_content, container, false);

//        return super.onCreateView(inflater, container, savedInstanceState);
    return v;
    }
}

package com.example.scherr3143.actionbarexperiment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by nathanscherr on 2017-03-02.
 */

public class BreakfastFragment extends Fragment{

    public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breakfast,container,false);
    }
}

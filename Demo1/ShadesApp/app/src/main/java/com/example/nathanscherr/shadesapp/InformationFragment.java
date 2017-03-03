package com.example.nathanscherr.shadesapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nathanscherr on 2017-03-02.
 */

public class InformationFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.information_fragment, container, false);
        return view;
    }

    public void setText(String shadeInfo){

        TextView view = (TextView) getView().findViewById(R.id.textView1);
        view.setText(shadeInfo);
    }
}

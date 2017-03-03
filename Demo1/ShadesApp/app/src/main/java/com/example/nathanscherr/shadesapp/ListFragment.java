package com.example.nathanscherr.shadesapp;

import android.view.View;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nathanscherr on 2017-03-02.
 */

public class ListFragment extends Fragment{

    private OnItemSelectedListener listener;
    private String information;

    public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.list_fragment, container, false);

        Button button1 = (Button) view.findViewById(R.id.button1);
        button1.setOnClickListener(ShadeChangeListener);

        Button button2 = (Button) view.findViewById(R.id.button2);
        button2.setOnClickListener(ShadeChangeListener);

        Button button3 = (Button) view.findViewById(R.id.button3);
        button3.setOnClickListener(ShadeChangeListener);

        return view;
    }

    private OnClickListener ShadeChangeListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            String description = (String) v.getContentDescription();
            information = description;
            updateDetail();
        }
    };

    public interface OnItemSelectedListener {
        public void onShadeItemSelected(String link);
    }

    public void onAttach(Activity activity){
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener){
            listener = (OnItemSelectedListener) activity;
        }
        else{
            throw new ClassCastException(activity.toString() + " must implement ListFragment.OnItemSelectedListener");
        }
    }

    public void updateDetail(){
        listener.onShadeItemSelected(information);
    }
}

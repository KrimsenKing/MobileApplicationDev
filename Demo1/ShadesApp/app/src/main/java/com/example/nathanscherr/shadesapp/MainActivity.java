package com.example.nathanscherr.shadesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements ListFragment.OnItemSelectedListener{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShadeItemSelected(String link){

        //Check if the information fragment exists in this layout
        InformationFragment informationFragment = (InformationFragment) getFragmentManager().findFragmentById(R.id.fragment2);

        //Check if a two pane configuration being displayed?
        if(informationFragment != null && informationFragment.isInLayout()){
            informationFragment.setText(link);
        }
        //A single pane exists
        else{
            //If the information fragment does not exist in this layout
            //activate the information fragment
            Intent intent = new Intent (this, InformationActivity.class);
            intent.putExtra("Information", link);
            startActivity(intent);
        }
    }
}

package com.example.nathanscherr.shadesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nathanscherr on 2017-03-02.
 */

public class InformationActivity extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //Verify the orientation has been switched to landscape mode
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        //Set the layout for this activity
        setContentView(R.layout.information_fragment);

        //Display the up icon in the actionbar
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Return the intent that started this activity
        Intent intent = getIntent();
        String shadeInformation = intent.getStringExtra("Information");

        TextView information = (TextView) findViewById(R.id.textView1);
        information.setText(shadeInformation);

    }
}

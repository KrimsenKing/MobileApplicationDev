package com.example.scherr3143.paintapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
/**
 * Created by scherr3143 on 1/10/2017.
 */
public class HepActivity extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_layout);
    }

    public void gotoInput(View view) {
        finish();
    }
}

package com.example.scherr3143.personnel_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new personData(854652, 12564, "Joey Time", "468 That Street", "639-456-8564", "timeiseternal@theuniverse.ca",
                "Tardis Repair Man", "The Doctor", "Time Lord", new Date(1964,6,24), 'N');
    }


}

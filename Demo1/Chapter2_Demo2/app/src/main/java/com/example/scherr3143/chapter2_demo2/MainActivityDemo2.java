package com.example.scherr3143.chapter2_demo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivityDemo2 extends Activity {

    private RadioGroup pattyRG;
    private CheckBox prosciuttoCBX;
    private RadioGroup cheeseRG;
    private SeekBar sauceSBR;
    private TextView caloriesTV;

    private Burger burger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_demo2);

        burger = new Burger();
        initialize();
        registerChangeListener();
    }

    private void initialize(){
        pattyRG = (RadioGroup) findViewById(R.id.radioGroup1);
        prosciuttoCBX = (CheckBox) findViewById(R.id.checkBox1);
        cheeseRG = (RadioGroup) findViewById(R.id.radioGroup2);
        sauceSBR = (SeekBar) findViewById(R.id.seekBar1);
        caloriesTV = (TextView) findViewById(R.id.textView2);
    }

    private void registerChangeListener(){
        pattyRG.setOnCheckedChangeListener(foodListener);
        prosciuttoCBX.setOnClickListener(baconListener);
        cheeseRG.setOnCheckedChangeListener(foodListener);
        sauceSBR.setOnSeekBarChangeListener(sauceListener);
    }

    private OnCheckedChangeListener foodListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup rbGroup, int radioID) {
            switch(radioID){
                case 0x7f0b0056: //Beef Patty
                    burger.setmPattyCal(Burger.Beef);
                    break;
                case 0x7f0b0057: //Lamb Patty
                    burger.setmPattyCal(Burger.Lamb);
                    break;
                case 0x7f0b0058: //Ostrich Patty
                    burger.setmPattyCal(Burger.Ostrich);
                    break;
                case 0x7f0b005b: //Asiago Cheese
                    burger.setmCheeseCal(Burger.Asiago);
                    break;
                case 0x7f0b005c: //Creme Fraiche
                    burger.setmCheeseCal(Burger.CremeFraiche);
                    break;
            }
            displayCalories();
        }
    };

    private OnClickListener baconListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if(((CheckBox) v).isChecked())
                burger.setmProsciuttoCal(Burger.Prosciutto);
            else
                burger.clearmProsciuttoCal();
            displayCalories();
        }
    };

    private OnSeekBarChangeListener sauceListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            burger.setmSauceCal(seekBar.getProgress());
            displayCalories();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void displayCalories(){
        String calorieText = "Calories: " + burger.getTotalCalories();
        caloriesTV.setText(calorieText);
    }


}

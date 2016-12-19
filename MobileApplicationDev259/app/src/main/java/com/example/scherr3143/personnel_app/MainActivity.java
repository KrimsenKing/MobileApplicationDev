package com.example.scherr3143.personnel_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText PersonnelID;
    EditText PictureID;
    EditText Name;
    EditText Address;
    EditText PhoneNumber;
    EditText Email;
    EditText Position;
    EditText Supervisor;
    EditText SupervisorPos;
    EditText BirthDate;
    EditText Age;
    EditText Married;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personData peeps = new personData(854652, 12564, "Joey Time", "468 That Street", "639-456-8564", "timeiseternal@theuniverse.ca",
                "Tardis Repair Man", "The Doctor", "Time Lord", new Date(1964,6,24), setAge(new Date(1964,6,24)), 'N');



        PersonnelID = (EditText) findViewById(R.id.editText2);
        //PictureID = (EditText) findViewById(R.id.editText2);
        Name = (EditText) findViewById(R.id.editText1);
        Address = (EditText) findViewById(R.id.editText3);
        PhoneNumber = (EditText) findViewById(R.id.editText4);
        Email = (EditText) findViewById(R.id.editText5);
        Position = (EditText) findViewById(R.id.editText6);
        Supervisor = (EditText) findViewById(R.id.editText7);
        SupervisorPos = (EditText) findViewById(R.id.editText8);
        BirthDate = (EditText) findViewById(R.id.editText9);
        Age = (EditText) findViewById(R.id.editText10);
        Married = (EditText) findViewById(R.id.editText11);


        PersonnelID.setText(String.valueOf(peeps.getPersonnelID()));
        Name.setText(peeps.getName());
        Address.setText(peeps.getAddress());
        PhoneNumber.setText(peeps.getPhone());
        Email.setText(peeps.getEmail());
        Position.setText(peeps.getPosition());
        Supervisor.setText(peeps.getSupervisorName());
        SupervisorPos.setText(peeps.getSupervisorRole());
        BirthDate.setText(String.valueOf(peeps.getBirthDate()));
        Age.setText(String.valueOf(peeps.getAge()));
        Married.setText(String.valueOf(peeps.getMarried()));

    }
    public int setAge(Date BirthDate) {
        int cYear = Calendar.getInstance().get(Calendar.YEAR);
        Calendar bYear = Calendar.getInstance();
        bYear.setTime(BirthDate);
        int Age = cYear - bYear.get(Calendar.YEAR);
        return Age;
    }
}

package com.example.scherr3143.personnel_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    EditText PersonnelID;
    ImageView PictureID;
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
    private personData personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personData peeps = new personData(854652, R.drawable.pid12564, "Joey Time", "468 That Street", "639-456-8564", "timeiseternal@theuniverse.ca",
                "Tardis Repair Man", "The Doctor", "Time Lord", "24-6-1964", setAge("24-6-1964"), "N");


        PersonnelID = (EditText) findViewById(R.id.editText2);
        PictureID = (ImageView) findViewById(R.id.imageView1);
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
        PictureID.setImageResource(peeps.getPictureID());
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

    public int setAge(String BirthDate) {
        int Age = 0;
        try {
            int cYear = Calendar.getInstance().get(Calendar.YEAR);
            SimpleDateFormat df = new SimpleDateFormat("DD-MM-yyyy");
            Date bd = df.parse(BirthDate);
            Calendar bYear = Calendar.getInstance();
            bYear.setTime(bd);
            Age = cYear - bYear.get(Calendar.YEAR);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Age;
    }

    private void registerChangeListener() {
        PersonnelID.setOnFocusChangeListener(textListener);
        Name.setOnFocusChangeListener(textListener);
        Address.setOnFocusChangeListener(textListener);
        PhoneNumber.setOnFocusChangeListener(textListener);
        Email.setOnFocusChangeListener(textListener);
        Position.setOnFocusChangeListener(textListener);
        Supervisor.setOnFocusChangeListener(textListener);
        SupervisorPos.setOnFocusChangeListener(textListener);
        BirthDate.setOnFocusChangeListener(textListener);
        Married.setOnFocusChangeListener(textListener);
    }

    private OnFocusChangeListener textListener = new OnFocusChangeListener() {

        public void OnFocusChange(View v, boolean hasFocus) {
            if(!hasFocus) {
                switch (v.getId()) {
                    case R.id.editText2:
                        personData.setPersonnelID(Integer.valueOf(PersonnelID.toString()));
                        break;
                    case R.id.editText1:
                        personData.setName(Name.toString());
                        break;
                    case R.id.editText3:
                        personData.setAddress(Address.toString());
                        break;
                    case R.id.editText4:
                        personData.setPhone(PhoneNumber.toString());
                        break;
                    case R.id.editText5:
                        personData.setEmail(Email.toString());
                        break;
                    case R.id.editText6:
                        personData.setPosition(Position.toString());
                        break;
                    case R.id.editText7:
                        personData.setSupervisorName(Supervisor.toString());
                        break;
                    case R.id.editText8:
                        personData.setSupervisorRole(SupervisorPos.toString());
                        break;
                    case R.id.editText9:
                        personData.setBirthDate(BirthDate.toString());
                        break;
                    case R.id.editText11:
                        personData.setMarried(Married.toString());
                        break;
                }
            }
        }
    };
}
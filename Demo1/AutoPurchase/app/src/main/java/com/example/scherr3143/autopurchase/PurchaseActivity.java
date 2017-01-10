package com.example.scherr3143.autopurchase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {

    //The auto object contains the information about the vehicle being purchased
    Auto mAuto;

    //The data to be passed to the loan activity
    String loanReport;
    String monthlyPayment;

    //Layout input references
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        //Establish references to editable text fields and radio button
        carPriceET = (EditText) findViewById(R.id.editText1);
        downPayET = (EditText) findViewById(R.id.editText2);
        loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);

        //Create an Auto object to store auto data
        mAuto = new Auto();
    }

    private void collectAutoInputData(){
        //Task 1: Set the car price
        mAuto.setPrice((double) Integer.valueOf(carPriceET.getText().toString()));

        //Task 2: Set the down payment
        mAuto.setDownPayment((double) Integer.valueOf(downPayET.getText().toString()));

        //Task 3: Set the loan term
        Integer radioID = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById(radioID);
        mAuto.setLoanTerm(term.getText().toString());
    }

    private void buildLoanReport(){

        //Task 1: Construct the monthly payment
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) + String.format("%.02f", mAuto.monthlyPayment());

        //Task 2: Constuct the loan report
        loanReport = res.getString(R.string.report_line6) + String.format("%10.02f", mAuto.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f", mAuto.getDownPayment());
        loanReport += res.getString(R.string.report_line9) + String.format("%18.02f", mAuto.taxAmount());
        loanReport += res.getString(R.string.report_line10) + String.format("%18.02f", mAuto.totalCost());
        loanReport += res.getString(R.string.report_line11) + String.format("%12.02f", mAuto.borrowdAmount());
        loanReport += res.getString(R.string.report_line12) + String.format("%12.02f", mAuto.interestAmount());

        loanReport += "\n\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";
        loanReport += "\n\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);
    }

    public void activaeLoanSummary(View view){
        //Task 1: Build a loan report from the input data
        collectAutoInputData();
        buildLoanReport();

        //Task 2: Create an intent to display the loan summary activity
        Intent launchReport = new Intent(this,LoanSummaryActivity.class);

        //Task 3: Pass the loan summary actrivity two pieces of data:
            //The loan report containing loan details
            //The monthly payment
        launchReport.putExtra("LoanReport", loanReport);
        launchReport.putExtra("MonthlyPayment", monthlyPayment);

        //Task 4: Start the loan activity
        startActivity(launchReport);
    }
}

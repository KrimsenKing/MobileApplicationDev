package com.example.scherr3143.autopurchase;


import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by scherr3143 on 1/10/2017.
 */
public class LoanSummaryActivity extends AppCompatActivity {

    //The auto object contains the information about the vehicle being purchased
    Auto mAuto;

    //The data to be passed to the loan activity
    String loanReport;
    String monthlyPayment;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loansummary_layout);
        TextView monthlyPayET = (TextView) findViewById(R.id.textView2);
        TextView loanReportET = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();

        double carPrice;
        carPrice = intent.getDoubleExtra("CarPrice",0);

        double downPay;
        downPay = intent.getDoubleExtra("downPayment",0);

        String strLoan;
        strLoan = intent.getStringExtra("loanTerm");

        //Create an Auto object to store auto data
        mAuto = new Auto();

        //Task 1: Build a loan report from the input data
        collectAutoInputData(carPrice,downPay,strLoan);
        buildLoanReport();


        monthlyPayET.setText(monthlyPayment);
        loanReportET.setText(loanReport);
    }

    private void collectAutoInputData(Double carPrice, Double downPay, String loan){
        //Task 1: Set the car price
        mAuto.setPrice(carPrice);

        //Task 2: Set the down payment
        mAuto.setDownPayment(downPay);

        //Task 3: Set the loan term
        mAuto.setLoanTerm(loan);
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

        loanReport += "\n" + res.getString(R.string.report_line8) + " " + mAuto.getLoanTerm() + " years.";
        loanReport += "\n" + res.getString(R.string.report_line2);
        loanReport += res.getString(R.string.report_line3);
        loanReport += res.getString(R.string.report_line4);
        loanReport += res.getString(R.string.report_line5);
    }

    public void goDataEntry(View view){
        Intent returnResults = new Intent(this,PurchaseActivity.class);
        returnResults.putExtra("TotalCost",mAuto.totalCost());
        returnResults.putExtra("BorrowedAmount",mAuto.borrowdAmount());
        returnResults.putExtra("InterestAmount",mAuto.interestAmount());
        setResult(RESULT_OK,returnResults);
        finish();
    }
}

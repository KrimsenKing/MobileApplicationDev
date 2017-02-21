package com.example.scherr3143.autopurchase;

import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.TextView;

public class PurchaseActivity extends AppCompatActivity {

    //Layout input references
    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    private TextView totalCostTV;
    private TextView borrowedAmountTV;
    private TextView interestAmountTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);

        //Establish references to editable text fields and radio button
        carPriceET = (EditText) findViewById(R.id.editText1);
        downPayET = (EditText) findViewById(R.id.editText2);
        loanTermRG = (RadioGroup) findViewById(R.id.radioGroup1);

        totalCostTV = (TextView) findViewById(R.id.tvTotalCost);
        borrowedAmountTV = (TextView) findViewById(R.id.tvBorrowedAmount);
        interestAmountTV = (TextView) findViewById(R.id.tvInterestAmount);

    }

    public void activateLoanSummary(View view){

        //Task 2: Create an intent to display the loan summary activity
        Intent launchReport = new Intent(this,LoanSummaryActivity.class);

        //Tas0k 3: Pass the loan summary activity two pieces of data:
            //The loan report containing loan details
            //The monthly payment

        launchReport.putExtra("CarPrice", (double) Integer.valueOf(carPriceET.getText().toString()));
        launchReport.putExtra("downPayment", (double) Integer.valueOf(downPayET.getText().toString()));
        Integer radID = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById(radID);
        launchReport.putExtra("loanTerm", term.getText().toString());

        //Task 4: Start the loan activity
        startActivityForResult(launchReport,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent returnResults) {
        super.onActivityResult(requestCode,resultCode,returnResults);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                double totalCost = returnResults.getDoubleExtra("TotalCost",0);
                double borrowedAmount = returnResults.getDoubleExtra("BorrowedAmount",0);
                double interestAmount = returnResults.getDoubleExtra("InterestAmount",0);

                totalCostTV.setText(String.valueOf(totalCost));
                borrowedAmountTV.setText(String.valueOf(borrowedAmount));
                interestAmountTV.setText(String.valueOf(interestAmount));

            }
        }



    }
}

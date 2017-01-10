package com.example.scherr3143.autopurchase;

/**
 * Created by scherr3143 on 1/10/2017.
 */
public class Auto {

    static final double STATE_TAX = .07;
    static final double INTEREST_RATE = .09;

    private double mPrice;
    private double mDownPayment;
    private int mLoanTerm;

    public void setPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public double getPrice() {
        return mPrice;
    }

    public void setDownPayment(double mDownPayment) {
        this.mDownPayment = mDownPayment;
    }

    public double getDownPayment() {
        return mDownPayment;
    }

    public void setLoanTerm(String term) {
        if(term.contains("2"))
            this.mLoanTerm = 2;
        else if(term.contains("3"))
            this.mLoanTerm = 3;
        else
            this.mLoanTerm = 4;
    }

    public int getLoanTerm() {
        return mLoanTerm;
    }

    public double taxAmount(){
        return mPrice * STATE_TAX;
    }

    public double totalCost(){
        return mPrice + taxAmount();
    }

    public double borrowdAmount(){
        return totalCost() - mDownPayment;
    }

    public double interestAmount(){
        return borrowdAmount() * INTEREST_RATE;
    }

    public double monthlyPayment(){
        return borrowdAmount() / (mLoanTerm * 12);
    }
}

package com.example.scherr3143.chapter2_demo2;

/**
 * Created by scherr3143 on 11/15/2016.
 */
public class Burger {

    static final int Beef = 100;
    static final int Lamb = 170;
    static final int Ostrich = 150;
    static final int Asiago = 90;
    static final int CremeFraiche = 120;
    static final int Prosciutto = 115;

    private int mPattyCal;
    private int mCheeseCal;
    private int mProsciuttoCal;
    private int mSauceCal;

    public Burger(){
        mPattyCal = Beef;
        mCheeseCal = Asiago;
        mProsciuttoCal = 0;
        mSauceCal = 0;
    }

    public void setmPattyCal(int calories){
        mPattyCal = calories;
    }

    public void setmCheeseCal (int calories){
        mCheeseCal = calories;
    }

    public void setmProsciuttoCal (int calories){
        mProsciuttoCal = calories;
    }

    public void clearmProsciuttoCal (){
        mProsciuttoCal = 0;
    }

    public void setmSauceCal (int calories){
        mSauceCal = calories;
    }

    public int getTotalCalories(){
        return mPattyCal + mCheeseCal + mProsciuttoCal + mSauceCal;
    }
}

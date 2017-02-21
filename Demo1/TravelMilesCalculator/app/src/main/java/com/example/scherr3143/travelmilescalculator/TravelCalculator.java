package com.example.scherr3143.travelmilescalculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;

import java.util.List;
import java.util.Objects;

/**
 * Created by scherr3143 on 1/23/2017.
 */
public class TravelCalculator {

    private String strCityFrom;
    private String strCityTo;
    private String strDiscountCode;
    private int intDistance;
    private double dblTicketPrice;
    private int intBonusMiles;

    public String getStrCityFrom() {
        return strCityFrom;
    }

    public void setStrCityFrom(String strCityFrom) {
        this.strCityFrom = strCityFrom;
        //Log.d("strCityFrom creater",this.strCityFrom);
    }

    public String getStrCityTo() {
        return strCityTo;
    }

    public void setStrCityTo(String strCityTo) {
        this.strCityTo = strCityTo;
        //Log.d("strCityTo creater",this.strCityTo);
    }

    public TravelCalculator(){}

    public String getStrDiscountCode(){ return strDiscountCode; }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setStrDiscountCode(String discountCode){
        //Log.d("Entered setStrDiscount","yes");
        if (Objects.equals(discountCode,"savings")){
            this.strDiscountCode = discountCode;
            //Log.d("strDiscountCode creater",this.strDiscountCode);
        }
        else{
            discountCode = "no discount";
            this.strDiscountCode = discountCode;
            //Log.d("strDiscountCode creater",this.strDiscountCode);
        }
    }

    public TravelCalculator(String strCityFrom, String strCityTo)
    {
        setStrCityFrom(strCityFrom);
        setStrCityTo(strCityTo);
    }

    public TravelCalculator(String strCityFrom, String strCityTo, String strDiscountCode)
    {
        setStrCityFrom(strCityFrom);
        setStrCityTo(strCityTo);
        setStrDiscountCode(strDiscountCode);
        setTCParameters();
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void setTCParameters()
    {
        if(Objects.equals(strCityFrom, "Regina")){
            if(Objects.equals(strCityTo,"Edmonton")){
                intDistance = 691;
                dblTicketPrice = 175.00;
                intBonusMiles = (int) (intDistance * 0.10);
            }
            else if(Objects.equals(strCityTo,"Vancouver")) {
                intDistance = 1335;
                dblTicketPrice = 245.00;
                intBonusMiles = (int) (intDistance * 0.18);
            }
        }
        else if(Objects.equals(strCityFrom, "Edmonton")){
            if(Objects.equals(strCityTo,"Regina")){
                intDistance = 691;
                dblTicketPrice = 175.00;
                intBonusMiles = (int) (intDistance * 0.10);
            }
            else if(Objects.equals(strCityTo,"Vancouver")) {
                intDistance = 809;
                dblTicketPrice = 195.00;
                intBonusMiles = (int) (intDistance * 0.12);
            }
        }
        else if(Objects.equals(strCityFrom, "Vancouver")){
            if(Objects.equals(strCityTo,"Edmonton")){
                intDistance = 809;
                dblTicketPrice = 195.00;
                intBonusMiles = (int) (intDistance * 0.12);
            }
            else if(Objects.equals(strCityTo,"Regina")) {
                intDistance = 1335;
                dblTicketPrice = 245.00;
                intBonusMiles = (int) (intDistance * 0.18);
            }
        }
        //Log.d("intDistance creater",String.valueOf(intDistance));
        //Log.d("dblTicketPrice creater",String.valueOf(dblTicketPrice));
        //Log.d("intBonusMils creater",String.valueOf(intBonusMiles));
    }

    public int getDistance(){
        return intDistance;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public double getTicketPrice(){
        if(Objects.equals(strDiscountCode,"savings")){
            return dblTicketPrice - (dblTicketPrice * 0.10);
        }
        else {
            return dblTicketPrice;
        }
    }

    public int getBonusMiles(){
        return intBonusMiles;
    }
}

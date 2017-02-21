package com.example.scherr3143.travelmilescalculator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by scherr3143 on 1/23/2017.
 */
public class TravelResultsActivity extends AppCompatActivity {

    private String discountCode;
    private String cityFrom;
    private String cityTo;
    private String distance;
    private String ticketPrice;
    private String bonusMiles;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travelresults_relativelayout);

        TextView tvFromCityResult = (TextView) findViewById(R.id.tvFromCityResult);
        TextView tvToCityResult = (TextView) findViewById(R.id.tvToCityResult);
        TextView tvDistanceResult = (TextView) findViewById(R.id.tvDistanceResult);
        TextView tvTicketPriceResult = (TextView) findViewById(R.id.tvTicketPriceResult);
        TextView tvBonusMilesResult = (TextView) findViewById(R.id.tvBonusMileResult);
        TextView tvDiscountCodeResult = (TextView) findViewById(R.id.tvDiscountResult);

        Intent intent = getIntent();

        cityFrom = intent.getStringExtra("CityFrom");
        cityTo = intent.getStringExtra("CityTo");
        discountCode = intent.getStringExtra("DiscountCode");

        Log.d("put into Intent Cities",discountCode);

        TravelCalculator tc;
        tc = new TravelCalculator(cityFrom,cityTo,discountCode);

        distance = String.valueOf(tc.getDistance());
        ticketPrice = String.valueOf(tc.getTicketPrice());
        bonusMiles = String.valueOf(tc.getBonusMiles());

        tvDistanceResult.setText(String.valueOf(tc.getDistance()));
        tvTicketPriceResult.setText(String.valueOf(tc.getTicketPrice()));
        tvBonusMilesResult.setText(String.valueOf(tc.getBonusMiles()));
        tvFromCityResult.setText(tc.getStrCityFrom());
        tvToCityResult.setText(tc.getStrCityTo());
        tvDiscountCodeResult.setText(tc.getStrDiscountCode());

    }
    public void goDataEntry(View view){

        //Intent returnResults = new Intent();

        //returnResults.putExtra("CityFrom",cityFrom);
        //returnResults.putExtra("CityTo",cityTo);
        //returnResults.putExtra("DiscountCode",discountCode);
        //returnResults.putExtra("Distance",distance);
        //returnResults.putExtra("TicketPrice",ticketPrice);
        //returnResults.putExtra("BonusMiles",bonusMiles);

        setResult(RESULT_OK);
        finish();

    }
}

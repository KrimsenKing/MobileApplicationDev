package com.example.scherr3143.travelmilescalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CitiesPage extends AppCompatActivity {

    private Spinner citySpinner1;
    private Spinner citySpinner2;
    private EditText etCode;
    private TextView tvDistanceResult;
    private TextView tvBonusMilesResult;
    private TextView tvTicketPriceResult;
    private String discountCode;
    private String cityFrom;
    private String cityTo;
    private int intRequestCode;
    private String distance;
    private String ticketPrice;
    private String bonusMile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincitiespage_relativelayout);

        Spinner dropdownFrom = (Spinner)findViewById(R.id.tvSpinnerItem1);
        String[] itemsFrom = new String[]{"Regina", "Edmonton", "Vancouver"};
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsFrom);
        dropdownFrom.setAdapter(adapterFrom);

        Spinner dropdownTo = (Spinner)findViewById(R.id.tvSpinnerItem2);
        String[] itemsTo = new String[]{"Edmonton", "Vancouver", "Regina"};
        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, itemsTo);
        dropdownTo.setAdapter(adapterTo);

        tvDistanceResult = (TextView)findViewById(R.id.tvDiscountResult);
        tvBonusMilesResult = (TextView)findViewById(R.id.tvBonusMileResult);
        tvTicketPriceResult = (TextView)findViewById(R.id.tvTicketPriceResult);
    }

    public void activateTravelResults(View view){

        etCode = (EditText) findViewById(R.id.etDiscountCode);
        citySpinner1 = (Spinner) findViewById(R.id.tvSpinnerItem1);
        citySpinner2 = (Spinner) findViewById(R.id.tvSpinnerItem2);

        discountCode = etCode.toString();
        cityFrom = citySpinner1.getSelectedItem().toString();
        cityTo = citySpinner2.getSelectedItem().toString();

        Intent launchResults = new Intent(this, TravelResultsActivity.class);
        intRequestCode = 1;

        launchResults.putExtra("CityFrom",cityFrom);
        launchResults.putExtra("CityTo",cityTo);
        launchResults.putExtra("DiscountCode",discountCode);
        //Log.d("put into Intent Cities",String.valueOf(discountCode));

        startActivityForResult(launchResults, intRequestCode);
        //startActivity(launchResults);
    }

    @Override
    protected void onActivityResult(int intRequestCode, int intResultCode, Intent returnResults){
        super.onActivityResult(intRequestCode,intResultCode,returnResults);

        if(intRequestCode == 1){
            if(intResultCode==RESULT_OK){

                //cityFrom = returnResults.getStringExtra("CityFrom");
                //cityTo = returnResults.getStringExtra("CityTo");
                //discountCode = returnResults.getStringExtra("DiscountCode");
                distance = returnResults.getStringExtra("Distance");
                ticketPrice = returnResults.getStringExtra("TicketPrice");
                bonusMile = returnResults.getStringExtra("BonusMiles");

                tvDistanceResult = (TextView)findViewById(R.id.tvDiscountResult);
                tvBonusMilesResult = (TextView)findViewById(R.id.tvBonusMileResult);
                tvTicketPriceResult = (TextView)findViewById(R.id.tvTicketPriceResult);


 //               tvDistanceResult.setText(distance);
  //              tvBonusMilesResult.setText(bonusMile);
 //               tvTicketPriceResult.setText(ticketPrice);
                tvDistanceResult.setText("111");
                tvBonusMilesResult.setText("222");
                tvTicketPriceResult.setText("333");
                //citySpinner1.setSelection(1);
                //citySpinner2.setSelection(1);
                //etCode.setText(discountCode);

            }
        }
    }

}

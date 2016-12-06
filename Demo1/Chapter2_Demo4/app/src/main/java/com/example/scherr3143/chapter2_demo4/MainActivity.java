package com.example.scherr3143.chapter2_demo4;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity {

    private TextView mNumberDisplay;
    private simpleExpression mExpression;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNumberDisplay = (TextView) findViewById(R.id.textView1);
        mExpression = new simpleExpression();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void goAC(View view) {
        mExpression.clearOperands();
        mNumberDisplay.setText("0");
    }

    public void goOperand(View view) {
        String val = (String) mNumberDisplay.getText();
        String digit = (String) view.getContentDescription();
        if (val.charAt(0) == '0') {
            mNumberDisplay.setText(digit);
        } else {
            mNumberDisplay.setText((String) mNumberDisplay.getText() + digit.charAt(0));
        }
    }

    public void goOperator(View view) {
        String operator = (String) view.getContentDescription();
        try {
            String val = (String) mNumberDisplay.getText();
            mExpression.setOperand1((int) Integer.parseInt(val.toString()));
        } catch (NumberFormatException e) {
            mExpression.setOperand1(0);
        }
        mNumberDisplay.setText("0");
        mExpression.setOperator(operator);
    }

    public void goCompute(View view) {
        try {
            String val = (String) mNumberDisplay.getText();
            mExpression.setOperand2((int) Integer.parseInt(val.toString()));
        } catch (NumberFormatException e) {
            mExpression.setOperand2(0);
        }
        mNumberDisplay.setText(mExpression.getValue().toString());
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.scherr3143.chapter2_demo4/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.scherr3143.chapter2_demo4/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
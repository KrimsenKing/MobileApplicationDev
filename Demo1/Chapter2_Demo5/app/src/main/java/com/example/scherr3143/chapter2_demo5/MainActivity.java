package com.example.scherr3143.chapter2_demo5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout mLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLinearLayout = (LinearLayout) findViewById(R.id.linearList);
        fillPaintingGallery();
    }

    private void fillPaintingGallery()
    {
        ImageButton buttonItem;
        for (int i = 0;i<RenaissanceDatabase.description.length;i++)
        {
            buttonItem = new ImageButton(this);
            Painting painting = new Painting(RenaissanceDatabase.description[i],RenaissanceDatabase.id[i]);
            buttonItem.setContentDescription(painting.getDescription());
            buttonItem.setImageDrawable(getResources().getDrawable(painting.getId()));
            buttonItem.setOnClickListener(displayPaintingInformation);
            mLinearLayout.addView(buttonItem);
        }
    }

    private View.OnClickListener displayPaintingInformation = new View.OnClickListener()
    {
        public void onClick(View btn){
            String paintingDescription = (String) btn.getContentDescription();
            displayToast(paintingDescription);
        }
    };

    private void displayToast(String paintingDescription)
    {
        Toast.makeText(this,paintingDescription,Toast.LENGTH_SHORT).show();
    }
}

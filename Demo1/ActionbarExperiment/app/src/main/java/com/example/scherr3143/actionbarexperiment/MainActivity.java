package com.example.scherr3143.actionbarexperiment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity{

    private static final String TAB_KEY_INDEX = "tab_key";
    private Fragment breakfastfragment;
    private Fragment snackfragment;
    private Fragment lunchfragment;
    private Fragment dinnerfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set the actionbar
        ActionBar actionBar = getActionBar();
        assert actionBar != null;
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(false);

        //Create the tabs and bind them to the action bar
        ActionBar.Tab breakfastTab = actionBar.newTab().setText(getString(R.string.ui_tabname_breakfast));
        ActionBar.Tab snackTab = actionBar.newTab().setText(getString(R.string.ui_tabname_snack));
        ActionBar.Tab lunchTab = actionBar.newTab().setText(getString(R.string.ui_tabname_lunch));
        ActionBar.Tab dinnerTab = actionBar.newTab().setText(getString(R.string.ui_tabname_dinner));

        //Create each fragment and bind them to the action bar
        breakfastfragment = new BreakfastFragment();
        snackfragment = new SnackFragment();
        lunchfragment = new LunchFragment();
        dinnerfragment = new DinnerFragment();

        //Set listener events for each of the action bar tabs
        breakfastTab.setTabListener(new MyTabListener(breakfastfragment, getApplicationContext()));
        snackTab.setTabListener(new MyTabListener(snackfragment, getApplicationContext()));
        lunchTab.setTabListener(new MyTabListener(lunchfragment, getApplicationContext()));
        dinnerTab.setTabListener(new MyTabListener(dinnerfragment, getApplicationContext()));

        //Add each of the tabs to the action bar
        actionBar.addTab(breakfastTab);
        actionBar.addTab(snackTab);
        actionBar.addTab(lunchTab);
        actionBar.addTab(dinnerTab);

        //Restore navigation
        if (savedInstanceState != null){
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(TAB_KEY_INDEX,0));
        }
    }

    class MyTabListener implements ActionBar.TabListener{

        public Fragment fragment;

        public MyTabListener(Fragment f, Context context){
            fragment = f;
        }

        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){}

        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
            ft.replace(R.id.fragment_container, fragment);
        }

        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){
            ft.remove(fragment);
        }

    }
}

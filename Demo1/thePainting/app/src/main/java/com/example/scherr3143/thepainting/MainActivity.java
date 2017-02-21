package com.example.scherr3143.thepainting;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    ViewGroup paintingContainer;
    Transition transition;

    Scene activeScene;
    Scene passiveScene;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        paintingContainer = (ViewGroup) findViewById(R.id.painting_container);
        transition = TransitionInflater.from(this).inflateTransition(R.anim.transition);

        activeScene = Scene.getSceneForLayout(paintingContainer,R.layout.scene01, this);
        passiveScene = Scene.getSceneForLayout(paintingContainer,R.layout.scene02, this);
        activeScene.enter();

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void changeScenes(View view){
        Scene temp = passiveScene;
        passiveScene = activeScene;
        activeScene = temp;

        TransitionManager.go(activeScene, transition);
    }
}

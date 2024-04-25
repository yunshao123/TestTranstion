package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionSet;
import android.view.Gravity;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.setDuration(300);
        //一起动画
        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.RIGHT);
        transitionSet.addTransition(slideTransition);
        Fade fadeTransition = new Fade();
        transitionSet.addTransition(fadeTransition);
        //排除状态栏
        transitionSet.excludeTarget(android.R.id.statusBarBackground, true);
        transitionSet.excludeTarget(android.R.id.navigationBarBackground, true);
        //是否同时执行
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowReturnTransitionOverlap(false);
        //进入
        getWindow().setEnterTransition(transitionSet);
        setContentView(R.layout.activity_test);
    }
}
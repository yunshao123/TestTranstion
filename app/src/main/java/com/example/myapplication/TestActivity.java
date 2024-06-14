package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.number.Scale;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.VideoView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        getWindow().setAllowEnterTransitionOverlap(false);
        getWindow().setAllowEnterTransitionOverlap(false);
        TransitionSet transitionSet = new TransitionSet();
        Fade fede = new Fade();
        fede.addTarget(R.id.btn2);
        fede.setMode(Fade.IN);
        transitionSet.addTransition(fede);
        transitionSet.setDuration(3000L);
        getWindow().setEnterTransition(transitionSet);
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                Log.i("进场动画开始===========" , "333333333333");
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                Log.i("进场动画开始===========" , "4444444444444");
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
//        TransitionSet transitionSet = new TransitionSet();
//        transitionSet.setDuration(300);
//        //一起动画
//        transitionSet.setOrdering(TransitionSet.ORDERING_TOGETHER);
//        Slide slideTransition = new Slide();
//        slideTransition.setSlideEdge(Gravity.RIGHT);
//        transitionSet.addTransition(slideTransition);
//        Fade fadeTransition = new Fade();
//        transitionSet.addTransition(fadeTransition);
//        //排除状态栏
//        transitionSet.excludeTarget(android.R.id.statusBarBackground, true);
//        transitionSet.excludeTarget(android.R.id.navigationBarBackground, true);
//        //是否同时执行
//        getWindow().setAllowEnterTransitionOverlap(false);
//        getWindow().setAllowReturnTransitionOverlap(false);
        //进入
//        getWindow().setEnterTransition(transitionSet);
        setContentView(R.layout.activity_test);

        // 启动动画过渡
//        supportStartPostponedEnterTransition();
        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.setDuration(3000L);
        Log.i("测试=====","000000000000000");
        if (transition != null) {
            Log.i("测试=====","11111111111111111");
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    Log.i("测试=====","222222222222");
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    Log.i("测试=====","3333333333333333");
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            });
        }
    }
}
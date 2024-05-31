package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private int progress;

    private VideoView videoView;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        TextView tvShow = findViewById(R.id.tvShow);
        Button button = findViewById(R.id.btn1);
        videoView = findViewById(R.id.video1);
        imageView = findViewById(R.id.imageview);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
//        imageView.setImageResource(R.drawable.ic_launcher_background);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                        if (i == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START){
                            imageView.setVisibility(View.GONE);
                        }
                        return true;
                    }
                });
            }
        });
//        videoView.start();
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                videoView.start();
//            }
//        });
        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                TransitionSet transition = new TransitionSet();
                transition.addTransition(new Slide(Gravity.LEFT).addTarget(R.id.btn1));
                getWindow().setExitTransition(transition);
                ActivityOptions activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this);
                startActivity(intent,activityOptions.toBundle());
            }
        });
    }

    @Override
    protected void onResume() {
//        imageView.setVisibility(View.VISIBLE);
//        videoView.seekTo(progress);
//        videoView.start();
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        progress = videoView.getCurrentPosition();
        videoView.pause();
    }
}
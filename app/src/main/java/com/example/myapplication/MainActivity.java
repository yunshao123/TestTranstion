package com.example.myapplication;

import static android.transition.TransitionSet.ORDERING_TOGETHER;
import static android.transition.Visibility.MODE_IN;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.transition.Visibility;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int progress;

    private ImageView imageView;

    private TagTextView tagTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_main);
        TextView tvShow = findViewById(R.id.tvShow);
        Button button = findViewById(R.id.btn1);
        imageView = findViewById(R.id.imageview);
        imageView.setImageResource(R.drawable.ic_launcher_background);
        tagTextView = findViewById(R.id.tagTextView);
        //  尾部标签
        tagTextView.setTagsIndex(TagTextView.TAGS_INDEX_AT_END);
        tagTextView.setSingleTagAndContent("尾部Tags", "这是一个尾手动阀手动阀部带有tag的TextView士大夫随风倒士大");
//        videoView.start();
//        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                videoView.start();
//            }
//        });
        button.setVisibility(View.VISIBLE);
        tvShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
//                ObjectAnimator
//                AnimatorSet animatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.test_animator);
//                animatorSet.setTarget();
//                animatorSet.start();
                TransitionSet transitionSet = new TransitionSet();
                Fade fede = new Fade();
                fede.addTarget(R.id.btn1);
                fede.setMode(Fade.OUT);
                transitionSet.addTransition(fede);
                transitionSet.setDuration(3000L);
                getWindow().setExitTransition(transitionSet);
                getWindow().getExitTransition().addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                        Log.i("离场动画开始===========" , "11111111111111");
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        Log.i("离场动画结束===========" , "22222222222222");
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
//                Log.i("测试=====","......................");
                ActivityOptions activityOptions = ActivityOptions
                        .makeSceneTransitionAnimation(MainActivity.this);
                startActivity(intent,activityOptions.toBundle());
//                getWindow().getSharedElementExitTransition().setDuration(3000L);
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
    }

//    private void addTagToTextView(TextView target, String title, String tag) {
//        if (TextUtils.isEmpty(title)) {
//            title = "";
//        }
//
//        String content = title + tag;
//
//
//        /**
//         * 创建TextView对象，设置drawable背景，设置字体样式，设置间距，设置文本等
//         * 这里我们为了给TextView设置margin，给其添加了一个父容器LinearLayout。不过他俩都只是new出来的，不会添加进任何布局
//         */
//        LinearLayout layout = new LinearLayout(this);
//        TextView textView = new TextView(this);
//        textView.setText(tag);
//        textView.setBackground(getResources().getDrawable(R.drawable.room_member_role_bg));
//        textView.setTextSize(12);
//        textView.setTextColor(Color.parseColor("#FDA413"));
//        textView.setIncludeFontPadding(false);
//        textView.setPadding(ScreenUtils.dip2px(this, 6), 0,
//                ScreenUtils.dip2px(this, 6), 0);
//        textView.setHeight(ScreenUtils.dip2px(this, 17));
//        textView.setGravity(Gravity.CENTER_VERTICAL);
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        // 设置左间距
//        layoutParams.leftMargin = ScreenUtils.dip2px(this, 6);
//        // 设置下间距，简单解决ImageSpan和文本竖直方向对齐的问题
//        layoutParams.bottomMargin = ScreenUtils.dip2px(this, 3);
//        layout.addView(textView, layoutParams);
//
//        /**
//         * 第二步，测量，绘制layout，生成对应的bitmap对象
//         */
//        layout.setDrawingCacheEnabled(true);
//        layout.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//        // 给上方设置的margin留出空间
//        layout.layout(0, 0, textView.getMeasuredWidth() + ScreenUtils.dip2px(this, (6 + 3)), textView.getMeasuredHeight());
//        // 获取bitmap对象
//        Bitmap bitmap = Bitmap.createBitmap(layout.getDrawingCache());
//        //千万别忘最后一步
//        layout.destroyDrawingCache();
//
//        /**
//         * 第三步，通过bitmap生成我们需要的ImageSpan对象
//         */
//        ImageSpan imageSpan = new ImageSpan(this, bitmap);
//
//
//        /**
//         * 第四步将ImageSpan对象设置到SpannableStringBuilder的对应位置
//         */
//        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
//        //将尾部tag字符用ImageSpan替换
//        ssb.setSpan(imageSpan, title.length(), content.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        target.setText(ssb);
//    }
}
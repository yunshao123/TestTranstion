package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


/********************************
 * @name MaskView
 * @author 段露
 * @company 段露
 * @createDate 2024/6/25 0:10
 * @updateDate 2024/6/25 0:10
 * @version V1.0.0
 * @describe 自定义带镂空效果的蒙层.
 ********************************/
public class MaskView extends View {

    private int mWidth;
    private int mHeight;

    //蒙层颜色.
    private int mMaskColor;

    private Paint mPaint;
    //镂空区域完全展开动画持续时长(单位：毫秒).
    private long mUnfoldDuration;
    //镂空区域初始半径占最终半径(宽or高取最大值)的比率(默认：10%).
    private float mUnfoldStartRadiusRatio;
    //镂空区域圆半径.
    private float mRadius;
    //镂空区域路径.
    private Path mPath;

    //镂空区域展开动画.
    private ValueAnimator mUnfoldValueAnimator;

    public MaskView(Context context) {
        super(context);
        initView(context, null, 0, 0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0, 0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr, 0);
    }

    public MaskView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        parseType(context, attrs, defStyleAttr, defStyleRes);

        //硬件加速不支持图形混合,所以这里关闭硬件加速.
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        //初始化镂空区域画笔.
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置镂空透明色.
        mPaint.setColor(Color.TRANSPARENT);
        //设置填充样式.
        mPaint.setStyle(Paint.Style.FILL);
        //设置图像混合模式.
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        //镂空区域路径.
        mPath = new Path();
    }

    private void parseType(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaskView, defStyleAttr, defStyleRes);
        mMaskColor = typedArray.getColor(R.styleable.MaskView_widget_mask_color, Color.argb(68, 255, 255, 255));
        mUnfoldDuration = typedArray.getInteger(R.styleable.MaskView_widget_unfold_duration, 6 * 1000);
        mUnfoldStartRadiusRatio = typedArray.getFloat(R.styleable.MaskView_widget_unfold_start_radius_ratio, 10.0f);
        typedArray.recycle();
    }

    /**
     * 展开镂空区域.
     */
    public void unfold() {
        if (mUnfoldValueAnimator != null && mUnfoldValueAnimator.isRunning()) return;

        mUnfoldValueAnimator = generateUnfoldAnimation();
        mUnfoldValueAnimator.start();
    }

    /**
     * 创建镂空区域动画.
     */
    private ValueAnimator generateUnfoldAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mUnfoldStartRadiusRatio, 100);
        valueAnimator.setDuration(mUnfoldDuration);
        valueAnimator.addUpdateListener(animation -> {
            float current = (float) animation.getAnimatedValue();
            //更新镂空区域圆半径.
            mRadius = 1.0f * Math.max(mWidth, mHeight) * current / 100;

            // 重绘.
            postInvalidate();
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //TODO 表示动画结束,也就表示镂空区域完全展开.
            }
        });
        return valueAnimator;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
        mHeight = MeasureSpec.getSize(heightMeasureSpec);

        //初始化镂空区域圆半径.
        mRadius = Math.max(mWidth, mHeight) * mUnfoldStartRadiusRatio / 100.f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 绘制蒙层.
        canvas.drawColor(mMaskColor);

        //设置镂空区域.
        mPath.reset();
        mPath.addCircle(mWidth / 2.0f, mHeight / 2.0f, mRadius, Path.Direction.CW);

        //绘制镂空区域.
        canvas.drawPath(mPath, mPaint);
    }
}

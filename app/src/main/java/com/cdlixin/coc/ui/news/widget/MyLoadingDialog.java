package com.cdlixin.coc.ui.news.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 加载进度条
 */
public class MyLoadingDialog extends View {

    //宽高
    private int mWidth,mHeight;

    //背景画笔
    private Paint mBackGroundPaint;
    //圆环画笔
    private Paint mCirclePaint;
    //圆环
    private Path mCircle;

    //长度截取
    private PathMeasure mPathMeasure;

    private ValueAnimator mAnimator;

    //默认动画执行期
    private int defaultDuration = 1500;

    //动画执行进度
    private float mAnimatorValue ;




    public MyLoadingDialog(Context context) {
        this(context,null);
    }

    public MyLoadingDialog(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyLoadingDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPath();
        initAnimator();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mWidth/2,mHeight/2);

        int radio1 = (int) (Math.min(mHeight,mHeight)*0.9/2);
        RectF back = new RectF(-radio1,-radio1,radio1,radio1);
        Path backPath = new Path();
        backPath.addArc(back,0,359.9f);
        canvas.drawPath(backPath,mBackGroundPaint);

        int radius2 = Math.min(mHeight,mWidth)/5;
        RectF in = new RectF(-radius2,-radius2,radius2,radius2);
        mCircle.addArc(in,225f,359.9f);
        mPathMeasure.setPath(mCircle,false);
        Path dst2 = new Path();
        float stop = mPathMeasure.getLength()*mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * 200f));
        mPathMeasure.getSegment(start, stop, dst2, true);
        canvas.drawPath(dst2, mCirclePaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    //画笔初始化
    private void initPaint(){

        mBackGroundPaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE,mBackGroundPaint);
        mBackGroundPaint.setShadowLayer(6,1,1, Color.GRAY);
        mBackGroundPaint.setColor(Color.WHITE);
        mBackGroundPaint.setStyle(Paint.Style.FILL);
        mBackGroundPaint.setAntiAlias(true);

        mCirclePaint = new Paint();
        setLayerType(LAYER_TYPE_SOFTWARE,mCirclePaint);
        mCirclePaint.setColor(Color.rgb(28, 153, 224));
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(8);
        mCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mCirclePaint.setAntiAlias(true);
    }

    //初始化路径
    private void initPath(){
        mCircle = new Path();
        mPathMeasure = new PathMeasure();

    }

    private void initAnimator(){
        mAnimator = ValueAnimator.ofFloat(0,1).setDuration(defaultDuration);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatorValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.setRepeatCount(5);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        mAnimator.start();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(visibility == VISIBLE){
            mAnimator.start();
        }else {
            mAnimator.cancel();
        }
    }
}

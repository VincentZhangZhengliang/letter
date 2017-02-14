package com.szxkbl.lettersnavigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author : Vincent
 * @time : 2017/2/14 15:08.
 * @Discription :
 */

public class LettersView extends View {
    private static final String   TAG       = "LettersView";
    private              String[] mStrChars = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#",""};
    private int checkIndex;
    Paint mPaint;
    private TextView                  mTextView;
    private OnLettersListViewListener onLettersListViewListener;

    public LettersView(Context context, String[] strChars) {
        super(context);
        mStrChars = strChars;
        init();
    }

    public LettersView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LettersView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //实例化画笔
        mPaint = new Paint();
        //设置style
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //设置抗锯齿
        mPaint.setAntiAlias(true);
    }


    public void setStrChars(String[] strChars) {
        mStrChars = strChars;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int height = getHeight();
        int width = getWidth();
        //每一个字母的高度,
        int singleHeight = height / mStrChars.length;

        for (int i = 0; i < mStrChars.length; i++) {

            if (i == checkIndex) {
                mPaint.setColor(getResources().getColor(R.color.color_55caca));
                mPaint.setTextSize(50);
            } else {
                mPaint.setColor(Color.BLACK);
                mPaint.setTextSize(30);
            }
            /**
             * 绘制字母(主要是坐标)
             * x: （view的宽度 - 文本的宽度）/ 2
             * y:  singleHeight * x + singleHeight  //单个字母的高度 + 最上面的字幕空白高度
             */

            float letterWidth = (width - mPaint.measureText(mStrChars[i])) / 2;
            float letterHeight = singleHeight * i + singleHeight;
            canvas.drawText(mStrChars[i], letterWidth, letterHeight, mPaint);
            mPaint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                mTextView.setVisibility(VISIBLE);
                setBackgroundResource(R.drawable.shape_letter);
                setPadding(0,0,0,20);
                float y = event.getY();
                int oldCheackIndex = checkIndex;

                //字母的索引
                int index = (int) (y / getHeight() * mStrChars.length);
//                Log.e(TAG, "dispatchTouchEvent: " + index);
                if (oldCheackIndex != index) {
                    if (index > 0 && index < mStrChars.length) {
                        if (onLettersListViewListener != null) {
                            onLettersListViewListener.onLettersListener(mStrChars[index]);
                        }

                        if (mTextView != null) {
                            mTextView.setVisibility(View.VISIBLE);
                            mTextView.setText(mStrChars[index]);
                        }
                    }
                }

                checkIndex = index;
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                //设置透明背景
                setBackgroundResource(android.R.color.transparent);
                //恢复不选中
                checkIndex = -1;
                invalidate();
                //是否显示
                if (mTextView != null) {
                    mTextView.setVisibility(View.GONE);
                }

                break;
        }
        return true;

    }

    public TextView getmTextView() {
        return mTextView;
    }

    public void setmTextView(TextView mTextView) {
        this.mTextView = mTextView;
    }

    public interface OnLettersListViewListener {
        public void onLettersListener(String s);
    }

    public void setOnLettersListViewListener(OnLettersListViewListener listener) {
        this.onLettersListViewListener = listener;
    }
}

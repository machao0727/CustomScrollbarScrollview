package com.machao.customscrollbarscrollview.customscrollbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.machao.customscrollbarscrollview.R;


/**
 * create by：mc on 2018/11/28 14:18
 * email:
 * 自定义scrollviewLayout
 */
public class CustomScrollViewLayout extends FrameLayout {

    private int height;
    private int scrollHeight;

    private ImageView mIvButton;
    private ImageView mIvbg;
    private ListenedScrollView scrollChild;


    private float thumbX;
    private float thumbY;
    private int thumbWith;
    private int thumbHeight;
    private int maxScrollWith;//最大滑动距离
    private int thumbMaxScrollWith;//滑块滑动最大距离

    public CustomScrollViewLayout(Context context) {
        super(context);
        initView(context, null, 0);
    }

    public CustomScrollViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public CustomScrollViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    /**
     * 自定义
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.custom_scroll_layout, this, true);
        mIvButton = findViewById(R.id.mIvButton);
        mIvbg = findViewById(R.id.mIvbg);
        scrollChild = findViewById(R.id.mcScrollView);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomScrollViewLayout);
        mIvButton.setImageDrawable(ContextCompat.getDrawable(context, a.getResourceId(R.styleable.CustomScrollViewLayout_thumb_drawable, 0)));
        mIvbg.setImageDrawable(ContextCompat.getDrawable(context, a.getResourceId(R.styleable.CustomScrollViewLayout_track_drawable, 0)));
        // 回收资源，这一句必须调用
        a.recycle();
        initEvent();
    }

    public void addContentView(View child) {
        scrollChild.addView(child);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initEvent() {
        scrollChild.setOnScrollListener(new ListenedScrollView.OnScrollListener() {
            @Override
            public void onBottomArrived() {

            }

            @Override
            public void onScrollStateChanged(ListenedScrollView view, int scrollState) {

            }

            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                if (t < 0) {
                    t = 0;
                }
                mIvButton.layout((int) thumbX, thumbMaxScrollWith * t / maxScrollWith, (int) thumbX + thumbWith, (thumbMaxScrollWith * t / maxScrollWith + thumbHeight));
            }
        });
        mIvButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastY = motionEvent.getRawY();
                        scrollY = scrollChild.getScrollY();
                        thumbY = mIvButton.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        scrollDistance = motionEvent.getRawY() - lastY;
                        t = (int) (thumbY + scrollDistance);
                        if (t >= thumbMaxScrollWith) {
                            t = thumbMaxScrollWith;
                        } else if (t <= 0) {
                            t = 0;
                        }
                        scrollChild.scrollTo((int) scrollChild.getX(), (scrollY + (int) (maxScrollWith * scrollDistance / thumbMaxScrollWith)));
                        view.layout((int) thumbX, t, (int) thumbX + thumbWith, (t + thumbHeight));
                        break;
                    case MotionEvent.ACTION_UP:
                        thumbY = thumbY + scrollDistance;
                        scrollY = scrollChild.getScrollY();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        thumbY = thumbY + scrollDistance;
                        scrollY = scrollChild.getScrollY();
                        break;
                }
                return true;
            }
        });
    }

    private float lastY;
    private float scrollDistance;
    private int t;
    private int scrollY;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        scrollHeight = scrollChild.getChildAt(0).getMeasuredHeight();
        thumbWith = mIvButton.getMeasuredWidth();
        thumbHeight = mIvButton.getMeasuredHeight();
        maxScrollWith = scrollHeight - height;
        thumbMaxScrollWith = height - thumbHeight;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    /**
     * 只有在view绘制完成过后才能获取x,y,left,right
     *
     * @param hasWindowFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        thumbX = mIvButton.getX();
        thumbY = mIvButton.getY();
    }
}

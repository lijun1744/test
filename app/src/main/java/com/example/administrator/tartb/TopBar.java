package com.example.administrator.tartb;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopBar extends RelativeLayout {

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    private float mLeftTextSize;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private float mRightTextSize;
    private String mRightText;

    private String mTitleText;
    private float mTitleTextSize;
    private int mTitleTextColor;

    private TextView mTitleView;
    private Button mLeftButton;
    private Button mRightButton;

    private RelativeLayout.LayoutParams mLeftParams;
    private RelativeLayout.LayoutParams mRightParams;
    private RelativeLayout.LayoutParams mTitleParams;

    // 创建一个接口对象
    private OnClickListener mListener;

    // 暴露一个方法给调用者来注册接口，通过接口来获得回调者对接口方法的实现
    public void setOnClickListener(OnClickListener mListener) {
        this.mListener = mListener;
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化的方法
        // 初始化属性
        initAttr(context, attrs);
        // 初始化布局
        initView(context);
        // 初如化事件
        initEvent();

    }

    private void initEvent() {
        // 按钮的点击事件，不需要具体的实现，只需要调用接口方法，回调的时候会有具体实现
        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.rightClick();
            }
        });
    }

    private void initView(Context context) {
        mTitleView = new TextView(context);
        mLeftButton = new Button(context);
        mRightButton = new Button(context);

        // 为创建的组件赋值，值就来源于引用的xml文件中给对应属性的赋值
        mTitleView.setText(mTitleText);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setTextSize(mLeftTextSize);
        mLeftButton.setBackgroundDrawable(mLeftBackground);

        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setTextSize(mRightTextSize);
        mRightButton.setBackgroundDrawable(mRightBackground);

        // 为组件元素设置相应的布局元素
        // 设置布局的layout_width和layout_height属性
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        // 该方法表示所设置节点的属性必须关联其他兄弟节点或者属性值为布尔值。
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(mTitleView, mTitleParams);
    }

    // 在类内部定义一个接口对象，实现回调机制，不用去考虑如何实现，具体实现由调用者去创建
    public interface OnClickListener {
        void leftClick();

        void rightClick();
    }

    private void initAttr(Context context, AttributeSet attrs) {
        // 通过这个方法，将你在attrs.xml中定义的declare-styleable的所有属性的值存储到TypedArray.
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);
        mLeftTextSize = ta.getDimension(R.styleable.TopBar_leftTextSize, 0);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);
        mRightTextSize = ta.getDimension(R.styleable.TopBar_rightTextSize, 0);

        mTitleText = ta.getString(R.styleable.TopBar_title);
        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        // 获取完TypedArray的值后，一般要调用recyle()方法来避免重新创建的时候的错误
        ta.recycle();

    }
}

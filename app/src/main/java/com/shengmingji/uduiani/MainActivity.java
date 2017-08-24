package com.shengmingji.uduiani;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Visibility;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_jiantou;
    private ImageView weChat;
    private ImageView iv_QQ;
    private boolean isUp = true;
    private int viewH;
    private RelativeLayout rl_parent;
    private ViewGroup.LayoutParams lp;
    private int tvHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_parent = (RelativeLayout) findViewById(R.id.rl_parent);
        lp = rl_parent.getLayoutParams();
        viewH = lp.height;
        tv_jiantou = (TextView) findViewById(R.id.tv_jiantou);

        //获取箭头的高度
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv_jiantou.measure(w, h);
        tvHeight = tv_jiantou.getMeasuredHeight();

        weChat = (ImageView) findViewById(R.id.iv_WeChat);
        iv_QQ = (ImageView) findViewById(R.id.iv_QQ);
        tv_jiantou.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (isUp) {//如果向上 点击后隐藏微信QQ
            weChat.setVisibility(View.GONE);
            iv_QQ.setVisibility(View.GONE);
            hide();
        } else {
            show();
        }
        isUp = !isUp;
    }

    public void show() {
        final ValueAnimator anim = ObjectAnimator.ofFloat(1f, 0.0f).setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                if (lp.height <= viewH) {
                    lp.height = viewH - ((int) ((viewH - tvHeight) * cVal));
                    rl_parent.setLayoutParams(lp);
                }
                if (cVal==0){
                    weChat.setVisibility(View.VISIBLE);
                    iv_QQ.setVisibility(View.VISIBLE);
                }
            }
        });

        anim.start();
    }

    public void hide() {
        ValueAnimator aimh = ObjectAnimator.ofFloat(tv_jiantou, "TranslationY", 0.0f, 1f).setDuration(1000);
        aimh.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                if (lp.height <= viewH) {
                    lp.height = viewH - ((int) ((viewH - tvHeight) * cVal));
                    rl_parent.setLayoutParams(lp);
                }
            }
        });
        aimh.start();
    }
}

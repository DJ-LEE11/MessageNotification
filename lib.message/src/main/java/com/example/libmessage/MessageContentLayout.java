package com.example.libmessage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MessageContentLayout extends FrameLayout {

    private LayoutInflater mInflater;
    private LinearLayout mLlMessageContent;
    private TextView mTvMessage;
    private ImageView mIvClose;


    private boolean isShowing = false;

    public MessageContentLayout(Context context) {
        this(context, null);
    }

    public MessageContentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initView();
    }


    private void initView() {
        View view = mInflater.inflate(R.layout.message_content_layout, this, false);
        mLlMessageContent = view.findViewById(R.id.llMessageContent);
        mTvMessage =  view.findViewById(R.id.tvMessage);
        mIvClose =  view.findViewById(R.id.ivClose);
        this.addView(view);
    }

    private MessageSendModel model;

    public MessageSendModel getModel() {
        return model;
    }

    public boolean equalsCurrentModel(MessageSendModel model){
        return this.model.equals(model);
    }

    public void setModel(MessageSendModel model){
        this.model = model;
        if (model.getMessageType()!=0) {
            mTvMessage.setText(MessageType.getMsg(model.getMessageType()));
        }
    }

    public boolean isShowing(){
        return isShowing;
    }

    public AnimatorSet startAnimation() {
        //布局飞入
        ObjectAnimator flyFromLtoR = MessageAnimationUtil.createFlyFromLtoR(mLlMessageContent, -getWidth()/12, 0, 300,new OvershootInterpolator());
        flyFromLtoR.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                MessageContentLayout.this.setVisibility(View.VISIBLE);
                MessageContentLayout.this.setAlpha(1f);
                isShowing = true;
            }
        });
        //向上渐变消失
        ObjectAnimator fadeAnimator = MessageAnimationUtil.createFadeAnimator(MessageContentLayout.this, 0, -100, 300, 3000);
        fadeAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                MessageContentLayout.this.setVisibility(View.INVISIBLE);
            }
        });
        // 复原
        ObjectAnimator fadeAnimator2 = MessageAnimationUtil.createFadeAnimator(MessageContentLayout.this, 100, 0, 20, 0);

        AnimatorSet animatorSet = MessageAnimationUtil.startAnimation(flyFromLtoR,fadeAnimator, fadeAnimator2);
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                isShowing = false;
            }

        });
        return animatorSet;
    }


}

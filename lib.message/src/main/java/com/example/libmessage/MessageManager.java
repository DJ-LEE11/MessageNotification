package com.example.libmessage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {


    MessageQueue queue;
    List<MessageContentLayout> viewList;

    public MessageManager() {
        viewList = new ArrayList<>();
        queue = new MessageQueue();
    }

    public void addView(MessageContentLayout messageContentLayout) {
        viewList.add(messageContentLayout);
    }

    public void addMessage(MessageSendModel messageSendModel) {
        queue.add(messageSendModel);
        beingAnimation();
    }

    private MessageContentLayout getShowCurrView(MessageSendModel model) {
        for (MessageContentLayout messageContentLayout : viewList) {
            if (messageContentLayout.isShowing() && messageContentLayout.equalsCurrentModel(model)) {
                return messageContentLayout;
            }
        }
        return null;
    }

    public void beingAnimation() {
        //有可用的控件就立即播放
        MessageContentLayout hideView = getHideView();
        if (hideView != null) {
            beginAnimation(hideView);
        }
    }


    /**
     * 获取当前没有展示的view
     *
     * @return
     */
    private MessageContentLayout getHideView() {
        for (MessageContentLayout messageContentLayout : viewList) {
            if (!messageContentLayout.isShowing()) {
                return messageContentLayout;
            }
        }
        return null;
    }


    public void beginAnimation(final MessageContentLayout messageContentLayout) {

        MessageSendModel model = queue.removeTop();
        if (model == null) {
            return;
        }
        messageContentLayout.setModel(model);
        final AnimatorSet animatorSet = messageContentLayout.startAnimation();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                synchronized (queue) {
                    //消息队列里还存在消息的情况
                    if (!queue.isEmpty()) {
                        beginAnimation(messageContentLayout);
                    }
                }
            }
        });
        //关闭动画
        messageContentLayout.findViewById(R.id.ivClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messageContentLayout.setVisibility(View.INVISIBLE);
                animatorSet.cancel();
            }
        });
    }
}

package com.example.libmessage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class MessageView extends LinearLayout {
    private MessageManager mMessageManager;
    private int viewCount = 1;

    public MessageView(Context context) {
        this(context, null);
    }

    public MessageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setViewCount(int viewCount) {
        if(viewCount<1){
            throw new IllegalArgumentException("viewCount 不能小于1");
        }
        this.viewCount = viewCount;
    }

    public void init(){
        mMessageManager = new MessageManager();
        setOrientation(VERTICAL);
        addMessageView();
    }

    private void addMessageView(){
        for(int i=0; i<viewCount; i++){
            MessageContentLayout messageContentLayout = new MessageContentLayout(getContext());
            messageContentLayout.setVisibility(View.INVISIBLE);
            mMessageManager.addView(messageContentLayout);
            addView(messageContentLayout);
        }
    }

    public void addMessage(MessageSendModel model){
        mMessageManager.addMessage(model);
    }

}

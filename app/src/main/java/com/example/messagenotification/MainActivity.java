package com.example.messagenotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.libmessage.MessageSendModel;
import com.example.libmessage.MessageType;
import com.example.libmessage.MessageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MessageView mMessageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMessageView = findViewById(R.id.messageView);
        mMessageView.init();
        findViewById(R.id.btnMessage1).setOnClickListener(this);
        findViewById(R.id.btnMessage2).setOnClickListener(this);
        findViewById(R.id.btnMessage3).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMessage1:
                sendMessage(MessageType.MESSAGE_TYPE1);
                break;
            case R.id.btnMessage2:
                sendMessage(MessageType.MESSAGE_TYPE2);
                break;
            case R.id.btnMessage3:
                sendMessage(MessageType.MESSAGE_TYPE3);
                break;
        }
    }

    //发送消息
    private void sendMessage(int messageType){
        if (mMessageView != null){
            MessageSendModel messageSendModel = new MessageSendModel(messageType);
            mMessageView.addMessage(messageSendModel);
        }
    }
}

package com.example.libmessage;

public class MessageSendModel {

    private int messageType;
    private String messageContent;

    public MessageSendModel(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    @Override
    public boolean equals(Object o) {
        MessageSendModel messageSendModel = (MessageSendModel)o;
        if (messageType == messageSendModel.getMessageType()){
            return false;
        }else {
            return true;
        }
    }
}
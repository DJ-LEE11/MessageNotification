package com.example.libmessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李栋杰
 * @time 2018/1/23  下午5:37
 * @desc 游戏消息类型
 */
public class MessageType {

    public static final int MESSAGE_TYPE1 = 0x001;
    public static final int MESSAGE_TYPE2 = 0x002;
    public static final int MESSAGE_TYPE3 = 0x003;

    //获取所有的消息
    public static Map<Integer,String> getAllMessage(){
        Map<Integer,String> allMessage = new HashMap<>();
        allMessage.put(MESSAGE_TYPE1, "发送消息11111111111");
        allMessage.put(MESSAGE_TYPE2, "发送消息22222222222");
        allMessage.put(MESSAGE_TYPE3, "发送消息33333333333");
        return allMessage;
    }

    //根据消息类型获取消息内容
    public static String getMsg(int messageType) {
        return getAllMessage().get(messageType);
    }
}

package com.example.libmessage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李栋杰
 * @time 2018/1/23  下午5:37
 * @desc 游戏消息类型
 */
public class MessageType {

    //扫码大战
    public static final int COLLECT_CARD_START_GAME = 0x1001;
    public static final int COLLECT_CARD_FIND_TREASURE_BOX = 0x1002;

    //获取所有的消息
    public static Map<Integer,String> getAllMessage(){
        Map<Integer,String> allMessage = new HashMap<>();
        allMessage.put(COLLECT_CARD_START_GAME, "欢迎来到集卡寻宝，游戏中请注意安全");
        allMessage.put(COLLECT_CARD_FIND_TREASURE_BOX, "恭喜你寻找到宝箱");
        return allMessage;
    }

    //根据消息类型获取消息内容
    public static String getMsg(int messageType) {
        return getAllMessage().get(messageType);
    }
}

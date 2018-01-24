package com.example.libmessage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MessageQueue {
    Map<Integer, List<MessageSendModel>> queue;

    public MessageQueue() {
        queue = new LinkedHashMap<>();
    }


    public List<MessageSendModel> getList(Integer key) {
        return queue.get(key);
    }

    /**
     * 获取当前队列的第一个的消息
     *
     * @return
     */
    public synchronized MessageSendModel removeTop() {
        MessageSendModel model = null;
        if (queue.size() > 0) {
            List<MessageSendModel> messageSendModels = getTopList();

            if (messageSendModels == null) {
                return model;
            }
            if (messageSendModels.size() > 0) {
                model = messageSendModels.remove(0);
            }
            if (messageSendModels.size() == 0) {
                queue.remove(model.getMessageType());
            }
        }
        return model;
    }

    /**
     * 获取第一个添加消息的用户的 消息列表
     *
     * @return
     */
    private List<MessageSendModel> getTopList() {
        Set<Integer> messageTypes = queue.keySet();
        for (Integer messageType : messageTypes) {
            return queue.get(messageType);
        }
        return null;
    }

    /**
     * 添加一个消息到队列
     *
     * @param model
     * @return
     */
    public synchronized void add(MessageSendModel model) {
        List<MessageSendModel> mapList = getList(model.getMessageType());
        //排除相同类型的消息
        if (mapList == null) {
            //新来的用户添加到任务队列中
            List<MessageSendModel> l = new ArrayList<>();
            l.add(model);
            queue.put(model.getMessageType(), l);
        }
    }
    public boolean isEmpty(){
        return queue.isEmpty();
    }

}

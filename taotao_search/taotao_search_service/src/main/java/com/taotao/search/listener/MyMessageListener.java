package com.taotao.search.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by xingguo on 2017-09-09 16:02.
 *  接收activemq队列消息的监听器
 * @author mengy
 * @project taotao
 */
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

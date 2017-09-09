package com.taotao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

/**
 * Created by xingguo on 2017-09-09 15:32.
 *
 * @author mengy
 * @project taotao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-config-activemq.xml")
public class TestSpringActiveMQ {
    /**
     * 获得jms对象
     */
    @Autowired
    private JmsTemplate jmsTemplate;
    //从容器获得destination对象
    @Autowired
    private Queue queue;
    @Test
    public void testActiveQueueProducer() throws Exception{
        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage spring_activemq_queue = session.createTextMessage("第二次测试");
                return spring_activemq_queue;
            }
        });

    }

    @Test
    public void testActiveQueueCustomer() throws Exception{

    }
}

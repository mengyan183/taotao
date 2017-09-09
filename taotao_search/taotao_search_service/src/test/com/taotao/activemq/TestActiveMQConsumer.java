package com.taotao.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xingguo on 2017-09-09 16:06.
 *
 * @author mengy
 * @project taotao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-config-activemq.xml")
public class TestActiveMQConsumer {

    @Test
    public void testMqConsumer() throws Exception{
        System.in.read();
    }


}

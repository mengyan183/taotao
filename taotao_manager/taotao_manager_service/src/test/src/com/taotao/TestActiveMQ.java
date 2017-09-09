package com.taotao;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

/**
 * Created by xingguo on 2017-09-09 14:46.
 * 测试activemq
 *
 * @author mengy
 * @project taotao
 */
public class TestActiveMQ {
    @Test
    public void testActiveMQProvider() throws Exception {
        //创建连接工厂,指定服务的ip和端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.228.134:61616");
        //使用工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用connection创建一个session
        //第一个参数是是否开启事务,只有第一个为false时,第二个参数才有作用 ,消息者的应答模式,手动应答或自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destnation.目的有两种topic . queue
        Queue queue = session.createQueue("test-queue");
        //使用session创建一个producer
        MessageProducer producer = session.createProducer(queue);
        //使用producer发送消息
        TextMessage textMessage = new ActiveMQTextMessage();
        textMessage.setText("测试activemq发送者");
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();

    }


    @Test
    public void testActiveMQConsumer() throws Exception {
        //创建连接工厂,指定服务的ip和端口
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.228.134:61616");
        //使用工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用connection创建一个session
        //第一个参数是是否开启事务,只有第一个为false时,第二个参数才有作用 ,消息者的应答模式,手动应答或自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destnation.目的有两种topic . queue
        Queue queue = session.createQueue("test-queue");
        //使用session创建一个consumer
        MessageConsumer consumer = session.createConsumer(queue);
        //获得消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    TextMessage textMessage=(TextMessage) message;
                    System.out.println(textMessage.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        //关闭资源
        consumer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testActiveMQTopic() throws Exception{
        //创建连接工厂,指定服务的ip和端口
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.228.134:61616");
        //使用工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用connection创建一个session
        //第一个参数是是否开启事务,只有第一个为false时,第二个参数才有作用 ,消息者的应答模式,手动应答或自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destnation.目的有两种topic . queue
        Topic topic = session.createTopic("test-topic");
        //使用session创建一个producer
        MessageProducer producer = session.createProducer(topic);
        //使用producer发送消息
        TextMessage textMessage=new ActiveMQTextMessage();
        textMessage.setText("测试activemq发送者topic1");
        producer.send(textMessage);
        //关闭资源
        producer.close();
        session.close();
        connection.close();
    }


    @Test
    public void testActiveMQTopicConsumer4() throws Exception{
        //创建连接工厂,指定服务的ip和端口
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.228.134:61616");
        //使用工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用connection创建一个session
        //第一个参数是是否开启事务,只有第一个为false时,第二个参数才有作用 ,消息者的应答模式,手动应答或自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destnation.目的有两种topic . queue
       Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("消费者4");
        System.in.read();
        consumer.close();
        //关闭资源
        session.close();
        connection.close();
    }
    @Test
    public void testActiveMQTopicConsumer3() throws Exception{
        //创建连接工厂,指定服务的ip和端口
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.228.134:61616");
        //使用工厂对象创建一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //使用connection创建一个session
        //第一个参数是是否开启事务,只有第一个为false时,第二个参数才有作用 ,消息者的应答模式,手动应答或自动应答
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //使用session对象创建一个destnation.目的有两种topic . queue
       Topic topic = session.createTopic("test-topic");
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage=(TextMessage)message;
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("消费者3");
        System.in.read();
        consumer.close();
        //关闭资源
        session.close();
        connection.close();
    }
}

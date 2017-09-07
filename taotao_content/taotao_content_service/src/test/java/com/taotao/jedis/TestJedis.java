package com.taotao.jedis;

import com.taotao.content.service.jedis.JedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xingguo on 2017-09-07 16:29.
 *
 * @author mengy
 * @project taotao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/spring-config-*.xml")
public class TestJedis {
    @Test
    public void testJedisSingle() throws Exception {
        Jedis jedis = new Jedis("192.168.228.136", 7001);
        jedis.set("mytest", "1000");
        String mytest = jedis.get("mytest");
        System.out.println(mytest);
        jedis.close();

    }

    /**
     * 使用jedis连接池
     */
    @Test
    public void testJedisPool() throws Exception {
        JedisPool jedisPool = new JedisPool("192.168.228.136", 6379);
        Jedis resource = jedisPool.getResource();
        System.out.println(resource.get("mytest"));
        resource.close();
        jedisPool.close();

    }

    /**
     * 操作集群版
     */
    @Test
    public void testJedisCluster() throws Exception {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.228.136", 7001));
        nodes.add(new HostAndPort("192.168.228.136", 7002));
        nodes.add(new HostAndPort("192.168.228.136", 7003));
        nodes.add(new HostAndPort("192.168.228.136", 7004));
        nodes.add(new HostAndPort("192.168.228.136", 7005));
        nodes.add(new HostAndPort("192.168.228.136", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("jediscluster", "123456");
        System.out.println(jedisCluster.get("jediscluster"));
        jedisCluster.close();


    }

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void testRedisClientPool() throws Exception {
        jedisClient.set("testJedisPool", "123456");
        System.out.println( jedisClient.get("testJedisPool"));
    }
}

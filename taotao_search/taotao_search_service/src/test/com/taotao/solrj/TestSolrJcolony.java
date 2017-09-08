package com.taotao.solrj;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xingguo on 2017-09-08 19:54.
 * 测试solrj 集群
 *
 * @author mengy
 * @project taotao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/spring-config-*.xml")
public class TestSolrJcolony {
/*
    @Resource(name = "cloudSolrClient")
    private CloudSolrClient build;
*/


    @Test
    public void testSolrCloud() throws Exception {
        List<String> zkHost = new ArrayList<String>();
        zkHost.add("192.168.228.138:2181");
        zkHost.add("192.168.228.138:2182");
        zkHost.add("192.168.228.138:2183");
        CloudSolrClient build = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        build.setDefaultCollection("core");
        SolrInputDocument solrInputFields = new SolrInputDocument();
        solrInputFields.addField("item_title", "测试商品");
        solrInputFields.addField("item_price", "100");
        solrInputFields.addField("id", "test001");
        build.add(solrInputFields);
        build.commit();

    } @Test
    public void testSolrCloudDelte() throws Exception {
        List<String> zkHost = new ArrayList<String>();
        zkHost.add("192.168.228.138:2181");
        zkHost.add("192.168.228.138:2182");
        zkHost.add("192.168.228.138:2183");
        CloudSolrClient build = new CloudSolrClient.Builder().withZkHost(zkHost).build();
        build.setDefaultCollection("core");
        build.deleteByQuery("*:*");
        build.commit();

    }
}

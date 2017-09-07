package com.taotao.solrj;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * Created by xingguo on 2017-09-07 22:40.
 *
 * @author mengy
 * @project taotao
 */
public class TestSolrj {
    @Test
    public void testSorJAdd() throws Exception {

        //创建一个solrServer对象
        HttpSolrClient build = new HttpSolrClient.Builder("http://192.168.228.137:8080/solr/core2").build();
        //创建一个文档solrinputdocument对象
        SolrInputDocument document = new SolrInputDocument();
        //向文档中添加域
        document.addField("id", "test001");
        document.addField("item_title", "测试商品");
        document.addField("item_price", "199");
        //把文档写入索引库
        build.add(document);
        // 第六步：提交。
        build.commit();
        build.close();


    }
}

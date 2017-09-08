package com.taotao.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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

    @Test
    public void TestQuery() throws IOException, SolrServerException {
        //创建一个solrServer对象
        HttpSolrClient build = new HttpSolrClient.Builder("http://192.168.228.137:8080/solr/core2").build();
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("三星");
        solrQuery.setStart(0);
        solrQuery.setRows(10);
        solrQuery.set("df", "item_title");
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em>");
        solrQuery.setHighlightSimplePost("</em>");
        //执行查询
        QueryResponse query = build.query(solrQuery);
        //获得所有结果
        SolrDocumentList results = query.getResults();
        //查询到的所有结果数目
        long numFound = results.getNumFound();
        for (SolrDocument solrDocument : results) {
            String itemName = null;
            //获取高亮文本
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            List<String> strings = highlighting.get(solrDocument.get("id")).get("item_title");
            if(strings!=null && strings.size()>0){
                itemName=strings.get(0);
            }else {
               itemName= (String) solrDocument.get("item_title");
            }
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
            System.out.println("高亮文本"+itemName);

        }
    }
}

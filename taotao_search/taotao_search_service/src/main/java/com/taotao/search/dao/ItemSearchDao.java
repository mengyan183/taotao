package com.taotao.search.dao;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品搜索dao
 * Created by xingguo on 2017-09-08 09:24.
 *
 * @author mengy
 * @project taotao
 */
@Repository
public class ItemSearchDao {

    @Resource(name = "solrClient")
    private SolrClient solrClient;

    public SearchResult getSearchResult(SolrQuery solrQuery) throws Exception {
        //根据query查询索引库
        QueryResponse query = solrClient.query(solrQuery);
        //获得所有结果
        SolrDocumentList results = query.getResults();
        SearchResult searchResult = new SearchResult();
        List<SearchItem> searchItems = new ArrayList<>();
        //查询到的所有结果数目
        long numFound = results.getNumFound();
        for (SolrDocument solrDocument : results) {
            SearchItem searchItem = new SearchItem();
            String itemName = null;
            //获取高亮文本
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            List<String> strings = highlighting.get(solrDocument.get("id")).get("item_title");
            if (strings != null && strings.size() > 0) {
                itemName = strings.get(0);
            } else {
                itemName = (String) solrDocument.get("item_title");
            }
            searchItem.setId(Long.valueOf((String) solrDocument.get("id")));
            searchItem.setImage((String) solrDocument.get("item_image"));
            searchItem.setTitle(itemName);
            searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
            searchItem.setPrice((Long) solrDocument.get("item_price"));
            searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
            searchItems.add(searchItem);
        }
        searchResult.setItemList(searchItems);
        searchResult.setRecordCount(numFound);
        searchResult.setPageCount((long) Math.ceil(numFound/solrQuery.getRows()));
        return searchResult;
    }
}

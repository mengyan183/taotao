package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.ItemSearchDao;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xingguo on 2017-09-08 10:09.
 *
 * @author mengy
 * @project taotao
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {
    /**
     * 根据query对象查询搜索结果
     *
     * @param queryString
     * @param page
     * @param rows
     */
    @Autowired
    private ItemSearchDao itemSearchDao;

    /**
     * 根据query对象查询搜索结果
     *
     * @param queryString
     * @param page
     * @param rows
     */
    @Override
    public SearchResult searchItemByAdvice(String queryString, int page, int rows) throws Exception {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(queryString);
        solrQuery.set("df", "item_title");
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");
        SearchResult searchResult = itemSearchDao.getSearchResult(solrQuery);
        return searchResult;
    }
}

package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;

/**
 * Created by xingguo on 2017-09-08 10:08.
 *
 * @author mengy
 * @project taotao
 */
public interface SearchService {
    /**
     * 根据query对象查询搜索结果
     */
    SearchResult searchItemByAdvice(String queryString, int page, int rows) throws Exception;

}

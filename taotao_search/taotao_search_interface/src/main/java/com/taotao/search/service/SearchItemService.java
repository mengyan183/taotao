package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by xingguo on 2017-09-07 23:06.
 *
 * @author mengy
 * @project taotao
 */
public interface SearchItemService {
    /**
     * 将所有的数据导入到索引库中
     * @return
     */
    TaotaoResult importAllItemsToIndex() throws Exception;
}

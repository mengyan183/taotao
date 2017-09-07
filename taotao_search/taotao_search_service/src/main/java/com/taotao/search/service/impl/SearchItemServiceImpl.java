package com.taotao.search.service.impl;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.SearchItemService;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * Created by xingguo on 2017-09-07 23:07.
 *
 * @author mengy
 * @project taotao
 */
@Service
@Transactional
public class SearchItemServiceImpl implements SearchItemService {
    @Resource(name = "solrClient")
    private HttpSolrClient httpSolrClient;

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 将所有的数据导入到索引库中
     *
     * @return
     */
    @Override
    public TaotaoResult importAllItemsToIndex() throws Exception {
        List<SearchItem> itemList = itemMapper.getItemList();
        for (SearchItem searchItem : itemList) {
            SolrInputDocument document = new SolrInputDocument();
            // 4、为文档添加域
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            httpSolrClient.add(document);
        }
        httpSolrClient.commit();
        httpSolrClient.close();
        return TaotaoResult.ok();
    }
}

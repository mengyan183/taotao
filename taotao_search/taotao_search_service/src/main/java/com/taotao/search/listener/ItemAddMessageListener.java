package com.taotao.search.listener;

import com.taotao.common.pojo.SearchItem;
import com.taotao.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by xingguo on 2017-09-09 16:23.
 *
 * @author mengy
 * @project taotao
 */
public class ItemAddMessageListener implements MessageListener{
    @Autowired
    private ItemMapper itemMapper;

    @Resource(name = "CloudSolrClient")
    private CloudSolrClient cloudSolrClient;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage= (TextMessage) message;
        try {
            //从消息中取商品id
            String id = textMessage.getText();
            long l = Long.parseLong(id);
            //根据商品id得到商品
            SearchItem searchItem = itemMapper.getItemById(l);
            //把商品信息添加到索引库
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", searchItem.getId());
            document.addField("item_title", searchItem.getTitle());
            document.addField("item_sell_point", searchItem.getSell_point());
            document.addField("item_price", searchItem.getPrice());
            document.addField("item_image", searchItem.getImage());
            document.addField("item_category_name", searchItem.getCategory_name());
            document.addField("item_desc", searchItem.getItem_desc());
            cloudSolrClient.add(document);
            cloudSolrClient.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

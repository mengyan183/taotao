package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

import java.util.List;

/**
 * Created by xingguo on 2017-09-04 14:22.
 *
 * @author mengy
 * @project taotao
 * <p>
 * 商品service 接口
 */
public interface ItemService {

    /**
     * 根据商品id查询商品
     *
     * @param id
     * @return
     */
    public TbItem getItemById(Long id);


    /**
     * 查询所有商品
     */
    public EasyUIDataGridResult getAllItemList(int page, int rows);


    /**
     * 添加商品
     */
    TaotaoResult addItem(TbItem tbItem, String itemDesc);


    /**
     * 我们采用通配符对商品进行操作
     * @param ids
     * @param operate
     * @return
     */
    void updateItemByOperate(String ids, String operate);

    /**
     * 对更新商品进行数据回显
     * 需要进行两个查询
     */
    /**
     * 查询商品信息
     * @param id
     * @return
     */

    /**
     *查询商品介绍
     */
    TbItemDesc itemDescByItemId(String id);

    /**
     * 更新商品
     * @param tbItem
     * @param desc
     */
    void updateItem(TbItem tbItem, String desc);
}

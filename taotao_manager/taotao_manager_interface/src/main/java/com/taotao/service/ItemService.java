package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

import java.util.List;

/**
 * Created by xingguo on 2017-09-04 14:22.
 *
 * @author mengy
 * @project taotao
 *
 * 商品service 接口
 */
public interface ItemService {

    /**
     * 根据商品id查询商品
     * @param id
     * @return
     */
   public TbItem getItemById(Long id);


    /**
     * 查询所有商品
     */
    public EasyUIDataGridResult getAllItemList(int page ,int rows);

}

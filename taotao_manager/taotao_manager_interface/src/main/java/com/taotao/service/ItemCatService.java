package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeData;
import com.taotao.pojo.TbItemCat;

import java.util.List;

/**
 * Created by xingguo on 2017-09-05 09:01.
 *
 * @author mengy
 * @project taotao
 *
 * 商品分类服务接口
 */
public interface ItemCatService {
    List<EasyUITreeData> getItemCatList(Long parentId);
}

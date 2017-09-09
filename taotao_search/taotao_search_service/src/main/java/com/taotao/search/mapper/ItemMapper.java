package com.taotao.search.mapper;

import com.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * Created by xingguo on 2017-09-07 22:30.
 *  搜索服务接口
 * @author mengy
 * @project taotao
 */
public interface ItemMapper {
    List<SearchItem> getItemList();
    SearchItem getItemById(long id);
}

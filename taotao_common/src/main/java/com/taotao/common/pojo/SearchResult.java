package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xingguo on 2017-09-08 09:21.
 *  获取查询的结果
 * @author mengy
 * @project taotao
 */
public class SearchResult implements Serializable {
    private List<SearchItem> itemList;
    private long recordCount;
    private long pageCount;

    public List<SearchItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<SearchItem> itemList) {
        this.itemList = itemList;
    }

    public long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(long recordCount) {
        this.recordCount = recordCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }
}

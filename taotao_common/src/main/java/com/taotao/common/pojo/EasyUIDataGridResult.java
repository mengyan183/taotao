package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xingguo on 2017-09-04 19:12.
 *
 * @author mengy
 * @project taotao
 */
public class EasyUIDataGridResult implements Serializable {
    private Long total;
    private List rows;

    @Override
    public String toString() {
        return "EasyUIDataGridResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}

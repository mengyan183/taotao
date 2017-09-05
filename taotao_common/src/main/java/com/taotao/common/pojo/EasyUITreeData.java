package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * Created by xingguo on 2017-09-05 08:54.
 *  对返回的前台tree 进行pojo封装
 * @author mengy
 * @project taotao
 */
public class EasyUITreeData implements Serializable {

    private Long id;
    private String text;
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

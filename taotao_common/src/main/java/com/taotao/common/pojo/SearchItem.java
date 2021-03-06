package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xingguo on 2017-09-07 22:24.
 *
 * @author mengy
 * @project taotao
 */
public class SearchItem implements Serializable{
    private Long id;
    private String title;
    private String sell_point;
    private Long price;
    private String image;
    private String category_name;
    private String item_desc;
    private List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSell_point() {
        return sell_point;
    }

    public void setSell_point(String sell_point) {
        this.sell_point = sell_point;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String[] getImage() {
        if(image!=null&&!"".equals(image)){
            String[] split = image.split(",");
            return split;
        }
        return null;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getItem_desc() {
        return item_desc;
    }

    public void setItem_desc(String item_desc) {
        this.item_desc = item_desc;
    }
}

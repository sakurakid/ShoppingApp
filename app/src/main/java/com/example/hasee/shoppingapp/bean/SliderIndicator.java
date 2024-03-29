package com.example.hasee.shoppingapp.bean;

/**
 * 轮播图里面的数据单元
 */
public class SliderIndicator {

    private long id ;
    private String name ;
    private String imgUrl ;
    private String description ;

    public SliderIndicator(String name , String imgUrl) {
        this.name = name ;
        this.imgUrl = imgUrl ;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


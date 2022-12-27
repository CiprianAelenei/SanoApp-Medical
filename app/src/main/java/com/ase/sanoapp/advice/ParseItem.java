package com.ase.sanoapp.advice;

public class ParseItem {

    private String imgUrl;
    private String title;
    private String hrefUrl;

    public ParseItem() {

    }

    public ParseItem(String imgUrl, String title, String hrefUrl) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.hrefUrl = hrefUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHrefUrl() {
        return hrefUrl;
    }

    public void setHrefUrl(String hrefUrl) {
        this.hrefUrl = hrefUrl;
    }
}

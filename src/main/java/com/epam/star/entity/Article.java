package com.epam.star.entity;

public class Article extends AbstractEntity {

    private String title;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText(){
        if (text.length() >= 300)
        return text.substring(0,300);
        else return text;
    }
}

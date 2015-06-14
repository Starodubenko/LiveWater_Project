package com.epam.star.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article extends AbstractEntity {

    private String title;
    private Date newsDate;
    private String content;

    public String getContent() {
        return content;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText(){
        if (content.length() >= 300)
        return content.substring(0,300);
        else return content;
    }

    public String getFormatedDate(){
        return new SimpleDateFormat("dd.MM.yyyy").format(newsDate);
    }
}

package com.example.administrator.sqlite.db;

import org.litepal.crud.DataSupport;

/**
 * Time      2016/12/1 09:51 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class Comment extends DataSupport{

    /*comment和news是多对一的关系*/
    private News news;

    private int id;
    private String content;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

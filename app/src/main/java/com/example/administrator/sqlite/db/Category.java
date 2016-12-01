package com.example.administrator.sqlite.db;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Time      2016/12/1 11:52 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class Category extends DataSupport {

    /*news和category是多对多的关系*/
    private List<News> newsList = new ArrayList<>();
    private int id;
    private String name;

    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

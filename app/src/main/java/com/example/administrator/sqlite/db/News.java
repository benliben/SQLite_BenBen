package com.example.administrator.sqlite.db;

import android.provider.ContactsContract;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Time      2016/12/1 09:27 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class News extends DataSupport {
    /*news和introduction是一对一的关系*/
    private Introduction introduction;

    /*comment和news是多对一的关系*/
    private List<Comment> commentList = new ArrayList<>();

    /*news和category是多对多的关系*/
    private List<Category> categoryList = new ArrayList<>();
    private int id;
    private String title;
    private String content;
    private Date publishData;
    private int commentCount;

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public Introduction getIntroduction() {
        return introduction;
    }

    public void setIntroduction(Introduction introduction) {
        this.introduction = introduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishData() {
        return publishData;
    }

    public void setPublishData(Date publishData) {
        this.publishData = publishData;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}

package com.example.administrator.sqlite.db;

import org.litepal.crud.DataSupport;

/**
 * Time      2016/12/1 11:51 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class Introduction extends DataSupport {

    private int id;
    private String guide;
    private String digest;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}

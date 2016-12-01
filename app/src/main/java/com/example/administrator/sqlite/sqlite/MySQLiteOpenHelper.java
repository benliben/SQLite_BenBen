package com.example.administrator.sqlite.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.TtsSpan;

/**
 * Time      2016/11/30 15:52 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String CREATE_BOOK = "create table book(" +
            "id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)";

    public static final String CREATE_NEWS = "create table news(" +
            "id integer primary key autoincrement," +
            "title text," +
            "content text," +
            "date text)";

    Context mContext;

    /**
     * 构造方法
     *
     * @param context 上下文
     * @param name 表名
     * @param factory
     * @param version 版本号
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        switch (i) {
            case 1:
                db.execSQL(CREATE_BOOK);
            case 2:
                db.execSQL(CREATE_NEWS);
            default:

        }
    }
}

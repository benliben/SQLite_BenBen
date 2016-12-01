package com.example.administrator.sqlite.litepal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.sqlite.R;
import com.example.administrator.sqlite.db.Category;
import com.example.administrator.sqlite.db.Comment;
import com.example.administrator.sqlite.db.Introduction;
import com.example.administrator.sqlite.db.News;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Time      2016/12/1 09:18 .
 * Author   : LiYuanXiong.
 * Content  :
 */

public class HomeActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private News news;//单条数据
    private List<News> newsList; //多条数据

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);
    }


    @OnClick({R.id.home_create, R.id.home_add, R.id.home_delete, R.id.home_up, R.id.home_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_create:
                createData();
                break;
            case R.id.home_add:
                addData();
                break;
            case R.id.home_delete:
                deleteData();
                break;
            case R.id.home_up:
                upData();
                break;
            case R.id.home_query:
                queryData();
                break;
        }
    }


    /**
     * 创建
     */
    private void createData() {
        db = Connector.getDatabase();
        Log.i("lyx", "createData: " + db);
    }

    /**
     * 添加数据
     */
    private void addData() {
        if (db == null) {
            createData();
        }
        for (int i = 0; i < 5; i++) {
            News news = new News();
            news.setTitle("这是一条新闻标题" + i);
            news.setContent("这是一条新闻的内容" + i);
            Log.d("lyx", "news id is " + news.getId());
            news.save();
            Log.d("lyx", "news id is " + news.getId());
            if (news.save()) {
                Log.i("lyx", "储存成功");
            } else {
                Log.i("lyx", "储存失败");
            }
        }


    }

    /**
     * 删除数据
     */
    private void deleteData() {
        /**
         * 删除id为2的数据
         */
//        DataSupport.delete(News.class, 2);

        /**
         * 删除content为这是一条新闻的内容12的数据
         */
        DataSupport.deleteAll(News.class, "content=?", "这是一条新闻的内容12");

        /**
         * 删除所有的数据
         */
//        DataSupport.deleteAll(News.class);

    }

    /**
     * 跟新数据
     */
    private void upData() {
//        ContentValues values = new ContentValues();
//        values.put("title", "我是题目哦不要惹我");
//        DataSupport.update(News.class, values, 8);
        //或者
        News update = new News();
        update.setTitle("也不要惹我  我也是题目");
        update.update(9);
    }

    /**
     * 查询数据
     */
    private void queryData() {
        /**
         * 查询id为1的这条记录
         */
//         news = DataSupport.find(News.class, 1);

        /**
         *查询第一条数据
         */
//        news = DataSupport.findFirst(News.class);

        /**
         *查询最后一条数据
         */
//        news = DataSupport.findLast(News.class);

        /**
         *查询id为1，3，5的数据
         */
//         newsList = DataSupport.findAll(News.class,1,3,5);
        /**
         *查询所有数据
         */
        newsList = DataSupport.findAll(News.class);

        /**
         *查询父类id为1的数据
         */
//        newsList = DataSupport.where("parentId=?", "1").find(News.class);


//        if (news != null) {
//            showNews(news);
//
//
//        }

        if (newsList != null) {
            for (int i = 0; i < newsList.size(); i++) {
                showNews(newsList.get(i));
            }
        }

    }

    private void showNews(News news) {
        int id = news.getId();
        String title = news.getTitle();
        String content = news.getContent();
        List<Category> categoryList = news.getCategoryList();
        List<Comment> commentList = news.getCommentList();
        int commentCount = news.getCommentCount();
        Introduction introduction = news.getIntroduction();
        Date publishData = news.getPublishData();
        Log.i("lyx", "===============================================================================");
        Log.i("lyx", " ||id:" + id + "\n"
                + "|| title: " + title + "\n"
                + "|| content: " + content + "\n"
                + "|| categoryList: " + categoryList + "\n"
                + "|| commentList: " + commentList + "\n"
                + "|| commentCount: " + commentCount + "\n"
                + "|| introduction: " + introduction + "\n"
                + "||publishData:" + publishData);
        Log.i("lyx", "===============================================================================");

    }
}



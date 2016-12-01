package com.example.administrator.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.sqlite.sqlite.MySQLiteOpenHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    MySQLiteOpenHelper mySQLiteOpenHelper;
    @InjectView(R.id.main_add_tv)
    TextView mAddTv;
    @InjectView(R.id.main_delete_tv)
    TextView mDeleteTv;
    @InjectView(R.id.main_update_tv)
    TextView mUpdateTv;
    @InjectView(R.id.main_retrieve_tv)
    TextView mRetrieveTv;
    private SQLiteDatabase database;

    int a = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

    }

    @OnClick({R.id.main_create, R.id.main_add, R.id.main_delete, R.id.main_update, R.id.main_retrieve, R.id.main_things})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_create:
                createData();
                break;
            case R.id.main_add:
                a = 1;
                addData();
                break;
            case R.id.main_delete:
                a = 2;
                deleteData();
                break;
            case R.id.main_update:
                a = 3;
                upData();
                break;
            case R.id.main_retrieve:
                retrieverData();
                break;
            case R.id.main_things:
                a = 4;
                thingsData();
                break;
        }
    }

    /**
     * 创建表
     */
    private void createData() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this, "Book", null, 1);
        database = mySQLiteOpenHelper.getWritableDatabase();

    }

    /**
     * 添加数据
     * 第一个参数：表名
     * 第二个参数：在未指定添加数据的情况下给某些可能为空的列表自动赋值，一般不用 传null
     * 第三个参数：ContentValues对象
     */
    private void addData() {
        if (database == null) {
            createData();
        }
        ContentValues values = new ContentValues();
        values.put("name", "犇犇");
        values.put("author", "大雄");
        values.put("price", "88.88");
        values.put("pages", "499");
        database.insert("Book", null, values);//插入第一条数据
        values.clear();

        values.put("name", "牛牛");
        values.put("author", "哆啦A梦");
        values.put("price", "11.88");
        values.put("pages", "501");
        database.insert("Book", null, values);
        values.clear();
    }

    /**
     * 删除数据
     * 第一个参数：表名
     * 第二个，第三个参数：用于去约束某一行或某几行的数据，不指定的话默认就是删除所有行
     */
    private void deleteData() {
        if (database == null) {
            createData();
        }
        database.delete("Book", "pages>?", new String[]{"500"});
    }

    /**
     * 更新数据
     * 第一个参数：表名
     * 第二个参数：ContentValues对象
     * 第三个，第四个参数：用于去约束莫一行或者某几行的数据，不指定默认跟新所有行
     */
    private void upData() {
        if (database == null) {
            createData();
        }
        ContentValues values = new ContentValues();
        values.put("price", "22.88");
        database.update("Book", values, "author=?", new String[]{"大雄"});

    }

    /**
     * 查询数据
     * 第一个参数：表名
     * 第二个参数：指定查询那几列，不指定就查询所有列
     * 第三个，第四个参数：约束约束查询某一行或某几行的数据，不指定为查询所有数据
     * 第五个参数：指定去group by的列，不指定表示不对查询的结果进行group by操作
     * 第六个参数：对group by之后的数据进一步的过滤，不指定则表示不过滤
     * 第七个参数：用于指定查询结果的排序，不指定则表示使用默认的排序方式
     */
    private void retrieverData() {
        if (database == null) {
            createData();
        }

        Cursor book = database.query("Book", null, null, null, null, null, null);
        if (book.moveToFirst()) {
            do {
                String name = book.getString(book.getColumnIndex("name"));
                String author = book.getString(book.getColumnIndex("author"));
                String price = book.getString(book.getColumnIndex("price"));
                int pages = book.getInt(book.getColumnIndex("pages"));

                switch (a) {
                    case 1:
                        mAddTv.setText(name + "这本书为" + author + "的巨作，该书共" + pages + "页 售价为：" + price + "元");
                        break;
                    case 2:
                        mDeleteTv.setText(name + "这本书为" + author + "的巨作，该书共" + pages + "页 售价为：" + price + "元");
                        break;
                    case 3:
                        mUpdateTv.setText(name + "这本书为" + author + "的巨作，该书共" + pages + "页 售价为：" + price + "元");
                        break;
                    case 4:
                        mRetrieveTv.setText(name + "这本书为" + author + "的巨作，该书共" + pages + "页 售价为：" + price + "元");
                        break;
                }
                Log.i("lyx", name + "这本书为" + author + "的巨作，该书共" + pages + "页 售价为：" + price + "元");

            } while (book.moveToNext());

        }

    }

    /**
     * 事物
     */
    private void thingsData() {

        database.beginTransaction();//开启事物
        try {
            database.delete("Book", null, null);//删除book里面全部的数据
            ContentValues values = new ContentValues();
            values.put("name", "Game of Thrones");
            values.put("author", "George Martin");
            values.put("pages", 720);
            values.put("price", 20.85);
            database.insert("Book", null, values);//重新插入数据
            database.setTransactionSuccessful();//事物已经执行成功
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.endTransaction();//结束事物
        }
        if (database == null) {
            createData();
        }
    }
}

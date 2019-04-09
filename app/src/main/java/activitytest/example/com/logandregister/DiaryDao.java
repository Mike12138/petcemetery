package activitytest.example.com.logandregister;

import java.util.List;
import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;

public class DiaryDao {
    private SQLiteDatabase sqLiteDatabase;
    private DBHelper dbHelper;
    // 构造方法，带有context参数，以便下一对象操作
    public DiaryDao(Context context){
        dbHelper=new DBHelper(context);
    }

    public void add(Diary diary) {
        String sql = "insert into td_diary(title,content,pubdate) values(?,?,?)";
        // 得到SQLite数据库
        sqLiteDatabase = dbHelper.getWritableDatabase();

        // 执行sql语句
        sqLiteDatabase.execSQL(sql,new String[] { diary.getTitle(), diary.getContent(), diary.getPubdate() });
    }

    public void delete(int id) {
        String sql = "delete from td_diary where id=?";
        // 得到SQLite数据库
        sqLiteDatabase = dbHelper.getWritableDatabase();
        // 执行sql语句
        sqLiteDatabase.execSQL(sql, new String[] { Integer.toString(id) });
    }

    public void update(Diary diary) {
        String sql = "update td_diary set title=?,content=? where id=?";
        // 得到SQLite数据库
        sqLiteDatabase = dbHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(
                sql,
                new String[] { diary.getTitle(), diary.getContent(),
                        Integer.toString(diary.getId()) });
    }

    public Diary select(int id) {
        Diary diary =null;
        String sql="select * from td_diary where id=?";
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(sql, new String[]{Integer.toString(id)});
        if(cursor.moveToFirst()){
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String content=cursor.getString(cursor.getColumnIndex("content"));
            String pubdate=cursor.getString(cursor.getColumnIndex("pubdate"));
            diary = new Diary(title, content, pubdate);
        }
        return diary;
    }

    public List<Diary> getAllDiarys(){
        List<Diary> list=new ArrayList<Diary>();
        sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from td_diary", null);
        while(cursor.moveToNext()){
            int id=cursor.getInt(cursor.getColumnIndex("id"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String content=cursor.getString(cursor.getColumnIndex("content"));
            String pubdate=cursor.getString(cursor.getColumnIndex("pubdate"));
            Diary diary = new Diary(title, content, pubdate);
            diary.setId(id);
            list.add(diary);
        }
        return list;
    }

}


package activitytest.example.com.logandregister;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DBNAME = "mydiary.db";
    private static final int DBVERSION = 1;

    public DBHelper(Context context){
        super(context,DBNAME ,null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table td_diary(id integer primary key autoincrement,title varchar(20),content varchar(1000),pubdate)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
    }
}

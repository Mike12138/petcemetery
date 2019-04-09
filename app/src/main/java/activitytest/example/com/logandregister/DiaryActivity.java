package activitytest.example.com.logandregister;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class DiaryActivity extends AppCompatActivity {

    private SwipeMenuListView listview;
    private DiaryDao dao = new DiaryDao(this);
    private DBHelper dbHelper;
    List<Map<String, Object>> data;
    List<Diary> diarys;
    int id=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        dbHelper = new DBHelper(this);
        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        SQLiteStudioService.instance().start(this);

        // 查找组件
        listview = this.findViewById(R.id.listView1);

        // 创建数据库操作实例类
        dbHelper.getWritableDatabase();
        data = new ArrayList<Map<String, Object>>();
        diarys = new ArrayList<Diary>();
        diarys = dao.getAllDiarys();
        for (Diary d : diarys) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", d.getSubTitle());
            map.put("pubdate", d.getPubdate());
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.item_diary, new String[] { "title", "pubdate" },
                new int[] { R.id.title, R.id.date });

        //绑定适配器
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //点击列表项时跳转修改日志页面，将当前的日志信息传过去。
            public void onItemClick(AdapterView<?> a, View v, int position,
                                    long l) {
                Diary diary = diarys.get(position);
                Intent intent = new Intent(DiaryActivity.this,
                        DiarySelectActivity.class);
                intent.putExtra("id", diary.getId());
                intent.putExtra("title", diary.getTitle());
                intent.putExtra("content", diary.getContent());
                intent.putExtra("date",diary.getPubdate());
                startActivity(intent);
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v, int arg2,
                                           long arg3) {
                Diary diary = diarys.get(arg2);
                Intent intent = new Intent(DiaryActivity.this,
                        DiaryUpdateActivity.class);
                intent.putExtra("id", diary.getId());
                intent.putExtra("title", diary.getTitle());
                intent.putExtra("content", diary.getContent());
                startActivity(intent);
                finish();
                return true;
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xc9,0xc9,0xCE)));//设置背景
                deleteItem.setWidth(200);//设置滑出 项 宽度
                //deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.parseColor("#ffffff"));
                deleteItem.setTitleSize(14);
                deleteItem.setIcon(R.drawable.ic_menu_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        listview.setMenuCreator(creator);
        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        id = dao.getAllDiarys().get(position).getId();
                        delete();
                        break;
                }
                return false;
            }
        });
    }


    @Override
    protected void onDestroy(){
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }

    // 菜单删除操作
    private void delete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("请确认");
        builder.setMessage("确定要删除第条日记吗？");
        //点击确定按钮则删除当前的日志，刷新主页面，显示列表
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("删除的日志id是:"+id);
                dao.delete(id);
                //Intent i = new Intent();
                Intent in = new Intent(DiaryActivity.this, DiaryActivity.class);
                startActivity(in);
                finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_diary, menu);
        menu.getItem(0).setIcon(android.R.drawable.ic_menu_add);
        //menu.getItem(1).setIcon(android.R.drawable.ic_menu_delete);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            /*case R.id.delete:
                delete();
                break;*/
            case R.id.add:
                Intent in = new Intent(DiaryActivity.this, DiaryAddActivity.class);
                startActivity(in);
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

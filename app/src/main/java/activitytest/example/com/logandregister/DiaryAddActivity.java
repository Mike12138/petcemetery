package activitytest.example.com.logandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DiaryAddActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private Button add;
    private DiaryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_add);

        // 查找组件
        title = this.findViewById(R.id.tit);
        content = this.findViewById(R.id.cont);
        add = this.findViewById(R.id.add);

        // 获取上个视图传的参数值
        Intent i = getIntent();
        final int id = i.getIntExtra("id", 0);
        System.out.println(id);

        // 设置文本
        title.setText(i.getStringExtra("title"));
        content.setText(i.getStringExtra("content"));

        // 添加事件点击处理监听
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Diary diary = new Diary( title.getText().toString(), content
                        .getText().toString(),getCurrentTime(new Date()));
                diary.setId(id);
                //执行更新
                dao=new DiaryDao(DiaryAddActivity.this);
                dao.add(diary);
                //提示
                Toast.makeText(DiaryAddActivity.this, "添加成功",
                        Toast.LENGTH_LONG).show();
                //更新后跳转到日志列表视图
                Intent in = new Intent(DiaryAddActivity.this,
                        DiaryActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    public String getCurrentTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd-hh:mm");
        return simpleDateFormat.format(date);
    }
}

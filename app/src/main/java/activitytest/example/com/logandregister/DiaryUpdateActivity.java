package activitytest.example.com.logandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DiaryUpdateActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Button save;
    private DiaryDao dao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_update);

        // 查找组件
        title = this.findViewById(R.id.tit);
        content = this.findViewById(R.id.cont);
        save = this.findViewById(R.id.save);

        // 获取上个视图传的参数值
        Intent i = getIntent();
        final int id = i.getIntExtra("id", 0);
        System.out.println(id);

        // 设置文本
        title.setText(i.getStringExtra("title"));
        content.setText(i.getStringExtra("content"));

        // 添加事件点击处理监听
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Diary diary = new Diary( title.getText().toString(), content
                        .getText().toString());
                diary.setId(id);
                //执行更新
                dao=new DiaryDao(DiaryUpdateActivity.this);
                dao.update(diary);
                //提示
                Toast.makeText(DiaryUpdateActivity.this, "修改成功",
                        Toast.LENGTH_LONG).show();
                //更新后跳转到日志列表视图
                Intent in = new Intent(DiaryUpdateActivity.this,
                        DiaryActivity.class);
                startActivity(in);
                finish();
            }
        });
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_diary, menu);
        return true;
    }*/

}

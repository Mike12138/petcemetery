package activitytest.example.com.logandregister;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DiarySelectActivity extends AppCompatActivity {

    private TextView title;
    private TextView content;
    private TextView date;
    private DiaryDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_select);

        title = this.findViewById(R.id.tit1);
        content = this.findViewById(R.id.cont1);
        date = this.findViewById(R.id.date);

        Intent i = getIntent();
        final int id = i.getIntExtra("id", 0);
        System.out.println(id);

        title.setText(i.getStringExtra("title"));
        content.setText(i.getStringExtra("content"));
        date.setText(i.getStringExtra("date"));

        dao = new DiaryDao(DiarySelectActivity.this);
        dao.select(id);
    }
}

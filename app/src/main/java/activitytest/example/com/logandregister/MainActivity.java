package activitytest.example.com.logandregister;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    public TextView positionText;

    private Image[] images = {
            new Image(R.drawable.pic1,"成都金菊花宠物殡葬","特点：自有果园300亩；为开展宠物殡葬服务提供了有利条件，深埋树葬，猫狗火化，立碑建墓。距离成都15公里。是成都最近的宠物公墓。","地址：成都市龙泉驿区同安镇","http://cd.ganji.com/fuwu_dian/312866/#tabl",104.309795,30.612459),
            new Image(R.drawable.pic2,"成都百福宠物殡葬服务有限公司","特点：成都百福宠物殡葬服务有限公司，专业从事宠物事后深埋树葬,立碑建墓,环保故有“锦官城”之称的成都，成都 成都市龙泉驿区东洪路173号西南150米，公司成立以来发展迅速，业务不断发展壮大我公司主要经营【成都宠物火化】【成都宠物墓地】【成都宠物殡葬】，我们有好的产品和专业的销售和技术团队，我公司属于成都环保设备加工公司行业。","地址：成都市龙泉驿区东洪路173号","http://www.11467.com/chengdu/co/287693.htm",104.157813,30.619235),
            new Image(R.drawable.pic3,"成都爱心园宠物服务有限公司","特点：全程上门接送，我们会发微信视频告知火化过程，第二天内尽快把宝贝骨灰给您送回。","地址：成都市东三环成渝立交附近迎晖路99号","http://www.cdaxycw.com/index.php?_m=mod_product&_a=prdlist&cap_id=67",104.147859,30.643272),
            new Image(R.drawable.pic4,"成都爱心林园宠物殡葬宠物墓地","特点：环境优美、四季花果飘香、交通便利。","地址：成都市龙泉山","http://www.cdcwbz.com/",104.081534,30.655822),
            new Image(R.drawable.pic5,"成都善爱天使宠物殡葬","特点：成都善爱天使宠物服务有限公司成立于2017年底，是由成都多位爱宠人士共同创立的。致力于提供以“友善”“关爱”为宗旨的人性化的宠物善终服务，给天堂的毛孩子一个永远的家。","地址：成都市双流区太平双简快速路旁","http://chengdu.liebiao.com/chongwufuwu/455888940.html",103.930392,30.580399),
            new Image(R.drawable.pic6,"成都宠物火化服务成都小兽宠物","特点：小兽宠物是经工商局注册的宠物火化殡葬正规化公司，注册资金五十万元，总部设于成都。是国内“线上+线下”综合运营的宠物火化公司！专业的火化团队、环保的火化机器、完善的火化流程，以其良好的口碑始终在国内宠物火化行业中处于领先的地位。成为国内宠物善后领先拓荒者。小兽宠物是全国实行水葬服务的公司，既满足单一的宠物善后服务，又给客户多一个选择的余地！另外公司本着“及时、尊重、正规”的愿景，24小时皆可服务！","地址：成都市金牛区沙湾路","http://672105.shop.258.com/",104.057585,30.69537),
            new Image(R.drawable.pic7,"成都云霄宠物殡葬火化有限公司","特点：本公司现拥有无烟无味专业宠物火化炉2台，宠物墓地100余亩，专业人员及工作人员20余人，专业接送人员及车辆设备2台；以“关爱动物，珍爱生命”为企业宗旨，以“绿色环保，专业服务，用心经营”为企业文化。","地址：成都市龙泉驿区十陵,东三环三段十陵立交","http://cd.ganji.com/fuwu_dian/2177386628x/",104.186861,30.659377),
            new Image(R.drawable.pic8,"绵阳派特宠物殡葬（火化）服务站","特点：人性化专业宠物善终服务ProfessionalService for Pet陪它们走好最后一段路。","地址：绵阳市龙山生态农业旅游风景区旁","http://www.peitepet.com/",104.685562,31.473663),
            new Image(R.drawable.pic9,"重庆萌宠宠物服务","特点：提供专业的宠物火化、宠物标本。","地址：重庆市科亚合成化工公司旁","http://www.cqmcbb.com/",106.424682,29.553538),
            new Image(R.drawable.pic10,"重庆宠物火化宠物殡葬服务公司","特点：提供最专业的重庆宠物火化、树葬、宠物标本、动物标本制作、宠物安乐、骨灰钻石、专车接送等宠物后事一条龙服务。","地址：重庆市沙坪坝歌乐山新开寺","http://cq.ganji.com/fuwu_dian/1763579867x/binzang/",106.427401,29.543619)};

    private List<Image> imageList = new ArrayList<>();

    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        positionText = findViewById(R.id.position_text_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_me);
        }
        //navView.setCheckedItem(R.id.pet_diary);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.pet_diary:
                        Intent intent = new Intent(MainActivity.this,DiaryActivity.class);
                        startActivity(intent);
                    default:
                }
                return true;
            }
        });
        initImages();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ImageAdapter(imageList);
        recyclerView.setAdapter(adapter);
    }

    private void initImages(){
        imageList.clear();
        for (int i= 0; i < 10;i++){
            imageList.add(images[i]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            /*case R.id.location:
                mDrawerLayout.openDrawer(GravityCompat.END);
                break;*/
            default:
        }
        return true;
    }
}

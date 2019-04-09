package activitytest.example.com.logandregister;

public class Diary {
    private int id;
    private String title;//日记标题
    private String content;//内容
    private String pubdate;

    public Diary(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    public Diary(String title, String content, String pubdate) {
        super();
        this.title = title;
        this.content = content;
        this.pubdate = pubdate;
    }

    //显示日记列表时，标题长度限制
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubTitle() {
        if (title.length() > 8)
            return title.substring(0, 7) + "……";
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
}

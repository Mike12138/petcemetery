package activitytest.example.com.logandregister;

public class Image {
    private int imageId;

    private String name;

    private String message;

    private String location;

    private String url;

    private double lng;

    private double lat;

    public Image(int imageId, String name, String message ,String location ,String url ,double lng ,double lat){
        this.imageId = imageId;
        this.name = name;
        this.message = message;
        this.location = location;
        this.url = url;
        this.lng = lng;
        this.lat = lat;
    }

    public int getImageId(){
        return imageId;
    }

    public String getName(){
        return name;
    }

    public String getMessage(){
        return message;
    }

    public String getLocation(){
        return location;
    }

    public String getUrl(){
        return url;
    }

    public double getLng(){
        return lng;
    }

    public double getLat(){
        return lat;
    }
}

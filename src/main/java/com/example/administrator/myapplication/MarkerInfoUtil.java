package com.example.administrator.myapplication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MarkerInfoUtil implements Serializable {
    private static final long serialVersionUID = 932858424587994390L;
    private double latitude;//纬度
    private double longitude;//经度
    private String name;//名字
    private int imgId;//图片
    private String description;//描述
    private  String person;
    private double price;//价格
    private double tushuma;


    public static List<MarkerInfoUtil>infos=new ArrayList<MarkerInfoUtil>();
    static {
        infos.add(new MarkerInfoUtil(30.523892,114.403307,"地大1号车位",R.drawable.car001,"停车位001","小明",111));
        infos.add(new MarkerInfoUtil(30.525195,114.40333,"地大2号车位",R.drawable.car002,"停车位002","小红",222));
        infos.add(new MarkerInfoUtil(30.523916,114.404758,"地大3号车位",R.drawable.car003,"停车位003","张三",333));
        infos.add(new MarkerInfoUtil(30.525337,114.406256,"地大4号车位",R.drawable.car004,"停车位004","李四",444));
        infos.add(new MarkerInfoUtil(30.524025,114.407731,"地大5号车位",R.drawable.car005,"停车位005","王武",555));
    }
    public MarkerInfoUtil() {};
    public MarkerInfoUtil(double latitude, double longitude, String name, int imgId, String description,String person,double tushuma) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.imgId = imgId;
        this.description = description;
        this.person=person;
        this.tushuma=tushuma;
    }

    //Tostring方法
    public String toString() {
        return "MarkerInfoUtil[latitude=" + latitude + ",longitude=" + longitude + ",name=" + name + ",imgId=" + imgId + ",description=" + description + ",person="+person+",tushuma="+tushuma+"]";
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public  String getPerson(){return person;}
    public void setPerson(String person){this.person=person;}

    public double getTushuma(){return tushuma;}
    public void setTushuma(double tushuma){this.tushuma=tushuma;}
}



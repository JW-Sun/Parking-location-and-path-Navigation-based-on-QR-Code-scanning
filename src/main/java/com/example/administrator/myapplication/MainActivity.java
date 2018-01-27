package com.example.administrator.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMapStatusChangeListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.google.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.map.MapStatusUpdateFactory.newLatLng;

public class MainActivity extends AppCompatActivity  {
    /**
     * 定位SDK核心类
     */
    private LocationClient locationClient;
    /**
     * 定位监听
     */
    public MyLocationListenner myListener = new MyLocationListenner();
    private BaiduMap baiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private MapView mapView;
    private Button fanhui;
    private Button yuyue;
    private Button queren;
    private Button kaishiyuedu;
    private Button erweima;
    private Button scan;

    private EditText tushuma;
    private Overlay o1;
    //当前地图缩放级别
    private float zoomLevel;
    private List<MarkerInfoUtil>infos;
    private RelativeLayout rl_marker;
    private LinearLayout xiangxi;
    private LinearLayout shurutushuma;


    //扫码界面
    private LinearLayout saoma;
    private final static int REQ_CODE = 1028;
    private Button btn_scan;
    private Button find;
    private ImageView iv_image;
    private TextView tv_result;
    //扫码界面2
    private LinearLayout saoma1;
    private Button btn_scan1;
    private Button find1;
    private Button chakandingdan;
    private ImageView iv_image1;
    private TextView tv_result1;

    //订单界面
    private LinearLayout dingdan;
    private Chronometer timer;
    private ImageView img_dd;
    private TextView chewei;
    private TextView chezhu;
    private LinearLayout ganxie;
    private TextView chorm;
    private TextView cost;
    //点
    final LatLng t1=new LatLng(30.523892,114.403307);
    final LatLng t2=new LatLng(30.525195,114.40333);
    final LatLng t3=new LatLng(30.523916,114.404758);
    final LatLng t4=new LatLng(30.525337,114.406256);
    final LatLng t5=new LatLng(30.524025,114.407731);
    //路径导航点
    private Button lu1,lu2,lu3,lu4,lu5;
    private Overlay lll1;
    LatLng p1=new LatLng(30.527042,114.407789);
    LatLng p2=new LatLng(30.524461,114.407687);
    LatLng p3=new LatLng(30.524464,114.403309);
    LatLng p4=new LatLng(30.524457,114.404778);
    LatLng p5=new LatLng(30.524488,114.406305);


    //定义Ground的显示地理范围
    LatLng southwest = new LatLng(30.5227320000,114.4030870000);
    LatLng northeast = new LatLng(30.5273550000,114.4079510000);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main2);
        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.bmapView);
        rl_marker=(RelativeLayout)findViewById(R.id.rl_marker);
        xiangxi=(LinearLayout)findViewById(R.id.xiangxi);
        shurutushuma=(LinearLayout)findViewById(R.id.shurutushuma);
        saoma=(LinearLayout) findViewById(R.id.saoma);

        scan=(Button)findViewById(R.id.scan);
        btn_scan=(Button)findViewById(R.id.btn_scan);
        find=(Button)findViewById(R.id.find);
        iv_image=(ImageView)findViewById(R.id.iv_image);
        tv_result=(TextView)findViewById(R.id.tv_result);
        //扫码界面2
        erweima=(Button)findViewById(R.id.erweima);
        saoma1=(LinearLayout) findViewById(R.id.saoma1);
        btn_scan1=(Button)findViewById(R.id.btn_scan1);
        find1=(Button)findViewById(R.id.find1);
        iv_image1=(ImageView)findViewById(R.id.iv_image1);
        tv_result1=(TextView)findViewById(R.id.tv_result1);
        chakandingdan=(Button)findViewById(R.id.chakandingdan);
        //订单界面
        dingdan=(LinearLayout)findViewById(R.id.dingdan);
        timer=(Chronometer)findViewById(R.id.timer);
        img_dd=(ImageView)findViewById(R.id.img_dd);
        chewei=(TextView)findViewById(R.id.chewei);
        chezhu=(TextView)findViewById(R.id.chezhu);
        ganxie=(LinearLayout)findViewById(R.id.ganxie);
        chorm=(TextView)findViewById(R.id.chorm);
        cost=(TextView)findViewById(R.id.cost);
        //路径导航
        lu1=(Button)findViewById(R.id.lu1);
        lu2=(Button)findViewById(R.id.lu2);
        lu3=(Button)findViewById(R.id.lu3);
        lu4=(Button)findViewById(R.id.lu4);
        lu5=(Button)findViewById(R.id.lu5);

        //获取百度地图对象
        baiduMap = mapView.getMap();
        baiduMap.setTrafficEnabled(true);

        // 开启定位图层
        baiduMap.setMyLocationEnabled(true);
//定义文字所显示的坐标点
        LatLng llText = new LatLng(30.5260897587,114.4037335372);
//构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(24)
                .fontColor(0xFFFF00FF)
                .text("图书")
                .rotate(0)
                .position(llText);
//在地图上添加该文字对象并显示
        //baiduMap.addOverlay(textOption);
        baiduMap.setOnMapStatusChangeListener(new OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus arg0) {

            }

            @Override
            public void onMapStatusChangeStart(MapStatus arg0, int i) {

            }

            @Override
            public void onMapStatusChange(MapStatus arg0) {
                zoomLevel = arg0.zoom;
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus arg0) {

            }
        });
        //地图点击操作
        baiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng arg0) {
                xiangxi.setVisibility(View.GONE);
                rl_marker.setVisibility(View.GONE);
                shurutushuma.setVisibility(View.GONE);
                saoma.setVisibility(View.GONE);
                saoma1.setVisibility(View.GONE);
                dingdan.setVisibility(View.GONE);
            }
            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                return false;
            }
        });

        //加停车场
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(northeast)
                .include(southwest)
                .build();
        //定义Ground显示的图片
        BitmapDescriptor bdGround = BitmapDescriptorFactory
                .fromResource(R.drawable.rrr);
        //定义Ground覆盖物选项
        OverlayOptions ooGround = new GroundOverlayOptions()
                .positionFromBounds(bounds)
                .image(bdGround)
                .transparency(0.8f);

//在地图中添加Ground覆盖物
        baiduMap.addOverlay(ooGround);
//加标注点
       /* LatLng point=new LatLng(30.5247130000,114.4058460000);
        BitmapDescriptor bitmap= BitmapDescriptorFactory
                .fromResource(R.drawable.icon);
        OverlayOptions option1=new MarkerOptions()
                .position(point)
                .icon(bitmap);
        baiduMap.addOverlay(option1);
        LatLng point2=new LatLng(30.5244380000,114.4075630000);
        OverlayOptions option2=new MarkerOptions()
                .position(point2)
                .icon(bitmap);
        baiduMap.addOverlay(option2);*/

        /**
         * 定位初始化
         */
        //声明定位SDK核心类
        locationClient = new LocationClient(this);
        //注册监听
        locationClient.registerLocationListener(myListener);
        //定位配置信息
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);//定位请求时间间隔
        locationClient.setLocOption(option);
        //开启定位
        locationClient.start();
        //创建Marker信息
        // setMarkerInfo();
        addOverlay(MarkerInfoUtil.infos);
        yuyue=(Button)findViewById(R.id.yuyue);
        yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_marker.setVisibility(View.GONE);
                xiangxi.setVisibility(View.VISIBLE);
            }
        });
        queren=(Button)findViewById(R.id.queren);
        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_marker.setVisibility(View.GONE);
                xiangxi.setVisibility(View.GONE);
                shurutushuma.setVisibility(View.VISIBLE);
            }
        });
        kaishiyuedu=(Button)findViewById(R.id.kaishiyuedu);
        tushuma=(EditText)super.findViewById(R.id.tushuma);

        //扫描二维码环节
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_marker.setVisibility(View.GONE);
                xiangxi.setVisibility(View.GONE);
                shurutushuma.setVisibility(View.GONE);
                saoma.setVisibility(View.VISIBLE);
            }
        });
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //扫码
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });

        //扫描二维码2
        erweima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rl_marker.setVisibility(View.GONE);
                xiangxi.setVisibility(View.GONE);
                shurutushuma.setVisibility(View.GONE);
                saoma.setVisibility(View.GONE);
                saoma1.setVisibility(View.VISIBLE);
            }
        });
        btn_scan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //扫码
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,REQ_CODE);
            }
        });

        chakandingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dingdan.setVisibility(View.VISIBLE);
            }
        });
        //路径导航
        List<LatLng> point1=new ArrayList<LatLng>();
        List<LatLng> point2=new ArrayList<LatLng>();
        List<LatLng> point3=new ArrayList<LatLng>();
        List<LatLng> point4=new ArrayList<LatLng>();
        List<LatLng> point5=new ArrayList<LatLng>();
        point1.add(p1);point1.add(p2);point1.add(p3);point1.add(t1);
        point2.add(p1);point2.add(p2);point2.add(p3);point2.add(t2);
        point3.add(p1);point3.add(p2);point3.add(p4);point3.add(t3);
        point4.add(p1);point4.add(p2);point4.add(p5);point4.add(t4);
        point5.add(p1);point5.add(p2);point5.add(t5);
        final OverlayOptions oo1=new PolylineOptions().width(10).color(0xAAFF0000).points(point1);
        final OverlayOptions oo2=new PolylineOptions().width(10).color(0xAAFF0000).points(point2);
        final OverlayOptions oo3=new PolylineOptions().width(10).color(0xAAFF0000).points(point3);
        final OverlayOptions oo4=new PolylineOptions().width(10).color(0xAAFF0000).points(point4);
        final OverlayOptions oo5=new PolylineOptions().width(10).color(0xAAFF0000).points(point5);
        lu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll1=baiduMap.addOverlay(oo1);
            }
        });
        lu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll1=baiduMap.addOverlay(oo2);
            }
        });
        lu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll1=baiduMap.addOverlay(oo3);
            }
        });
        lu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll1=baiduMap.addOverlay(oo4);
            }
        });
        lu5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lll1=baiduMap.addOverlay(oo5);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            iv_image.setVisibility(View.VISIBLE);
            iv_image1.setVisibility(View.VISIBLE);
            String result = data.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);
            Bitmap bitmap = data.getParcelableExtra(CaptureActivity.SCAN_QRCODE_BITMAP);

            tv_result.setText("扫描结果为：" + result+"号车位");
            tv_result1.setText("扫描结果为：" + result+"号车位");
            showToast("扫码结果："+result+"号车位");
            if (bitmap != null) {
                iv_image.setImageBitmap(bitmap);
                iv_image1.setImageBitmap(bitmap);
            }
            ffind(result);
            ffind1(result);
        }
    }



    private void showToast(String msg) {
        Toast.makeText(MainActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
    }
    private void ffind(final String msg){
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (msg.toString().equals("001")){
                    MapStatus m=new MapStatus.Builder().target(t1).zoom(22).build();
                    MapStatusUpdate mm=MapStatusUpdateFactory.newMapStatus(m);
                    baiduMap.setMapStatus(mm);
                }
                else if (msg.toString().equals("002")){
                    MapStatus m=new MapStatus.Builder().target(t2).zoom(22).build();
                    MapStatusUpdate mm=MapStatusUpdateFactory.newMapStatus(m);
                    baiduMap.setMapStatus(mm);
                }
                else if (msg.toString().equals("003")){
                    MapStatus m=new MapStatus.Builder().target(t3).zoom(22).build();
                    MapStatusUpdate mm=MapStatusUpdateFactory.newMapStatus(m);
                    baiduMap.setMapStatus(mm);
                }
                else if (msg.toString().equals("004")){
                    MapStatus m=new MapStatus.Builder().target(t4).zoom(22).build();
                    MapStatusUpdate mm=MapStatusUpdateFactory.newMapStatus(m);
                    baiduMap.setMapStatus(mm);
                }
                else if (msg.toString().equals("005")){
                    MapStatus m=new MapStatus.Builder().target(t5).zoom(22).build();
                    MapStatusUpdate mm=MapStatusUpdateFactory.newMapStatus(m);
                    baiduMap.setMapStatus(mm);
                }
            }
        });
    }
    private void ffind1(final String msg) {
        find1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganxie.setVisibility(View.GONE);
                if(msg.toString().equals("001")){
                    BitmapDescriptor bp1=BitmapDescriptorFactory.fromResource(R.drawable.icon_mark1);
                    OverlayOptions op1=new MarkerOptions()
                            .position(t1)
                            .icon(bp1);
                    o1=baiduMap.addOverlay(op1);
                    lu1.setVisibility(View.VISIBLE);

                }
                else if(msg.toString().equals("002")){
                    BitmapDescriptor bp1=BitmapDescriptorFactory.fromResource(R.drawable.icon_mark2);
                    OverlayOptions op1=new MarkerOptions()
                            .position(t2)
                            .icon(bp1);
                    o1=baiduMap.addOverlay(op1);
                    lu2.setVisibility(View.VISIBLE);
                }
                else if(msg.toString().equals("003")){
                    BitmapDescriptor bp1=BitmapDescriptorFactory.fromResource(R.drawable.icon_mark3);
                    OverlayOptions op1=new MarkerOptions()
                            .position(t3)
                            .icon(bp1);
                    o1=baiduMap.addOverlay(op1);
                    lu3.setVisibility(View.VISIBLE);
                }
                else if(msg.toString().equals("004")){
                    BitmapDescriptor bp1=BitmapDescriptorFactory.fromResource(R.drawable.icon_mark4);
                    OverlayOptions op1=new MarkerOptions()
                            .position(t4)
                            .icon(bp1);
                    o1=baiduMap.addOverlay(op1);
                    lu4.setVisibility(View.VISIBLE);
                }
                else if(msg.toString().equals("005")){
                    BitmapDescriptor bp1=BitmapDescriptorFactory.fromResource(R.drawable.icon_mark5);
                    OverlayOptions op1=new MarkerOptions()
                            .position(t5)
                            .icon(bp1);
                    o1=baiduMap.addOverlay(op1);
                    lu5.setVisibility(View.VISIBLE);
                }
                Toast.makeText(MainActivity.this,"成功建立第"+msg+"号车位的停车标识",Toast.LENGTH_LONG).show();
                chakandingdan.setVisibility(View.VISIBLE);
                startClick(view);
                iv_image.setImageBitmap(null);
                tv_result.setText(null);
            }
        });
    }
    public void startClick(View view){
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
    }
    public void stopClick1(View view){
        timer.stop();
        Toast toast=Toast.makeText(getApplicationContext(),"感谢使用二维码扫码停车服务",Toast.LENGTH_LONG);
        toast.show();
        chakandingdan.setVisibility(View.GONE);
        lu1.setVisibility(View.GONE);lu2.setVisibility(View.GONE);lu3.setVisibility(View.GONE);lu4.setVisibility(View.GONE);lu5.setVisibility(View.GONE);
        ganxie.setVisibility(View.VISIBLE);
        String timetotal=getChangingConfigurations(timer);
        chorm.setText("停车时间总计为:"+timetotal+"秒。");
        double cost1=Double.parseDouble(timetotal)*0.02;
        cost.setText( cost1+"元");
        o1.remove();
        lll1.remove();
        Toast.makeText(MainActivity.this,"停车时间总计为:"+timer.getText().toString(),Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this,"感谢使用，再见",Toast.LENGTH_LONG).show();
    }

    private String getChangingConfigurations(Chronometer timer) {
        int totalss = 0;
        String string = timer.getText().toString();
        if(string.length()==8){
            String[] split = string.split(":");
            String string2 = split[0];
            int hour = Integer.parseInt(string2);
            int Hours =hour*3600;
            String string3 = split[1];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[2]);
            totalss = Hours+Mins+SS;
            return String.valueOf(totalss);
        }
        return String.valueOf(totalss);
    }

    /* private void setMarkerInfo(){
         infos=new ArrayList<MarkerInfoUtil>();
         infos.add(new MarkerInfoUtil(30.5247130000,114.4058460000,"中国地质大学图书馆",R.drawable.library,"《数据结构》","小明"));
         infos.add(new MarkerInfoUtil(30.5244380000,114.4075630000,"中国地质大学西校门",R.drawable.xuexiao,"《计算机网络》","小红"));
         infos.add(new MarkerInfoUtil(30.5359160000,114.4046350000,"中国地质大学北校门",R.drawable.northdoor,"《计算机组成与结构》","张三"));
         infos.add(new MarkerInfoUtil(30.5354960000,114.4066870000,"中国地质大学北区综合楼",R.drawable.jxl,"《计算机操作系统》","李四"));
         infos.add(new MarkerInfoUtil(30.5270350000,114.4065700000,"中国地质大学主楼",R.drawable.mainbuild,"《Android studio》","王武"));
     }*/
    private void addOverlay(List<MarkerInfoUtil> infos){
        //baiduMap.clear();
        BitmapDescriptor bitmap= BitmapDescriptorFactory.fromResource(R.drawable.icon);
        for (MarkerInfoUtil info:infos) {
            LatLng latLng = new LatLng(info.getLatitude(), info.getLongitude());
            OverlayOptions options = new MarkerOptions()
                    .position(latLng)
                    .icon(bitmap);
            Marker marker = (Marker) baiduMap.addOverlay(options);

            LatLng text1=new LatLng(info.getLatitude(), info.getLongitude());
            OverlayOptions textOption=new TextOptions()
                    .position(text1)
                    .text(info.getName())
                    .bgColor(0xAAFFFF00)
                    .fontSize(24)
                    .fontColor(0xFFFF00FF);
            baiduMap.addOverlay(textOption);
            //使用marker携带info信息，当点击事件的时候可以通过marker获得info信息
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", info);
            marker.setExtraInfo(bundle);
        }
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                //从marker中获取info的信息
                Bundle bundle = marker.getExtraInfo();
                final MarkerInfoUtil infoUtil = (MarkerInfoUtil) bundle.getSerializable("info");
                //将信息显示在界面上
                ImageView iv_img = (ImageView) rl_marker.findViewById(R.id.iv_img);
                iv_img.setBackgroundResource(infoUtil.getImgId());
                TextView tv_name = (TextView) rl_marker.findViewById(R.id.tv_name);
                tv_name.setText(infoUtil.getName());
                TextView tv_description = (TextView) rl_marker.findViewById(R.id.tv_description);
                tv_description.setText(infoUtil.getDescription());
                //将布局显示出来
                rl_marker.setVisibility(View.VISIBLE);
                TextView shuru1=(TextView)xiangxi.findViewById(R.id.shuru1);
                shuru1.setText(infoUtil.getName());
                TextView shuru2=(TextView)xiangxi.findViewById(R.id.shuru2);
                shuru2.setText(infoUtil.getDescription());
                TextView shuru3=xiangxi.findViewById(R.id.shuru3);
                shuru3.setText(infoUtil.getPerson());
                kaishiyuedu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast=Toast.makeText(getApplicationContext(),"输入码正确，停车成功",Toast.LENGTH_SHORT);
                        toast.show();
                        Intent t=new Intent(MainActivity.this,JishiActivity.class);
                        startActivity(t);
                    }
                });
                img_dd.setBackgroundResource(infoUtil.getImgId());
                chewei.setText(infoUtil.getDescription());
                chezhu.setText(infoUtil.getPerson());
                return true;
            }
        });
    }




    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
           /* MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(p1.latitude)
                    .longitude(p1.longitude).build();*/
            baiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
               LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
               /* LatLng ll = new LatLng(p1.latitude,
                        p1.longitude);*/
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
            final LatLng lll=new LatLng(location.getLatitude(),
                    location.getLongitude());
            fanhui=(Button)findViewById(R.id.fanhui);
            fanhui.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    MapStatusUpdate mm= newLatLng(lll);
                    baiduMap.setMapStatus(mm);
                }
            });
        }
        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    protected void onDestroy() {

        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();


    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }
}

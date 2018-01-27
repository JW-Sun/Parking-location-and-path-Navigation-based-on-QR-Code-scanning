package com.example.administrator.myapplication;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;




public class JishiActivity extends AppCompatActivity {
    private Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jishi);
        timer=(Chronometer)findViewById(R.id.timer);
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
        Toast.makeText(JishiActivity.this,timer.getText().toString(),Toast.LENGTH_SHORT).show();
        String result=getChangingConfigurations(timer);
        Toast.makeText(JishiActivity.this,result,Toast.LENGTH_LONG).show();
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
       /* else if(string.length()==5){

            String[] split = string.split(":");
            String string3 = split[0];
            int min = Integer.parseInt(string3);
            int Mins =min*60;
            int  SS =Integer.parseInt(split[1]);

            totalss =Mins+SS;
            return String.valueOf(totalss);
        }*/
        return String.valueOf(totalss);
    }


}

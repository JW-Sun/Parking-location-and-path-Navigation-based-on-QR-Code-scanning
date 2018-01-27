package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/11/4 0004.
 */

public class AppearActivity extends FragmentActivity implements View.OnClickListener{
    // 初始化顶部栏显示
    private ImageView titleLeftImv;
    private TextView titleTv;
    // 定义4个Fragment对象
    private FirstFragment fg1;
    private SecondFragment fg2;
    private ThirdFragment fg3;
    private FourthFragment fg4;
    // 帧布局对象，用来存放Fragment对象
    private FrameLayout frameLayout;
    // 定义每个选项中的相关控件
    private TextView firstLayout;
    private TextView secondLayout;
    private TextView thirdLayout;
    private TextView fourthLayout;
    // 定义几个颜色
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int dark = 0xff000000;
    // 定义FragmentManager对象管理器
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom);
        fragmentManager = getSupportFragmentManager();
        firstLayout = (TextView) findViewById(R.id.first_layout);
        secondLayout = (TextView) findViewById(R.id.second_layout);
        thirdLayout = (TextView) findViewById(R.id.third_layout);
        fourthLayout = (TextView) findViewById(R.id.fourth_layout);
        firstLayout.setOnClickListener(AppearActivity.this);
        secondLayout.setOnClickListener(AppearActivity.this);
        thirdLayout.setOnClickListener(AppearActivity.this);
        fourthLayout.setOnClickListener(AppearActivity.this);
        setChioceItem(0); // 初始化页面加载时显示第一个选项卡
    }
    private void setSelected(){
        firstLayout.setSelected(false);
        secondLayout.setSelected(false);
        thirdLayout.setSelected(false);
        fourthLayout.setSelected(false);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first_layout:
                setChioceItem(0);
                setSelected();
                firstLayout.setSelected(true);
                break;
            case R.id.second_layout:
                setChioceItem(1);
                setSelected();
                secondLayout.setSelected(true);
                break;
            case R.id.third_layout:
                setChioceItem(2);
                setSelected();
                thirdLayout.setSelected(true);
                break;
            case R.id.fourth_layout:
                setChioceItem(3);
                setSelected();
                fourthLayout.setSelected(true);
                break;
            default:
                break;
        }
    }
    private void setChioceItem(int index) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        clearChioce(); // 清空, 重置选项, 隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index){
            case 0:
                if (fg1 == null) {
                    fg1 = new FirstFragment();
                    fragmentTransaction.add(R.id.content, fg1);
                } else {
// 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);
                }
                break;
            case 1:
                if (fg2 == null) {
                    fg2 = new SecondFragment();
                    fragmentTransaction.add(R.id.content, fg2);
                } else {
                    fragmentTransaction.show(fg2);
                }
                break;
            case 2:
                if (fg3 == null) {
                    fg3 = new ThirdFragment();
                    fragmentTransaction.add(R.id.content, fg3);
                } else {
                    fragmentTransaction.show(fg3);
                }
                break;
            case 3:
                if (fg4 == null) {
                    fg4 = new FourthFragment();
                    fragmentTransaction.add(R.id.content, fg4);
                } else {
                    fragmentTransaction.show(fg4);
                }
                break;

        }
        fragmentTransaction.commit(); // 提交

    }
    private void hideFragments(FragmentTransaction fragmentTransaction) {
        if (fg1 != null) {
            fragmentTransaction.hide(fg1);
        }
        if (fg2 != null) {
            fragmentTransaction.hide(fg2);
        }
        if (fg3 != null) {
            fragmentTransaction.hide(fg3);
        }
        if (fg4 != null) {
            fragmentTransaction.hide(fg4);
        }

    }

    private void clearChioce() {
    }
}

package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chat.MainActivity222;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class SecondFragment extends Fragment {
    private ExpandableListView expandableListView;
    private List<String> Group;
    private List<List<String>> Child;
    private List<String> ChildFirst;
    private List<String> ChildSecond;
    private List<String> ChildThird;
    private List<List<Integer>> ChildPicture;
    public SecondFragment(){}
    @Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view =inflater.inflate(R.layout.fg2,container,false);
        Group=new ArrayList<String>();
        Group.add("我的设备");
        Group.add("我的好友");
        Group.add("我的相关");
        ChildFirst = new ArrayList<String>();
        ChildFirst.add("我的电脑");
        ChildFirst.add("我的手机");
        ChildFirst.add("我的手表");

        ChildSecond= new ArrayList<String>();
        ChildSecond.add("小红");
        ChildSecond.add("小明");
        ChildSecond.add("小张");

        ChildThird= new ArrayList<String>();
        ChildThird.add("我的书本");
        ChildThird.add("我的记录");
        ChildThird.add("我的地图");

        Child = new ArrayList<List<String>>();
        Child.add(ChildFirst);
        Child.add(ChildSecond);
        Child.add(ChildThird);

        List<Integer> tmp_list = new ArrayList<Integer>();
        tmp_list.add(R.drawable.user1);
        tmp_list.add(R.drawable.user2);
        tmp_list.add(R.drawable.user3);

        ChildPicture = new ArrayList<List<Integer>>();
        ChildPicture.add(tmp_list);
        ChildPicture.add(tmp_list);
        ChildPicture.add(tmp_list);
        //把东西都放到里面

        expandableListView = (ExpandableListView)view.findViewById(R.id.my_phone);
        //实例化expandableLIstView

        //给他设置adapter，这里getActivity（），如果在Activity中用就是this
        expandableListView.setAdapter(new MyExpandableListViewAdapter(getActivity()));
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View convertView, int groupPosition, int childPosition, long l) {
                Intent intent = new Intent(getActivity(), MainActivity222.class);
                startActivity(intent);
                return true;
            }
        });
        return view;
}
    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
        private Context context;
        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return Group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return Child.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return Group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return Child.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;     //我们自己设定的一个简单类，用来存储控件的相关信息
            if (convertView == null){//这里的convertView其实是一个起缓冲作用的，工具，因为当一个item从屏幕中滚出，我们把它放到convertView里                                                 //，这样再滑回来的时候可以直接去取，不用重新创建，这里也推荐一个网址，大家可以详细了解
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.group, null);      //把界面放到缓冲区
                groupHolder = new GroupHolder();          //实例化我们创建的这个类
                groupHolder.txt = (TextView) convertView.findViewById(R.id.notice_item_date);  //实例化类里的TextView
                convertView.setTag(groupHolder);
            }
            else {
                groupHolder = (GroupHolder) convertView.getTag();     //然后他就直接来拿
            }
            groupHolder.txt.setText(Group.get(groupPosition));//最后在相应的group里设置他相应的Text
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            ItemHolder itemHolder = null;
            if (convertView == null){
                convertView = (View) getActivity().getLayoutInflater().from(context).inflate(
                        R.layout.child, null);
                itemHolder = new ItemHolder();
                itemHolder.txt=(TextView)convertView.findViewById(R.id.group);
                itemHolder.img=(ImageView)convertView.findViewById(R.id.iv);
                convertView.setTag(itemHolder);
            }
            else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.txt.setText(Child.get(groupPosition).get(
                    childPosition));
            itemHolder.img.setBackgroundResource(ChildPicture.get(groupPosition).get(
                    childPosition));
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    private class GroupHolder {
        public TextView txt;
        public ImageView img;
    }

    private class ItemHolder {
        public ImageView img;
        public TextView txt;
    }
}

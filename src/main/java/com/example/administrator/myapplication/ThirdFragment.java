package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kongjiandongtai.MainActivity33;

/**
 * Created by Administrator on 2017/10/31 0031.
 */

public class ThirdFragment extends Fragment {
    private Button gengduo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fg3,container,false);
        gengduo=(Button)view.findViewById(R.id.gengduo);
        gengduo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(getActivity().getApplicationContext(), MainActivity33.class);
                startActivity(t);
            }
        });
        return view;
    }
}

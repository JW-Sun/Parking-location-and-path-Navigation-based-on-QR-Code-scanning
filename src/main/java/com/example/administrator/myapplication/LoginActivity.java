package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




public class LoginActivity extends AppCompatActivity {
    private Button login;
    EditText username;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.login);
        username=(EditText)super.findViewById(R.id.username);
        password=(EditText)super.findViewById(R.id.password);//获取用户密码
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("6")&&password.getText().toString().equals("8"))
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"登陆成功",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent t=new Intent(LoginActivity.this,AppearActivity.class);//从登陆界面跳转到主界面
                    startActivity(t);
                }
                if(!username.getText().toString().equals("6")||!password.getText().toString().equals("8"))
                {
                    Toast.makeText(getApplicationContext(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

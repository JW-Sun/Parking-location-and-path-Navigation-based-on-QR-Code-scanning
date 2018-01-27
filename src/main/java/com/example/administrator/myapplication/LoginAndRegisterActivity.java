package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginAndRegisterActivity extends AppCompatActivity {

    private static final int USER_LOGIN =1 ;
    private Button login;
    private Button register;
    private EditText username;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_register);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=username.getText().toString().trim();
                String pw=password.getText().toString().trim();
                SendByHttpClient(id,pw);

                if(username.getText().toString().equals("000")&&password.getText().toString().equals("000"))
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"非数据库服务默认用户登陆成功",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent t=new Intent(LoginAndRegisterActivity.this,AppearActivity.class);//从登陆界面跳转到主界面
                    startActivity(t);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String id1=username.getText().toString().trim();
                String pw1=password.getText().toString().trim();
                SendByHttpClient1(id1,pw1);*/

                Intent t=new Intent(LoginAndRegisterActivity.this,Register.class);
                startActivity(t);
            }
        });

    }

    public void SendByHttpClient1(final String id1, final String pw1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient hc=new DefaultHttpClient();
                    HttpPost hp=new HttpPost("http://172.20.10.9:8080/Register");
                    List<NameValuePair> params=new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("ID1",id1));
                    params.add(new BasicNameValuePair("PW1",pw1));
                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf_8");
                    hp.setEntity(entity);
                    HttpResponse httpResponse=hc.execute(hp);
                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        String response= EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
                        Message message1=new Message();
                        message1.what=USER_LOGIN;
                        message1.obj=response;
                        handler1.sendMessage(message1);
                    }
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void SendByHttpClient(final String id, final String pw) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient=new DefaultHttpClient();
                    HttpPost httpPost=new HttpPost("http://39.108.155.221:8080/Login");//服务器地址，指向Servlet
                    List<NameValuePair> params=new ArrayList<NameValuePair>();//将id和pw装入list
                    params.add(new BasicNameValuePair("ID",id));
                    params.add(new BasicNameValuePair("PW",pw));
                    final UrlEncodedFormEntity entity=new UrlEncodedFormEntity(params,"utf-8");//以UTF-8格式发送
                    httpPost.setEntity(entity);
                    HttpResponse httpResponse= httpclient.execute(httpPost);
                    if(httpResponse.getStatusLine().getStatusCode()==200)//在200毫秒之内接收到返回值
                    {
                        HttpEntity entity1=httpResponse.getEntity();
                        String response= EntityUtils.toString(entity1, "utf-8");//以UTF-8格式解析
                        Message message=new Message();
                        message.what=USER_LOGIN;
                        message.obj=response;
                        handler.sendMessage(message);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public static final int SHOW_RESPONSE=1;
    public Handler handler=new Handler() {
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    Toast.makeText(LoginAndRegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    if (response.toString().equals("true")) {
                        Intent t = new Intent(LoginAndRegisterActivity.this, AppearActivity.class);
                        startActivity(t);
                    }
                default:
                    break;
            }
        }
    };
    public Handler handler1=new Handler(){
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    Toast.makeText(LoginAndRegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    if (response.toString().equals("true")) {
                    }
                default:
                    break;
            }
        }
    };
}

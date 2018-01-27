package com.example.administrator.myapplication;

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



public class Register extends AppCompatActivity {
    private static final int USER_LOGIN =1 ;
    private EditText username;
    private EditText password;
    private Button register1;
    private Button retu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        register1=(Button)findViewById(R.id.register1);
        retu=(Button)findViewById(R.id.retu);
        register1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id1=username.getText().toString().trim();
                String pw1=password.getText().toString().trim();
                SendByHttpClient1(id1,pw1);
            }
        });
        retu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register.this.finish();
            }
        });
    }

    public void SendByHttpClient1(final String id1, final String pw1) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpClient hc=new DefaultHttpClient();
                    HttpPost hp=new HttpPost("http://39.108.155.221:8080/Register");
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
    public static final int SHOW_RESPONSE=1;
    public Handler handler1=new Handler(){
        public void handleMessage(Message msg)
        {
            switch (msg.what){
                case SHOW_RESPONSE:
                    String response=(String)msg.obj;
                    Toast.makeText(Register.this, response, Toast.LENGTH_SHORT).show();
                   /* if (response.toString().equals("true")) {
                        Intent t=new Intent(Register.this,LoginAndRegisterActivity.class);
                        startActivity(t);
                    }*/
                default:
                    break;
            }
        }
    };
}

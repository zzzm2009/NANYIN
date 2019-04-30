package com.example.nyapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nyapp.Common.MD5Utils;
import com.example.nyapp.Common.jsonstr2map;
import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.xiasuhuei321.loadingdialog.view.LoadingDialog.Speed.SPEED_TWO;


public class RegisterActivity extends AppCompatActivity {

    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    private String adduserpath="http://129.204.153.125:8080/api/user/adduser";
    private int flag;
    private LoadingDialog mLoadingDialog;
    private String Tag=RegisterActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置页面布局 ,注册界面
        setContentView(R.layout.activity_register);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Toolbar toolbar = (Toolbar) findViewById(R.id.registertoolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
        //统计按钮事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        init();
    }

    private void init() {

        //从activity_register.xml 页面中获取对应的UI控件
        Button btn_register = (Button) findViewById(R.id.btn_register);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        et_psw= (EditText) findViewById(R.id.et_psw);
        et_psw_again= (EditText) findViewById(R.id.et_psw_again);
        //注册按钮
        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //获取输入在相应控件中的字符串
                getEditString();
                //判断输入框内容
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;

                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;

                }else if(TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(RegisterActivity.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if(!psw.equals(pswAgain)){
                    Toast.makeText(RegisterActivity.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                    return;
                }
                flag=0;
                showLoading();
                Date now=new Date();
                final String ddate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(now);
                try{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient client=new OkHttpClient();
                            RequestBody requestBody = new FormBody.Builder().add("username",userName).add("password", psw).add("registerday", String.valueOf(ddate)).build();
                            Log.i(Tag, String.valueOf(requestBody));
                            Request request = new Request.Builder().url(adduserpath).post(requestBody).build();
                            try{
                                Response response=client.newCall(request).execute();
                                String result = response.body().string();
                                Map<String, Object> map= jsonstr2map.jsonstr2map(result);

                                String x=map.get("message").toString();
                                if(x.equals("SUCCESS")){
                                    flag=1;
                                }else {
                                    flag=2;
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    while (flag==0){
                        try{
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    };
                    setLoaded(flag);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
        );
    }
    /**
     * 获取控件中的字符串
     */
    private void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
        pswAgain=et_psw_again.getText().toString().trim();
    }

    private void setLoaded(int result) {
        if (result==1) mLoadingDialog.loadSuccess(); else if(result==2) mLoadingDialog.loadFailed();
    }

    private void showLoading() {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setLoadingText("加载中")
                .setSuccessText("注册成功")//显示加载成功时的文字
                .setFailedText("注册失败")
                .setInterceptBack(false)
                .setLoadSpeed(SPEED_TWO)
                .show();
    }

}

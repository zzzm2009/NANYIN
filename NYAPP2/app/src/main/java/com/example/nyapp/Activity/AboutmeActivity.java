package com.example.nyapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.xiasuhuei321.loadingdialog.view.LoadingDialog.Speed.SPEED_TWO;

public class AboutmeActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String userName,registerDay;
    private TextView username_tv,registerday_tv;
    private TextView yourcomment_tv,changepassword_tv,logout_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
        Toolbar toolbar = (Toolbar) findViewById(R.id.aboutmeBar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        username_tv= ((TextView) findViewById(R.id.username));
        registerday_tv= ((TextView) findViewById(R.id.registerday));
        yourcomment_tv= ((TextView) findViewById(R.id.yourcomment));
        changepassword_tv= ((TextView) findViewById(R.id.changepassword));
        logout_tv= ((TextView) findViewById(R.id.logout));

        sp=getSharedPreferences("userinfo", MODE_PRIVATE);
        editor=sp.edit();
        userName = sp.getString("username","");
        registerDay=sp.getString("registerday","");

        try {
            getuserInfo();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        //按钮点击事件
        yourcomment_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AboutmeActivity.this,YourComment.class);
                startActivity(intent);
            }
        });
        changepassword_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AboutmeActivity.this,ChangePassword.class);
                startActivity(intent);
            }
        });
        logout_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AboutmeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getuserInfo() throws ParseException {
        username_tv.setText(userName);
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date dt=df.parse(registerDay);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(dt);
        registerday_tv.setText(date);
    }


    private void setLoaded(int result) {
        if (result==1) mLoadingDialog.loadSuccess(); else if(result==2) mLoadingDialog.loadFailed();
    }

    /**
     * 显示加载的进度款
     */
    public void showLoading() {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setLoadingText("加载中")
                .setSuccessText("加载成功")//显示加载成功时的文字
                .setFailedText("加载失败")
                .setInterceptBack(false)
                .setLoadSpeed(SPEED_TWO)
                .show();
    }

    /**
     * 隐藏加载的进度框
     */
    public void hideLoading() {
        if (mLoadingDialog != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mLoadingDialog.close();
                }
            });
        }
    }

    @Override
    public void onBackPressed(){
        if (mLoadingDialog != null) {
            hideLoading();
            finish();
        } else {
            finish();
        }

    }
}

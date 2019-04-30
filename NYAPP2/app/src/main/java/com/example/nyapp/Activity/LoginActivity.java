package com.example.nyapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nyapp.Common.jsonstr2map;
import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xiasuhuei321.loadingdialog.view.LoadingDialog.Speed.SPEED_TWO;


public class LoginActivity extends AppCompatActivity {
    private String userName,psw,registerDay;
    private EditText et_user_name,et_psw;//编辑框
    private String getuserinfo="http://129.204.153.125:8080/api/user/getuserInfo";
    private int flag;
    private  LoadingDialog mLoadingDialog;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sp=getSharedPreferences("userinfo", MODE_PRIVATE);
        init();
    }
    //获取界面控件
    private void init() {
        //从main_title_bar中获取的id
        //从activity_login.xml中获取的
        TextView tv_register = (TextView) findViewById(R.id.tv_register);
        TextView tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        et_psw= (EditText) findViewById(R.id.et_psw);
        //立即注册控件的点击事件
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //为了跳转到注册界面，并实现注册功能
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        //找回密码控件的点击事件
        tv_find_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,LostFindActivity.class));
            }
        });
        //登录按钮的点击事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始登录，获取用户名和密码 getText().toString().trim();
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                    // md5Psw.equals(); 判断，输入的密码加密后，是否与保存在SharedPreferences中一致
                }
                showLoading();//显示加载框
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder().url(getuserinfo+"?username="+userName).build();
                        try {
                            Response response = client.newCall(request).execute();//发送请求
                            String result = response.body().string();
                            Map<String, Object> map= jsonstr2map.jsonstr2map(result);
                            /**
                             * 将 string 转为json格式
                             */
                            String temp=map.get("data").toString();
                            temp=temp.substring(1,temp.length()-1).replace(" ", "");;
                            String[] strs=temp.split(",");

                            Map<String,String> m=new HashMap<String,String>();
                            for(String s:strs){
                                String[] ms=s.split("=");
                                if(ms.length==2)
                                    m.put(ms[0],ms[1]);
                            }
                            if(map.get("message").equals("SUCCESS")){
                                if(psw.equals(m.get("password"))) {
                                    registerDay=m.get("registerday");
                                    flag = 1;
                                }
                            }
                            else flag=2;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                while(flag==0){
                    try{
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
                setLoaded(flag);
                if(flag==1){
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",userName);
                    editor.putString("registerday",registerDay);
                    editor.commit();
                    Intent mainintent = new Intent(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(mainintent);
                    LoginActivity.this.finish();
                }
            }
        });
    }

    private void setLoaded(int flag) {
        if (flag==1) mLoadingDialog.loadSuccess(); else if(flag==2) mLoadingDialog.loadFailed();
    }

    private void showLoading() {
        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setLoadingText("加载中")
                .setSuccessText("登陆成功")//显示加载成功时的文字
                .setFailedText("登陆失败")
                .setInterceptBack(false)
                .setLoadSpeed(SPEED_TWO)
                .show();
    }

    /**
     *从SharedPreferences中根据用户名读取密码
     */
    private String readPsw(String userName){
        //getSharedPreferences("loginInfo",MODE_PRIVATE);
        //"loginInfo",mode_private; MODE_PRIVATE表示可以继续写入
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //sp.getString() userName, "";
        return sp.getString(userName , "");
    }
    /**
     *保存登录状态和登录用户名到SharedPreferences中
     */
    private void saveLoginStatus(boolean status,String userName){
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.apply();
    }
    /**
     * 注册成功的数据返回至此
     * @param requestCode 请求码
     * @param resultCode 结果码
     * @param data 数据
     */
    @Override
    //显示数据， onActivityResult
    //startActivityForResult(intent, 1); 从注册界面中获取数据
    //int requestCode , int resultCode , Intent data
    // LoginActivity -> startActivityForResult -> onActivityResult();
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){
            //是获取注册界面回传过来的用户名
            // getExtra().getString("***");
            String userName=data.getStringExtra("userName");
            if(!TextUtils.isEmpty(userName)){
                //设置用户名到 et_user_name 控件
                et_user_name.setText(userName);
                //et_user_name控件的setSelection()方法来设置光标位置
                et_user_name.setSelection(userName.length());
            }
        }
    }
}

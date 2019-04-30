package com.example.nyapp.Activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.nyapp.Adapter.Comment;
import com.example.nyapp.Adapter.CommentAdapter;
import com.example.nyapp.Common.jsonstr2map;
import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YourComment extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;
    private List<Comment> mcommentlist=new ArrayList<>();
    private ListView commentListView;
    private String getcommentpath="http://129.204.153.125:8080/api/comment/getcommentlistbyuser";
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private String userName;
    private int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_comment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.yourcommenttoolbar);
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

        sp=getSharedPreferences("userinfo", MODE_PRIVATE);
        editor=sp.edit();
        userName = sp.getString("username","");
        initYourComment();

        CommentAdapter adapter=new CommentAdapter(YourComment.this,R.layout.yourcommentlist,mcommentlist);
        commentListView=findViewById(R.id.yourcomment_listview);
        commentListView.setAdapter(adapter);
    }

    private void initYourComment() {
        mcommentlist.clear();
        flag=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(getcommentpath+"?username="+userName).build();
                try {
                    Response response = client.newCall(request).execute();//发送请求
                    String result = response.body().string();
                    Map<String, Object> map= jsonstr2map.jsonstr2map(result);
                    /**
                     * 将 string 转为json格式
                     */

                    String temp = map.get("data").toString();
                    if(temp.equals("null")){
                        flag=1;
                        // Toast.makeText(OperateRecord.this,"查不到信息",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    temp = temp.substring(1, temp.length() - 1).replace(" ", "").replace("{", "").replace("}", "").replace("\"","").replace("\"","");
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                    String[] strs = temp.split(",");
                    Map<String, String> map2 = new HashMap<String, String>();
                    for (String s : strs) {
                        String sss=s.replace(" ","");
                        String[] ms = sss.split("=");

                        if (ms[1].equals("null")&&ms.length==2) {
                            ms[1] = "";
                        }
                        if (map2.containsKey(ms[0])) {
                            Comment comment = new Comment(map2.get("username"),map2.get("context"),map2.get("commenttime"));
                            mcommentlist.add(comment);
                            map2.clear();
                            if (ms.length==2)
                                map2.put(ms[0],ms[1]);
                        }else{
                            if (ms.length==2)
                                map2.put(ms[0], ms[1]);
                        }
                    }
                    Comment comment = new Comment(map2.get("username"),map2.get("context"),map2.get("commenttime"));
                    mcommentlist.add(comment);
                    if(mcommentlist.size()!=0){
                        flag=1;
                    }else flag=2;
                } catch (Exception e) {
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
        if(flag==1){
        }
    }

    }


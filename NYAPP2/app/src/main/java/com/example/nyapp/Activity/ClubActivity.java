package com.example.nyapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.nyapp.Adapter.Club;
import com.example.nyapp.Adapter.ClubAdapter;
import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import static com.xiasuhuei321.loadingdialog.view.LoadingDialog.Speed.SPEED_TWO;

public class ClubActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;
    private List<Club> mclubList=new ArrayList<>();
    private ListView clubListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        showLoading();
        setLoaded(1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.clubtoolbar);
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

        initClub();
        ClubAdapter adapter=new ClubAdapter(ClubActivity.this,R.layout.club_list,mclubList);
        clubListView=findViewById(R.id.club_listview);
        clubListView.setAdapter(adapter);
    }

    private void initClub() {
        Club club1=new Club("泉州市南音艺术家协会","泉州南音艺苑（一楼西侧）");
        Club club2=new Club("泉州台商投资区南音协会","东园镇南侨南音协会");
        Club club3=new Club("泉州市集贤宾南音社","泉州市鲤城区路下沟1号");
        Club club4=new Club("泉州师范学院南音学院","泉州师院");
        Club club5=new Club("泉州市文化宫南音乐社"," 福建省泉州市区九一街2号（文化宫内）文艺楼二楼");
        Club club6=new Club("泉州市南音乐团"," 福建省泉州市新门街芳草园“南音艺苑”");
        Club club7=new Club("泉州市群弦阁","南音社鲤城区伍堡溪灵宫");
        Club club8=new Club("泉州市白奇回族南音社 "," 泉州百崎乡白奇村");
        Club club9=new Club("泉州市曲艺家协会","   福建省泉州市鲤城区府学路9号附近");
        Club club10=new Club("泉州市洛江区南音协会 ","泉州马甲镇文化中心");
        mclubList.add(club1);
        mclubList.add(club2);
        mclubList.add(club3);
        mclubList.add(club4);
        mclubList.add(club5);
        mclubList.add(club6);
        mclubList.add(club7);
        mclubList.add(club8);
        mclubList.add(club9);
        mclubList.add(club10);
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

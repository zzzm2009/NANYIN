package com.example.nyapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.nyapp.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button avBtn,instrumentBtn,clubBtn,aboutmeBtn,aboutusBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /**
         * 初始化按钮
         */
        avBtn= ((Button) findViewById(R.id.avBtn));
        instrumentBtn= ((Button) findViewById(R.id.instrumentBtn));
        clubBtn= ((Button) findViewById(R.id.clubBtn));
        aboutmeBtn= ((Button) findViewById(R.id.aboutumeBtn));
        aboutusBtn= ((Button) findViewById(R.id.aboutuusBtn));

        /**
         * 设置按钮点击事件
         */
        avBtn.setOnClickListener(this);
        instrumentBtn.setOnClickListener(this);
        clubBtn.setOnClickListener(this);
        aboutmeBtn.setOnClickListener(this);
        aboutusBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.avBtn:
                Toast.makeText(MainActivity.this,"功能正在开发中，敬请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.instrumentBtn:
                Intent intent=new Intent(MainActivity.this,InstrumentActivity.class);
                startActivity(intent);
                break;
            case R.id.clubBtn:
                Intent intent1=new Intent(MainActivity.this,ClubActivity.class);
                startActivity(intent1);
                break;
            case R.id.aboutumeBtn:
                Intent intent2=new Intent(MainActivity.this,AboutmeActivity.class);
                startActivity(intent2);
                break;
            case R.id.aboutuusBtn:
                Intent intent3=new Intent(MainActivity.this,AboutusActivity.class);
                startActivity(intent3);
                break;
            default:
                Toast.makeText(MainActivity.this,"功能正在开发中，敬请期待",Toast.LENGTH_LONG).show();
                break;
        }
    }
}

package com.example.nyapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nyapp.R;

public class MainActivity extends AppCompatActivity {

    private Button videoBtn,audioBtn,aboutmeBtn,loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoBtn= ((Button) findViewById(R.id.video_Btn));
        audioBtn= ((Button) findViewById(R.id.audio_Btn));
        aboutmeBtn= ((Button) findViewById(R.id.aboutme_Btn));
        loginBtn= ((Button) findViewById(R.id.login_Btn));

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,VideoActivity.class);
                startActivity(intent);
            }
        });
    }
}

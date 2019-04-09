package com.example.nyapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nyapp.Adapter.Instrument;
import com.example.nyapp.R;

public class InstrumentSituationActivity extends AppCompatActivity {

    private Instrument instrument;
    private ImageView instrumentimg;
    private TextView nameTv;
    private TextView contentTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_situation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        Intent intent = getIntent();
        instrument = (Instrument) intent.getSerializableExtra("instrument");
        instrumentimg= ((ImageView) findViewById(R.id.instrumentImg));
        nameTv= ((TextView) findViewById(R.id.instrumentnameTv));
        contentTv= ((TextView) findViewById(R.id.content));

        instrumentimg.setImageResource(instrument.getImageId());
        nameTv.setText(instrument.getName());
        contentTv.setText(instrument.getContent());

    }
}

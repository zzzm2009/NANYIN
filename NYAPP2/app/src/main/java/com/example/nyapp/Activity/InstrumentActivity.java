package com.example.nyapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nyapp.Adapter.Instrument;
import com.example.nyapp.Adapter.InstrumentAdapter;
import com.example.nyapp.R;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.xiasuhuei321.loadingdialog.view.LoadingDialog.Speed.SPEED_TWO;

public class InstrumentActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;
    private ListView instrumentlist;
    private List<Instrument> minstrumengList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument);
        showLoading();
        setLoaded(1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.instrumenttoolbar);
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
        initstrumentList();
        InstrumentAdapter adapter=new InstrumentAdapter(InstrumentActivity.this,R.layout.instrumentlist,minstrumengList);
        instrumentlist=findViewById(R.id.instrument_listview);
        instrumentlist.setAdapter(adapter);

        /**
         * list点击事件
         */
        instrumentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(InstrumentActivity.this, InstrumentSituationActivity.class);
                Instrument instrument=minstrumengList.get(i);
                intent.putExtra("instrument", (Serializable) instrument);
                startActivity(intent);

            }
        });
    }

    private void initstrumentList() {
        Instrument instrument1 = new Instrument(R.mipmap.pipa, "南音琵琶", "流行于闽南和台湾一带。是演奏福建南音的主要乐器之一，在乐队中起着指挥作用。常和南音洞箫配在一起，共同演奏乐曲的旋律，使清淡、委婉的福建南音，富有浓郁的地方特色。");
        Instrument instrument2 = new Instrument(R.mipmap.dongxiao, "南音洞箫", "泉州南音伴奏、合奏的主要乐器，属于泉州南音“上四管”之一，是一种构造独特、演奏风格别致的竹管乐器。它音色优美，演奏技法丰富，具有很强的音乐表现力。南音洞箫的形制继承了唐宋尺八精华，并保留了唐代六孔尺八的规制。南音洞箫是其他南音乐器的定音乐器，琵琶、三弦和二弦均要以洞箫的音高定弦。");
        Instrument instrument3 = new Instrument(R.mipmap.erxuan, "南音二弦", "南音二弦与古代奚琴有一定的关系。它是南音乐队中唯一的拉弦乐器，是泉州南音各种形式演奏（唱）不可或缺的乐器。二弦的音色柔和甜美，与洞箫演奏相辅相成，烘托唱腔。形制古朴，音色独特。");
        Instrument instrument4 = new Instrument(R.mipmap.sanxuan, "南音三弦", "南音三弦属弹拨乐器，据传是由秦代“弦鼗“演变而成的，于明初流行于我国南方，尤其是闽浙一带。泉州南音三弦演奏时，以斜式持琴，弦担与上身保持45度角。南音三弦等弹奏指法与南琶相同。三弦等音高比琵琶低一个八度，在演奏时担辅助琵琶低角色，其音色深厚沉稳。");
        Instrument instrument5 = new Instrument(R.mipmap.paiban, "南音拍板", "由五块板组成。用荔木制作，板长约26厘米。中间三块板平整，外侧两块板稍长稍厚，一面平整，一面削成半圆形。上端用绳穿系在一起，绳端有彩穗为饰.演奏时，双手持拍板，三块握在左手，两块握在右手，互相撞击发音。南音拍板形制和演奏方法，均保留着古代拍板之遗制，与成都五代前蜀王建墓乐舞浮雕中的击拍板图象。");
        Instrument instrument6 = new Instrument(R.mipmap.xiang, "南音响盏", "属于锣的一种，锣是中国古老且常见的民族乐器。泉州南音所使用的“响盏”号称为最小的的锣，由于体积较小，难以拿在手上演奏，所以就把它放置于竹子编织的竹箍中，以方便击打。“响盏”由“响盏锣”、“响盏箍”和“响盏槌”三部分组成。演奏时左手持响盏箍，右手持响盏槌。响盏基本上是按南音琵琶的指法击打，但是逢拍位要休止。");
        Instrument instrument7 = new Instrument(R.mipmap.xiaojiao, "南音小叫", "小叫，也成为小叫铎。它实际上是两件乐器，即小叫锣和铎（木鱼），小叫锣也是锣的一种，它和晋剧的打击乐器“狗娃子”相类似，但比“狗娃子”略小。“小叫”由“小叫锣”、“木鱼”和“小叫槌”三部分组成。“小叫铎”的独特之处在于用左手同时持拿“小叫锣”和“木鱼”，右手用木槌分别敲击。小叫的演奏没有定谱，演奏者可根据乐曲的情绪，或舒或密、或击或停，自由发挥，但如逢琵琶撚指（滚）、贯声（时值三拍的长音）或乐曲旋律处于较低的音区时则要休止。小叫的击打由多种节奏型的变化，在演奏中可以灵活运用。");
        Instrument instrument8 = new Instrument(R.mipmap.sibao, "南音四宝", "四宝，竹制，其音质具有良好的融合性，可以和金属或木质的打击乐器同时演奏。“四宝”选用四块带有竹皮制作而成。在“下四管”中，四宝的演奏技巧难度最高。演奏时左右手各执两块，可两手互击，也可同一手两块相击，敲击的位置有多种变化，演奏者可灵活应用。如逢“拍位”，“四宝”应双手分别合击。");
        Instrument instrument9 = new Instrument(R.mipmap.shuang, "南音双铃", "双铃，也称双音、碰铃、双磬、碰钟等，它属于“钟”的范畴，其声清越悠扬。双铃广泛流传于我国大部分地区，在民族乐队及众多的乐种、剧种中都有它的身影，只是在规制上有所不同。在泉州南音的几种金属打击乐器中，双铃的声音最为清逸。在演奏“叠拍”这种只有“拍位”没有“撩位”的乐曲时，要用扁鼓来替换它。演奏时左右各执一个击打乐曲的“撩位”（相当于次强拍）。");
        Instrument instrument10=new Instrument(R.mipmap.biangu,"南音扁鼓","扁鼓，是泉州南音的一件“替补乐器”。鼓是我国历史悠久的民族乐器之一，种类繁多。泉州南音使用的“扁鼓”是其中的一员，属革类乐器。它通常作为双铃的替换乐器与响盏、小叫、四宝同时演奏。在演奏中，扁鼓能起到调和各种打击乐器音色的作用。与其他几件打击乐器相比，扁鼓的音色较为温和，和四宝一样具有良好的融合性，所以在演奏“叠拍”乐曲的时候，用它替代“无撩位可击”的双铃来击打“拍位”，以保证“下四管”的完整。");
        minstrumengList.add(instrument1);
        minstrumengList.add(instrument2);
        minstrumengList.add(instrument3);
        minstrumengList.add(instrument4);
        minstrumengList.add(instrument5);
        minstrumengList.add(instrument6);
        minstrumengList.add(instrument7);
        minstrumengList.add(instrument8);
        minstrumengList.add(instrument9);
        minstrumengList.add(instrument10);
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

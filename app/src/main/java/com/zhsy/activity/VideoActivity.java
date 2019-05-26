package com.zhsy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.zhsy.R;

import cn.jzvd.JZVideoPlayerStandard;


public class VideoActivity extends BaseActivity {

    private JZVideoPlayerStandard jzVideoPlayerStandard;
    private TextView subjectContent;
    private String videoTitle="";
    private String videoUrl="";
    private String videoContent="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent intent=getIntent();
        videoTitle=intent.getStringExtra("videoTitle");
        videoUrl=intent.getStringExtra("videoUrl");
        videoContent=intent.getStringExtra("videoContent");
        initVedio();
    }

    public void initVedio(){
        jzVideoPlayerStandard = (JZVideoPlayerStandard) findViewById(R.id.video_player);
        subjectContent = (TextView) findViewById(R.id.video_content);
        jzVideoPlayerStandard.setUp(videoUrl, JZVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoTitle);
        subjectContent.setText(Html.fromHtml(videoContent));
        //jzVideoPlayerStandard.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        jzVideoPlayerStandard.release();
    }


}

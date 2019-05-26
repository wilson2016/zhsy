package com.zhsy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhsy.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    private ImageView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashView=findViewById(R.id.splash_view);
        Glide.with( this ).load( R.drawable.splash ).into( splashView ) ;
        getHomeActivity();
    }


    //3秒后转跳到home页面
    public void getHomeActivity(){
        Timer timer=new Timer();
        TimerTask task=new TimerTask(){
            public void run(){
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        };
        timer.schedule(task, 3000);
    }
}

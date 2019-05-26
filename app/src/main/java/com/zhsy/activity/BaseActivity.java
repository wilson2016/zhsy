package com.zhsy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhsy.R;
import com.zhsy.utils.PrefsUtils;
import com.zhsy.utils.ZHSYConstants;

/**
 * Created by zht on 2017/11/21.
 */

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(this);
        super.onCreate(savedInstanceState);
    }


    //设置主题
    public static void setTheme(Activity activity) {
        boolean isBlueBG = PrefsUtils.read(activity, ZHSYConstants.THEME_CONFIG, false);
        activity.setTheme(isBlueBG ? R.style.AppBlueTheme : R.style.AppCommonTheme);
    }


    //重新构建
    public void reCreate() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recreate();
            }
        });
    }



}

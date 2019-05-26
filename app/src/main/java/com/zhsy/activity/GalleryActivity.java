package com.zhsy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhsy.R;
import com.zhsy.utils.ImageUtils;
import com.zhsy.view.TouchImageView;


public class GalleryActivity extends BaseActivity {

    private TouchImageView showIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        showIv=findViewById(R.id.show_iv);
        ImageUtils.loadImage(this,getIntent().getStringExtra("imageUrl"),showIv);
    }
}

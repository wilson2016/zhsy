package com.zhsy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhsy.R;
import com.zhsy.utils.AudioPlayer;
import com.zhsy.utils.AudioUtils;
import com.zhsy.view.XListView;

import java.text.SimpleDateFormat;

public abstract class BaseFragment extends Fragment {

    protected XListView mLv;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView=inflater.inflate(R.layout.fragment_base,null);
        mLv=rootView.findViewById(R.id.mListview);
        initData();
        return rootView;
    }

    public void onLoad() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");//格式化
        String time = formatter.format(System.currentTimeMillis());
        mLv.setRefreshTime(time);
        AudioPlayer.stopAudio();
    }

    abstract void initData();

}

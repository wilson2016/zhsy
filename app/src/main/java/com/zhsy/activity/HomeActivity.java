package com.zhsy.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zhsy.R;
import com.zhsy.fragment.ImageFragment;
import com.zhsy.fragment.IntroductionFragment;
import com.zhsy.fragment.LangFragment;
import com.zhsy.fragment.MusicFragment;
import com.zhsy.fragment.VideoFragment;
import com.zhsy.utils.AudioPlayer;
import com.zhsy.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity {

    private TabLayout homeTabs;
    private ViewPager homeVp;
    private PageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViews();
        initData();
    }

    private void findViews() {
        homeTabs = (TabLayout)findViewById( R.id.home_tabs );
        homeVp = (ViewPager)findViewById( R.id.home_vp );
    }

    //初始化主页面的内容
    public void initData(){
        adapter=new PageAdapter(getSupportFragmentManager());
        adapter.addFragment(LangFragment.newInstance(), "语言\nསསྐད་ཆ་");
        adapter.addFragment(MusicFragment.newInstance(), "音乐\nགླུ་དབྱངས།");
        adapter.addFragment(ImageFragment.newInstance(), "乘法\nསྒྱུར་རྩིས་རེའུ་མིག་");
        adapter.addFragment(VideoFragment.newInstance(), "视频\nབརྙན་རིས་");
        adapter.addFragment(IntroductionFragment.newInstance(), "关于\nསྐོར་");

        for (int i = 0; i < homeTabs.getTabCount(); i++) {
            TabLayout.Tab tab = homeTabs.getTabAt(i);
            View view = LayoutInflater.from(this).inflate(R.layout.item_home_tab, null);
            TextView tv=view.findViewById(R.id.home_tab_title);
            tv.setText(homeVp.getAdapter().getPageTitle(i));
            if (null != tab) { tab.setCustomView(view);}
            int tabLayoutWidth = DisplayUtil.getScreenWidthInPx(this);
            tv.setWidth(tabLayoutWidth / homeTabs.getTabCount()>0?tabLayoutWidth / homeTabs.getTabCount():tabLayoutWidth/4);
        }
        homeVp.setAdapter(adapter);
        homeTabs.setupWithViewPager(homeVp);
        homeTabs.setTabMode(TabLayout.MODE_FIXED);
    }

    //主页面viewPager适配器
    static class PageAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        private PageAdapter(FragmentManager fm) {
            super(fm);
        }

        private void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return PagerAdapter.POSITION_NONE;
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioPlayer.stopAudio();
    }
}

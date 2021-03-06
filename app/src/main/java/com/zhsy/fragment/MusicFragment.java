package com.zhsy.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhsy.adapter.MusicAdapter;
import com.zhsy.bean.LangBean;
import com.zhsy.bean.MusicBean;
import com.zhsy.utils.AudioPlayer;
import com.zhsy.utils.OKHttpUtils;
import com.zhsy.utils.ZHSYConstants;
import com.zhsy.view.XListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicFragment extends BaseFragment implements XListView.IXListViewListener{

    private List<MusicBean> musicDatas;
    private MusicAdapter audioAdapter;
    private MyHandler handler=new MyHandler();
    private int pageNO;
    private boolean isLoadMore;//是否正在加载更多数据的标记

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }


    @Override
    void initData() {
        musicDatas =new ArrayList<>();
        mLv.setPullRefreshEnable(true);//设置可以下拉刷新，默认就是true
        mLv.setPullLoadEnable(true);//设置可以上拉加载，默认是false
        mLv.setXListViewListener(this);
        audioAdapter=new MusicAdapter(getActivity(), musicDatas);
        mLv.setAdapter(audioAdapter);
        loadData(++pageNO);
    }


    @Override
    public void onRefresh() {
        pageNO=0;
        loadData(++pageNO);
    }

    @Override
    public void onLoadMore() {
        isLoadMore = true;
        loadData(++pageNO);
    }


    public void loadData(int pageNO){
        Request request = new Request.Builder().url(ZHSYConstants.APP_MUSICS_URL+"pageNo="+pageNO+ZHSYConstants.PAGE_LIMIT).build();//创建一个Request
        OKHttpUtils.enqueue(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { }

            @Override
            public void onResponse(Response response) throws IOException {

                String resultStr=response.body().string();
                if(TextUtils.isEmpty(resultStr)){return;}
                try{
                    JSONObject object=new JSONObject(resultStr);
                    if(object.getBoolean("success")){
                        JSONObject mathDatas=object.getJSONObject("data");
                        String maths = mathDatas.getString("musics");
                        List<MusicBean> netDatas= JSON.parseArray(maths,MusicBean.class);

                        Message msg = handler.obtainMessage();
                        msg.obj = netDatas;
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }

                }catch (JSONException e){}

            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isVisibleToUser){ AudioPlayer.stopAudio();}
    }


    public class MyHandler extends Handler {

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            List<MusicBean> netDatas = (ArrayList<MusicBean>) msg.obj;
            if(netDatas!=null&&netDatas.size()>0){
                if(isLoadMore){
                    musicDatas.addAll(netDatas);
                }else{
                    musicDatas.clear();
                    musicDatas.addAll(netDatas);
                }
            }

            if(isLoadMore){
                isLoadMore = false;
                mLv.stopLoadMore();//刷新完毕，关闭上拉加载效果
            }else{
                mLv.stopRefresh();//刷新完毕，关闭下拉刷新效果
            }
            audioAdapter.setImageDatas(musicDatas);
            onLoad();
        }
    }

}

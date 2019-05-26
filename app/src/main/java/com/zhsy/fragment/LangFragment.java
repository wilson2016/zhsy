package com.zhsy.fragment;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhsy.adapter.LangAdapter;
import com.zhsy.bean.ImageBean;
import com.zhsy.bean.LangBean;
import com.zhsy.utils.AudioPlayer;
import com.zhsy.utils.OKHttpUtils;
import com.zhsy.utils.ZHSYConstants;
import com.zhsy.view.XListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LangFragment extends BaseFragment implements XListView.IXListViewListener{

    private List<LangBean> mAudioDatas;
    private LangAdapter audioAdapter;
    private MyHandler handler=new MyHandler();
    private int pageNO;
    private boolean isLoadMore;//是否正在加载更多数据的标记

    public static LangFragment newInstance() {
        LangFragment fragment = new LangFragment();
        return fragment;
    }


    @Override
    void initData() {
        mAudioDatas=new ArrayList<>();
        mLv.setPullRefreshEnable(true);//设置可以下拉刷新，默认就是true
        mLv.setPullLoadEnable(true);//设置可以上拉加载，默认是false
        mLv.setXListViewListener(this);
        audioAdapter=new LangAdapter(getActivity(),mAudioDatas);
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
        Request request = new Request.Builder().url(ZHSYConstants.APP_LANGBASE_URL+"pageNo="+pageNO+ZHSYConstants.PAGE_LIMIT).build();//创建一个Request
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
                        String maths = mathDatas.getString("langs");
                        List<LangBean> netDatas= JSON.parseArray(maths,LangBean.class);

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

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            List<LangBean> netDatas = (ArrayList<LangBean>) msg.obj;
            if(netDatas!=null&&netDatas.size()>0){
                if(isLoadMore){
                    mAudioDatas.addAll(netDatas);
                }else{
                    mAudioDatas.clear();
                    mAudioDatas.addAll(netDatas);
                }
            }

            if(isLoadMore){
                isLoadMore = false;
                mLv.stopLoadMore();//刷新完毕，关闭上拉加载效果
            }else{
                mLv.stopRefresh();//刷新完毕，关闭下拉刷新效果
            }

            audioAdapter.setImageDatas(mAudioDatas);
            onLoad();
        }
    }

}

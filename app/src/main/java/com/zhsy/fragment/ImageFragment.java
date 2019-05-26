package com.zhsy.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhsy.activity.GalleryActivity;
import com.zhsy.adapter.ImageAdapter;
import com.zhsy.bean.ImageBean;
import com.zhsy.utils.OKHttpUtils;
import com.zhsy.utils.ZHSYConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ImageFragment extends BaseFragment {

    private static final String TAG ="ImageFragment";

    private List<ImageBean> imageDatas;//图片信息
    private ImageAdapter imageAdapter;
    private MyHandler handler=new MyHandler();

    public static ImageFragment newInstance() {
        ImageFragment fragment = new ImageFragment();
        return fragment;
    }

    @Override
    void initData() {
        mLv.setPullLoadEnable(false);
        mLv.setPullRefreshEnable(false);
        imageDatas=new ArrayList<ImageBean>();
        imageAdapter=new ImageAdapter(getActivity(),imageDatas);
        mLv.setAdapter(imageAdapter);
        mLv.setOnItemClickListener(itemClick);
        loadData();
    }

    AdapterView.OnItemClickListener itemClick=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent showIntent=new Intent(getActivity(), GalleryActivity.class);
            showIntent.putExtra("imageUrl",imageDatas!=null&&imageDatas.size()>0?imageDatas.get(i).getMath_link():"");
            startActivity(showIntent);
        }
    };


    //获取数据
    public void loadData(){
        Request request = new Request.Builder().url(ZHSYConstants.APP_MATHS_URL).build();//创建一个Request
        OKHttpUtils.enqueue(request, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) { }

            @Override
            public void onResponse(Response response) throws IOException {

                String resultStr=response.body().string();
                if(TextUtils.isEmpty(resultStr)){return;}
                try{
                    JSONObject object=new JSONObject(resultStr);
                    boolean state=object.getBoolean("success");
                    if(state){
                        JSONObject mathDatas=object.getJSONObject("data");
                        JSONObject maths = mathDatas.getJSONObject("maths");

                        Iterator iterator = maths.keys();
                        List<ImageBean> dtatas;
                        List<ImageBean> totalDatas=new ArrayList<>();
                        while(iterator.hasNext()){
                            dtatas=JSON.parseArray(maths.getString((String) iterator.next()),ImageBean.class);
                            totalDatas.addAll(dtatas);
                        }

                        Message msg = handler.obtainMessage();
                        msg.obj = totalDatas;
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }

                }catch (JSONException e){}

            }
        });
    }


    public class MyHandler extends Handler {

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            imageDatas = (ArrayList<ImageBean>) msg.obj;
            imageAdapter.setImageDatas(imageDatas);
        }
    }


}

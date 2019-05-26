package com.zhsy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhsy.R;
import com.zhsy.activity.BaseActivity;
import com.zhsy.activity.HomeActivity;
import com.zhsy.utils.OKHttpUtils;
import com.zhsy.utils.PrefsUtils;
import com.zhsy.utils.ZHSYConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class IntroductionFragment extends Fragment {

    private static final String TAG ="IntroductionFragment";

    private TextView  aboutShow;
    private Button bgSwitch;
    private String content;
    private MyHandler handler=new MyHandler();

    public static IntroductionFragment newInstance() {
        IntroductionFragment fragment = new IntroductionFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootVie=inflater.inflate(R.layout.fragment_introduction,null);
        aboutShow=rootVie.findViewById(R.id.about_show);
        bgSwitch=rootVie.findViewById(R.id.bg_switch_bt);
        bgSwitch.setOnClickListener(ocil);
        loadData();
        return rootVie;
    }

    View.OnClickListener ocil=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"更换主题",Toast.LENGTH_SHORT).show();
            boolean isBlueBG = PrefsUtils.read(getActivity(), ZHSYConstants.THEME_CONFIG, true);
            PrefsUtils.write(getActivity(),ZHSYConstants.THEME_CONFIG,!isBlueBG);
            getActivity().recreate();
        }
    };


    //绑定数据
    void loadData(){
        Request request = new Request.Builder().url(ZHSYConstants.APP_ABOUT_URL).build();//创建一个Request
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
                        JSONObject maths = mathDatas.getJSONObject("introduce");
                        String intro_content=maths.getString("intro_content");
                        String intro_contentShow=maths.getString("intro_contentShow");
                        content=intro_contentShow;
                        handler.sendEmptyMessage(0);
                    }

                }catch (JSONException e){}

            }
        });
    }


    class MyHandler extends Handler {

        // 子类必须重写此方法，接受数据
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            aboutShow.setText(Html.fromHtml(content));
        }
    }



}

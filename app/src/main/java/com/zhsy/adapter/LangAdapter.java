package com.zhsy.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhsy.R;
import com.zhsy.bean.LangBean;
import com.zhsy.utils.AudioPlayer;

import java.util.List;


public class LangAdapter extends BaseAdapter {

    private Context context;
    private List<LangBean> langDatas;
    private MediaPlayer mediaPlayer=new MediaPlayer();

    public LangAdapter(Context context, List<LangBean> audioDatas) {
        this.context = context;
        this.langDatas = audioDatas;
    }

    public void setImageDatas(List<LangBean> audioDatas) {
        this.langDatas = audioDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return langDatas ==null?0: langDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return langDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final AudioViewHolder audioHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_audio_layout, parent, false);
            audioHolder=new AudioViewHolder(convertView);
            convertView.setTag(audioHolder);
        }else{
            audioHolder=(AudioViewHolder) convertView.getTag();
        }

        LangBean item=(LangBean) getItem(position);
        audioHolder.setData(item);
        audioHolder.switchIv.setImageResource(item.isPlayState()? R.drawable.audio_playing:R.drawable.audio_idle);
        audioHolder.switchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(langDatas.get(position).isPlayState()){
                    Toast.makeText(context,"停止",Toast.LENGTH_SHORT).show();
                    langDatas.get(position).setPlayState(false);
                    notifyDataSetChanged();
                    AudioPlayer.stopAudio();
                }else{
                    Toast.makeText(context,"播放",Toast.LENGTH_SHORT).show();
                    langDatas.get(position).setPlayState(true);
                    updatePlayState(position);
                    AudioPlayer.playAudio(langDatas.get(position).getLang_link());
                }

            }
        });
        return convertView;
    }



    //语言基础内容
    private class AudioViewHolder {

        TextView audioName;
        TextView audioAuthor;
        ImageView switchIv;

        public AudioViewHolder(View root){
            audioAuthor = root.findViewById(R.id.audio_author);
            audioName = root.findViewById(R.id.audio_name);
            switchIv= root.findViewById(R.id.switch_iv);
        }

        public void setData(LangBean musicBean){
            audioName.setText(musicBean.getLang_title());
            String diff="";
            if(musicBean.getLang_difficulty()!=null&&musicBean.getLang_difficulty().equals("1")){
                diff="易";
            }else if(musicBean.getLang_difficulty()!=null&&musicBean.getLang_difficulty().equals("2")){
                diff="中";
            }else if(musicBean.getLang_difficulty()!=null&&musicBean.getLang_difficulty().equals("3")){
                diff="难";
            }
            audioAuthor.setText(diff);
        }
    }


    //更新播放状态
    void updatePlayState(int position){
        for(int i = 0; i< langDatas.size(); i++){
            langDatas.get(i).setPlayState(i==position);
        }
        notifyDataSetChanged();
    }


}

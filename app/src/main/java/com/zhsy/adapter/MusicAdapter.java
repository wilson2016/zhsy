package com.zhsy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhsy.R;
import com.zhsy.bean.MusicBean;
import com.zhsy.utils.AudioPlayer;
import com.zhsy.utils.AudioUtils;

import java.util.List;


public class MusicAdapter extends BaseAdapter {

    private Context context;
    private List<MusicBean> audioDatas;

    public MusicAdapter(Context context, List<MusicBean> audioDatas) {
        this.context = context;
        this.audioDatas = audioDatas;
    }

    public void setImageDatas(List<MusicBean> audioDatas) {
        this.audioDatas = audioDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return audioDatas==null?0:audioDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return audioDatas.get(i);
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

        MusicBean item=(MusicBean) getItem(position);
        audioHolder.setData(item);
        audioHolder.switchIv.setImageResource(item.isPlayState()? R.drawable.audio_playing:R.drawable.audio_idle);
        audioHolder.switchIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"播放",Toast.LENGTH_SHORT).show();
                if(audioDatas.get(position).isPlayState()){
                    audioDatas.get(position).setPlayState(false);
                    notifyDataSetChanged();
                    AudioPlayer.stopAudio();
                }else{
                    audioDatas.get(position).setPlayState(true);
                    updatePlayState(position);
                    AudioPlayer.playAudio(audioDatas.get(position).getMusic_link());
                }

            }
        });
        return convertView;
    }

    //图片内容
    private class AudioViewHolder {

        TextView audioName;
        TextView audioAuthor;
        ImageView switchIv;

        public AudioViewHolder(View root){
            audioAuthor = root.findViewById(R.id.audio_author);
            audioName = root.findViewById(R.id.audio_name);
            switchIv= root.findViewById(R.id.switch_iv);
        }

        public void setData(MusicBean musicBean){
            audioName.setText(musicBean.getMusic_title());

            String diff="";
            if(musicBean.getMusic_difficulty()!=null&&musicBean.getMusic_difficulty().equals("1")){
                diff="易";
            }else if(musicBean.getMusic_difficulty()!=null&&musicBean.getMusic_difficulty().equals("2")){
                diff="中";
            }else if(musicBean.getMusic_difficulty()!=null&&musicBean.getMusic_difficulty().equals("3")){
                diff="难";
            }
            audioAuthor.setText(diff);
        }
    }


    //更新播放状态
    void updatePlayState(int position){
        for(int i=0;i<audioDatas.size();i++){
            audioDatas.get(i).setPlayState(i==position);
        }
        notifyDataSetChanged();
    }



}

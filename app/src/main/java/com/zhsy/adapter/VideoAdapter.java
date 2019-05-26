package com.zhsy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhsy.R;
import com.zhsy.activity.VideoActivity;
import com.zhsy.bean.VideoBean;
import com.zhsy.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;


public class VideoAdapter extends BaseAdapter {

    private Context context;
    private List<VideoBean> videoDatas;

    public VideoAdapter(Context context, List<VideoBean> videoDatas) {
        this.context = context;
        this.videoDatas = videoDatas;
    }

    public void setVideoDatas(List<VideoBean> videoDatas) {
        this.videoDatas = videoDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return videoDatas ==null?0: videoDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return videoDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VedioViewHolder imgViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_video_layout, parent, false);
            imgViewHolder=new VedioViewHolder(convertView);
            convertView.setTag(imgViewHolder);
        }else{
            imgViewHolder=(VedioViewHolder) convertView.getTag();
        }

        VideoBean item=(VideoBean) getItem(position);
        imgViewHolder.setData(item);
        return convertView;
    }

    //图片内容
    private class VedioViewHolder {

        TextView videoAuthor;
        TextView videoName;
        ImageView videoImg;

        public VedioViewHolder(View root){
            videoImg = (ImageView) root.findViewById(R.id.video_img);
            videoName = (TextView) root.findViewById(R.id.video_name);
            videoAuthor = (TextView) root.findViewById(R.id.video_author);
        }

        public void setData(final VideoBean videoBean){
            videoName.setText(videoBean.getVedio_title());
            videoAuthor.setText(videoBean.getReal_name());
            Glide.with( context ).load( R.drawable.video_default_bg).into( videoImg ) ;
            videoImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, VideoActivity.class);
                    intent.putExtra("videoUrl",videoBean.getVedio_link());
                    intent.putExtra("videoTitle",videoBean.getVedio_title());
                    intent.putExtra("videoContent",videoBean.getVedio_contentShow());
                    context.startActivity(intent);
                }
            });
        }
    }

}

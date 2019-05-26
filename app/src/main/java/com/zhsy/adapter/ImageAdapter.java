package com.zhsy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhsy.R;
import com.zhsy.bean.ImageBean;
import com.zhsy.utils.ImageUtils;

import java.util.List;


public class ImageAdapter extends BaseAdapter {

    private Context context;
    private List<ImageBean> imageDatas;

    public ImageAdapter(Context context, List<ImageBean> imageDatas) {
        this.context = context;
        this.imageDatas = imageDatas;
    }

    public void setImageDatas(List<ImageBean> imageDatas) {
        this.imageDatas = imageDatas;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return imageDatas==null?0:imageDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return imageDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageViewHolder imgViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_image_layout, parent, false);
            imgViewHolder=new ImageViewHolder(convertView);
            convertView.setTag(imgViewHolder);
        }else{
            imgViewHolder=(ImageViewHolder) convertView.getTag();
        }

        ImageBean item=(ImageBean) getItem(position);
        imgViewHolder.setData(item);
        return convertView;
    }

    //图片内容
    private class ImageViewHolder{

        ImageView imgView;
        public ImageViewHolder(View root){
            imgView = (ImageView) root.findViewById(R.id.item_image);
        }
        public void setData(ImageBean imageBean){
            ImageUtils.loadImage(context,imageBean.getMath_link(),imgView);
        }
    }

}

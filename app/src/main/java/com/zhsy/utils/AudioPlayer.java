package com.zhsy.utils;

import android.media.MediaPlayer;

import java.io.IOException;

public class AudioPlayer {

    public static MediaPlayer mediaPlayer=new MediaPlayer();

    //播放音频
    public static void playAudio(String path) {
        //播放之前要先把音频文件重置
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);//调用方法传进去要播放的音频路径
            mediaPlayer.prepareAsync();//异步准备音频资源
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();//开始音频
                }
            });//调用mediaPlayer的监听方法，音频准备完毕会响应此方法
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                    mediaPlayer.setLooping(true);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //停止音频
    public static void stopAudio() {
        mediaPlayer.pause();
    }



}

package com.zhsy.utils;

import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class AudioUtils {

    private static MediaPlayer mPlayer;

    //播放音频
    public static void playAudio(String url){

        if(mPlayer!=null&&mPlayer.isPlaying()){
            stopAudio();
            mPlayer=new MediaPlayer();
        }
        if(mPlayer==null){ mPlayer=new MediaPlayer(); }

        try{
            mPlayer.setDataSource(url);
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    if(mPlayer!=null) {
                        mPlayer.start();
                        mPlayer.setLooping(true);
                    }
                }
            });

            mPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    if(mPlayer!=null){
                        mPlayer.release();
                        mPlayer=null;
                    }
                    return false;
                }
            });
            mPlayer.prepareAsync();
        }catch (IOException e){
            if(mPlayer!=null){
                mPlayer.release();
                mPlayer=null;
            }
        }
    }


    //暂停播放
    public static void stopAudio(){
        if(mPlayer!= null && mPlayer.isPlaying()){
            mPlayer.stop();
            mPlayer.release();
            mPlayer=null;
        }
    }



}

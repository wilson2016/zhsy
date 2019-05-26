package com.zhsy.bean;


public class MusicBean {

    private int end;
    private int limit;
    private String music_author;
    private int music_click;
    private String music_content;
    private String music_contentShow;
    private String music_difficulty;
    private int music_flag;
    private int music_id;
    private String music_link;
    private String music_time;
    private String music_title;
    private String nick_name;
    private String real_name;
    private int start;
    private int user_id;

    private boolean playState=false;


    public MusicBean() {}

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getMusic_author() {
        return music_author;
    }

    public void setMusic_author(String music_author) {
        this.music_author = music_author;
    }

    public int getMusic_click() {
        return music_click;
    }

    public void setMusic_click(int music_click) {
        this.music_click = music_click;
    }

    public String getMusic_content() {
        return music_content;
    }

    public void setMusic_content(String music_content) {
        this.music_content = music_content;
    }

    public String getMusic_contentShow() {
        return music_contentShow;
    }

    public void setMusic_contentShow(String music_contentShow) {
        this.music_contentShow = music_contentShow;
    }

    public String getMusic_difficulty() {
        return music_difficulty;
    }

    public void setMusic_difficulty(String music_difficulty) {
        this.music_difficulty = music_difficulty;
    }

    public int getMusic_flag() {
        return music_flag;
    }

    public void setMusic_flag(int music_flag) {
        this.music_flag = music_flag;
    }

    public int getMusic_id() {
        return music_id;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public String getMusic_link() {
        return music_link;
    }

    public void setMusic_link(String music_link) {
        this.music_link = music_link;
    }

    public String getMusic_time() {
        return music_time;
    }

    public void setMusic_time(String music_time) {
        this.music_time = music_time;
    }

    public String getMusic_title() {
        return music_title;
    }

    public void setMusic_title(String music_title) {
        this.music_title = music_title;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isPlayState() {
        return playState;
    }

    public void setPlayState(boolean playState) {
        this.playState = playState;
    }
}

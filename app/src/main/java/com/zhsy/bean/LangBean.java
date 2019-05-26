package com.zhsy.bean;

/**
 * Created by Administrator on 2017/11/19.
 */

public class LangBean {

    private int end;
    private int lang_author;
    private String lang_content;
    private String lang_contentShow;
    private String lang_difficulty;
    private int lang_flag;
    private int lang_id;
    private String lang_link;
    private String lang_time;
    private String lang_title;
    private int limit;
    private String nick_name;
    private String real_name;
    private int start;

    private boolean playState=false;

    public LangBean() {
    }

    public LangBean(int end, int lang_author, String lang_content, String lang_contentShow, String lang_difficulty, int lang_flag, int lang_id, String lang_link, String lang_time, String lang_title, int limit, String nick_name, String real_name, int start) {
        this.end = end;
        this.lang_author = lang_author;
        this.lang_content = lang_content;
        this.lang_contentShow = lang_contentShow;
        this.lang_difficulty = lang_difficulty;
        this.lang_flag = lang_flag;
        this.lang_id = lang_id;
        this.lang_link = lang_link;
        this.lang_time = lang_time;
        this.lang_title = lang_title;
        this.limit = limit;
        this.nick_name = nick_name;
        this.real_name = real_name;
        this.start = start;
    }


    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getLang_author() {
        return lang_author;
    }

    public void setLang_author(int lang_author) {
        this.lang_author = lang_author;
    }

    public String getLang_content() {
        return lang_content;
    }

    public void setLang_content(String lang_content) {
        this.lang_content = lang_content;
    }

    public String getLang_contentShow() {
        return lang_contentShow;
    }

    public void setLang_contentShow(String lang_contentShow) {
        this.lang_contentShow = lang_contentShow;
    }

    public String getLang_difficulty() {
        return lang_difficulty;
    }

    public void setLang_difficulty(String lang_difficulty) {
        this.lang_difficulty = lang_difficulty;
    }

    public int getLang_flag() {
        return lang_flag;
    }

    public void setLang_flag(int lang_flag) {
        this.lang_flag = lang_flag;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public String getLang_link() {
        return lang_link;
    }

    public void setLang_link(String lang_link) {
        this.lang_link = lang_link;
    }

    public String getLang_time() {
        return lang_time;
    }

    public void setLang_time(String lang_time) {
        this.lang_time = lang_time;
    }

    public String getLang_title() {
        return lang_title;
    }

    public void setLang_title(String lang_title) {
        this.lang_title = lang_title;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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

    public boolean isPlayState() {
        return playState;
    }

    public void setPlayState(boolean playState) {
        this.playState = playState;
    }
}

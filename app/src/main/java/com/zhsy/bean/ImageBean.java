package com.zhsy.bean;


public class ImageBean {

    private int end;
    private int limit;
    private String math_chinese;
    private int math_id;
    private String math_link;
    private String math_start;
    private String math_value;
    private String math_zang;
    private int start;


    public ImageBean() {
    }

    public ImageBean(int end, int limit, String math_chinese, int math_id, String math_link, String math_start, String math_value, String math_zang, int start) {
        this.end = end;
        this.limit = limit;
        this.math_chinese = math_chinese;
        this.math_id = math_id;
        this.math_link = math_link;
        this.math_start = math_start;
        this.math_value = math_value;
        this.math_zang = math_zang;
        this.start = start;
    }


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

    public String getMath_chinese() {
        return math_chinese;
    }

    public void setMath_chinese(String math_chinese) {
        this.math_chinese = math_chinese;
    }

    public int getMath_id() {
        return math_id;
    }

    public void setMath_id(int math_id) {
        this.math_id = math_id;
    }

    public String getMath_link() {
        return math_link;
    }

    public void setMath_link(String math_link) {
        this.math_link = math_link;
    }

    public String getMath_start() {
        return math_start;
    }

    public void setMath_start(String math_start) {
        this.math_start = math_start;
    }

    public String getMath_value() {
        return math_value;
    }

    public void setMath_value(String math_value) {
        this.math_value = math_value;
    }

    public String getMath_zang() {
        return math_zang;
    }

    public void setMath_zang(String math_zang) {
        this.math_zang = math_zang;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }
}

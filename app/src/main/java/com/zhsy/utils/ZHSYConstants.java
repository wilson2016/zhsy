package com.zhsy.utils;


public class ZHSYConstants {

    /***接口链接
     * http://[2001:da8:270:2018:f816:3eff:fec6:8dc7]:8080/PreEduFinal/app_about.action
     * http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/PreEduFinal/app_maths.action
     *http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/PreEduFinal/PreEduFinal/app_music.action
     * http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/PreEduFinal/app_langbase.action
     * http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/PreEduFinal/app_sps.action
     */

    //public static final String APP_HOST="http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/";//主机接口
    public static final String APP_HOST="http://47.95.225.44:8080/";
    //public static final String APP_HOST="http://[2001:da8:270:2018:f816:3eff:fe10:38a4]:8080/";//主机接口
//    public static final String APP_ABOUT_URL=APP_HOST+"PreEduFinal/app_about.action";//关于主机接口
//    public static final String APP_MATHS_URL=APP_HOST+"PreEduFinal/app_maths.action";//乘法表主机接口
//    public static final String APP_MUSICS_URL=APP_HOST+"PreEduFinal/app_music.action?";//音乐主机接口
//    public static final String APP_LANGBASE_URL=APP_HOST+"PreEduFinal/app_langbase.action?";//语言基础主机接口
//    public static final String APP_SPS_URL = APP_HOST+"PreEduFinal/app_sps.action?";//科普主机接口


    public static final String APP_ABOUT_URL=APP_HOST+"PreEdu/app_about.action";//关于主机接口
    public static final String APP_MATHS_URL=APP_HOST+"PreEdu/app_maths.action";//乘法表主机接口
    public static final String APP_MUSICS_URL=APP_HOST+"PreEdu/app_music.action?";//音乐主机接口
    public static final String APP_LANGBASE_URL=APP_HOST+"PreEdu/app_langbase.action?";//语言基础主机接口
    public static final String APP_SPS_URL = APP_HOST+"PreEdu/app_sps.action?";//科普主机接口

    public static final String PAGE_LIMIT="&limit=10";//每页限制条数

    public static final String THEME_CONFIG="theme_config";//主题的配置参数
}

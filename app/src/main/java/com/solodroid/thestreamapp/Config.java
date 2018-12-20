package com.solodroid.thestreamapp;

public class Config {

    //your admin panel url
    public static final String ADMIN_PANEL_URL = "http://desenhosonline24hs.pxstream.tv";

    //your api key which obtained from admin panel
    public static final String API_KEY = "cda11XdHyCgbj1lxV2oMYIaGfe5U7c6z4RNBstnQim0Er3pWZD";

    //Ads Configuration
    //set true to enable or set false to disable
    public static final boolean ENABLE_ADMOB_BANNER_ADS = true;

    public static final boolean ENABLE_ADMOB_INTERSTITIAL_ADS = true;
    public static final int ADMOB_INTERSTITIAL_ADS_INTERVAL = 3;

    public static final boolean ENABLE_ADMOB_NATIVE_ADS_CHANNEL_LIST = true;
    public static final boolean ENABLE_ADMOB_NATIVE_ADS_CHANNEL_DETAIL = true;
    public static final int ADMOB_NATIVE_ADS_INTERVAL = 8;

    public static final boolean ENABLE_STARTAPP_SPLASH_ADS = false;
    public static final boolean ENABLE_STARTAPP_INTERSTITIAL_EXIT_ADS = false;

    //set true to enable tab layout or set false to disable tab layout
    public static final boolean ENABLE_TAB_LAYOUT = true;

    //if you use RTL Language e.g : Arabic Language or other, set true
    public static final boolean ENABLE_RTL_MODE = false;

    //load more for next channel list
    public static final int LOAD_MORE = 20;

    //splash screen duration in millisecond
    public static final int SPLASH_TIME = 3000;

}

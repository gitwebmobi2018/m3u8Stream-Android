package com.solodroid.thestreamapp.utils;

import com.solodroid.thestreamapp.Config;

public class Constant {

    public static final String EXTRA_OBJC = "key.EXTRA_OBJC";
    public static final String KEY_CHANNEL_CATEGORY = "key.CHANNEL_CATEGORY";
    public static final String KEY_CHANNEL_ID = "key.CHANNEL_ID";
    public static final String KEY_CHANNEL_NAME = "key.CHANNEL_NAME";
    public static final String KEY_CHANNEL_IMAGE = "key.CHANNEL_IMAGE";
    public static final String KEY_CHANNEL_URL = "key.CHANNEL_URL";
    public static final String KEY_CHANNEL_DESCRIPTION = "key.CHANNEL_DESCRIPTION";

    //push notification
    public static final String TOKEN_URL = Config.ADMIN_PANEL_URL + "/register.php";
    public static final String TOPIC_GLOBAL = "global";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    public static final String SHARED_PREF = "ah_firebase";
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;

    //other
    public static final long DELAY_TIME = 250;
    public static final int MAX_SEARCH_RESULT = 100;

}
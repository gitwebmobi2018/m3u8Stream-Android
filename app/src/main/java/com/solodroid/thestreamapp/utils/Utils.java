package com.solodroid.thestreamapp.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class Utils {

    public static String getFacebookUrl(FragmentActivity activity, String facebook_url) {
        if (activity == null || activity.isFinishing()) return null;

        PackageManager packageManager = activity.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                Log.d("facebook api", "new");
                return "fb://facewebmodal/f?href=" + facebook_url;
            } else { //older versions of fb app
                Log.d("facebook api", "old");
                return "fb://page/" + splitUrl(activity, facebook_url);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("facebook api", "exception");
            return facebook_url; //normal web url
        }
    }

    public static String splitUrl(Context context, String url) {
        if (context == null) return null;
        Log.d("Split string: ", url + " ");
        try {
            String splittedUrl[] = url.split(".com/");
            Log.d("Split string: ", splittedUrl[1] + " ");
            return splittedUrl.length == 2 ? splittedUrl[1] : url;
        } catch (Exception ex) {
            return url;
        }
    }

//    click listener

//                    String url ="https://www.facebook.com/solodroid.net";
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    String facebookUrl = Utils.getFacebookUrl(this, url);
//                    intent.setData(Uri.parse(facebookUrl));
//                    startActivity(intent);

}

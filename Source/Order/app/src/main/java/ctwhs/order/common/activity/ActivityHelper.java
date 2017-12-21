package ctwhs.order.common.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.util.Locale;

import ctwhs.common.utils.ConvertUtils;
import ctwhs.order.constant.ConstActivity;

/**
 * Created by UNGTQ on 3/9/2017.
 */

public class ActivityHelper {
    private static Bundle bundleLang;

    /**
     * set Languages Bundle
     * @param lang
     */
    public static void setLanguageToBundle(String lang){
        if(ConvertUtils.nullOrBlank(bundleLang)){
            bundleLang = new Bundle();
        }

        bundleLang.putString(ConstActivity.CONST_ACTIVITY_LANG, lang);
    }

    /**
     * get Bundles languages
     * @return
     */
    public static Bundle getBundleLang(){
        return bundleLang;
    }

    /**
     * set view languages
     * @param lang
     */
    public static void setViewLanguages(String lang, FragmentActivity activity, int layoutResID){
        try{
            String languageToLoad  = lang; // your language
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            activity.getBaseContext().getResources().updateConfiguration(config,
                    activity.getBaseContext().getResources().getDisplayMetrics());
            activity.setContentView(layoutResID);
            activity.recreate();

            //save to Bundles
            setLanguageToBundle(lang);
        } catch (Exception ex) {
            Log.e("1", ex.getMessage());
        }
    }
}

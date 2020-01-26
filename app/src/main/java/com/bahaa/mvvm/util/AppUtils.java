package com.bahaa.mvvm.util;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

public class AppUtils {
    public static void setLanguage(String language, Activity from){
        Resources activityRes = from.getResources();
        Configuration activityConf = activityRes.getConfiguration();
        Locale newLocale = new Locale(language);
        activityConf.setLocale(newLocale);
        activityRes.updateConfiguration(activityConf, activityRes.getDisplayMetrics());

        Resources applicationRes = from.getApplicationContext().getResources();
        Configuration applicationConf = applicationRes.getConfiguration();
        applicationConf.setLocale(newLocale);
        applicationRes.updateConfiguration(applicationConf,
                applicationRes.getDisplayMetrics());
    }
    public static void setLanguageWithoutReload(String language, Activity from, Class to){
        Locale languageLocale = new Locale(language);
        Locale.setDefault(languageLocale);
        Configuration languageConfig = new Configuration();
        languageConfig.locale = languageLocale;
        from.getResources().updateConfiguration(languageConfig, from.getResources().getDisplayMetrics());
    }

}

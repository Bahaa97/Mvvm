package com.bahaa.mvvm.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
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

    public void fillSpinner(Context context,List list, Spinner spinner) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }
    public void hideKeyboard(Activity context) {
        try {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (context.getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            Log.e("MultiBackStack", "Failed to add fragment to back stack", e);
        }
    }
}

package com.huijian.tui.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import androidx.annotation.RequiresApi;

import java.util.Locale;

public class LanguageUtil {

    @SuppressLint("ObsoleteSdkInt")
    public static void changeAppLanguage(Context context, Locale newLanguage){
        if (TextUtils.isEmpty(newLanguage.getLanguage())){
            return;
        }
        Resources resources=context.getResources();
        Configuration configuration= resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLocale(newLanguage);
        }else {
            configuration.locale=newLanguage;
        }
        DisplayMetrics displayMetrics= resources.getDisplayMetrics();
        resources.updateConfiguration(configuration,displayMetrics);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Context createResources(Context context, String newLanguage){
        Resources resources=context.getResources();
        Configuration configuration= resources.getConfiguration();
        Locale locale=getLocaleByLanguage(newLanguage);
        configuration.setLocale(locale);
        configuration.setLocales(new LocaleList(locale));
        return context.createConfigurationContext(configuration);
    }

    private static Locale getLocaleByLanguage(String newLanguage){
        if (newLanguage.equals("en")){
            return Locale.ENGLISH;
        }else if (newLanguage.equals("ja")){
            return Locale.JAPAN;
        }else {
            return Locale.SIMPLIFIED_CHINESE;
        }
    }

}

package com.example.ddopik.phlogbusiness.Utiltes;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Ayman Abouzeid on 6/12/17.
 */

public abstract class PrefUtils {
    public static final String ARABIC_LANG = "ar";
    public static final String ENGLISH_LANG = "en";
    private static final String LOGIN_PROVIDER = "LOGIN_PROVIDER";
    private static final String NOTIFICATION_TOKEN = "NOTIFICATION_TOKEN";
    private static final String IS_FIRST_LAUNCH = "IS_FIRST_LAUNCH";
    private static final String IS_LANGUAGE_SELECTED = "IS_LANGUAGE_SELECTED";
    private static final String IS_TOKEN_SAVED = "IS_TOKEN_SAVED";
    private static final String APP_LANG = "APP_LANG";


    private static final String IS_SETUP_BRAND = "is_setup_brand";
    private static final String IS_PHONE_VERIFIED = "is_phone_verified";
    private static final String IS_MAIL_VERIFIED = "is_email_verified";

    private static final String BRAND_STATUS = "brand_status";

    private static final String BRAND_HASH = "hash";
    private static final String BRAND_TOKEN = "token";

    private static final String BRAND_ID = "id";

    private static final String IS_BRAND = "is_brand";
    private static final String IS_BRAND_TEXT = "is_brand_text";


    private static String PREF_FILE_NAME;

    public PrefUtils() {
        PREF_FILE_NAME = getProjectName();
    }

    private static SharedPreferences getSharedPref(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
    }

    public static void setBrandID(Context context, String userID) {
        getSharedPref(context).edit().putString(BRAND_ID, userID).apply();
    }

    public static void setBrandToken(Context context, String userToken) {
        getSharedPref(context).edit().putString(BRAND_TOKEN, userToken).apply();
    }

    public static void setNotificationToken(Context context, String notificationToken) {
        getSharedPref(context).edit().putString(NOTIFICATION_TOKEN, notificationToken).apply();
    }

    public static void setIsFirstLaunch(Context context, boolean isFirstLaunch) {
        getSharedPref(context).edit().putBoolean(IS_FIRST_LAUNCH, isFirstLaunch).apply();
    }


    public static String getBrandID(Context mContext) {
        return getSharedPref(mContext).getString(BRAND_ID, "");
    }

    public static String getBrandToken(Context mContext) {
        return getSharedPref(mContext).getString(BRAND_TOKEN, "");
//        return "ac99b777bf0d1e58e8e7cd8653da52f5";
    }

    public static String getNotificationToken(Context mContext) {
        return getSharedPref(mContext).getString(NOTIFICATION_TOKEN, "");
    }

    public static boolean isFirstLaunch(Context context) {
        return getSharedPref(context).getBoolean(IS_FIRST_LAUNCH, true);
    }

    public static String getAppLang(Context context) {
        return getSharedPref(context).getString(APP_LANG, ARABIC_LANG);
    }

    public static void setAppLang(Context context, String appLang) {
        getSharedPref(context).edit().putString(APP_LANG, appLang).apply();
    }

    public static boolean isLanguageSelected(Context context) {
        return getSharedPref(context).getBoolean(IS_LANGUAGE_SELECTED, false);
    }

    public static void setIsLanguageSelected(Context context, boolean isSelected) {
        getSharedPref(context).edit().putBoolean(IS_LANGUAGE_SELECTED, isSelected).apply();
    }

    public static boolean isTokenSaved(Context context) {
        return getSharedPref(context).getBoolean(IS_TOKEN_SAVED, false);
    }

    public static void setLoginState(Context context, boolean state) {
        getSharedPref(context).edit().putBoolean(LOGIN_PROVIDER, state).apply();

    }


    public static void setBrandSetUup(Context context, boolean state) {
        getSharedPref(context).edit().putBoolean(IS_SETUP_BRAND, state).apply();
    }

    public static String getBrandSetUp(Context context) {
        return getSharedPref(context).getString(IS_SETUP_BRAND, ARABIC_LANG);
    }


    public static void setIsPhoneVerified(Context context, boolean state) {
        getSharedPref(context).edit().putBoolean(IS_PHONE_VERIFIED, state).apply();
    }

    public static String getIsPhoneVerified(Context context) {
        return getSharedPref(context).getString(IS_PHONE_VERIFIED, ARABIC_LANG);
    }

    public static void setIsMailVerified(Context context, boolean state) {
        getSharedPref(context).edit().putBoolean(IS_MAIL_VERIFIED, state).apply();
    }

    public static String getIsMailVerified(Context context) {
        return getSharedPref(context).getString(IS_MAIL_VERIFIED, ARABIC_LANG);
    }

    public static void setBrandStatus(Context context, int state) {
        getSharedPref(context).edit().putInt(BRAND_STATUS, state).apply();
    }

    public static int getBrandStatus(Context context) {
        return getSharedPref(context).getInt(BRAND_STATUS, 0);
    }

    public static void setBrandHash(Context context, String hash) {
        getSharedPref(context).edit().putString(BRAND_HASH, hash).apply();
    }

    public static String getBrandHash(Context context) {
        return getSharedPref(context).getString(BRAND_HASH, "");
    }


    public static boolean setIsBrand(Context context) {
        return getSharedPref(context).getBoolean(IS_BRAND, false);
    }

    public static boolean getIsBrand(Context context) {
        return getSharedPref(context).getBoolean(IS_BRAND, false);
    }


    public static String setIsBrand(Context context) {
        return getSharedPref(context).getBoolean(IS_BRAND_TEXT, false);
    }

    public static String getBrandText(Context context) {
        return getSharedPref(context).getString(IS_BRAND_TEXT, "");
    }














    public static boolean isLoginProvided(Context context) {
        return getSharedPref(context).getBoolean(IS_BRAND, false);
    }


    public static void firstTimeAskingPermission(Context context, String permission, boolean isFirstTime) {
        SharedPreferences sharedPreference = context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
        sharedPreference.edit().putBoolean(permission, isFirstTime).apply();
    }

    public static boolean isFirstTimeAskingPermission(Context context, String permission) {
        return context.getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE).getBoolean(permission, true);
    }


    public String getProjectName() {
        return PREF_FILE_NAME;
    }

    public abstract void setPrefFileName(String projectName);
}

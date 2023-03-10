package com.fin.fininfocom.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Regeti Jhansi Rani  on 09-03-2023.
 */
public class MySharedPreferences {
    private final SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;
    public static String file_name = "filename";
    public static String login_status= "login_status";
    public static String user_name= "user_name";


    public MySharedPreferences(Context context) {
        mPrefs = context.getSharedPreferences(file_name, Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }
    public void setLoginStatus(boolean status){
        mEditor.putBoolean(login_status, status);
        mEditor.commit();
    }
    public  boolean getLoginStatus(){
        return mPrefs.getBoolean(login_status, false);
    }
    public void setUserName(String userName){
        mEditor.putString(user_name, userName);
        mEditor.commit();
    }
    public String getUserName(){
        return mPrefs.getString(user_name,"");
    }
    public void logoutUser() {
        mEditor.clear();
        mEditor.commit();
    }
}

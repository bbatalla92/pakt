package btech.pakt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.facebook.login.LoginManager;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.listeners.OnLogoutListener;

/**
 * Created by Brennan on 11/7/2015.
 */
public class SharedPrefs {

    private static final String LOGGEDIN = "loggedIn";
    private static final String FIRST_NAME= "fName";
    private static final String MAIN_PIC= "mainPic";
    private static final String COVER_PIC = "coverURL";

    private static  final String PREFS_NAME = "sharedPrefs";
    private static  final String TAG = "sharedPrefs";


    SimpleFacebook  mSimpleFacebook;

    private static SharedPreferences settingsPrefs;

    public SharedPrefs(Context context){
        settingsPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    }

    public void setLoggedIn(boolean logged){
        SharedPreferences.Editor editor = settingsPrefs.edit();
        editor.putBoolean(LOGGEDIN, logged);
        editor.apply();
    }



    public void setFirstName(String fName){
        SharedPreferences.Editor editor = settingsPrefs.edit();
        editor.putString(FIRST_NAME, fName);
        editor.apply();
    }


    public void setProPic(String url){
        SharedPreferences.Editor editor = settingsPrefs.edit();
        editor.putString(MAIN_PIC, url);
        editor.apply();
    }


    public void setCoverURL(String urll){
        SharedPreferences.Editor editor = settingsPrefs.edit();
        editor.putString(COVER_PIC, urll);
        editor.apply();
    }

    public boolean checkLoggedIn(){

        return settingsPrefs.getBoolean(LOGGEDIN, false);
    }


    public String getFirstName(){

        return settingsPrefs.getString(FIRST_NAME, "");
    }



    public String getProPic(){

        return settingsPrefs.getString(MAIN_PIC, "");
    }



    public String getCoverURL(){

        return settingsPrefs.getString(COVER_PIC,"");
    }



    public void logOut(){
        SharedPreferences.Editor editor = settingsPrefs.edit();
        editor.putBoolean(LOGGEDIN, false);
        editor.apply();

        OnLogoutListener onLogoutListener = new OnLogoutListener() {

            @Override
            public void onLogout() {
                Log.i(TAG, "You are logged out");
            }

        };
        mSimpleFacebook = SimpleFacebook.getInstance();
        mSimpleFacebook.logout(onLogoutListener);

        LoginActivity.ref.unauth();

    }



}

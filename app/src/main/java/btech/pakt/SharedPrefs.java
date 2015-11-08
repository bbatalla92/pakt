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
    private static final String FB_TOKEN = "accessToken";
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

    public boolean checkLoggedIn(){

        return settingsPrefs.getBoolean(LOGGEDIN, false);
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


    }



}

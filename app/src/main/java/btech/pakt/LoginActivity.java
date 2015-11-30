package btech.pakt;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.sromku.simple.fb.utils.Attributes;
import com.sromku.simple.fb.utils.PictureAttributes;

import java.io.Serializable;
import java.util.List;
import java.util.logging.LogRecord;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    static final String TAG = "LOGIN ACTIVITY";
    SharedPrefs sharedPrefs;
    UserData user;

    Handler handlerTimer;

    //Facebook permissions
    Permission[] permissions = new Permission[] {
            Permission.USER_PHOTOS,
            Permission.EMAIL,
            Permission.PUBLISH_ACTION,
            Permission.USER_ABOUT_ME
    };

    SimpleFacebook mSimpleFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mSimpleFacebook = SimpleFacebook.getInstance(this);
        sharedPrefs = new SharedPrefs(this);

        handlerTimer = new Handler();

        if(sharedPrefs.checkLoggedIn()){

            getUserData();


        }

        sharedPrefs =  new SharedPrefs(getApplicationContext());
        loginButton = (Button) findViewById(R.id.login_button);



        SimpleFacebookConfiguration configuration = new SimpleFacebookConfiguration.Builder()
                .setAppId(String.valueOf(R.string.facebook_app_id))
                .setNamespace("sromkuapp")
                .setPermissions(permissions)
                .build();

        SimpleFacebook.setConfiguration(configuration);


            final OnLoginListener onLoginListener = new OnLoginListener() {

            @Override
            public void onLogin(String accessToken, List<Permission> acceptedPermissions, List<Permission> declinedPermissions) {
                // change the state of the button or do whatever you want
                Log.i(TAG, "Logged in");
                getUserData();
                sharedPrefs.setLoggedIn(true);




            }

            @Override
            public void onCancel() {
                // user canceled the dialog
                sharedPrefs.setLoggedIn(false);
            }

            @Override
            public void onFail(String reason) {
                // failed to login
                sharedPrefs.setLoggedIn(false);
            }

            @Override
            public void onException(Throwable throwable) {
                // exception from facebook
            }

        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSimpleFacebook.login(onLoginListener);
            }
        });




    }



    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mSimpleFacebook.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult");
        nextActivity();
    }

    public void nextActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                finish();
            }
        }, 300);


    }

    public void getUserData(){

        PictureAttributes pictureAttributes = Attributes.createPictureAttributes();
        pictureAttributes.setHeight(500);
        pictureAttributes.setWidth(500);
        pictureAttributes.setType(PictureAttributes.PictureType.SMALL);

        Profile.Properties properties = new Profile.Properties.Builder()
                .add(Profile.Properties.FIRST_NAME)
                .add(Profile.Properties.COVER)
                .add(Profile.Properties.PICTURE, pictureAttributes)
                .add(Profile.Properties.LOCATION)
                .build();



        OnProfileListener onProfileListener = new OnProfileListener() {
            @Override
            public void onComplete(Profile profile) {

                    sharedPrefs.setFirstName(profile.getFirstName());

                    sharedPrefs.setProPic(profile.getPicture());

                    sharedPrefs.setCoverURL(profile.getCover().getSource());


            }

        };

        mSimpleFacebook.getProfile(properties, onProfileListener);

        Log.i(TAG + " P_url", sharedPrefs.getProPic());
        Log.i(TAG, sharedPrefs.getFirstName());
        Log.i(TAG + " C_url", ": " + sharedPrefs.getCoverURL());

        if (sharedPrefs.checkLoggedIn())
            nextActivity();
    }

}

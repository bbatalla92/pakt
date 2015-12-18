package btech.pakt;

import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.melnykov.fab.FloatingActionButton;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.entities.Profile;
import com.sromku.simple.fb.listeners.OnLoginListener;
import com.sromku.simple.fb.listeners.OnProfileListener;
import com.sromku.simple.fb.utils.Attributes;
import com.sromku.simple.fb.utils.PictureAttributes;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.LogRecord;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    static final String TAG = "LOGIN ACTIVITY";
    SharedPrefs sharedPrefs;
    public static Firebase ref;
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();

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
        Firebase.setAndroidContext(this);

        fireSetup();

        mSimpleFacebook = SimpleFacebook.getInstance(this);
        sharedPrefs = new SharedPrefs(this);
        Log.i(TAG + "Logged In", ""+sharedPrefs.checkLoggedIn());
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

                onFacebookAccessTokenChange(accessToken);




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

    public void fireSetup(){
        FireBaseAPI fireAPI = new FireBaseAPI();
        ref = new Firebase(fireAPI.getBase());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(), dataSnapshot.getValue().toString(),Toast.LENGTH_LONG ).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(),"Firebase: "+ firebaseError.getMessage(),Toast.LENGTH_LONG ).show();

            }
        });

        Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {

            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {

            }
        };
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

    private void onFacebookAccessTokenChange(String token){

        if (token != null) {
            ref.authWithOAuthToken("facebook", token, new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) {
                    // The Facebook user is now authenticated with your Firebase app

                    // Authentication just completed successfully :)
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("provider", authData.getProvider());
                    map.put("bio","");
                    map.put("lastLogin",dateFormat.format(date));
                    if(authData.getProviderData().containsKey("displayName")) {
                        map.put("displayName", authData.getProviderData().get("displayName").toString());
                    }
                    ref.child("users").child(authData.getUid()).setValue(map);

                    sharedPrefs.setLoggedIn(true);
                }
                @Override
                public void onAuthenticationError(FirebaseError error) {
                    // there was an error
                    Log.e(TAG+"FIRE", error.getMessage());
                    switch (error.getCode()) {
                        case FirebaseError.USER_DOES_NOT_EXIST:
                            // handle a non existing user
                            break;
                        case FirebaseError.INVALID_PASSWORD:
                            // handle an invalid password
                            break;
                        default:
                            // handle other errors
                            break;
                    }
                }
            });
        } else {
        /* Logged out of Facebook so do a logout from the Firebase app */
            ref.unauth();
        }
    }

}

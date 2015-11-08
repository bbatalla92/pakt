package btech.pakt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.sromku.simple.fb.Permission;
import com.sromku.simple.fb.SimpleFacebook;
import com.sromku.simple.fb.SimpleFacebookConfiguration;
import com.sromku.simple.fb.listeners.OnLoginListener;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Button loginButton;
    CallbackManager callbackManager;
    static final String TAG = "LogIn Activity";
    SharedPrefs sharedPrefs;

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

        if(sharedPrefs.checkLoggedIn()){
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            finish();
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
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        finish();
    }


}

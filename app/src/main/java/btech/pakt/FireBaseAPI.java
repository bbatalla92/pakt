package btech.pakt;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.sromku.simple.fb.entities.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brennan on 12/5/2015.
 */
public class FireBaseAPI {

    Map map;
    private final String TAG = "FireBase API";
    UserData user = null;

    private static String BASE = "https://pakt-url.firebaseio.com/";

    public FireBaseAPI(){

    }

    public FireBaseAPI(Map map){
        this.map = map;
    }

    public String getBase(){
        return BASE;
    }

    public String getUsersURL(){
        return BASE+"users";
    }

    public void updateSingleChild(Firebase ref, String key, Object value){
        Map<String, Object> map = new HashMap<>();
        map.put(key, value);
        ref.updateChildren(map);
    }

    public void updateChildren(Firebase ref, Map<String, Object> map){
        ref.updateChildren(map);
    }

    public UserData getUserData(final String authID) {

        Firebase userRef = new Firebase(getUsersURL()+"/"+authID);



        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(UserData.class);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.i(TAG, "Firebase Error: " + firebaseError.getMessage());
            }
        });




        return user;
    }

    public void postMessage(Firebase ref){

    }

}



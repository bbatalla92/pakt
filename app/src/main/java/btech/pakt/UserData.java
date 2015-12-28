package btech.pakt;

import com.sromku.simple.fb.entities.Photo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Brennan on 11/7/2015.
 */
public class UserData {

    public String displayName;
    public String profileImageURL;
    public String coverImageURL;
    public String lastLogin;
    public String provider;
    public String bio;
    public Map<String, String> conversationKeys;

    public UserData(String name,
                    String profilePic,
                    String coverPic) {

        this.displayName = name;
        this.profileImageURL = profilePic;
        this.coverImageURL = coverPic;
    }

    public UserData() {

    }

    public ArrayList<String> getConversationKeys() {
            ArrayList<String> conKeys = new ArrayList<>();
        if (conversationKeys != null && !conversationKeys.isEmpty()) {
            for (Map.Entry<String, String> entry : conversationKeys.entrySet()) {
                System.out.println(entry.getKey() + "/" + entry.getValue());
                conKeys.add(entry.getValue());
            }
        }

        return conKeys;
    }

    public void setConversationKeys(Map<String, String> conversationKeys) {
        this.conversationKeys = conversationKeys;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLastLogin() {
        return lastLogin;
    }



    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getCoverImageURL() {
        return coverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        this.coverImageURL = coverImageURL;
    }

    public String getDisplayName() {
        return displayName;
    }


    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

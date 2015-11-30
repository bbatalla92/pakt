package btech.pakt;

import com.sromku.simple.fb.entities.IdName;
import com.sromku.simple.fb.entities.Photo;

/**
 * Created by Brennan on 11/7/2015.
 */
public class UserData {

    public String name;
    public String location;
    public String profilePic;
    public Photo coverPic;

    public UserData(String name,
                    String location,
                    String profilePic,
                    Photo coverPic) {

        this.name = name;
        this.location = location;
        this.profilePic = profilePic;
        this.coverPic = coverPic;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Photo getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(Photo coverPic) {
        this.coverPic = coverPic;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}

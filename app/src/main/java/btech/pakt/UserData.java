package btech.pakt;

/**
 * Created by Brennan on 11/7/2015.
 */
public class UserData {

    public String name;
    public String location;

    public UserData(String name, String location){
        this.name = name;
        this.location = location;
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

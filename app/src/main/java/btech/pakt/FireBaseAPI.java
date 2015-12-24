package btech.pakt;

import java.util.Map;

/**
 * Created by Brennan on 12/5/2015.
 */
public class FireBaseAPI {

    Map map;

    private static String BASE = "https://pakt-url.firebaseio.com/";

    public FireBaseAPI(){

    }

    public FireBaseAPI(Map map){
        this.map = map;
    }

    public String getBase(){
        return BASE;
    }



}

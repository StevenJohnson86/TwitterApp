package Model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by steven on 9/16/17.
 */

public class SJUser {
    private static final String TAG = "SJUser";
    public String name, profileImg, location;

    public SJUser(JSONObject j){
        try {
            this.name = j.getString("name");
            this.profileImg = j.getString("profile_image_url_https");
            this.location = j.getString("location");
        } catch(Exception e){
            Log.d(TAG, "SJUser: Exception - " + e);
        }
    }
}

package Model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by steven on 9/16/17.
 */

public class SJTweet {
    private static final String TAG = "SJTweet";
    String text, id;
    SJUser user;

    public SJTweet(JSONObject j){
        try {
            this.text = j.getString("text");
            this.id = j.getString("id_str");
            this.user = new SJUser(j.getJSONObject("user"));
        } catch(Exception e){
            Log.d(TAG, "SJTweet: Exception - " + e);
        }
    }
}

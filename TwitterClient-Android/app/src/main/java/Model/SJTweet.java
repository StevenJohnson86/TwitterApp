package Model;

import android.util.Log;

import com.twitter.sdk.android.core.models.Tweet;

import org.json.JSONObject;

/**
 * Created by steven on 9/16/17.
 */

public class SJTweet {
    public static Tweet selectedTweet;
    private static final String TAG = "SJTweet";
    public String text, id;
    public SJUser user;

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

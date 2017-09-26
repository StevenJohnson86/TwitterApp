package Model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by steven on 9/16/17.
 */

public class SJJson {
    private static final String TAG = "SJJson";
    private static String jsonString;
    private static ArrayList<SJTweet> tweets = new ArrayList<SJTweet>();

    public static void jsonAsString(Context context){


        try {

            InputStream stream = context.getAssets().open("Tweets.json");
            byte[] buffer = new byte[stream.available()];
            stream.read(buffer);
            stream.close();
            jsonString = new String(buffer);
        } catch(Exception e){
            Log.d(TAG, "jsonAsString: Exception - " + e);
        }
    }

    public static ArrayList<SJTweet> parseTweets(){

        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            for(int i = 0; i < jsonArray.length(); i++){
                SJTweet t = new SJTweet(jsonArray.getJSONObject(i));
                tweets.add(t);
            }

        } catch(Exception e){
            Log.d(TAG, "parseJsonString: Exception - " + e);
        }
        return tweets;

    }
}

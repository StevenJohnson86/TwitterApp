package sjohnsoncf.twitterclient_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import Model.SJJson;

public class HomeTimelineActivity extends AppCompatActivity {
    private static final String TAG = "HomeTimelineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);
        SJJson test = new SJJson();
        test.jsonAsString(this);
        test.parseTweets();
//        Log.d(TAG, "onCreate: tweets ArrayList: " + t);
    }

    
}

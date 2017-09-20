package sjohnsoncf.twitterclient_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Model.SJJson;
import Model.SJTweet;
import Model.TweetAdapter;

public class HomeTimelineActivity extends AppCompatActivity {
    private static final String TAG = "HomeTimelineActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);
        getTweets();

    }

    public void getTweets(){
        ListView homeListView = (ListView) findViewById(R.id.HomeListView);
        SJJson.jsonAsString(this);
        ArrayList<SJTweet> tweets = SJJson.parseTweets();

//        ArrayAdapter<String> myAdapter = new ArrayAdapter<Stringt>(this, android.R.layout.simple_list_item_1, tweets);
        TweetAdapter myAdapter = new TweetAdapter(this,R.layout.tweet_view_item, R.id.tweet_text, tweets);
        homeListView.setAdapter(myAdapter);

        homeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LinearLayout lin = (LinearLayout) view;
                TextView t = (TextView) lin.findViewById(R.id.tweet_text);
                Log.d(TAG, "onItemClick: Tweet - " + t.getText());

            }
        });
    }

//    public void logTweet(View view){
//
//        Log.d(TAG, "logTweet: Tweet - " + view);
//
//    }


}

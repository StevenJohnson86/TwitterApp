package sjohnsoncf.twitterclient_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import java.util.ArrayList;

import Model.SJJson;
import Model.SJTweet;
import Model.TweetAdapter;

public class HomeTimelineActivity extends AppCompatActivity {
    private static final String TAG = "HomeTimelineActivity";
    private ListView tweetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_timeline);
        Twitter.initialize(this);
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        if(session != null) {
//            getTweets();
            setupUserTimelineList();
        } else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        getTweets();
        setupUserTimelineList();
    }

    private void setupUserTimelineList(){

        UserTimeline userTimeline = new UserTimeline.Builder().build();
        TweetTimelineListAdapter userTimelineAdapter = new TweetTimelineListAdapter(this, userTimeline);

        tweetListView = (ListView) findViewById(R.id.HomeListView);
        tweetListView.setAdapter(userTimelineAdapter);

//        Log.d(TAG, "setupUserTimelineList: **LISTLENGTH** " + userTimelineAdapter.getCount());

    }

    public void logoutUser(View view){

        try {
            TwitterCore.getInstance().getSessionManager().clearActiveSession();
//        Clear cache/cookies?
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        } catch(Exception e){
            Log.d(TAG, "logoutUser: Exception-> " + e);
        }

    }
//    private void setupTimelineFeedList(){
//
//    }

    public void getTweets(){
        tweetListView = (ListView) findViewById(R.id.HomeListView);
        SJJson.jsonAsString(this);
        ArrayList<SJTweet> tweets = SJJson.parseTweets();

        TweetAdapter myAdapter = new TweetAdapter(tweets) {

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                SJTweet currentTweet = (SJTweet) this.getItem(i);

                view = getLayoutInflater().inflate(R.layout.tweet_view_item, null);

                TextView userText = (TextView) view.findViewById(R.id.tweet_user);
                TextView tweetText = (TextView) view.findViewById(R.id.tweet_text);
                userText.setText(currentTweet.user.name);
                tweetText.setText(currentTweet.text);
                return view;
            }
        };
        tweetListView.setAdapter(myAdapter);

        tweetListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout itemContainer = (RelativeLayout) view;
                TextView t = (TextView) itemContainer.findViewById(R.id.tweet_text);
                Log.d(TAG, "onItemClick: Tweet - " + t.getText());

            }
        });
    }



}

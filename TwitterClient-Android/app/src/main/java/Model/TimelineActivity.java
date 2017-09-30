package Model;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.Timeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import sjohnsoncf.twitterclient_android.LoginActivity;
import sjohnsoncf.twitterclient_android.R;
import sjohnsoncf.twitterclient_android.TweetDetailActivity;

/**
 * Created by steven on 9/30/17.
 */

public abstract class TimelineActivity extends AppCompatActivity {
    private static final String TAG = "TimelineActivity";
    private ListView tweetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        setupTimeline();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupTimeline();
    }

    public void updateListViewWithList(Timeline<Tweet> tweets){
        tweetListView = (ListView) findViewById(R.id.TimelineListView);
        final Context TLActivityCtx = this;
        TweetTimelineListAdapter adapter = new TweetTimelineListAdapter(this, tweets){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position,convertView,parent);
                final Tweet mSelectedTweet = getItem(position);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SJTweet.selectedTweet = mSelectedTweet;
                        Intent detailTweetIntent = new Intent(TLActivityCtx, TweetDetailActivity.class);
                        startActivity(detailTweetIntent);
                    }
                });

                return view;
            }

        };
        tweetListView.setAdapter(adapter);
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

    public abstract void setupTimeline();
}

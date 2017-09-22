package sjohnsoncf.twitterclient_android;

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

import java.util.ArrayList;

import Model.SJJson;
import Model.SJTweet;
import Model.TweetAdapter;

public class HomeTimelineActivity extends AppCompatActivity {
    private static final String TAG = "HomeTimelineActivity";
//    private boolean tweetsInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        Log.d(TAG, "onCreate: Twitter Instance: " + Twitter.getInstance());
        setContentView(R.layout.activity_home_timeline);git add

        getTweets();
        //instantiate once? static initializer? add functionality in activity lifecycle methods?



    }

    public void getTweets(){
//        tweetsInitialized = true;
        ListView homeListView = (ListView) findViewById(R.id.HomeListView);
        SJJson.jsonAsString(this);
        ArrayList<SJTweet> tweets = SJJson.parseTweets();

        TweetAdapter myAdapter = new TweetAdapter(tweets) {

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                SJTweet currentTweet = (SJTweet) this.getItem(i);

                view = getLayoutInflater().inflate(R.layout.tweet_view_item, null);

//                ImageView profileImg = (ImageView) view.findViewById();
                TextView userText = (TextView) view.findViewById(R.id.tweet_user);
                TextView tweetText = (TextView) view.findViewById(R.id.tweet_text);
                userText.setText(currentTweet.user.name);
                tweetText.setText(currentTweet.text);
                return view;
            }
        };
        homeListView.setAdapter(myAdapter);

        homeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RelativeLayout itemContainer = (RelativeLayout) view;
                TextView t = (TextView) itemContainer.findViewById(R.id.tweet_text);
                Log.d(TAG, "onItemClick: Tweet - " + t.getText());

            }
        });
    }



}

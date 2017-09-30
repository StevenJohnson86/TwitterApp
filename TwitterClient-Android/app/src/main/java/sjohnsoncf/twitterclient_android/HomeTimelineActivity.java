package sjohnsoncf.twitterclient_android;

import android.os.Bundle;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;

import java.util.List;

import Model.TimelineActivity;
import retrofit2.Call;

public class HomeTimelineActivity extends TimelineActivity {
    private static final String TAG = "HomeTimelineActivity";

    @Override
    public void setupTimeline(){

        TwitterApiClient apiClient = TwitterCore.getInstance().getApiClient();
        Call<List<Tweet>> homeTLCall = apiClient.getStatusesService().homeTimeline(null,null,null,null,null,null,null);

        homeTLCall.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {

                FixedTweetTimeline.Builder builder = new FixedTweetTimeline.Builder().setTweets(result.data);
                FixedTweetTimeline homeTL = builder.build();
                updateListViewWithList(homeTL);
            }

            @Override
            public void failure(TwitterException exception) {

                Log.d(TAG, "failure: homeTLCall-> " + exception.getMessage());
            }
        });

    }

}

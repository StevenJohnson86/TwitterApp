package sjohnsoncf.twitterclient_android;

import android.os.Bundle;

import com.twitter.sdk.android.tweetui.UserTimeline;

import Model.SJTweet;
import Model.TimelineActivity;

public class UserTimelineActivity extends TimelineActivity {

    @Override
    public void setupTimeline() {
        UserTimeline userTimeline = new UserTimeline.Builder().userId(SJTweet.selectedTweet.user.id).build();
        updateListViewWithList(userTimeline);

    }

}

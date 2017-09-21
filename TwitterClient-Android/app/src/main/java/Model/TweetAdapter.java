package Model;

import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by steven on 9/20/17.
 */

public abstract class TweetAdapter extends BaseAdapter {
    private ArrayList<SJTweet> tweets;

    public TweetAdapter(ArrayList<SJTweet> tweets){
        super();
        this.tweets = tweets;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int i) {
        return tweets.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.valueOf(tweets.get(i).id);
    }

}

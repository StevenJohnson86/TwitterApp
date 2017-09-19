package Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sjohnsoncf.twitterclient_android.HomeTimelineActivity;
import sjohnsoncf.twitterclient_android.R;

import static sjohnsoncf.twitterclient_android.R.layout.tweet_view_item;

/**
 * Created by steven on 9/16/17.
 */

public class TweetAdapter extends ArrayAdapter{
    private Context context;
    private int resource;
    private List<SJTweet> objects;


    public TweetAdapter(Context context, int resource, int textViewResourceId, List<SJTweet> Objects){
        super(context, resource, textViewResourceId, Objects);
        this.context = context;
        this.resource = resource;
        this.objects = Objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        super.getView(position, convertView, parent);

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(resource, parent, false);

        TextView tweetUser = (TextView) row.findViewById(R.id.tweet_user);
        tweetUser.setText(objects.get(position).user.name);

        TextView tweetText = (TextView) row.findViewById(R.id.tweet_text);
        tweetText.setText(objects.get(position).text);

        return row;
    }
}


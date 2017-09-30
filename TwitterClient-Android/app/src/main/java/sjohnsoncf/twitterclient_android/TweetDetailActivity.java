package sjohnsoncf.twitterclient_android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.twitter.sdk.android.core.models.Tweet;

import java.io.InputStream;

import Model.SJTweet;

public class TweetDetailActivity extends AppCompatActivity {
    private static final String TAG = "TweetDetailActivity";

    private Tweet mSelectedTweet;

    private ImageView mImageView;
    private TextView mUsernameView;
    private TextView mTweetTextView;
    private TextView mRetweetStatus;
    private Button mViewFeedBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_detail);

        mSelectedTweet = SJTweet.selectedTweet;

        configureLayout();
    }

    public void configureLayout(){
        final Context ctx = this;
        mImageView = (ImageView) findViewById(R.id.img_view_profile);
        mUsernameView = (TextView) findViewById(R.id.text_view_tweet_username);
        mTweetTextView = (TextView) findViewById(R.id.text_view_tweet_text);
        mRetweetStatus = (TextView) findViewById(R.id.text_view_retweet);
        mViewFeedBtn = (Button) findViewById(R.id.view_feed_btn);


        mUsernameView.setText(mSelectedTweet.user.screenName);
        mTweetTextView.setText(mSelectedTweet.text);
        if(mSelectedTweet.retweeted) {
            mRetweetStatus.setVisibility(View.VISIBLE);
            mRetweetStatus.setText(R.string.retweet_true);
        }
        new DownloadImage().execute(mSelectedTweet.user.profileImageUrlHttps);

        mViewFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userTLIntent = new Intent(ctx, UserTimelineActivity.class);
                startActivity(userTLIntent);
            }
        });


    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... strings) {

            Bitmap bitmap;
            try {
                InputStream input = new java.net.URL(strings[0]).openStream();
                bitmap = BitmapFactory.decodeStream(input);
            } catch(Exception e){
                Log.d(TAG, "doInBackground: exception-> " + e);
                return null;
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }
}

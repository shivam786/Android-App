package com.mwp.movies;


import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        String posterPath = i.getStringExtra("posterPath");

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avenger)
                .showImageForEmptyUri(R.drawable.avenger)
                .showImageOnFail(R.drawable.avenger).cacheInMemory(true)
                .cacheOnDisc(true).considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
        ImageView img = (ImageView) findViewById(R.id.detailedImg);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(SecondActivity.this).build();
        imageLoader.init(config);
        imageLoader.displayImage("http://image.tmdb.org/t/p/w300" + posterPath, img, options);

        TextView id, title, adult,overView,popularity,releaseDate,average_vote,vote_count;
        id = (TextView)findViewById((R.id.detailedId));
        title = (TextView)findViewById(R.id.detailedTitle);
        overView = (TextView)findViewById(R.id.detailedOverView);
        adult = (TextView)findViewById(R.id.detailedAdult);
        popularity = (TextView)findViewById(R.id.detailedPopularity);
        releaseDate = (TextView)findViewById(R.id.detailedRelease);
        average_vote = (TextView)findViewById(R.id.detailedRating);
        vote_count = (TextView)findViewById(R.id.detailedVote);

        id.setText(i.getStringExtra("id"));
        popularity.setText(i.getStringExtra("popularity"));
        title.setText(i.getStringExtra("title"));
        overView.setText(i.getStringExtra("overView"));
        adult.setText(i.getStringExtra("adult"));
        releaseDate.setText(i.getStringExtra("releaseDate"));
        average_vote.setText(i.getStringExtra("average_vote"));
        vote_count.setText(i.getStringExtra("vote_count"));

        VideoView vid = (VideoView)findViewById(R.id.videoView);


    }


}

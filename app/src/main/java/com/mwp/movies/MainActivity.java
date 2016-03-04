package com.mwp.movies;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

	static TabHost mTabHost;

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.getTabWidget().setDividerDrawable(null);
		Intent intent; // Reusable Intent for each tab

		intent = new Intent().setClass(MainActivity.this, TopRatedActivity.class);
		setupTab(new TextView(this), "Top Rated Movies", intent);

		intent = new Intent().setClass(MainActivity.this, UpcomingActivity.class);
		setupTab(new TextView(this), "Upcoming Movies", intent);

		intent = new Intent().setClass(MainActivity.this, NowPlayingActivity.class);
		setupTab(new TextView(this), "Now Playing Movies", intent);
		
		intent = new Intent().setClass(MainActivity.this, PopularMovies.class);
		setupTab(new TextView(this), "Popular Movies", intent);
	} // end onCreate

	private void setupTab(final View view, final String tag, final Intent myIntent) {

		View tabview = createTabView(mTabHost.getContext(), tag);
		TabSpec setContent =  mTabHost.newTabSpec(tag).setIndicator(tabview).setContent(myIntent);
		mTabHost.addTab(setContent);
	}

	private static View createTabView(final Context context, final String text) {

		View view = LayoutInflater.from(context).inflate(R.layout.tabs_bg, null);
		TextView tv = (TextView) view.findViewById(R.id.tabsText);
		tv.setText(text);
		return view;
	}
}